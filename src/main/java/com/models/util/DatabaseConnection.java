package com.models.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The DatabaseConnection class provides methods to manage a static connection
 * to a SQLite database. It includes functionality to get and close the connection.
 */
public class DatabaseConnection {
    private static Connection connection;
    private static final String DATABASE_URL = "jdbc:sqlite:src/main/resources/musica.db";


    public static Connection getConnection() throws SQLException {
        try {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(DATABASE_URL);
        }
        return connection;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}