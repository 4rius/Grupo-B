package Datos;

import java.io.Serializable;

public class Humano extends Esbirro implements Serializable {

    protected enum lealtad {
        low,
        medium,
        high
    }

    private lealtad nivellealtad;

    public Humano(String nombre, int salud) {
        super(nombre, salud);
    }

    public lealtad getNivellealtad() {
        return nivellealtad;
    }

    public void setNivellealtad(lealtad nivellealtad) {
        this.nivellealtad = nivellealtad;
    }
}
