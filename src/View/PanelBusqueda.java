package View;

import javax.swing.*;
import java.awt.*;

public class PanelBusqueda extends JPanel {
    private JTextField campoBusqueda;
    private JButton botonBuscar;
    private JTable tablaResultados;

    public PanelBusqueda() {
        setLayout(new BorderLayout());
        setBackground(new Color(40, 40, 40));

        JPanel panelBusqueda = new JPanel();
        panelBusqueda.setLayout(new FlowLayout());
        panelBusqueda.setBackground(new Color(40, 40, 40));

        JLabel etiquetaBusqueda = new JLabel("Buscar:");
        etiquetaBusqueda.setForeground(Color.WHITE);
        campoBusqueda = new JTextField(20);

        botonBuscar = new JButton("Buscar");
        botonBuscar.setBackground(new Color(30, 215, 96));
        botonBuscar.setForeground(Color.WHITE);

        panelBusqueda.add(etiquetaBusqueda);
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
