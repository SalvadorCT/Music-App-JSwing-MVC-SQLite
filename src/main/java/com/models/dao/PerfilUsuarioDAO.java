package com.models.dao;

import com.models.PerfilUsuario;

import com.models.util.BaseDAO;
import com.models.util.GenericDAO;
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

public class PerfilUsuarioDAO extends BaseDAO<PerfilUsuario> implements GenericDAO<PerfilUsuario> {
    private static final Logger logger = LoggerFactory.getLogger(PerfilUsuarioDAO.class);
    private final QueryRunner queryRunner;

    public PerfilUsuarioDAO(Connection connection) {
        super(connection);
        this.queryRunner = new QueryRunner();
    }

    public PerfilUsuarioDAO() throws SQLException {
        super();
        this.queryRunner = new QueryRunner();
    }

    @Override
    protected String getTableName() {
        return "Perfil_Usuario";
    }

    @Override
    protected BeanHandler<PerfilUsuario> getHandler() {
        return new BeanHandler<>(PerfilUsuario.class);
    }

    @Override
    public void insertar(PerfilUsuario perfilUsuario) throws SQLException {
        String sql = "INSERT INTO Perfiles_Usuarios (perfil_id, usuario_id, foto_perfil, biografia) VALUES (?, ?, ?, ?)";
        Object[] params = {
                perfilUsuario.getPerfilId(),
                perfilUsuario.getUsuarioId(),
                perfilUsuario.getFotoPerfil(),
        };

        try {
            int generatedId = queryRunner.insert(connection, sql, new ScalarHandler<Integer>(), params);
            perfilUsuario.setPerfilId(generatedId);
        } catch (SQLException e) {
            logger.error("Error al insertar perfil de usuario: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM Perfiles_Usuarios WHERE perfil_id = ?";
        try {
            int affectedRows = queryRunner.update(connection, sql, id);
            if (affectedRows == 0) {
                throw new SQLException("No se eliminó ninguna fila, el perfil de usuario con ID " + id + " no existe.");
            }
        } catch (SQLException e) {
            logger.error("Error al eliminar perfil de usuario: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public Optional<PerfilUsuario> obtenerPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Perfiles_Usuarios WHERE perfil_id = ?";
        try {
            PerfilUsuario perfilUsuario = queryRunner.query(connection, sql, new BeanHandler<>(PerfilUsuario.class), id);
            return Optional.ofNullable(perfilUsuario);
        } catch (SQLException e) {
            logger.error("Error al obtener perfil de usuario: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public List<PerfilUsuario> obtenerTodos() throws SQLException {
        String sql = "SELECT * FROM Perfiles_Usuarios";
        try {
            return queryRunner.query(connection, sql, new BeanListHandler<>(PerfilUsuario.class));
        } catch (SQLException e) {
            logger.error("Error al obtener perfiles de usuario: {}", e.getMessage(), e);
            throw e;
        }
    }

    public List<PerfilUsuario> obtenerPerfilesUsuarioPorUsuario(int usuarioId) throws SQLException {
        String sql = "SELECT * FROM Perfiles_Usuarios WHERE usuario_id = ?";
        try {
            return queryRunner.query(connection, sql, new BeanListHandler<>(PerfilUsuario.class), usuarioId);
        } catch (SQLException e) {
            logger.error("Error al obtener perfiles de usuario por usuario ID: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void actualizar(PerfilUsuario perfilUsuario) throws SQLException {
        String sql = "UPDATE Perfiles_Usuarios SET foto_perfil = ?, biografia = ? WHERE perfil_id = ?";
        Object[] params = {
                perfilUsuario.getFotoPerfil(),
                perfilUsuario.getBiografia(),
                perfilUsuario.getPerfilId()
        };

        try {
            int affectedRows = queryRunner.update(connection, sql, params);
            if (affectedRows == 0) {
                throw new SQLException("No se actualizó ninguna fila, el perfil de usuario con ID " + perfilUsuario.getPerfilId() + " no existe.");
            }
        } catch (SQLException e) {
            logger.error("Error al actualizar perfil de usuario: {}", e.getMessage(), e);
            throw e;
        }
    }
}