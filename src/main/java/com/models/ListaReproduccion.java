package com.models;

import lombok.Data;

public @Data class ListaReproduccion {
    private int listaId;
    private String nombre;
    private int usuarioId;
    private Long fechaCreacion;
    private String privacidad;
}
