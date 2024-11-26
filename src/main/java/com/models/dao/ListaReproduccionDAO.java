package com.models.dao;

import com.models.ListaReproduccion;
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

public class ListaReproduccionDAO extends BaseDAO<ListaReproduccion> {
    private static final Logger logger = LoggerFactory.getLogger(ListaReproduccionDAO.class);
    private final QueryRunner queryRunner;

    public ListaReproduccionDAO(Connection connection) {
        super(connection);
        this.queryRunner = new QueryRunner();
    }

    public ListaReproduccionDAO() throws SQLException {
        super();
        this.queryRunner = new QueryRunner();
    }

    @Override
    protected String getTableName() {
        return "Lista_Reproduccion";
    }

    @Override
    protected BeanHandler<ListaReproduccion> getHandler() {
        return new BeanHandler<>(ListaReproduccion.class);
    }

    public void insertarListaReproduccion(ListaReproduccion listaReproduccion) throws SQLException {
        String sql = "INSERT INTO Listas_Reproduccion (nombre, usuario_id, fecha_creacion, privacidad) VALUES (?, ?, ?, ?)";
        Object[] params = {
                listaReproduccion.getNombre(),
                listaReproduccion.getUsuarioId(),
                listaReproduccion.getFechaCreacion(),
                listaReproduccion.getPrivacidad(),
        };

        try {
            int generatedId = queryRunner.insert(connection, sql, new ScalarHandler<Integer>(), params);
            listaReproduccion.setListaId(generatedId);
        } catch (SQLException e) {
            logger.error("Error al insertar lista de reproducción: {}", e.getMessage(), e);
            throw e;
        }
    }

    public void eliminarListaReproduccion(int id) throws SQLException {
        String sql = "DELETE FROM Listas_Reproduccion WHERE lista_id = ?";
        try {
            int affectedRows = queryRunner.update(connection, sql, id);
            if (affectedRows == 0) {
                throw new SQLException("No se eliminó ninguna fila, la lista de reproducción con ID " + id + " no existe.");
            }
        } catch (SQLException e) {
            logger.error("Error al eliminar lista de reproducción: {}", e.getMessage(), e);
            throw e;
        }
    }

    public void actualizarListaReproduccion(ListaReproduccion listaReproduccion) throws SQLException {
        String sql = "UPDATE Listas_Reproduccion SET nombre = ?, usuario_id = ?, fecha_creacion = ?, privacidad = ? WHERE lista_id = ?";
        Object[] params = {
                listaReproduccion.getNombre(),
                listaReproduccion.getUsuarioId(),
                listaReproduccion.getFechaCreacion(),
                listaReproduccion.getPrivacidad(),
                listaReproduccion.getListaId()
        };

        try {
            int affectedRows = queryRunner.update(connection, sql, params);
            if (affectedRows == 0) {
                throw new SQLException("No se actualizó ninguna fila, la lista de reproducción con ID " + listaReproduccion.getListaId() + " no existe.");
            }
        } catch (SQLException e) {
            logger.error("Error al actualizar lista de reproducción: {}", e.getMessage(), e);
            throw e;
        }
    }

    public Optional<ListaReproduccion> obtenerListaReproduccion(int id) throws SQLException {
        String sql = "SELECT * FROM Listas_Reproduccion WHERE lista_id = ?";
        try {
            ListaReproduccion listaReproduccion = queryRunner.query(connection, sql, new BeanHandler<>(ListaReproduccion.class), id);
            return Optional.ofNullable(listaReproduccion);
        } catch (SQLException e) {
            logger.error("Error al obtener lista de reproducción: {}", e.getMessage(), e);
            throw e;
        }
    }

    public List<ListaReproduccion> obtenerListasReproduccion() throws SQLException {
        String sql = "SELECT * FROM Listas_Reproduccion";
        try {
            return queryRunner.query(connection, sql, new BeanListHandler<>(ListaReproduccion.class));
        } catch (SQLException e) {
            logger.error("Error al obtener listas de reproducción: {}", e.getMessage(), e);
            throw e;
        }
    }

    public List<ListaReproduccion> obtenerListasReproduccionPorUsuario(int usuarioId) throws SQLException {
        String sql = "SELECT * FROM Listas_Reproduccion WHERE usuario_id = ?";
        try {
            return queryRunner.query(connection, sql, new BeanListHandler<>(ListaReproduccion.class), usuarioId);
        } catch (SQLException e) {
            logger.error("Error al obtener listas de reproducción por usuario ID: {}", e.getMessage(), e);
            throw e;
        }
    }

    public List<ListaReproduccion> obtenerListasReproduccionPorNombre(String nombre) throws SQLException {
        String sql = "SELECT * FROM Listas_Reproduccion WHERE nombre = ?";
        try {
            return queryRunner.query(connection, sql, new BeanListHandler<>(ListaReproduccion.class), nombre);
        } catch (SQLException e) {
            logger.error("Error al obtener listas de reproducción por nombre: {}", e.getMessage(), e);
            throw e;
        }
    }

    public List<ListaReproduccion> obtenerListasReproduccionPorPrivacidad(String privacidad) throws SQLException {
        String sql = "SELECT * FROM Listas_Reproduccion WHERE privacidad = ?";
        try {
            return queryRunner.query(connection, sql, new BeanListHandler<>(ListaReproduccion.class), privacidad);
        } catch (SQLException e) {
            logger.error("Error al obtener listas de reproducción por privacidad: {}", e.getMessage(), e);
            throw e;
        }
    }

    public List<ListaReproduccion> obtenerListasReproduccionPorFechaCreacion(String fechaCreacion) throws SQLException {
        String sql = "SELECT * FROM Listas_Reproduccion WHERE fecha_creacion = ?";
        try {
            return queryRunner.query(connection, sql, new BeanListHandler<>(ListaReproduccion.class), fechaCreacion);
        } catch (SQLException e) {
            logger.error("Error al obtener listas de reproducción por fecha de creación: {}", e.getMessage(), e);
            throw e;
        }
    }

}

