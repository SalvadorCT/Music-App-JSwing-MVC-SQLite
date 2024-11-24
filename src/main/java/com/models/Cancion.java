package com.models;

import lombok.Data;

public @Data class Cancion {
    private int cancionId;
    private String titulo;
    private int duracion;
    private int albumId;
    private String urlArchivo;
    private int conteoReproducciones;

}
