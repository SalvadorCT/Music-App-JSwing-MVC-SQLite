package com.models;

import lombok.Data;

public @Data class ListaReproduccion {
    private int lista_Id;
    private String nombre;
    private int usuario_Id;
    private Long fecha_Creacion;
    private String privacidad;
}
