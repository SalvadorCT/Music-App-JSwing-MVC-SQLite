package com.models.dao;

import com.models.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class BaseDAO<T> {

    protected Connection connection;

    public BaseDAO(Connection connection) {
        this.connection = connection;
    }

    public BaseDAO() throws SQLException {
        this.connection = DatabaseConnection.getConnection();
    }

    protected Connection getConnection() throws SQLException {
        return connection;
    }

    protected void cerrarRecursos(AutoCloseable... recursos) {
        for (AutoCloseable recurso : recursos) {
            if (recurso != null) {
                try {
                    recurso.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
