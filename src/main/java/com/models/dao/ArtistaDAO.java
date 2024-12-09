package com.models.dao;


import com.models.Artista;
import com.models.util.BaseDAO;
import com.models.util.GenericDAO;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.RowProcessor;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ArtistaDAO extends BaseDAO<Artista> implements GenericDAO<Artista> {
    private static final Logger logger = LoggerFactory.getLogger(ArtistaDAO.class);
    private final QueryRunner queryRunner;

    public ArtistaDAO(Connection connection) {
        super(connection);
        this.queryRunner = new QueryRunner();
    }

    public ArtistaDAO() throws SQLException {
        super();
        this.queryRunner = new QueryRunner();
    }

    private static final Map<String, String> columnToPropertyOverrides = new HashMap<>();
    static {
        columnToPropertyOverrides.put("artista_id", "artistaId");
        columnToPropertyOverrides.put("pais_origen", "paisOrigen");
    }

    private static final BeanProcessor beanProcessor = new BeanProcessor(columnToPropertyOverrides);
    private static final RowProcessor rowProcessor = new BasicRowProcessor(beanProcessor);

    @Override
    protected String getTableName() {
        return "Artistas";
    }

    @Override
    protected BeanHandler<Artista> getHandler() {
        return new BeanHandler<>(Artista.class, rowProcessor);
    }


    @Override
    public void insertar(Artista entity) throws SQLException {
        String sql = "INSERT INTO Artistas (nombre, genero, pais_origen) VALUES (?, ?, ?)";
        Object[] params = {
                entity.getNombre(),
                entity.getGenero(),
                entity.getPais_Origen()
        };

        try {
            int generatedId = queryRunner.insert(connection, sql, new ScalarHandler<Integer>(), params);
            entity.setArtista_Id(generatedId);
        } catch (SQLException e) {
            logger.error("Error al insertar artista: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM Artistas WHERE artista_id = ?";
        try {
            int affectedRows = queryRunner.update(connection, sql, id);
            if (affectedRows == 0) {
                throw new SQLException("No se eliminó ninguna fila, el artista con ID " + id + " no existe.");
            }
        } catch (SQLException e) {
            logger.error("Error al eliminar artista: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void actualizar(Artista entity) throws SQLException {
        String sql = "UPDATE Artistas SET nombre = ?, genero = ?, pais_origen = ? WHERE artista_id = ?";
        Object[] params = {
                entity.getNombre(),
                entity.getGenero(),
                entity.getPais_Origen(),
                entity.getArtista_Id()
        };

        try {
            int affectedRows = queryRunner.update(connection, sql, params);
            if (affectedRows == 0) {
                throw new SQLException("No se actualizó ninguna fila, el artista con ID " + entity.getArtista_Id() + " no existe.");
            }
        } catch (SQLException e) {
            logger.error("Error al actualizar artista: {}", e.getMessage(), e);
            throw e;
        }

    }

    @Override
    public Optional<Artista> obtenerPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Artistas WHERE artista_id = ?";
        try {
            Artista artista = queryRunner.query(connection, sql, new BeanHandler<>(Artista.class), id);
            return Optional.ofNullable(artista);
        } catch (SQLException e) {
            logger.error("Error al obtener artista por ID: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public List<Artista> obtenerTodos() throws SQLException {
        String sql = "SELECT * FROM Artistas";
        try {
            List<Artista> artistas = queryRunner.query(connection, sql, new BeanListHandler<>(Artista.class));
            return artistas;
        } catch (SQLException e) {
            logger.error("Error al obtener artistas: {}", e.getMessage(), e);
            throw e;
        }
    }
}
