package com.View;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class PantallaPrincipal extends JFrame {
    private final JPanel panelLateral;
    @Getter
    private final JPanel panelPrincipal;
    @Getter
    private final JButton btnInicio;
    @Getter
    private final JButton btnBuscar;
    @Getter
    private final JButton btnPlaylists;
    @Getter
    private final JButton btnConfiguracion;

    private final PanelReproductor panelReproductor;
    private final PanelDetallesCancion panelDetallesCancion;

    private void inicializar() throws SQLException {
        // Establecer el panel inicial
        cambiarPanel(new PanelCanciones());
    }

    public PantallaPrincipal() throws SQLException {
        setTitle("Spotify Clon");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // barra de navegación
        panelLateral = new JPanel();
        panelLateral.setLayout(new BoxLayout(panelLateral, BoxLayout.Y_AXIS));
        panelLateral.setBackground(new Color(25, 25, 25));
        panelLateral.setPreferredSize(new Dimension(200, getHeight()));

        btnInicio = new JButton("Inicio");
        btnBuscar = new JButton("Buscar");
        btnPlaylists = new JButton("Playlists");
        btnConfiguracion = new JButton("Configuración");

        // Estilo de los botones
        JButton[] botones = {btnInicio, btnBuscar, btnPlaylists, btnConfiguracion};
        for (JButton boton : botones) {
            boton.setBackground(new Color(30, 30, 30));
            boton.setForeground(Color.WHITE);
            setIconImage(new ImageIcon("src/main/java/com/View/icons/JPotify.png").getImage());
            boton.setFocusPainted(false);
            boton.setBorderPainted(false);
            boton.setAlignmentX(Component.CENTER_ALIGNMENT);
            boton.setMaximumSize(new Dimension(180, 40));
            panelLateral.add(Box.createRigidArea(new Dimension(0, 20)));// Espaciado
            panelLateral.add(boton);
        }

        panelPrincipal = new JPanel();
        panelPrincipal.setBackground(new Color(40, 40, 40));
        panelPrincipal.setLayout(new BorderLayout());

        panelDetallesCancion = new PanelDetallesCancion();
        add(panelDetallesCancion, BorderLayout.EAST);

        panelReproductor = new PanelReproductor(panelDetallesCancion);

        add(panelReproductor, BorderLayout.SOUTH);
        add(panelLateral, BorderLayout.WEST);
        add(panelPrincipal, BorderLayout.CENTER);

        btnBuscar.addActionListener(e -> cambiarPanel(new PanelBusqueda()));
        btnPlaylists.addActionListener(e -> cambiarPanel(new PanelPlaylists()));

        inicializar();

        // Configurar ActionListener para el botón Inicio
        getBtnInicio().addActionListener(e -> {
            try {
                cambiarPanel(new PanelCanciones());
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        // Configurar ActionListener para el botón Buscar
        getBtnBuscar().addActionListener(e -> cambiarPanel(new PanelBusqueda()));

        // Configurar ActionListener para el botón Playlists
        getBtnPlaylists().addActionListener(e -> cambiarPanel(new PanelPlaylists()));
    }


    public void cambiarPanel(JPanel nuevoPanel) {
        getPanelPrincipal().removeAll();
        getPanelPrincipal().add(nuevoPanel);
        getPanelPrincipal().revalidate();
        getPanelPrincipal().repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PantallaPrincipal pantalla = null;
            try {
                pantalla = new PantallaPrincipal();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            pantalla.setVisible(true);
        });
    }
}

