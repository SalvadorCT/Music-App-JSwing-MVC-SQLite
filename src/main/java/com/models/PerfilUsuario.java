package com.models;

import lombok.Data;

public @Data class PerfilUsuario {
    private int perfil_Id;
    private int usuario_Id;
    private String foto_Perfil;
    private String biografia;
}
