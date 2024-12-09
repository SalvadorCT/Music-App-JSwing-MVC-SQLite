package com.controller;

import com.View.VentanaLogin;
import com.View.VentanaRegistro;
import com.models.Usuario;
import com.models.dao.UsuarioDAO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class RegistroController {
    private final VentanaRegistro ventanaRegistro;
    private final UsuarioDAO usuarioDAO;

    public RegistroController(VentanaRegistro ventanaRegistro, UsuarioDAO usuarioDAO) {
        this.ventanaRegistro = ventanaRegistro;
        this.usuarioDAO = usuarioDAO;

        // Configurar listeners para los botones
        ventanaRegistro.getBotonRegistrar().addActionListener(new RegistrarActionListener());
        ventanaRegistro.getBotonVolver().addActionListener(new VolverActionListener());
    }

    private class RegistrarActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String nombre = ventanaRegistro.getNombre();
            String correo = ventanaRegistro.getCorreo();
            String contrasena = ventanaRegistro.getContrasena();
            String confirmarContrasena = ventanaRegistro.getConfirmarContrasena();

            // Validaciones básicas
            if (nombre.isEmpty() || correo.isEmpty() || contrasena.isEmpty() || confirmarContrasena.isEmpty()) {
                ventanaRegistro.mostrarMensaje("Todos los campos son obligatorios.");
                return;
            }

            if (!contrasena.equals(confirmarContrasena)) {
                ventanaRegistro.mostrarMensaje("Las contraseñas no coinciden.");
                return;
            }

            // Validación de correo electrónico
            if (!Pattern.matches("^[A-Za-z0-9+_.-]+@(.+)$", correo)) {
                ventanaRegistro.mostrarMensaje("Correo electrónico no válido.");
                return;
            }

            // Crear un nuevo usuario
            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setNombre(nombre);
            nuevoUsuario.setEmail(correo);
            nuevoUsuario.setContrasena_Hash(contrasena); // Aquí deberías usar un hash seguro
            nuevoUsuario.setFecha_Creacion(System.currentTimeMillis());
            nuevoUsuario.setTipo_Suscripcion("Gratis"); // Por defecto, tipo de suscripción gratis

            try {
                usuarioDAO.insertar(nuevoUsuario);
                ventanaRegistro.mostrarMensaje("Registro exitoso. Ahora puedes iniciar sesión.");
                ventanaRegistro.limpiarCampos();
            } catch (SQLException ex) {
                ventanaRegistro.mostrarMensaje("Error al registrar el usuario: " + ex.getMessage());
            }
        }
    }

    private class VolverActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            VentanaLogin ventanaLogin = new VentanaLogin();
            LoginController loginController = new LoginController(ventanaLogin, usuarioDAO);
            ventanaLogin.setVisible(true);
            ventanaRegistro.dispose();

        }
    }
}