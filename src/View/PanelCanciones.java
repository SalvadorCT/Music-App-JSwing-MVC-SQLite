package View;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PanelCanciones extends JPanel {
    private JTable tablaCanciones;
    private JButton botonReproducir;
    private JButton botonAgregarAPlaylist;

    public PanelCanciones() {
        setLayout(new BorderLayout());
        setBackground(new Color(40, 40, 40));

        JLabel titulo = new JLabel("Canciones Disponibles");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(titulo, BorderLayout.NORTH);

        String[] columnas = {"Título", "Artista", "Álbum", "Género"};
        Object[][] datos = {}; // Datos iniciales vacíos
        tablaCanciones = new JTable(datos, columnas);
        tablaCanciones.setBackground(new Color(25, 25, 25));
        tablaCanciones.setForeground(Color.WHITE);
        tablaCanciones.setFillsViewportHeight(true);
        add(new JScrollPane(tablaCanciones), BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout());
        panelBotones.setBackground(new Color(40, 40, 40));

        botonReproducir = new JButton("Reproducir");
        botonReproducir.setBackground(new Color(30, 215, 96));
        botonReproducir.setForeground(Color.WHITE);

        botonAgregarAPlaylist = new JButton("Agregar a Playlist");
        botonAgregarAPlaylist.setBackground(new Color(30, 215, 96));
        botonAgregarAPlaylist.setForeground(Color.WHITE);

        panelBotones.add(botonReproducir);
        panelBotones.add(botonAgregarAPlaylist);

        add(panelBotones, BorderLayout.SOUTH);
    }
    public void setCanciones(Object[][] canciones) {
        String[] columnas = {"Título", "Artista", "Álbum", "Género"};
        tablaCanciones.setModel(new javax.swing.table.DefaultTableModel(canciones, columnas));
    }
    public int getCancionSeleccionada() {
        return tablaCanciones.getSelectedRow();
    }
    public JButton getBotonReproducir() {
        return botonReproducir;
    }
    public JButton getBotonAgregarAPlaylist() {
        return botonAgregarAPlaylist;
    }
}

