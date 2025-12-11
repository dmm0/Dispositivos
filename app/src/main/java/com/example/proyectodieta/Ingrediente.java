package com.example.proyectodieta;

public class Ingrediente {

    private int idIngrediente;
    private String nombre;
    private int consumido;

    public Ingrediente(int idIngrediente, String nombre, int consumido) {
        this.idIngrediente = idIngrediente;
        this.nombre = nombre;
        this.consumido = consumido;
    }

    public int getIdIngrediente() {
        return idIngrediente;
    }

    public void setIdIngrediente(int idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getConsumido() {
        return consumido;
    }

    public void setConsumido(int consumido) {
        this.consumido = consumido;
    }
}

