package com.View;

import com.models.util.DatabaseConnection;
import com.models.dao.CancionDAO;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;

public class PanelBusqueda extends JPanel {
    private final JTextField campoBusqueda;
    @Getter
    private final JButton botonBuscar;
    private final JTable tablaResultados;
    private final String textPlaceHolder = "¿Qué quieres reproducir?";

    public PanelBusqueda() {
        setLayout(new BorderLayout());
        setBackground(new Color(40, 40, 40));

        JPanel panelBusqueda = new JPanel();
        panelBusqueda.setLayout(new FlowLayout());
        panelBusqueda.setBackground(new Color(40, 40, 40));

        //JLabel etiquetaBusqueda = new JLabel("Buscar:");
        //etiquetaBusqueda.setForeground(Color.WHITE);
        //etiquetaBusqueda.setFont(new Font("Arial", Font.PLAIN, 16));

        campoBusqueda = new PlaceholderTextField(textPlaceHolder, 20);
        campoBusqueda.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        campoBusqueda.setBackground(new Color(25, 25, 25));
        campoBusqueda.setForeground(Color.WHITE);
        campoBusqueda.setCaretColor(Color.WHITE);


        botonBuscar = new JButton("Buscar");
        botonBuscar.setBackground(new Color(30, 215, 96));
        botonBuscar.setForeground(Color.WHITE);

        //panelBusqueda.add(etiquetaBusqueda);
        panelBusqueda.add(campoBusqueda);
        panelBusqueda.add(botonBuscar);

        add(panelBusqueda, BorderLayout.NORTH);

        String[] columnas = {"Título", "Artista", "Álbum", "Género"};
        Object[][] datos = {}; // Datos iniciales vacíos
        tablaResultados = new JTable(datos, columnas);
        tablaResultados.setBackground(new Color(25, 25, 25));
        tablaResultados.setForeground(Color.WHITE);
        tablaResultados.setFillsViewportHeight(true);
        add(new JScrollPane(tablaResultados), BorderLayout.CENTER);

        botonBuscar.addActionListener(e -> {
            String queryText = getTextoBusqueda();
            // Realizar búsqueda en la base de datos
            Connection conn = null;
            try {
                conn = DatabaseConnection.getConnection();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            CancionDAO dao = new CancionDAO(conn);
            Object[][] resultados = null;
            try {
                resultados = dao.obtenerCancionesPorTexto(queryText);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            setResultados(resultados);
        });
    }

    public String getTextoBusqueda() {
        return campoBusqueda.getText();
    }

    public void setResultados(Object[][] resultados) {
        String[] columnas = {"Título", "Artista", "Álbum", "Género"};
        tablaResultados.setModel(new javax.swing.table.DefaultTableModel(resultados, columnas));
    }
    public int getCancionSeleccionada() {
        return tablaResultados.getSelectedRow();
    }

}