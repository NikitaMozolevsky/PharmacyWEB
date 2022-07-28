package by.mozolevskij.pharmacy.pool;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class PropertyWriter {
    static Logger logger = LogManager.getLogger();
    private static final String DB_PROPERTIES_FILEPATH =
            "C:/IdeaProjects/project/src/main/resources/database.properties";
    private static PropertyWriter instance = new PropertyWriter();

    private PropertyWriter() {}

    public static PropertyWriter getInstance() {
        return instance;
    }

    public Properties writeProperty() {
        File file = new File(DB_PROPERTIES_FILEPATH);
        Properties prop = new Properties();
        try {
            prop.setProperty("driver", "com.mysql.cj.jdbc.Driver");
            prop.setProperty("user", "root");
            prop.setProperty("password", "1111");
            prop.setProperty("autoReconnect", "true");
            prop.setProperty("characterEncoding", "UTF-8");
            prop.setProperty("useUnicode", "true");
            prop.setProperty("useSSL", "true");
            prop.setProperty("useJDBCCompliantTimezoneShift", "true");
            prop.setProperty("useLegacyDatetimeCode", "true");
            prop.setProperty("serverTimezone", "UTC");
            prop.setProperty("serverSslCert", "classpath:server.crt");
            prop.setProperty("url","jdbc:mysql://localhost:3306/pharmacy");
            prop.store(new FileWriter(file.getAbsolutePath()), null);
        } catch (IOException e) {
            logger.log(Level.ERROR, "Failed to write data base properties file.", e);
        }
        return prop;
    }
}
