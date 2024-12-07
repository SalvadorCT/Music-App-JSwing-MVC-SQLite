package com.View;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

public class VentanaRegistro extends JFrame {
    private final JTextField campoNombre;
    private final JTextField campoCorreo;
    private final JPasswordField campoContrasena;
    private final JPasswordField campoConfirmarContrasena;
    @Getter
    private final JButton botonRegistrar;
    @Getter
    private final JButton botonVolver;

    public VentanaRegistro() {
        setTitle("Registro de Usuario");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new GridBagLayout());
        panelPrincipal.setBackground(new Color(40, 40, 40));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titulo = new JLabel("Crear Cuenta");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panelPrincipal.add(titulo, gbc);

        JLabel etiquetaNombre = new JLabel("Nombre:");
        etiquetaNombre.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panelPrincipal.add(etiquetaNombre, gbc);

        campoNombre = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panelPrincipal.add(campoNombre, gbc);

        JLabel etiquetaCorreo = new JLabel("Correo:");
        etiquetaCorreo.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panelPrincipal.add(etiquetaCorreo, gbc);

        campoCorreo = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panelPrincipal.add(campoCorreo, gbc);

        JLabel etiquetaContrasena = new JLabel("Contraseña:");
        etiquetaContrasena.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panelPrincipal.add(etiquetaContrasena, gbc);

        campoContrasena = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panelPrincipal.add(campoContrasena, gbc);

        JLabel etiquetaConfirmarContrasena = new JLabel("Confirmar Contraseña:");
        etiquetaConfirmarContrasena.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 4;
        panelPrincipal.add(etiquetaConfirmarContrasena, gbc);

        campoConfirmarContrasena = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 4;
        panelPrincipal.add(campoConfirmarContrasena, gbc);

        botonRegistrar = new JButton("Registrar");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        botonRegistrar.setBackground(new Color(30, 215, 96));
        botonRegistrar.setForeground(Color.WHITE);
        panelPrincipal.add(botonRegistrar, gbc);

        botonVolver = new JButton("Volver");
        botonVolver.setBackground(new Color(25, 25, 25));
        botonVolver.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 6;
        panelPrincipal.add(botonVolver, gbc);

        add(panelPrincipal, BorderLayout.CENTER);
    }

    public String getNombre() {
        return campoNombre.getText();
    }
    public String getCorreo() {
        return campoCorreo.getText();
    }
    public String getContrasena() {
        return new String(campoContrasena.getPassword());
    }
    public String getConfirmarContrasena() {
        return new String(campoConfirmarContrasena.getPassword());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaRegistro ventana = new VentanaRegistro();
            ventana.setVisible(true);
        });
    }
}

