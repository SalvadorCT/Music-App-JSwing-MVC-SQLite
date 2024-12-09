package com.models.util;

import java.io.InputStream;
public class PausingInputStream extends InputStream {
    private InputStream inputStream;
    private boolean isPaused = false;

    public PausingInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void pause() {
        isPaused = true;
    }

    public void resume() {
        isPaused = false;
    }

    @Override
    public int read() throws java.io.IOException {
        while (isPaused) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        return inputStream.read();
    }

    @Override
    public int read(byte[] b, int off, int len) throws java.io.IOException {
        while (isPaused) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        return inputStream.read(b, off, len);
    }
}