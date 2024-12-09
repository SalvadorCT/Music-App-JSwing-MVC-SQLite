package com.View;

import com.controller.ConfiguracionesController;
import com.models.dao.UsuarioDAO;
import lombok.Getter;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import com.models.dao.UsuarioDAO;

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
        // Seleccionar el botón Inicio
        seleccionarBoton(btnInicio, "src/main/java/com/View/icons/home_focused.png");
    }

    public PantallaPrincipal() throws SQLException {
        setTitle("Spotify Clon");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setIconImage(new ImageIcon("src/main/java/com/View/icons/JPotify.png").getImage());

        // barra de navegación
        panelLateral = new JPanel();
        panelLateral.setLayout(new BoxLayout(panelLateral, BoxLayout.Y_AXIS));
        panelLateral.setBackground(new Color(25, 25, 25));
        panelLateral.setPreferredSize(new Dimension(200, getHeight()));

        btnInicio = crearBotonConIcono("Inicio", "src/main/java/com/View/icons/home.png");
        btnBuscar = crearBotonConIcono("Buscar", "src/main/java/com/View/icons/search.png");
        btnPlaylists =crearBotonConIcono("Playlists", "src/main/java/com/View/icons/playlist.png");
        btnConfiguracion = crearBotonConIcono("Configuración", "src/main/java/com/View/icons/settings.png");

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
        /*
        btnBuscar.addActionListener(e -> {
            cambiarPanel(new PanelBusqueda());
            seleccionarBoton(btnBuscar, "src/main/java/com/View/icons/home_focused.png");
        });
        btnPlaylists.addActionListener(e -> cambiarPanel(new PanelPlaylists()));
        */
        inicializar();

        // ActionListener para el botón Inicio
        getBtnInicio().addActionListener(e -> {
            try {
                cambiarPanel(new PanelCanciones());
                seleccionarBoton(btnInicio, "src/main/java/com/View/icons/home_focused.png");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        // ActionListener para el botón Buscar
        getBtnBuscar().addActionListener(e -> {
            cambiarPanel(new PanelBusqueda());
            seleccionarBoton(btnBuscar, "src/main/java/com/View/icons/search_focused.png");
        });
        // ActionListener para el botón Playlists
        getBtnPlaylists().addActionListener(e -> {
            cambiarPanel(new PanelPlaylists());
            seleccionarBoton(btnPlaylists, "src/main/java/com/View/icons/playlist_focused.png");
        });

        getBtnConfiguracion().addActionListener(e -> {
            PanelConfiguraciones panelConfiguraciones = new PanelConfiguraciones();
            try {
                int usuarioId = obtenerIdUsuarioActual();
                UsuarioDAO usuarioDAO;
                usuarioDAO = new UsuarioDAO();
                ConfiguracionesController controlador = new ConfiguracionesController(panelConfiguraciones, usuarioDAO, usuarioId);
                cambiarPanel(panelConfiguraciones);
                seleccionarBoton(btnConfiguracion, "src/main/java/com/View/icons/settings_focused.png");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al cargar configuraciones: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

    }

    private int obtenerIdUsuarioActual() {
        // Simular usuario actual con ID fijo para prueba
        return 1;
    }

    public void cambiarPanel(JPanel nuevoPanel) {
        getPanelPrincipal().removeAll();
        getPanelPrincipal().add(nuevoPanel);
        getPanelPrincipal().revalidate();
        getPanelPrincipal().repaint();
    }
    /**
     * Crea un botón con un icono y un texto
     * Ajusta el tamaño del icono a 20x20
     * @param texto     Texto del botón
     * @param rutaIcono Ruta del icono
     * @return Botón con icono
     */
    private JButton crearBotonConIcono(String texto, String rutaIcono) {
        JButton boton = new JButton(texto);
        boton.setLayout(new BorderLayout());
        boton.setForeground(Color.WHITE);
        boton.setBackground(new Color(30, 30, 30));
        boton.setIcon(new ImageIcon(new ImageIcon(rutaIcono).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        return boton;
    };

    /**
     * Selecciona un botón y cambia su icono
     * @param boton Botón a seleccionar
     * @param rutaIcono Ruta del icono seleccionado
     */
    private void seleccionarBoton(JButton boton, String rutaIcono) {
        // Restablecer iconos de todos los botones
        btnInicio.setIcon(new ImageIcon(new ImageIcon("src/main/java/com/View/icons/home.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        btnBuscar.setIcon(new ImageIcon(new ImageIcon("src/main/java/com/View/icons/search.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        btnConfiguracion.setIcon(new ImageIcon(new ImageIcon("src/main/java/com/View/icons/settings.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        btnPlaylists.setIcon(new ImageIcon(new ImageIcon("src/main/java/com/View/icons/playlist.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        // Establecer icono del botón seleccionado
        boton.setIcon(new ImageIcon(new ImageIcon(rutaIcono).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
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

