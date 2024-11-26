package com.models.dao;

import com.models.Usuario;
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

public class UsuarioDAO extends BaseDAO<Usuario> implements GenericDAO<Usuario> {
    private static final Logger logger = LoggerFactory.getLogger(UsuarioDAO.class);
    private final QueryRunner queryRunner;

    public UsuarioDAO(Connection connection) {
        super(connection);
        this.queryRunner = new QueryRunner();
    }

    public UsuarioDAO() throws SQLException {
        super();
        this.queryRunner = new QueryRunner();
    }

    @Override
    protected String getTableName() {
        return "Usuarios";
    }

    @Override
    protected BeanHandler<Usuario> getHandler() {
        return new BeanHandler<>(Usuario.class);
    }

    @Override
    public void insertar(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO Usuarios (nombre, email, tipo_suscripcion, fecha_creacion, contrasena_hash) VALUES (?, ?, ?, ?, ?)";
        Object[] params = {
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getTipoSuscripcion(),
                usuario.getFechaCreacion(),
                usuario.getContrasenaHash()
        };

        try {
            int generatedId = queryRunner.insert(connection, sql, new ScalarHandler<Integer>(), params);
            usuario.setUsuarioId(generatedId);
        } catch (SQLException e) {
            logger.error("Error al insertar usuario: {}", e.getMessage(), e);
            throw e;
        }
    }
    
    @Override
    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM Usuarios WHERE usuario_id = ?";
        try {
            int affectedRows = queryRunner.update(connection, sql, id);
            if (affectedRows == 0) {
                throw new SQLException("No se eliminó ninguna fila, el usuario con ID " + id + " no existe.");
            }
        } catch (SQLException e) {
            logger.error("Error al eliminar usuario: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void actualizar(Usuario usuario) throws SQLException {
        String sql = "UPDATE Usuarios SET nombre = ?, email = ?, tipo_suscripcion = ?, fecha_creacion = ?, contrasena_hash = ? WHERE usuario_id = ?";
        Object[] params = {
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getTipoSuscripcion(),
                usuario.getFechaCreacion(),
                usuario.getContrasenaHash(),
                usuario.getUsuarioId()
        };

        try {
            int affectedRows = queryRunner.update(connection, sql, params);
            if (affectedRows == 0) {
                throw new SQLException("No se actualizó ninguna fila, el usuario con ID " + usuario.getUsuarioId() + " no existe.");
            }
        } catch (SQLException e) {
            logger.error("Error al actualizar usuario: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public List<Usuario> obtenerTodos() throws SQLException {
        String sql = "SELECT * FROM Usuarios";
        try {
            return queryRunner.query(connection, sql, new BeanListHandler<>(Usuario.class));
        } catch (SQLException e) {
            logger.error("Error al obtener usuarios: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public Optional<Usuario> obtenerPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Usuarios WHERE usuario_id = ?";
        try {
            Usuario usuario = queryRunner.query(connection, sql, new BeanHandler<>(Usuario.class), id);
            return Optional.ofNullable(usuario);
        } catch (SQLException e) {
            logger.error("Error al obtener usuario por ID: {}", e.getMessage(), e);
            throw e;
        }
    }
}