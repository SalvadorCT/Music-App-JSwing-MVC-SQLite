package com.View;

import javax.swing.*;
import java.awt.*;

public class PanelDetallesCancion extends JPanel {
    private final JLabel etiquetaPortada;

    private final JLabel etiquetaTitulo;
    private final JLabel etiquetaArtista;
    private final JLabel etiquetaAlbum;
    private final JLabel etiquetaAno;
    private final JLabel etiquetaGenero;
    private final JLabel etiquetaPista;
    private final JLabel etiquetaDuracion;

    private final JButton botonFavoritos;
    private final JButton botonCompartir;

    public PanelDetallesCancion() {
        // Configuración general del panel
        setLayout(new BorderLayout());
        setBackground(new Color(25, 25, 25));
        setPreferredSize(new Dimension(300, 0));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Márgenes internos

        // Portada del álbum
        etiquetaPortada = new JLabel();
        etiquetaPortada.setHorizontalAlignment(SwingConstants.CENTER);
        etiquetaPortada.setPreferredSize(new Dimension(150, 150));
        etiquetaPortada.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        etiquetaPortada.setIcon(crearIconoEscalado("src/main/resources/PortadaArticMonkeys.jpg", 150, 150));
        add(etiquetaPortada, BorderLayout.NORTH);

        // Panel central para detalles de la canción
        JPanel panelDetalles = new JPanel();
        panelDetalles.setLayout(new BoxLayout(panelDetalles, BoxLayout.Y_AXIS));
        panelDetalles.setBackground(new Color(25, 25, 25));
        panelDetalles.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Espaciado interno para los elementos

        etiquetaTitulo = new JLabel("Título: N/A");
        etiquetaTitulo.setForeground(Color.WHITE);
        etiquetaTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        etiquetaTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);

        etiquetaArtista = new JLabel("Artista: N/A");
        etiquetaArtista.setForeground(Color.LIGHT_GRAY);
        etiquetaArtista.setFont(new Font("Arial", Font.PLAIN, 14));
        etiquetaArtista.setAlignmentX(Component.LEFT_ALIGNMENT);

        etiquetaAlbum = new JLabel("Álbum: N/A");
        etiquetaAlbum.setForeground(Color.LIGHT_GRAY);
        etiquetaAlbum.setFont(new Font("Arial", Font.PLAIN, 14));
        etiquetaAlbum.setAlignmentX(Component.LEFT_ALIGNMENT);

        etiquetaAno = new JLabel("Año: N/A");
        etiquetaAno.setForeground(Color.LIGHT_GRAY);
        etiquetaAno.setFont(new Font("Arial", Font.PLAIN, 14));
        etiquetaAno.setAlignmentX(Component.LEFT_ALIGNMENT);

        etiquetaGenero = new JLabel("Género: N/A");
        etiquetaGenero.setForeground(Color.LIGHT_GRAY);
        etiquetaGenero.setFont(new Font("Arial", Font.PLAIN, 14));
        etiquetaGenero.setAlignmentX(Component.LEFT_ALIGNMENT);

        etiquetaPista = new JLabel("Pista: N/A");
        etiquetaPista.setForeground(Color.LIGHT_GRAY);
        etiquetaPista.setFont(new Font("Arial", Font.PLAIN, 14));
        etiquetaPista.setAlignmentX(Component.LEFT_ALIGNMENT);

        etiquetaDuracion = new JLabel("Duración: N/A");
        etiquetaDuracion.setForeground(Color.LIGHT_GRAY);
        etiquetaDuracion.setFont(new Font("Arial", Font.PLAIN, 14));
        etiquetaDuracion.setAlignmentX(Component.LEFT_ALIGNMENT);

        panelDetalles.add(etiquetaTitulo);
        panelDetalles.add(Box.createRigidArea(new Dimension(0, 10))); // Espaciado vertical
        panelDetalles.add(etiquetaArtista);
        panelDetalles.add(Box.createRigidArea(new Dimension(0, 10)));
        panelDetalles.add(etiquetaAlbum);
        panelDetalles.add(Box.createRigidArea(new Dimension(0, 10)));
        panelDetalles.add(etiquetaAno);
        panelDetalles.add(Box.createRigidArea(new Dimension(0, 10)));
        panelDetalles.add(etiquetaGenero);
        panelDetalles.add(Box.createRigidArea(new Dimension(0, 10)));
        panelDetalles.add(etiquetaPista);
        panelDetalles.add(Box.createRigidArea(new Dimension(0, 10)));
        panelDetalles.add(etiquetaDuracion);

        add(panelDetalles, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.LEFT)); // Botones alineados a la izquierda
        panelBotones.setBackground(new Color(25, 25, 25));

        botonFavoritos = new JButton("❤ Añadir a Favoritos");
        botonFavoritos.setBackground(new Color(30, 215, 96));
        botonFavoritos.setForeground(Color.WHITE);
        botonFavoritos.setFocusPainted(false);
        botonFavoritos.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        botonCompartir = new JButton("Compartir");
        botonCompartir.setBackground(new Color(24, 119, 242));
        botonCompartir.setForeground(Color.WHITE);
        botonCompartir.setFocusPainted(false);
        botonCompartir.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        panelBotones.add(botonFavoritos);
        panelBotones.add(botonCompartir);

        add(panelBotones, BorderLayout.SOUTH);
    }
    /**
     * Muestra los detalles de una canción en el panel
     * @param titulo Título de la canción
     * @param artista Artista de la canción
     * @param album Álbum de la canción
     * @param ano Año de la canción
     * @param genero Género de la canción
     * @param pista Número de pista
     * @param duracion Duración de la canción
     * @param rutaPortada Ruta de la imagen de la portada
     */
    public void mostrarDetallesCancion(String titulo, String artista, String album, String ano, String genero, String pista, String duracion, String rutaPortada) {
        etiquetaTitulo.setText("Título: " + titulo);
        etiquetaArtista.setText("Artista: " + artista);
        etiquetaAlbum.setText("Álbum: " + album);
        etiquetaAno.setText("Año: " + ano);
        etiquetaGenero.setText("Género: " + genero);
        etiquetaPista.setText("Pista: " + pista);
        etiquetaDuracion.setText("Duración: " + duracion);

        if (rutaPortada != null && !rutaPortada.isEmpty()) {
            etiquetaPortada.setIcon(crearIconoEscalado(rutaPortada, 150, 150));
        }
    }
    /**
     * Crea un ImageIcon escalado a las dimensiones especificadas
     * @param ruta Ruta de la imagen
     * @param ancho Ancho deseado
     * @param alto Alto deseado
     * @return ImageIcon escalado
     */
    private ImageIcon crearIconoEscalado(String ruta, int ancho, int alto) {
        ImageIcon icono = new ImageIcon(ruta);
        Image imagen = icono.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        return new ImageIcon(imagen);
    }

    public JButton getBotonFavoritos() {
        return botonFavoritos;
    }

    public JButton getBotonCompartir() {
        return botonCompartir;
    }
}


