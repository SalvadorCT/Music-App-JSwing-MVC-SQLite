package com.View;

import lombok.Getter;
import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class VentanaLogin extends JFrame {
    @Getter
    private final JButton botonLogin;
    @Getter
    private final JButton botonRegistro;

    private final JTextField campoCorreo;
    private final JPasswordField campoContrasena;

    public VentanaLogin() {
        setTitle("Inicio de Sesión");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        campoCorreo = new PlaceholderTextField("Ingresa tu correo", 20);
        campoContrasena = new PlaceholderPasswordField("Ingresa tu contraseña", 20);
        botonLogin = new JButton("Iniciar Sesión");
        botonRegistro = new JButton("Crear Cuenta");
        setIconImage(new ImageIcon("src/main/java/com/View/icons/JPotify.png").getImage());

        JPanel panelPrincipal = crearPanelPrincipal();
        add(panelPrincipal, BorderLayout.CENTER);
    }

    private JPanel crearPanelPrincipal() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(40, 40, 40));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel imagen = crearImagenEncabezado();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(imagen, gbc);

        JLabel titulo = new JLabel("Inicia Sesión");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridy = 1;
        panel.add(titulo, gbc);

        JLabel etiquetaCorreo = new JLabel("Correo:");
        etiquetaCorreo.setForeground(Color.WHITE);
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        panel.add(etiquetaCorreo, gbc);

        configurarEstiloCampo(campoCorreo);
        addFieldValidation(campoCorreo, this::validarCorreo);
        gbc.gridx = 1;
        panel.add(campoCorreo, gbc);

        JLabel etiquetaContrasena = new JLabel("Contraseña:");
        etiquetaContrasena.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(etiquetaContrasena, gbc);

        configurarEstiloCampo(campoContrasena);
        gbc.gridx = 1;
        panel.add(campoContrasena, gbc);

        configurarEstiloBoton(botonLogin, "Iniciar Sesión", new Color(122, 231, 165));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panel.add(botonLogin, gbc);

        configurarEstiloBoton(botonRegistro, "Crear Cuenta", new Color(25, 25, 25));
        gbc.gridy = 5;
        panel.add(botonRegistro, gbc);

        return panel;
    }

    private JLabel crearImagenEncabezado() {
        ImageIcon icon = new ImageIcon("src/main/java/com/View/icons/JPotify.png");
        Image image = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        return new JLabel(new ImageIcon(image));
    }

    /**
     * Establece el estilo de un campo de texto.
     * @param campo Campo de texto al que se le establecerá el estilo.
     */
    private void configurarEstiloCampo(JTextField campo) {
        campo.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        //campo.setBorder(BorderFactory.createCompoundBorder(
        //        campo.getBorder(),
        //        BorderFactory.createEmptyBorder(0,10,0,0) // top, left, bottom, right
        //));
        campo.setBackground(new Color(25, 25, 25));
        campo.setForeground(Color.WHITE);
        campo.setCaretColor(Color.WHITE);
    }
    /**
     * Establece el estilo de un botón.
     * @param boton Botón al que se le establecerá el estilo.
     * @param texto Texto que mostrará el botón.
     * @param colorFondo Color de fondo del botón.
     */
    private void configurarEstiloBoton(JButton boton, String texto, Color colorFondo) {
        boton.setText(texto);
        boton.setBackground(colorFondo);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorder(new RoundBorder(7));
    }

    public String getCorreo() {
        return campoCorreo.getText().trim();
    }

    public String getContrasena() {
        return new String(campoContrasena.getPassword()).trim();
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Mensaje", JOptionPane.INFORMATION_MESSAGE);
    }
    /**
     * Establece el borde de un campo de texto.
     * @param field Campo de texto al que se le establecerá el borde.
     * @param isValid Indica si el campo es válido o no.
     */
    public void setFieldBorder(JTextField field, boolean isValid) {
        field.setBorder(BorderFactory.createLineBorder(isValid ? Color.GREEN : Color.RED));
    }

    /**
     * Agrega una validación a un campo de texto.
     * @param field Campo de texto a validar.
     * @param validationLogic Lógica de validación a ejecutar.
     */
    private void addFieldValidation(JTextField field, Runnable validationLogic) {
        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                validationLogic.run();
            }
        });
    }

    private void validarCorreo() {
        String correo = getCorreo();
        boolean esValido = correo.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
        setFieldBorder(campoCorreo, esValido);
        if (!esValido && !correo.isEmpty()) {
            mostrarMensaje("Correo inválido. Por favor verifica el formato.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaLogin ventana = new VentanaLogin();
            ventana.setVisible(true);
        });
    }
}

