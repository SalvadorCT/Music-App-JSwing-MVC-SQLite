package com.models.dao;

import com.models.PerfilUsuario;

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

public class PerfilUsuarioDAO extends BaseDAO<PerfilUsuario> {
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

    public void insertarPerfilUsuario(PerfilUsuario perfilUsuario) throws SQLException {
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

    public void eliminarPerfilUsuario(int id) throws SQLException {
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

    public Optional<PerfilUsuario> obtenerPerfilUsuario(int id) throws SQLException {
        String sql = "SELECT * FROM Perfiles_Usuarios WHERE perfil_id = ?";
        try {
            PerfilUsuario perfilUsuario = queryRunner.query(connection, sql, new BeanHandler<>(PerfilUsuario.class), id);
            return Optional.ofNullable(perfilUsuario);
        } catch (SQLException e) {
            logger.error("Error al obtener perfil de usuario: {}", e.getMessage(), e);
            throw e;
        }
    }

    public List<PerfilUsuario> obtenerPerfilesUsuario() throws SQLException {
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

    public void actualizarPerfilUsuario(PerfilUsuario perfilUsuario) throws SQLException {
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

    public void actualizarFotoPerfil(int perfilId, String fotoPerfil) throws SQLException {
        String sql = "UPDATE Perfiles_Usuarios SET foto_perfil = ? WHERE perfil_id = ?";
        Object[] params = { fotoPerfil, perfilId };

        try {
            int affectedRows = queryRunner.update(connection, sql, params);
            if (affectedRows == 0) {
                throw new SQLException("No se actualizó ninguna fila, el perfil de usuario con ID " + perfilId + " no existe.");
            }
        } catch (SQLException e) {
            logger.error("Error al actualizar foto de perfil: {}", e.getMessage(), e);
            throw e;
        }
    }

    public void actualizarBiografia(int perfilId, String biografia) throws SQLException {
        String sql = "UPDATE Perfiles_Usuarios SET biografia = ? WHERE perfil_id = ?";
        Object[] params = { biografia, perfilId };

        try {
            int affectedRows = queryRunner.update(connection, sql, params);
            if (affectedRows == 0) {
                throw new SQLException("No se actualizó ninguna fila, el perfil de usuario con ID " + perfilId + " no existe.");
            }
        } catch (SQLException e) {
            logger.error("Error al actualizar biografía: {}", e.getMessage(), e);
            throw e;
        }
    }

    public void actualizarFotoPerfilYBiografia(int perfilId, String fotoPerfil, String biografia) throws SQLException {
        String sql = "UPDATE Perfiles_Usuarios SET foto_perfil = ?, biografia = ? WHERE perfil_id = ?";
        Object[] params = { fotoPerfil, biografia, perfilId };

        try {
            int affectedRows = queryRunner.update(connection, sql, params);
            if (affectedRows == 0) {
                throw new SQLException("No se actualizó ninguna fila, el perfil de usuario con ID " + perfilId + " no existe.");
            }
        } catch (SQLException e) {
            logger.error("Error al actualizar foto de perfil y biografía: {}", e.getMessage(), e);
            throw e;
        }
    }

    public void actualizarFotoPerfilPorUsuario(int usuarioId, String fotoPerfil) throws SQLException {
        String sql = "UPDATE Perfiles_Usuarios SET foto_perfil = ? WHERE usuario_id = ?";
        Object[] params = { fotoPerfil, usuarioId };

        try {
            int affectedRows = queryRunner.update(connection, sql, params);
            if (affectedRows == 0) {
                throw new SQLException("No se actualizó ninguna fila, el usuario con ID " + usuarioId + " no tiene un perfil de usuario asociado.");
            }
        } catch (SQLException e) {
            logger.error("Error al actualizar foto de perfil por usuario: {}", e.getMessage(), e);
            throw e;
        }
    }

    public void actualizarBiografiaPorUsuario(int usuarioId, String biografia) throws SQLException {
        String sql = "UPDATE Perfiles_Usuarios SET biografia = ? WHERE usuario_id = ?";
        Object[] params = { biografia, usuarioId };

        try {
            int affectedRows = queryRunner.update(connection, sql, params);
            if (affectedRows == 0) {
                throw new SQLException("No se actualizó ninguna fila, el usuario con ID " + usuarioId + " no tiene un perfil de usuario asociado.");
            }
        } catch (SQLException e) {
            logger.error("Error al actualizar biografía por usuario: {}", e.getMessage(), e);
            throw e;
        }
    }

    public void actualizarFotoPerfilYBiografiaPorUsuario(int usuarioId, String fotoPerfil, String biografia) throws SQLException {
        String sql = "UPDATE Perfiles_Usuarios SET foto_perfil = ?, biografia = ? WHERE usuario_id = ?";
        Object[] params = { fotoPerfil, biografia, usuarioId };

        try {
            int affectedRows = queryRunner.update(connection, sql, params);
            if (affectedRows == 0) {
                throw new SQLException("No se actualizó ninguna fila, el usuario con ID " + usuarioId + " no tiene un perfil de usuario asociado.");
            }
        } catch (SQLException e) {
            logger.error("Error al actualizar foto de perfil y biografía por usuario: {}", e.getMessage(), e);
            throw e;
        }
    }
}