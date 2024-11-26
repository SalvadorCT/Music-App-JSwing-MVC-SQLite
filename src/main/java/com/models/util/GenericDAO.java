package com.models.util;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Generic repository interface defining CRUD operations.
 *
 * @param <T> The type of entity managed by the repository.
 */
public interface GenericDAO<T> {

    /**
     * Inserts a new entity.
     *
     * @param entity The entity to insert.
     */
    void insertar(T entity) throws SQLException;

    /**
     * Deletes an entity by its ID.
     *
     * @param id The ID of the entity to delete.
     */
    void eliminar(int id) throws SQLException;

    /**
     * Updates an existing entity.
     *
     * @param entity The entity to update.
     */
    void actualizar(T entity) throws SQLException;

    /**
     * Retrieves an entity by its ID.
     *
     * @param id The ID of the entity to retrieve.
     * @return An Optional containing the found entity or empty if not found.
     */
    Optional<T> obtenerPorId(int id) throws SQLException;

    /**
     * Retrieves all entities.
     *
     * @return A list of all entities.
     */
    List<T> obtenerTodos() throws SQLException;
}