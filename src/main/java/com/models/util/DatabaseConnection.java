package com.models.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseConnection {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseConnection.class);
    private static final String DATABASE_URL = "jdbc:sqlite:src/main/resources/musica.db";
    private static final String CONNECTION_ERROR_MESSAGE = "Failed to create connection to the database";
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            createConnection();
        }
        return connection;
    }

    public static void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
            connection = null; // Clear the connection reference after closing
        }
    }

    private static void createConnection() throws SQLException {
        try {
            connection = DriverManager.getConnection(DATABASE_URL);
        } catch (SQLException e) {
            logger.error(CONNECTION_ERROR_MESSAGE, e);
            throw e; // Let the caller handle the SQLException
        }
    }
}