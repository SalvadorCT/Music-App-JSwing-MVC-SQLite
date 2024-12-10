package com.View;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelReproductor extends JPanel {
    @Getter
    private final JLabel etiquetaCancionActual;
    @Getter
    private final JButton botonPlayPausa;
    @Getter
    private final JButton botonSiguiente;
    @Getter
    private final JButton botonAnterior;
    private boolean reproduciendo = false;
    @Getter
    private final JSlider sliderVolumen;

    private final PanelDetallesCancion panelDetallesCancion;
    /**
     * Constructor que inicializa los componentes del panel.
     * @param panelDetallesCancion Panel de detalles de la canción
     */
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
    }
    /**
     * Crea un botón con el texto especificado y lo personaliza.
     *
     * @param texto Texto del botón
     * @return Botón personalizado
     */
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
    public void setDetallesCancion(String titulo, String artista, String album, String ano, String genero, String pista, String duracion, String rutaPortada) {
        panelDetallesCancion.mostrarDetallesCancion(titulo, artista, album, ano, genero, pista, duracion, rutaPortada);
    }
}
