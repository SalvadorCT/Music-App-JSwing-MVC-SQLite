package com.View;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.models.Cancion;
import com.models.util.DatabaseConnection;
import com.models.dao.CancionDAO;
import lombok.Getter;

public class PanelCanciones extends JPanel {
    private final JTable tablaCanciones;
    @Getter
    private final JButton botonReproducir;
    @Getter
    private final JButton botonAgregarAPlaylist;

    public PanelCanciones() throws SQLException {
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

        Connection conn = DatabaseConnection.getConnection();
        CancionDAO dao = new CancionDAO(conn);
        List<Cancion> cancionesData = dao.obtenerTodos();
        setCanciones(cancionesData);
    }

    public void setCanciones(List<Cancion> canciones) {
        String[] columnas = {"Título", "Artista", "Álbum", "Género"};

        // Create a data vector for the table model
        Vector<Vector<Object>> dataVector = new Vector<>();

        for (Cancion cancion : canciones) {
            Vector<Object> row = new Vector<>();
            row.add(cancion.getTitulo());
            // Add additional fields from Cancion as needed or related entities
            // row.add(cancion.getArtista());
            // row.add(cancion.getAlbum());
            // row.add(cancion.getGenero());

            dataVector.add(row);
        }

        // Set the model with the data and column names
        tablaCanciones.setModel(new javax.swing.table.DefaultTableModel(dataVector, new Vector<>(List.of(columnas))));
    }
    public int getCancionSeleccionada() {
        return tablaCanciones.getSelectedRow();
    }

}

