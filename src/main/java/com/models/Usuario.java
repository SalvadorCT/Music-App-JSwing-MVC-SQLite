package com.models;

import lombok.Data;

public @Data class Usuario {
    private int usuarioId;
    private String nombre;
    private String email;
    private String tipoSuscripcion;
    private Long fechaCreacion;
    private String contrasenaHash;
    private String estado;
}
