package com.models;

import lombok.Data;

public @Data class Cancion {
    private int cancion_Id;
    private String titulo;
    private int duracion;
    private int album_Id;
    private String url_archivo;
    private int conteo_Reproducciones;
}
