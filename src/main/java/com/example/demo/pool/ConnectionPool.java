package com.example.demo.pool;

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
    public static final String DB_HOST = "db.host";
    public static final String DATABASE_PROPERTIES = "database.properties";
    private BlockingQueue<Connection> connections = new LinkedBlockingQueue<>(CONNECTION_CAPACITY);
    private BlockingQueue<Connection> used = new LinkedBlockingQueue<>(CONNECTION_CAPACITY);
    Driver driver;

    //try (InputStream inputStream = ConnectionPool.class.getClassLoader()
    //                .getResourceAsStream("database.properties")) {
    //            properties.load(inputStream);
    // TODO: 18.04.2022 scr. 4
    // information read from there, from file

    private ConnectionPool() {
        try {
            driver = new com.mysql.cj.jdbc.Driver();
        } catch (SQLException e) {
            throw new ExceptionInInitializerError(e);
        }
        Properties prop = new Properties();
        try (InputStream inputStream = ConnectionPool.class.getClassLoader()
                .getResourceAsStream(DATABASE_PROPERTIES)) {
            prop.load(inputStream);
        } catch (IOException e) {
            logger.log(Level.ERROR, e);
        }
        String url = prop.getProperty(DB_HOST);
        for (int i = 0; i < CONNECTION_CAPACITY; i++) {
            try {
                Connection connection = DriverManager.getConnection(url, prop);
                connections.add(connection);
            } catch (SQLException e) {
                throw new ExceptionInInitializerError(e.getMessage());
            }
        }
    }

    public static ConnectionPool getInstance() {
        if (!isCreate.get()) {
            reentrantLock.lock();
            connectionPool = new ConnectionPool();
            isCreate.set(true);
            reentrantLock.unlock();
        }
        return connectionPool;
        // TODO: 11.04.2022 thread-safe creation
        //lock
        /*connectionPool = new ConnectionPool();*/
        //unlock
    }

    public Connection getConnection() { // TODO: 18.04.2022 Proxy connection
        Connection connection = null;
        try {
            connection = connections.take();
            used.put(connection);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        try {
            used.remove(connection);
            connections.put(connection);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void destroyPool() {
        for (int i = 0; i < CONNECTION_CAPACITY; i++) {
            try {
                connections.take().close();
            } catch (SQLException | InterruptedException e) {
                logger.log(Level.ERROR, e); // TODO: 12.04.2022  logger
            }
        }
    }

    public void deregisterDriver() {
        try {
            DriverManager.deregisterDriver(driver);
        } catch (SQLException e) {
            throw new ExceptionInInitializerError(e.getMessage());
        }
    } // TODO: 18.04.2022 add DeregisterDriver
}
