package com.controller;

import com.View.VentanaLogin;
import com.View.VentanaRegistro;
import com.View.PantallaPrincipal;
import com.models.Usuario;
import com.models.dao.UsuarioDAO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Optional;

public class LoginController {
    private final VentanaLogin ventanaLogin;
    private final UsuarioDAO usuarioDAO;
    /**
     * Constructor que recibe la ventana de inicio de sesión y el DAO de Usuario.
     * @param ventanaLogin Ventana de inicio de sesión.
     * @param usuarioDAO   DAO de Usuario.
     */
    public LoginController(VentanaLogin ventanaLogin, UsuarioDAO usuarioDAO) {
        this.ventanaLogin = ventanaLogin;
        this.usuarioDAO = usuarioDAO;

        // Configurar listeners para los botones
        ventanaLogin.getBotonLogin().addActionListener(new LoginActionListener());
        ventanaLogin.getBotonRegistro().addActionListener(new RegistroActionListener());
    }
    /**
     * Clase interna que implementa ActionListener para el botón de inicio de sesión.
     */
    private class LoginActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String correo = ventanaLogin.getCorreo();
            String contrasena = ventanaLogin.getContrasena();

            if (correo.isEmpty() || contrasena.isEmpty()) {
                ventanaLogin.mostrarMensaje("Todos los campos son obligatorios.");
                return;
            }

            try {
                Optional<Usuario> usuarioOptional = usuarioDAO.obtenerPorEmail(correo).stream()
                        .filter(usuario -> correo.equals(usuario.getEmail()) &&
                                (usuario.getContrasenaHash() != null && usuario.getContrasenaHash().equals(contrasena)))
                        .findFirst();

                if (usuarioOptional.isPresent()) {
                    ventanaLogin.mostrarMensaje("Inicio de sesión exitoso.");
                    // Aquí puedes implementar la lógica para redirigir a la ventana principal
                    PantallaPrincipal pantallaPrincipal = new PantallaPrincipal();
                    PantallaPrincipalController pantallaPrincipalController = new PantallaPrincipalController(pantallaPrincipal, usuarioDAO);
                    pantallaPrincipal.setVisible(true);
                    ventanaLogin.dispose();

                } else {
                    ventanaLogin.mostrarMensaje("Correo o contraseña incorrectos.");
                }
            } catch (SQLException ex) {
                ventanaLogin.mostrarMensaje("Error al iniciar sesión: " + ex.getMessage());
            }
        }
    }
    /**
     * Clase interna que implementa ActionListener para el botón de registro.
     */
    private class RegistroActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            VentanaRegistro ventanaRegistro = new VentanaRegistro();
            RegistroController registroController = new RegistroController(ventanaRegistro, usuarioDAO);
            ventanaRegistro.setVisible(true);

            // Cerrar la ventana de inicio de sesión
            ventanaLogin.dispose();
        }
    }
}