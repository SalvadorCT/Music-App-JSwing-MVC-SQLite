package com.models;

import lombok.Data;
import java.util.Date;


public @Data class Usuario {
    private int usuarioId;
    private String nombre;
    private String email;
    private String tipoSuscripcion;
    private Date fechaCreacion;
    private String contrasenaHash;
    private String estado;
}
