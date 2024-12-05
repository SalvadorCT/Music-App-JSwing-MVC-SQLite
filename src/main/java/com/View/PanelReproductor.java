package com.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelReproductor extends JPanel {
    private JLabel etiquetaCancionActual;
    private JButton botonPlayPausa,botonSiguiente,botonAnterior;
    private boolean reproduciendo = false;
    private JSlider sliderVolumen;

    private final PanelDetallesCancion panelDetallesCancion;

    public PanelReproductor(PanelDetallesCancion panelDetallesCancion) {
        this.panelDetallesCancion = panelDetallesCancion;

        setLayout(new BorderLayout());
        setBackground(new Color(25, 25, 25));
        setPreferredSize(new Dimension(0, 80));

        etiquetaCancionActual = new JLabel("Ninguna canción en reproducción");
        etiquetaCancionActual.setForeground(Color.WHITE);
        etiquetaCancionActual.setHorizontalAlignment(SwingConstants.CENTER);
        add(etiquetaCancionActual, BorderLayout.NORTH);

        JPanel panelControles = new JPanel();
        panelControles.setLayout(new FlowLayout());
        panelControles.setBackground(new Color(25, 25, 25));

        botonAnterior = crearBoton("<<");
        botonPlayPausa = crearBoton("▶");
        botonSiguiente = crearBoton(">>");

        botonPlayPausa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reproduciendo = !reproduciendo;
                if (reproduciendo) {
                    botonPlayPausa.setText("||");
                } else {
                    botonPlayPausa.setText("▶");
                }
            }
        });

        panelControles.add(botonAnterior);
        panelControles.add(botonPlayPausa);
        panelControles.add(botonSiguiente);

        add(panelControles, BorderLayout.CENTER);

         // Slider de volumen
        JPanel panelVolumen = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelVolumen.setBackground(new Color(30, 30, 30));

        JLabel etiquetaVolumen = new JLabel("Volumen");
        etiquetaVolumen.setForeground(Color.WHITE);
        etiquetaVolumen.setFont(new Font("Arial", Font.PLAIN, 12));

        sliderVolumen = new JSlider(0, 100, 50);
        sliderVolumen.setPreferredSize(new Dimension(150, 20));
        sliderVolumen.setBackground(new Color(30, 30, 30));
        sliderVolumen.setForeground(Color.WHITE);

        panelVolumen.add(etiquetaVolumen);
        panelVolumen.add(sliderVolumen);

        add(panelVolumen, BorderLayout.EAST);
    }

    private JButton crearBoton(String texto) {
        JButton boton = new JButton(texto);
        boton.setBackground(new Color(50, 50, 50));
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setPreferredSize(new Dimension(50, 50));
        return boton;
    }
    public void setCancionActual(String titulo) {
        etiquetaCancionActual.setText("Reproduciendo: " + titulo);
    }
    public void setDetallesCancion(String titulo, String artista, String album) {
        panelDetallesCancion.mostrarDetallesCancion(titulo, artista, album);
    }
    public JSlider getSliderVolumen() {return sliderVolumen;}
    public JButton getBotonPlayPausa() {return botonPlayPausa;}
    public JButton getBotonSiguiente() {return botonSiguiente;}
    public JButton getBotonAnterior() {return botonAnterior;}
}
