package com;/*
package com;
import com.View.VentanaLogin;
import com.controller.LoginController;
import com.models.dao.UsuarioDAO;

import javax.swing.*;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UsuarioDAO usuarioDAO = new UsuarioDAO();

                VentanaLogin ventanaLogin = new VentanaLogin();
                new LoginController(ventanaLogin, usuarioDAO);
                ventanaLogin.setVisible(true);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al iniciar la aplicación: " + e.getMessage());
            }
        });
    }
}*/

import com.View.PanelDetallesCancion;
import com.View.PanelReproductor;
import com.controller.ReproductorController;
import com.models.Cancion;
import com.models.dao.CancionDAO;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException {
        // Create a mock PanelReproductor
        PanelReproductor panelReproductor = new PanelReproductor(new PanelDetallesCancion());
        ReproductorController controller = new ReproductorController(panelReproductor);

        // Create a list of Cancion objects with absolute file paths
        List<Cancion> playlist = new CancionDAO().obtenerTodos();
        controller.setPlaylist((List<Cancion>) playlist);

        // Set up the JFrame to display the PanelReproductor
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panelReproductor);
        frame.pack();
        frame.setVisible(true);

        // Simulate playing the first song
        controller.playSong(1);

        // Simulate user interactions (uncomment as needed)
        // controller.pause();
        // controller.resume();
        // controller.next();
        // controller.previous();
        // controller.stop();
    }
}