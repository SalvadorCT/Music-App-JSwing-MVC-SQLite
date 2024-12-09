package com.View;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class VentanaLogin extends JFrame {
    private final JTextField campoCorreo;
    private final JPasswordField campoContrasena;
    @Getter
    private final JButton botonLogin;
    @Getter
    private final JButton botonRegistro;

    public VentanaLogin() {
        setTitle("Inicio de Sesión");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new GridBagLayout());
        panelPrincipal.setBackground(new Color(40, 40, 40));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        setIconImage(new ImageIcon("src/main/java/com/View/icons/JPotify.png").getImage());

        ImageIcon icon = new ImageIcon("src/main/java/com/View/icons/JPotify.png");
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImage);
        JLabel imagen = new JLabel(icon);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panelPrincipal.add(imagen, gbc);

        JLabel titulo = new JLabel("Inicia Sesión");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panelPrincipal.add(titulo, gbc);

        JLabel etiquetaCorreo = new JLabel("Correo:");
        etiquetaCorreo.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        panelPrincipal.add(etiquetaCorreo, gbc);

        campoCorreo = new PlaceholderTextField("Ingresa tu correo", 20);
        campoCorreo.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        campoCorreo.setBackground(new Color(25, 25, 25));
        campoCorreo.setForeground(Color.WHITE);
        campoCorreo.setCaretColor(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panelPrincipal.add(campoCorreo, gbc);

        JLabel etiquetaContrasena = new JLabel("Contraseña:");
        etiquetaContrasena.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panelPrincipal.add(etiquetaContrasena, gbc);

        campoContrasena = new PlaceholderPasswordField("Ingresa tu contraseña", 20);
        campoContrasena.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        campoContrasena.setBackground(new Color(25, 25, 25));
        campoContrasena.setForeground(Color.WHITE);
        campoContrasena.setCaretColor(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panelPrincipal.add(campoContrasena, gbc);

        botonLogin = new JButton("Iniciar Sesión");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        botonLogin.setBackground(new Color(30, 215, 96));
        botonLogin.setForeground(Color.WHITE);
        panelPrincipal.add(botonLogin, gbc);

        botonRegistro = new JButton("Crear Cuenta");
        botonRegistro.setBackground(new Color(25, 25, 25));
        botonRegistro.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 5;
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

    public void mostrarMensaje(String s) {
        JOptionPane.showMessageDialog(this, s);
    }

    public void setFieldBorder(JTextField field, boolean isValid) {
        field.setBorder(BorderFactory.createLineBorder(isValid ? Color.GREEN : Color.RED));
    }
    public void addFieldValidation(JTextField field, Runnable validationLogic) {
        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                validationLogic.run();
            }
        });
    }
}

