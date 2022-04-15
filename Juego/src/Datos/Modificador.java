package Datos;

public class Modificador {
    private String Nombre;
    private int mod;
    private int tipomod;

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
