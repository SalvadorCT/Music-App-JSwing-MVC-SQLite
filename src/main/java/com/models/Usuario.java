package com.models;

import lombok.Data;

public @Data class Usuario {
    private int usuarioId;
    private String nombre;
    private String email;
    private String tipo_Suscripcion;
    private Long fecha_Creacion;
    private String contrasena_Hash;
    private String estado;
}
