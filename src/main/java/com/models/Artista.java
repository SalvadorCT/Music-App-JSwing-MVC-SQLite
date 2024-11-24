package com.models;
import lombok.Data;


public @Data class Artista {
    private int artistaId;
    private String nombre;
    private String genero;
    private String paisOrigen;
}