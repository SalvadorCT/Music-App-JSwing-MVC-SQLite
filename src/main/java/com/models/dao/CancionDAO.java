package com.models.dao;

import com.models.Cancion;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CancionDAO extends BaseDAO<Cancion> implements GenericDAO<Cancion> {

    private static final Logger logger = LoggerFactory.getLogger(CancionDAO.class);
    private final QueryRunner queryRunner;

    public CancionDAO(Connection connection) {
        super(connection);
        this.queryRunner = new QueryRunner();
    }

    public CancionDAO() throws SQLException {
        super();
        this.queryRunner = new QueryRunner();
    }

    @Override
    protected String getTableName() {
        return "Canciones";
    }

    @Override
    protected BeanHandler<Cancion> getHandler() {
        return new BeanHandler<>(Cancion.class);
    }


    @Override
    public void insertar(Cancion cancion) throws SQLException {
        String sql = "INSERT INTO Canciones (titulo, duracion, album_id, url_archivo, conteo_reproducciones) VALUES (?, ?, ?, ?, ?)";
        Object[] params = {
                cancion.getTitulo(),
                cancion.getDuracion(),
                cancion.getAlbumId(),
                cancion.getUrlArchivo(),
                cancion.getConteoReproducciones()
        };

        try {
            // Ejecuta la inserción y obtiene el ID generado
            int generatedId = queryRunner.insert(connection, sql, new ScalarHandler<Integer>(), params);
            cancion.setCancionId(generatedId);
        } catch (SQLException e) {
            logger.error("Error al insertar canción: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM Canciones WHERE cancion_id = ?";
        try {
            // Ejecuta la eliminación y verifica si se afectaron filas
            int affectedRows = queryRunner.update(connection, sql, id);
            if (affectedRows == 0) {
                throw new SQLException("No se eliminó ninguna fila, la canción con ID " + id + " no existe.");
            }
        } catch (SQLException e) {
            logger.error("Error al eliminar canción: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void actualizar(Cancion cancion) throws SQLException {
        String sql = "UPDATE Canciones SET titulo = ?, duracion = ?, album_id = ?, url_archivo = ?, conteo_reproducciones = ? WHERE cancion_id = ?";
        Object[] params = {
                cancion.getTitulo(),
                cancion.getDuracion(),
                cancion.getAlbumId(),
                cancion.getUrlArchivo(),
                cancion.getConteoReproducciones(),
                cancion.getCancionId()
        };

        try {
            // Ejecuta la actualización y verifica si se afectaron filas
            int affectedRows = queryRunner.update(connection, sql, params);
            if (affectedRows == 0) {
                throw new SQLException("No se actualizó ninguna fila, la canción con ID " + cancion.getCancionId() + " no existe.");
            }
        } catch (SQLException e) {
            logger.error("Error al actualizar canción: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public List<Cancion> obtenerTodos() throws SQLException {
        String sql = "SELECT * FROM Canciones";
        try {
            // Ejecuta la consulta y mapea los resultados a objetos Cancion
            return queryRunner.query(connection, sql, new BeanListHandler<>(Cancion.class));
        } catch (SQLException e) {
            logger.error("Error al obtener canciones: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public Optional<Cancion> obtenerPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Canciones WHERE cancion_id = ?";
        try {
            // Ejecuta la consulta y mapea el resultado a un objeto Cancion
            Cancion cancion = queryRunner.query(connection, sql, new BeanHandler<>(Cancion.class), id);
            return Optional.ofNullable(cancion);
        } catch (SQLException e) {
            logger.error("Error al obtener canción por ID: {}", e.getMessage(), e);
            throw e;
        }
    }
}
