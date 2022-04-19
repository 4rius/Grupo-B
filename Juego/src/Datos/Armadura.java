package Datos;

import java.io.Serializable;

public class Armadura extends Equipo implements Serializable {

    public Armadura(String nombre, int modataque, int moddef) {
        super(nombre, modataque, moddef);
    }
}
