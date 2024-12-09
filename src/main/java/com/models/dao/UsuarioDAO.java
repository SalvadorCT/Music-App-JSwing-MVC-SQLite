package com.models.dao;

import com.models.Usuario;
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

    /**
     * A mapping between column names and property names used to override default mappings.
     * This map is intended to customize or handle specific cases where column names do not
     * directly match the property names in the Java object.
     */
    private static final Map<String, String> columnToPropertyOverrides = new HashMap<>();
    static {
        columnToPropertyOverrides.put("usuario_id", "usuarioId");
        columnToPropertyOverrides.put("tipo_suscripcion", "tipoSuscripcion");
        columnToPropertyOverrides.put("fecha_creacion", "fechaCreacion");
        columnToPropertyOverrides.put("contrasena_hash", "contrasenaHash");
    }

    /**
     * A statically initialized instance of BeanProcessor responsible for processing
     * beans within the UsuarioDAO class. This instance uses specific column-to-property
     * name mapping as defined by the columnToPropertyOverrides variable.
     *
     * The BeanProcessor facilitates the conversion between database column names
     * and the corresponding Java bean property names, providing a streamlined
     * approach to map ResultSet data into Java objects.
     *
     * Being a static final instance, this BeanProcessor is shared across all instances
     * of UsuarioDAO and is immutable after its initial configuration.
     */
    private static final BeanProcessor beanProcessor = new BeanProcessor(columnToPropertyOverrides);
    /**
     * A static final instance of {@link RowProcessor} used in the UsuarioDAO class
     * to process database rows into JavaBeans. It utilizes a {@link BasicRowProcessor}
     * which is initialized with a {@link BeanProcessor}, allowing for customizable
     * conversion of database columns to bean properties.
     */
    private static final RowProcessor rowProcessor = new BasicRowProcessor(beanProcessor);

    @Override
    protected String getTableName() {
        return "Usuarios";
    }

    @Override
    protected BeanHandler<Usuario> getHandler() {
        return new BeanHandler<>(Usuario.class, rowProcessor);
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
            Usuario usuario = queryRunner.query(connection, sql, getHandler(), id);
            return Optional.ofNullable(usuario);
        } catch (SQLException e) {
            logger.error("Error al obtener usuario por ID: {}", e.getMessage(), e);
            throw e;
        }
    }

    public Optional<Usuario> obtenerPorEmail(String email) throws SQLException {
        String sql = "SELECT * FROM Usuarios WHERE email = ?";
        try {
            Usuario usuario = queryRunner.query(connection, sql, getHandler(), email);
            System.out.println("Fetched usuario: " + usuario);
            System.out.println("Password hash: " + usuario.getContrasenaHash());
            return Optional.of(usuario);
        } catch (SQLException e) {
            logger.error("Error al obtener usuario por email: {}", e.getMessage(), e);
            throw e;
        }
    }

}