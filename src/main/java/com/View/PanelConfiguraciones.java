package com.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.function.Function;

public class PanelConfiguraciones extends JPanel {
    private final JTextField campoNombre;
    private final JTextField campoCorreo;
    private final JButton botonGuardar;

    public PanelConfiguraciones() {
        setLayout(new GridBagLayout());
        setBackground(new Color(40, 40, 40));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titulo = new JLabel("Configuraciones");
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titulo, gbc);

        JLabel etiquetaNombre = new JLabel("Nombre:");
        etiquetaNombre.setForeground(Color.WHITE);
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(etiquetaNombre, gbc);

        campoNombre = new JTextField(20);
        gbc.gridx = 1;
        add(campoNombre, gbc);

        JLabel etiquetaCorreo = new JLabel("Correo:");
        etiquetaCorreo.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(etiquetaCorreo, gbc);

        campoCorreo = new JTextField(20);
        gbc.gridx = 1;
        add(campoCorreo, gbc);

        botonGuardar = new JButton("Guardar Cambios");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(botonGuardar, gbc);
    }
    public JTextField getCampoNombre() {return campoNombre;}
    public JTextField getCampoCorreo() {return campoCorreo;}
    public JButton getBotonGuardar() {return botonGuardar;}

    /**
     * Configura el estilo de un campo de texto
     * @param campo Campo de texto a configurar
     */
    private void configurarEstiloCampo(JTextField campo) {
        campo.setBackground(new Color(25, 25, 25));
        campo.setForeground(Color.WHITE);
        campo.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    }
    /**
     * Configura el estilo de un botón
     * @param boton Botón a configurar
     * @param texto Texto del botón
     * @param colorFondo Color de fondo del botón
     */
    private void configurarEstiloBoton(JButton boton, String texto, Color colorFondo) {
        boton.setText(texto);
        boton.setBackground(colorFondo);
        boton.setForeground(Color.WHITE);
    }
    /**
     * Agrega un listener para validar el contenido de un campo de texto
     * @param field Campo de texto a validar
     * @param validation Función que recibe el texto del campo y retorna si es válido o no
     */
    private void addFieldValidation(JTextField field, Function<String, Boolean> validation) {
        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String text = field.getText();
                if (!validation.apply(text)) {
                    field.setBackground(new Color(255, 0, 0, 50));
                } else {
                    field.setBackground(new Color(25, 25, 25));
                }
            }
        });
    }
    /**
     * Valida el contenido de un campo de texto
     * @param correo Correo a validar
     * @return Si el correo es válido o no
     */
    private boolean validarCorreo(String correo) {
        return correo.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
    }
    /**
     * Valida el contenido de un campo de texto
     * @param nombre Nombre a validar
     * @return Si el nombre es válido o no
     */
    private boolean validarNombre(String nombre) {
        return !nombre.isEmpty();
    }
    /**
     * Obtiene el nombre ingresado por el usuario
     * @return Nombre ingresado
     */
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Exito", JOptionPane.INFORMATION_MESSAGE);
    }
    /**
     * Establece el borde de un campo de texto
     * @param field Campo de texto a establecer el borde
     * @param isValid Si el campo es válido o no
     */
    public void setFieldBorder(JTextField field, boolean isValid) {
        field.setBorder(BorderFactory.createLineBorder(isValid ? Color.GREEN : Color.RED));
    }
    /**
     * Muestra un mensaje de error
     * @param mensaje Mensaje de error
     */
    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}

