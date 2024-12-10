package com.controller;

import com.View.PantallaPrincipal;
import com.View.PanelDetallesCancion;
import com.View.PanelReproductor;
import com.models.dao.UsuarioDAO;
import com.models.dao.CancionDAO;
import com.models.Cancion;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class PantallaPrincipalController {
    private final PantallaPrincipal pantallaPrincipal;
    private final UsuarioDAO usuarioDAO;
    private final ReproductorController reproductorController;
    /**
     * Constructor de la clase PantallaPrincipalController
     * @param pantallaPrincipal Pantalla principal de la aplicación
     * @param usuarioDAO Objeto de acceso a datos de Usuario
     */
    public PantallaPrincipalController(PantallaPrincipal pantallaPrincipal, UsuarioDAO usuarioDAO) {
        this.pantallaPrincipal = pantallaPrincipal;
        this.usuarioDAO = usuarioDAO;

        PanelDetallesCancion panelDetallesCancion = new PanelDetallesCancion();
        PanelReproductor panelReproductor = new PanelReproductor(panelDetallesCancion);
        reproductorController = new ReproductorController(panelReproductor);

        try {
            List<Cancion> playlist = new CancionDAO().obtenerTodos();
            reproductorController.setPlaylist(playlist);
            pantallaPrincipal.add(panelReproductor, BorderLayout.SOUTH);
            pantallaPrincipal.add(panelDetallesCancion, BorderLayout.EAST);
            // Reproducir la primera canción si la lista no está vacía
            if (!playlist.isEmpty()) {
                reproductorController.playSong(playlist.get(0).getCancion_Id());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ReproductorController getReproductorController() {return reproductorController;}
}