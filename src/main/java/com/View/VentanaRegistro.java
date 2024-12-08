package com.View;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class VentanaRegistro extends JFrame {
    @Getter
    private final JTextField campoNombre;
    @Getter
    private final JTextField campoCorreo;
    @Getter
    private final JPasswordField campoContrasena;
    @Getter
    private final JPasswordField campoConfirmarContrasena;
    @Getter
    private final JButton botonRegistrar;
    @Getter
    private final JButton botonVolver;

    public VentanaRegistro() {
        setTitle("Registro de Usuario");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon("src/main/java/com/View/icons/JPotify.png").getImage());

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new GridBagLayout());
        panelPrincipal.setBackground(new Color(40, 40, 40));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // ajustar tamaño de la imagen
        ImageIcon icon = new ImageIcon("src/main/java/com/View/icons/JPotify.png");
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImage);
        JLabel imagen = new JLabel(icon);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panelPrincipal.add(imagen, gbc);

        JLabel titulo = new JLabel("Crear Cuenta");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panelPrincipal.add(titulo, gbc);

        JLabel etiquetaNombre = new JLabel("Nombre:");
        etiquetaNombre.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        panelPrincipal.add(etiquetaNombre, gbc);

        campoNombre = new PlaceholderTextField("Ingresa tu nombre", 20);
        campoNombre.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        campoNombre.setBackground(new Color(25, 25, 25));
        campoNombre.setForeground(Color.WHITE);
        campoNombre.setCaretColor(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panelPrincipal.add(campoNombre, gbc);

        JLabel etiquetaCorreo = new JLabel("Correo:");
        etiquetaCorreo.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panelPrincipal.add(etiquetaCorreo, gbc);

        campoCorreo = new PlaceholderTextField("Ingresa tu correo", 20);
        campoCorreo.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        campoCorreo.setBackground(new Color(25, 25, 25));
        campoCorreo.setForeground(Color.WHITE);
        campoCorreo.setCaretColor(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panelPrincipal.add(campoCorreo, gbc);

        JLabel etiquetaContrasena = new JLabel("Contraseña:");
        etiquetaContrasena.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 4;
        panelPrincipal.add(etiquetaContrasena, gbc);

        campoContrasena = new PlaceholderPasswordField("Ingresa tu contraseña", 20);
        campoContrasena.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        campoContrasena.setBackground(new Color(25, 25, 25));
        campoContrasena.setForeground(Color.WHITE);
        campoContrasena.setCaretColor(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 4;
        panelPrincipal.add(campoContrasena, gbc);

        JLabel etiquetaConfirmarContrasena = new JLabel("Confirmar Contraseña:");
        etiquetaConfirmarContrasena.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 5;
        panelPrincipal.add(etiquetaConfirmarContrasena, gbc);

        campoConfirmarContrasena = new PlaceholderPasswordField("Confirma tu contraseña", 20);
        campoConfirmarContrasena.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        campoConfirmarContrasena.setBackground(new Color(25, 25, 25));
        campoConfirmarContrasena.setForeground(Color.WHITE);
        campoConfirmarContrasena.setCaretColor(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 5;
        panelPrincipal.add(campoConfirmarContrasena, gbc);

        botonRegistrar = new JButton("Registrar");
        botonRegistrar.setBackground(new Color(30, 215, 96));
        botonRegistrar.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        panelPrincipal.add(botonRegistrar, gbc);

        botonVolver = new JButton("Volver");
        botonVolver.setBackground(new Color(25, 25, 25));
        botonVolver.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 7;
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
    public void limpiarCampos() {
        campoNombre.setText("");
        campoCorreo.setText("");
        campoContrasena.setText("");
        campoConfirmarContrasena.setText("");
    }
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaRegistro ventana = new VentanaRegistro();
            ventana.setVisible(true);
        });
    }
}

class PlaceholderPasswordField extends JPasswordField {
    private final String placeholder;

    public PlaceholderPasswordField(String placeholder, int columns) {
        super(columns);
        this.placeholder = placeholder;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (String.valueOf(getPassword()).isEmpty()) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(Color.GRAY);
            g2.setFont(getFont().deriveFont(Font.ITALIC));
            g2.drawString(placeholder, getInsets().left, g.getFontMetrics().getMaxAscent() + getInsets().top);
            g2.dispose();
        }
    }
}

