package com.models;
import lombok.Data;


public @Data class Artista {
    private int artista_Id;
    private String nombre;
    private String genero;
    private String pais_Origen;
}