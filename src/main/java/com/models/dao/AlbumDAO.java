package com.models.dao;

import com.models.Album;
import com.models.util.BaseDAO;
import com.models.util.GenericDAO;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.RowProcessor;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AlbumDAO extends BaseDAO<Album> implements GenericDAO<Album> {
    private static final Logger logger = LoggerFactory.getLogger(AlbumDAO.class);
    private final QueryRunner queryRunner;

    public AlbumDAO(Connection connection) {
        super(connection);
        this.queryRunner = new QueryRunner();
    }

    public AlbumDAO() throws SQLException {
        super();
        this.queryRunner = new QueryRunner();
    }

    private static final Map<String, String> columnToPropertyOverrides = new HashMap<>();
    static {
        columnToPropertyOverrides.put("album_id", "albumId");
        columnToPropertyOverrides.put("fecha_lanzamiento", "fechaLanzamiento");
        columnToPropertyOverrides.put("url_portada", "urlPortada");
    }

    private static final BeanProcessor beanProcessor = new BeanProcessor(columnToPropertyOverrides);
    private static final RowProcessor rowProcessor = new BasicRowProcessor(beanProcessor);

    @Override
    protected String getTableName() {
        return "Albumes";
    }

    @Override
    protected BeanHandler<Album> getHandler() {
        return new BeanHandler<>(Album.class, rowProcessor);
    }

    @Override
    public void insertar(Album entity) throws SQLException {
        String sql = "INSERT INTO Albumes (nombre, fecha_lanzamiento, genero, url_portada) VALUES (?, ?, ?, ?)";
        Object[] params = {
                entity.getNombre(),
                entity.getFecha_Lanzamiento(),
                entity.getGenero(),
                entity.getUrl_Portada()
        };

        try {
            int generatedId = queryRunner.insert(connection, sql, new ScalarHandler<Integer>(), params);
            entity.setAlbum_Id(generatedId);
        } catch (SQLException e) {
            logger.error("Error al insertar álbum: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM Albumes WHERE album_id = ?";
        try {
            int affectedRows = queryRunner.update(connection, sql, id);
            if (affectedRows == 0) {
                throw new SQLException("No se eliminó ninguna fila, el álbum con ID " + id + " no existe.");
            }
        } catch (SQLException e) {
            logger.error("Error al eliminar álbum: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void actualizar(Album entity) throws SQLException {
        String sql = "UPDATE Albumes SET nombre = ?, fecha_lanzamiento = ?, genero = ?, url_portada = ? WHERE album_id = ?";
        Object[] params = {
                entity.getNombre(),
                entity.getFecha_Lanzamiento(),
                entity.getGenero(),
                entity.getUrl_Portada(),
                entity.getAlbum_Id()
        };

        try {
            int affectedRows = queryRunner.update(connection, sql, params);
            if (affectedRows == 0) {
                throw new SQLException("No se actualizó ninguna fila, el álbum con ID " + entity.getAlbum_Id() + " no existe.");
            }
        } catch (SQLException e) {
            logger.error("Error al actualizar álbum: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public Optional<Album> obtenerPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Albumes WHERE album_id = ?";
        try {
            Album album = queryRunner.query(connection, sql, new BeanHandler<>(Album.class, rowProcessor), id);
            return Optional.ofNullable(album);
        } catch (SQLException e) {
            logger.error("Error al obtener álbum por ID: {}", e.getMessage(), e);
            throw e;
        }
    }
    public Optional<Album> obtenerPorIdStatic(int id) throws SQLException {
        String sql = "SELECT * FROM Albumes WHERE album_id = ?";
        try {
            Album album = queryRunner.query(connection, sql, new BeanHandler<>(Album.class, rowProcessor), id);
            return Optional.ofNullable(album);
        } catch (SQLException e) {
            logger.error("Error al obtener álbum por ID: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public List<Album> obtenerTodos() throws SQLException {
        String sql = "SELECT * FROM Albumes";
        try {
            List<Album> albums = queryRunner.query(connection, sql, new BeanListHandler<>(Album.class, rowProcessor));
            return albums;
        } catch (SQLException e) {
            logger.error("Error al obtener álbumes: {}", e.getMessage(), e);
            throw e;
        }
    }
}