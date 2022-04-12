package Datos;

public class Esbirro {
    private String Nombre;
    private int Salud;

    public Esbirro(String nombre, int salud) {
        Nombre = nombre;
        Salud = salud;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public int getSalud() {
        return Salud;
    }

    public void setSalud(int salud) {
        Salud = salud;
    }
}
