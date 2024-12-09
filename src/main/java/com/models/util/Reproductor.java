package com.models.util;

import javazoom.jl.player.Player;


import java.io.FileInputStream;

public class Reproductor {
    private PausingInputStream pausingInputStream;
    private Player player;
    private boolean isPaused = false;

    public void play(String filePath) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            pausingInputStream = new PausingInputStream(fis);
            player = new Player(pausingInputStream);
            new Thread(() -> {
                try {
                    player.play();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
            isPaused = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pause() {
        if (pausingInputStream != null) {
            pausingInputStream.pause();
            isPaused = true;
        }
    }

    public void resume() {
        if (pausingInputStream != null && isPaused) {
            pausingInputStream.resume();
            isPaused = false;
        }
    }

    public void stop() {
        if (player != null) {
            player.close();
            pausingInputStream = null;
            isPaused = false;
        }
    }

    public boolean isPaused() {
        return isPaused;
    }
}