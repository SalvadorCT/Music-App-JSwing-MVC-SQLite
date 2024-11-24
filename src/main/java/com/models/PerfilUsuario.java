package com.models;

import lombok.Data;

public @Data class PerfilUsuario {
    private int perfilId;
    private int usuarioId;
    private String fotoPerfil;
    private String biografia;
}
