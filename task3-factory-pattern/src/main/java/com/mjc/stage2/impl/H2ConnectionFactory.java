package com.mjc.stage2.impl;

import com.mjc.stage2.ConnectionFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class H2ConnectionFactory implements ConnectionFactory {

    private static final String CONNECTION_PROPERTIES_FILE_NAME = "h2database.properties";
    private static final String DRIVER_CLASS = "jdbc_driver";
    private static final String CONNECTION_URL = "db_url";
    private static final String USERNAME = "user";
    private static final String PASSWORD = "password";

    private final String databaseConnectionUrl;
    private final String databaseUsername;
    private final String databasePassword;

    public H2ConnectionFactory() {
        Properties properties = new Properties();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(CONNECTION_PROPERTIES_FILE_NAME);
        try {
            properties.load(inputStream);
            String databaseDriverClass = properties.getProperty(DRIVER_CLASS);
            databaseConnectionUrl = properties.getProperty(CONNECTION_URL);
            databaseUsername = properties.getProperty(USERNAME);
            databasePassword = properties.getProperty(PASSWORD);
            Class.forName(databaseDriverClass);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Connection createConnection() {
        try {
            return DriverManager.getConnection(databaseConnectionUrl, databaseUsername, databasePassword);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
