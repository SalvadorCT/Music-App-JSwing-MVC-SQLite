package com.View;

import javax.swing.*;
import java.awt.*;

class PlaceholderTextField extends JTextField {
    private String placeholder;

    public PlaceholderTextField(String placeholder, int columns) {
        super(columns);
        this.placeholder = placeholder;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (getText().isEmpty()) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(Color.GRAY);
            g2.setFont(getFont().deriveFont(Font.ITALIC));
            g2.drawString(placeholder, getInsets().left, g.getFontMetrics().getMaxAscent() + getInsets().top);
            g2.dispose();
        }
    }
}

public class PanelBusqueda extends JPanel {
    private JTextField campoBusqueda;
    private JButton botonBuscar;
    private JTable tablaResultados;
    private String textPlaceHolder = "¿Qué quieres reproducir?";

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
    public JButton getBotonBuscar() {
        return botonBuscar;
    }
}