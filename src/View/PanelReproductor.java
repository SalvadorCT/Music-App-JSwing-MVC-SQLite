package View;

import javax.swing.*;
import java.awt.*;

public class PanelReproductor extends JPanel {
    private JLabel etiquetaCancionActual;
    private JButton botonPlayPausa;
    private JButton botonSiguiente;
    private JButton botonAnterior;

    public PanelReproductor() {
        setLayout(new BorderLayout());
        setBackground(new Color(25, 25, 25));

        etiquetaCancionActual = new JLabel("Ninguna canción en reproducción");
        etiquetaCancionActual.setForeground(Color.WHITE);
        etiquetaCancionActual.setHorizontalAlignment(SwingConstants.CENTER);
        add(etiquetaCancionActual, BorderLayout.NORTH);

        JPanel panelControles = new JPanel();
        panelControles.setLayout(new FlowLayout());
        panelControles.setBackground(new Color(25, 25, 25));

        botonAnterior = new JButton("<<");
        botonPlayPausa = new JButton("Play");
        botonSiguiente = new JButton(">>");

        JButton[] botones = {botonAnterior, botonPlayPausa, botonSiguiente};
        for (JButton boton : botones) {
            boton.setBackground(new Color(30, 30, 30));
            boton.setForeground(Color.WHITE);
        }

        panelControles.add(botonAnterior);
        panelControles.add(botonPlayPausa);
        panelControles.add(botonSiguiente);

        add(panelControles, BorderLayout.CENTER);
    }

    public void setCancionActual(String titulo) {
        etiquetaCancionActual.setText("Reproduciendo: " + titulo);
    }
    public JButton getBotonPlayPausa() {
        return botonPlayPausa;
    }
    public JButton getBotonSiguiente() {
        return botonSiguiente;
    }
    public JButton getBotonAnterior() {
        return botonAnterior;
    }

    // Prueba
    /*
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame pantalla = new JFrame("Panel Reproductor");
            pantalla.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            pantalla.setSize(400, 200);
            pantalla.add(new PanelReproductor());

            pantalla.setVisible(true);
        });
    }*/
}
