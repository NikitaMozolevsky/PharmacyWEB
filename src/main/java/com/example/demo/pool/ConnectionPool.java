package com.example.demo.pool;

import com.example.demo.exception.ConnectionException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {

    static Logger logger = LogManager.getLogger();
    private static ConnectionPool connectionPool;
    private static final ReentrantLock reentrantLock = new ReentrantLock();
    private static final AtomicBoolean isCreate = new AtomicBoolean(false);
    private static final int CONNECTION_CAPACITY = 8;
    public static final String URL = "url";
    public static final String DATABASE_PROPERTIES = "database.properties";
    private BlockingQueue<ProxyConnection> freeConnections = new LinkedBlockingQueue<>(CONNECTION_CAPACITY);
    private BlockingQueue<ProxyConnection> usedConnections = new LinkedBlockingQueue<>(CONNECTION_CAPACITY);

    //try (InputStream inputStream = ConnectionPool.class.getClassLoader()
    //                .getResourceAsStream("database.properties")) {
    //            properties.load(inputStream);
    // TODO: 18.04.2022 scr. 4
    // information read from there, from file

    private ConnectionPool() {
        Properties prop = PropertyWriter.getInstance().writeProperty();
        try (InputStream inputStream = ConnectionPool.class.getClassLoader()
                .getResourceAsStream(DATABASE_PROPERTIES)) {
            prop.load(inputStream);
        } catch (IOException e) {
            logger.log(Level.ERROR, e.getMessage());
            throw new ExceptionInInitializerError();
        }
        /*String driverClassName = prop.getProperty("db.driver", "com.mysql.cj.jdbc.Driver");*/
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        } catch (SQLException e) {
            logger.log(Level.ERROR, "driver can't register");
        }
        /*driver = new com.mysql.cj.jdbc.Driver();*/
        /*String url = prop.getProperty(DB_HOST);*/
        for (int i = 0; i < CONNECTION_CAPACITY; i++) {
            try {
                Connection connection = DriverManager.getConnection(prop.getProperty(URL), prop);
                ProxyConnection proxyConnection = new ProxyConnection(connection);
                freeConnections.add(proxyConnection);
            } catch (SQLException e) {
                logger.log(Level.ERROR, e.getMessage());
                throw new ExceptionInInitializerError();
            }
        }
    }

    public static ConnectionPool getInstance() {
        if (!isCreate.get()) {
            // TODO: 5/11/2022 check null 
            reentrantLock.lock();
            if (connectionPool==null) {
                connectionPool = new ConnectionPool();
                isCreate.set(true);
                logger.log(Level.INFO, "connection pool created");
            }
            reentrantLock.unlock();
        }
        return connectionPool;
        // TODO: 11.04.2022 thread-safe creation
        //lock
        /*connectionPool = new ConnectionPool();*/
        //unlock
    }

    public Connection getConnection() { // TODO: 18.04.2022 Proxy connection
        ProxyConnection connection = null;
        try {
            logger.log(Level.INFO, "freeConnections.size() == {}" ,freeConnections.size());
            connection = freeConnections.take();
            usedConnections.put(connection);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        if (connection.getClass() == ProxyConnection.class) {

            usedConnections.remove((ProxyConnection) connection);
            logger.log(Level.INFO, "release proxy connection {}", freeConnections.size());
            try {
                freeConnections.put((ProxyConnection) connection);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.log(Level.ERROR, "put connection to freeConnection is failed");
            }
        }
    }

    public void destroyPool() {
        for (int i = 0; i < CONNECTION_CAPACITY; i++) {
            try {
                freeConnections.take().reallyClose();
            } catch (InterruptedException e) {
                logger.log(Level.ERROR, e); // TODO: 12.04.2022  logger
            }
        }
    }

    public void deregisterDriver() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                logger.log(Level.ERROR, "deregister driver exception");
            }
        });

    } // TODO: 18.04.2022 add DeregisterDriver
}
