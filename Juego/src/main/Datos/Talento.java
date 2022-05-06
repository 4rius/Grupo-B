package main.Datos;

import java.io.Serializable;

public class Talento extends Habilidad implements Serializable {

    private int edad;
    public Talento(String nombre, int atq, int dfs, int edad) {
        super(nombre, atq, dfs);
    }
}
