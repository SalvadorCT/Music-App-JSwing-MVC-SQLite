package com.controller;

import com.models.Usuario;
import com.models.dao.UsuarioDAO;
import com.View.PanelConfiguraciones;
import java.sql.SQLException;
import java.util.Optional;

public class ConfiguracionesController {
    private final PanelConfiguraciones vista;
    private final UsuarioDAO usuarioDAO;
    private Usuario usuario;

    /**
     * Constructor de la clase ConfiguracionesController
     * @param vista PanelConfiguraciones
     * @param usuarioDAO UsuarioDAO
     * @param usuarioId int
     */
    public ConfiguracionesController(PanelConfiguraciones vista, UsuarioDAO usuarioDAO, int usuarioId) {
        this.vista = vista;
        this.usuarioDAO = usuarioDAO;

        try {
            Optional<Usuario> usuarioOptional = usuarioDAO.obtenerPorId(usuarioId);
            if (usuarioOptional.isEmpty()) {
                throw new IllegalArgumentException("No se encontró un usuario con ID: " + usuarioId);
            }
            this.usuario = usuarioOptional.get();
            cargarDatosEnVista();
        } catch (SQLException e) {
            vista.mostrarError("Ocurrió un error al cargar los datos del usuario: " + e.getMessage());
        }

        configurarEventos();
    }

    private void cargarDatosEnVista() {
        vista.getCampoNombre().setText(usuario.getNombre());
        vista.getCampoCorreo().setText(usuario.getEmail());
    }

    private void configurarEventos() {
        vista.getBotonGuardar().addActionListener(e -> guardarCambios());
    }

    private void guardarCambios() {
        String nuevoNombre = vista.getCampoNombre().getText().trim();
        String nuevoCorreo = vista.getCampoCorreo().getText().trim();

        if (nuevoNombre.isEmpty() || nuevoCorreo.isEmpty()) {
            vista.mostrarError("Por favor, complete todos los campos.");
            return;
        }

        if (!nuevoCorreo.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
            vista.mostrarError("Correo inválido. Por favor verifica el formato.");
            return;
        }
        usuario.setNombre(nuevoNombre);
        usuario.setEmail(nuevoCorreo);

        try {
            usuarioDAO.actualizar(usuario); // Sincronizar cambios con la base de datos
            vista.mostrarMensaje("Datos actualizados correctamente.");
        } catch (SQLException ex) {
            vista.mostrarError("Ocurrió un error al guardar los cambios: " + ex.getMessage());
        }
    }
}
