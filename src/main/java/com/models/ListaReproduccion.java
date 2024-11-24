package com.models;

import lombok.Data;
import java.util.Date;

public @Data class ListaReproduccion {
    private int listaId;
    private String nombre;
    private int usuarioId;
    private Date fechaCreacion;
    private String privacidad;
}
