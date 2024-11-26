package com.models.dao;

import com.models.util.DatabaseConnection;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * BaseDAO is an abstract class that provides common database operations for different entity DAOs.
 * It includes methods for managing database connections and closing resources.
 *
 * @param <T> The type of entity that the DAO will manage.
 */
public abstract class BaseDAO<T> {
    protected Connection connection;
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseDAO.class);

    public BaseDAO(Connection connection) {
        this.connection = connection;
    }

    public BaseDAO() throws SQLException {
        this.connection = DatabaseConnection.getConnection();
    }

    protected void closeResources(AutoCloseable... resources) {
        for (AutoCloseable resource : resources) {
            closeQuietly(resource);
        }
    }

    private void closeQuietly(AutoCloseable resource) {
        if (resource != null) {
            try {
                resource.close();
            } catch (Exception e) {
                LOGGER.error("Error closing resource: {}", e.getMessage(), e);
            }
        }
    }

    protected abstract String getTableName();
    protected abstract BeanHandler<T> getHandler();
}