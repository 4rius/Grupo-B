package Datos;

import java.io.Serializable;

public class Equipo implements Serializable {
    private String Nombre;
    private int modataque;
    private int moddef;

    public Equipo(String nombre, int modataque, int moddef) {
        Nombre = nombre;
        this.modataque = modataque;
        this.moddef = moddef;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public int getModataque() {
        return modataque;
    }

    public void setModataque(int modataque) {
        this.modataque = modataque;
    }

    public int getModdef() {
        return moddef;
    }

    public void setModdef(int moddef) {
        this.moddef = moddef;
    }
}
