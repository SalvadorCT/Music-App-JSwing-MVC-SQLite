package com.View;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;

public class VentanaLogin extends JFrame {
    private final JTextField campoCorreo;
    private final JPasswordField campoContrasena;
    @Getter
    private final JButton botonLogin;
    @Getter
    private final JButton botonRegistro;

    public VentanaLogin() {
        setTitle("Inicio de Sesión");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new GridBagLayout());
        panelPrincipal.setBackground(new Color(40, 40, 40));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titulo = new JLabel("Inicia Sesión");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panelPrincipal.add(titulo, gbc);

        JLabel etiquetaCorreo = new JLabel("Correo:");
        etiquetaCorreo.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panelPrincipal.add(etiquetaCorreo, gbc);

        campoCorreo = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panelPrincipal.add(campoCorreo, gbc);

        JLabel etiquetaContrasena = new JLabel("Contraseña:");
        etiquetaContrasena.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panelPrincipal.add(etiquetaContrasena, gbc);

        campoContrasena = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panelPrincipal.add(campoContrasena, gbc);

        botonLogin = new JButton("Iniciar Sesión");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        botonLogin.setBackground(new Color(30, 215, 96));
        botonLogin.setForeground(Color.WHITE);
        panelPrincipal.add(botonLogin, gbc);

        botonRegistro = new JButton("Crear Cuenta");
        botonRegistro.setBackground(new Color(25, 25, 25));
        botonRegistro.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 4;
        panelPrincipal.add(botonRegistro, gbc);

        add(panelPrincipal, BorderLayout.CENTER);
    }

    public String getCorreo() {
        return campoCorreo.getText();
    }
    public String getContrasena() {
        return new String(campoContrasena.getPassword());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaLogin ventana = new VentanaLogin();
            ventana.setVisible(true);
        });
    }
}

