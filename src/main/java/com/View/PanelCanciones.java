package com.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Vector;

import com.controller.ReproductorController;
import com.models.Album;
import com.models.Cancion;
import com.models.dao.AlbumDAO;
import com.models.util.DatabaseConnection;
import com.models.dao.CancionDAO;
import lombok.Getter;

public class PanelCanciones extends JPanel {
    private final JTable tablaCanciones;
    @Getter
    private final JButton botonReproducir;
    @Getter
    private final JButton botonAgregarAPlaylist;

    private ReproductorController reproductorController;

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
        // Ensure that the data returned from obtenerTodos() can be correctly mapped to Cancion
        List<Cancion> cancionesData = dao.obtenerTodos();
        setCanciones(cancionesData);
    }

    public PanelCanciones(ReproductorController reproductorController) throws SQLException {
        this.reproductorController = reproductorController;
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
        // Ensure that the data returned from obtenerTodos() can be correctly mapped to Cancion
        List<Cancion> cancionesData = dao.obtenerTodos();
        setCanciones(cancionesData);

        // Configurar el ActionListener para el botón "Reproducir"
        botonReproducir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tablaCanciones.getSelectedRow();
                if (selectedRow != -1) {
                    int cancionId = (int) tablaCanciones.getValueAt(selectedRow, 0); // Suponiendo que la columna 0 es el ID de la canción
                    reproductorController.playSong(cancionId);
                } else {
                    JOptionPane.showMessageDialog(PanelCanciones.this, "Por favor, seleccione una canción para reproducir.");
                }
            }
        });
    }

    public void setCanciones(List<Cancion> canciones) throws SQLException {
        String[] columnas = {"Título", "Artista", "Álbum", "Género"};

        Vector<Vector<Object>> dataVector = new Vector<>();

        for (Cancion cancion : canciones) {
            Vector<Object> row = new Vector<>();
            row.add(cancion.getTitulo()); // Título
            row.add("ArtistaPlaceholder"); // Replace with actual method to obtain artist if available

            int albumId = cancion.getAlbum_Id();
            AlbumDAO albumDao = new AlbumDAO();
            Optional<Album> albumOpt = albumDao.obtenerPorIdStatic(albumId);
            row.add(albumOpt.map(Album::getNombre).orElse("Unknown")); // Álbum
            row.add(albumOpt.map(Album::getGenero).orElse("Unknown")); // Género

            dataVector.add(row);
        }

        // Update table model
        tablaCanciones.setModel(new javax.swing.table.DefaultTableModel(dataVector, new Vector<>(List.of(columnas))));
    }



    public int getCancionSeleccionada() {
        return tablaCanciones.getSelectedRow();
    }

}