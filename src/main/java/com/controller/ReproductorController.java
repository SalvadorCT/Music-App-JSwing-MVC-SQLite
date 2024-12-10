package com.controller;

import com.View.PanelReproductor;
import com.models.Cancion;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.datatype.Artwork;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class ReproductorController {
    private final PanelReproductor panelReproductor;
    private List<Cancion> playlist;
    private int currentSongIndex = 0;
    private AdvancedPlayer reproductor;
    private Thread playThread;
    /**
     * Constructor de la clase ReproductorController.
     * @param panelReproductor El panel de reproducción.
     */
    public ReproductorController(PanelReproductor panelReproductor) {
        this.panelReproductor = panelReproductor;
        initListeners();
    }
    /**
     * Establece la lista de reproducción.
     * @param playlist La lista de reproducción.
     */
    public void setPlaylist(List<Cancion> playlist) {
        this.playlist = playlist;
    }
    /**
     * Inicializa los listeners de los botones del reproductor.
     */
    private void initListeners() {
        panelReproductor.getBotonPlayPausa().addActionListener(e -> togglePlayPause());
        panelReproductor.getBotonSiguiente().addActionListener(e -> playNextSong());
        panelReproductor.getBotonAnterior().addActionListener(e -> playPreviousSong());
    }
    /**
     * Reproduce la canción con el ID especificado.
     * @param cancionId El ID de la canción a reproducir.
     */
    public void playSong(int cancionId) {
        for (int i = 0; i < playlist.size(); i++) {
            if (playlist.get(i).getCancion_Id() == cancionId) {
                currentSongIndex = i;
                playCurrentSong();
                break;
            }
        }
    }
    /**
     * Pausa la reproducción de la canción actual.
     */
    private void playCurrentSong() {
        if (reproductor != null) {
            reproductor.close();
        }
        try {
            Cancion cancion = playlist.get(currentSongIndex);
            FileInputStream fis = new FileInputStream(cancion.getUrl_archivo());
            reproductor = new AdvancedPlayer(fis);
            playThread = new Thread(() -> {
                try {
                    reproductor.play();
                } catch (JavaLayerException e) {
                    e.printStackTrace();
                }
            });
            playThread.start();
            panelReproductor.setCancionActual(cancion.getTitulo());
            extractAndSetMetadata(cancion.getUrl_archivo());
        } catch (IOException | JavaLayerException e) {
            e.printStackTrace();
        }
    }
    /**
     * Captura y establece los metadatos de la canción actual.
     * @param filePath La ruta del archivo de la canción.
     */
    private void extractAndSetMetadata(String filePath) {
        try {
            AudioFile audioFile = AudioFileIO.read(new java.io.File(filePath));
            Tag tag = audioFile.getTag();

            String title = tag.getFirst(FieldKey.TITLE);
            String artist = tag.getFirst(FieldKey.ARTIST);
            String album = tag.getFirst(FieldKey.ALBUM);
            String year = tag.getFirst(FieldKey.YEAR);
            String genre = tag.getFirst(FieldKey.GENRE);
            String track = tag.getFirst(FieldKey.TRACK);
            String duration = String.valueOf(audioFile.getAudioHeader().getTrackLength());
            String coverArtPath = null;

            Artwork artwork = tag.getFirstArtwork();
            if (artwork != null) {
                coverArtPath = "cover.jpg";
                java.nio.file.Files.write(java.nio.file.Paths.get(coverArtPath), artwork.getBinaryData());
            }

            panelReproductor.setDetallesCancion(title, artist, album, year, genre, track, duration, coverArtPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Alterna entre reproducir y pausar la canción actual.
     */
    private void togglePlayPause() {
        if (reproductor != null) {
            if (playThread.isAlive()) {
                reproductor.close();
                panelReproductor.getBotonPlayPausa().setText("▶");
            } else {
                playCurrentSong();
                panelReproductor.getBotonPlayPausa().setText("||");
            }
        }
    }
    /**
     * Reproduce la siguiente canción en la lista de reproducción.
     */
    private void playNextSong() {
        if (currentSongIndex < playlist.size() - 1) {
            currentSongIndex++;
            playCurrentSong();
        }
    }
    /**
     * Reproduce la canción anterior en la lista de reproducción.
     */
    private void playPreviousSong() {
        if (currentSongIndex > 0) {
            currentSongIndex--;
            playCurrentSong();
        }
    }
}