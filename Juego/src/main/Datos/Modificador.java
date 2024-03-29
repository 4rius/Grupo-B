package main.Datos;

import java.io.Serializable;

public class Modificador implements Serializable {
    private String Nombre;
    private int mod;
    private int tipomod; //Debilidad o fortaleza

    public Modificador(String nombre, int mod, int tipomod) {
        Nombre = nombre;
        this.mod = mod;
        this.tipomod = tipomod;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public int getMod() {
        return mod;
    }

    public void setMod(int mod) {
        this.mod = mod;
    }

    public int isTipomod() {
        return tipomod;
    }

    public void setTipomod(int tipomod) {
        this.tipomod = tipomod;
    }
}
