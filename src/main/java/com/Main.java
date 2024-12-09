package com;


import com.View.VentanaLogin;
import com.controller.LoginController;
import com.models.dao.UsuarioDAO;

import javax.swing.*;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UsuarioDAO usuarioDAO = new UsuarioDAO();

                VentanaLogin ventanaLogin = new VentanaLogin();
                new LoginController(ventanaLogin, usuarioDAO);
                ventanaLogin.setVisible(true);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al iniciar la aplicación: " + e.getMessage());
            }
        });
    }
}