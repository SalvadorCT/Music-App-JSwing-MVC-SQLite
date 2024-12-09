package com.models;

import lombok.Data;

import java.sql.Date;

@Data
public class Album {
    private int album_Id;
    private String nombre;
    private Date fecha_Lanzamiento;
    private String genero;
    private String url_Portada;
}