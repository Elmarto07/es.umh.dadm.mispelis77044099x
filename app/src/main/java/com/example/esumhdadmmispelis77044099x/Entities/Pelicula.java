package com.example.esumhdadmmispelis77044099x.Entities;

public class Pelicula {

    private int id_pelicula;
    private byte [] caratula;
    private String titulo, genero, calificacion, duracion;

    public Pelicula(int id_pelicula, byte[] caratula, String titulo, String genero, String calificacion, String duracion) {
        this.id_pelicula = id_pelicula;
        this.caratula = caratula;
        this.titulo = titulo;
        this.genero = genero;
        this.calificacion = calificacion;
        this.duracion = duracion;
    }

    public int getId_pelicula() {
        return id_pelicula;
    }

    public byte[] getCaratula() {
        return caratula;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getGenero() {
        return genero;
    }

    public String getCalificacion() {
        return calificacion;
    }

    public String getDuracion() {
        return duracion;
    }


}
