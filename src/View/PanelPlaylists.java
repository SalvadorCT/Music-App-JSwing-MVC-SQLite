package View;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PanelPlaylists extends JPanel {
    private JList<String> listaPlaylists;
    private JButton botonCrearPlaylist;
    private JButton botonVerPlaylist;

    public PanelPlaylists() {
        setLayout(new BorderLayout());
        setBackground(new Color(40, 40, 40));

        JLabel titulo = new JLabel("Tus Playlists");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(titulo, BorderLayout.NORTH);

        listaPlaylists = new JList<>();
        listaPlaylists.setBackground(new Color(25, 25, 25));
        listaPlaylists.setForeground(Color.WHITE);
        add(new JScrollPane(listaPlaylists), BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout());
        panelBotones.setBackground(new Color(40, 40, 40));

        botonCrearPlaylist = new JButton("Nueva Playlist");
        botonCrearPlaylist.setBackground(new Color(30, 215, 96));
        botonCrearPlaylist.setForeground(Color.WHITE);

        botonVerPlaylist = new JButton("Ver Playlist");
        botonVerPlaylist.setBackground(new Color(30, 215, 96));
        botonVerPlaylist.setForeground(Color.WHITE);

        panelBotones.add(botonCrearPlaylist);
        panelBotones.add(botonVerPlaylist);

        add(panelBotones, BorderLayout.SOUTH);
    }

    public void setPlaylists(List<String> playlists) {
        listaPlaylists.setListData(playlists.toArray(new String[0]));
    }
    public String getPlaylistSeleccionada() {
        return listaPlaylists.getSelectedValue();
    }
    public JButton getBotonCrearPlaylist() {
        return botonCrearPlaylist;
    }
    public JButton getBotonVerPlaylist() {
        return botonVerPlaylist;
    }
}

