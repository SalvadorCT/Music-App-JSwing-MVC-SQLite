package com.controller;

import com.View.PanelReproductor;
import com.models.Cancion;
import com.models.util.Reproductor;
import lombok.Setter;

import java.util.List;

public class ReproductorController {
    private Reproductor reproductor;
    private PanelReproductor panelReproductor;
    @Setter
    private List<Cancion> playlist;
    private int currentSongIndex = -1;

    public ReproductorController(PanelReproductor panelReproductor) {
        this.reproductor = new Reproductor();
        this.panelReproductor = panelReproductor;
        setupListeners();
    }

    private void setupListeners() {
        panelReproductor.getBotonPlayPausa().addActionListener(e -> {
            if (reproductor.isPaused()) {
                reproductor.resume();
                panelReproductor.getBotonPlayPausa().setText("||");
            } else if (currentSongIndex != -1) {
                reproductor.play(playlist.get(currentSongIndex).getUrl_archivo());
                panelReproductor.getBotonPlayPausa().setText("||");
            } else {
                // No song selected
            }
        });

        panelReproductor.getBotonSiguiente().addActionListener(e -> {
            if (playlist != null && !playlist.isEmpty()) {
                currentSongIndex = (currentSongIndex + 1) % playlist.size();
                reproductor.stop();
                reproductor.play(playlist.get(currentSongIndex).getUrl_archivo());
                panelReproductor.getBotonPlayPausa().setText("||");
            }
        });

        panelReproductor.getBotonAnterior().addActionListener(e -> {
            if (playlist != null && !playlist.isEmpty()) {
                currentSongIndex = (currentSongIndex - 1 + playlist.size()) % playlist.size();
                reproductor.stop();
                reproductor.play(playlist.get(currentSongIndex).getUrl_archivo());
                panelReproductor.getBotonPlayPausa().setText("||");
            }
        });

        panelReproductor.getSliderVolumen().addChangeListener(e -> {
            // Volume control implementation is not feasible with JLayer
        });
    }

    public void playSong(int cancionId) {
        for (int i = 0; i < playlist.size(); i++) {
            if (playlist.get(i).getCancion_Id() == cancionId) {
                currentSongIndex = i;
                reproductor.play(playlist.get(i).getUrl_archivo());
                panelReproductor.getBotonPlayPausa().setText("||");
                break;
            }
        }
    }
}