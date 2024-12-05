package com.View;

import javax.swing.*;
import java.awt.*;

public class PantallaPrincipal extends JFrame {
    private JPanel panelLateral;
    private JPanel panelPrincipal;
    private JButton btnInicio;
    private JButton btnBuscar;
    private JButton btnPlaylists;
    private JButton btnConfiguracion;

    private PanelReproductor panelReproductor;
    private PanelDetallesCancion panelDetallesCancion;

    public PantallaPrincipal() {
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
    }

    public JButton getBtnInicio() {
        return btnInicio;
    }
    public JButton getBtnBuscar() {
        return btnBuscar;
    }
    public JButton getBtnPlaylists() {
        return btnPlaylists;
    }
    public JButton getBtnConfiguracion() {
        return btnConfiguracion;
    }
    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void cambiarPanel(JPanel nuevoPanel) {
        getPanelPrincipal().removeAll();
        getPanelPrincipal().add(nuevoPanel);
        getPanelPrincipal().revalidate();
        getPanelPrincipal().repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PantallaPrincipal pantalla = new PantallaPrincipal();
            pantalla.setVisible(true);
        });
    }
}

