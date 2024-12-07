package com.View;

import javax.swing.*;
import java.awt.*;

public class PanelDetallesCancion extends JPanel {
    private final JLabel etiquetaTitulo;
    private final JLabel etiquetaArtista;
    private final JLabel etiquetaAlbum;

    public PanelDetallesCancion() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(25, 25, 25));
        setPreferredSize(new Dimension(300, 0));

        etiquetaTitulo = new JLabel("Título: N/A", SwingConstants.CENTER);
        etiquetaTitulo.setForeground(Color.WHITE);
        etiquetaTitulo.setFont(new Font("Arial", Font.BOLD, 18));

        etiquetaArtista = new JLabel("Artista: N/A", SwingConstants.CENTER);
        etiquetaArtista.setForeground(Color.LIGHT_GRAY);
        etiquetaArtista.setFont(new Font("Arial", Font.PLAIN, 14));

        etiquetaAlbum = new JLabel("Álbum: N/A", SwingConstants.CENTER);
        etiquetaAlbum.setForeground(Color.LIGHT_GRAY);
        etiquetaAlbum.setFont(new Font("Arial", Font.PLAIN, 14));

        add(Box.createRigidArea(new Dimension(0, 50)));
        add(etiquetaTitulo);
        add(Box.createRigidArea(new Dimension(0, 20))); // Espaciado
        add(etiquetaArtista);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(etiquetaAlbum);
    }

    public void mostrarDetallesCancion(String titulo, String artista, String album) {
        etiquetaTitulo.setText("Título: " + titulo);
        etiquetaArtista.setText("Artista: " + artista);
        etiquetaAlbum.setText("Álbum: " + album);
    }
}

