package Datos;

import java.io.IOException;
import java.util.List;

public class Cazador extends Personaje {
    private int ptosvoluntad;
    private int edad;

    private Talento talento;

    public int getPtosvoluntad() {
        return ptosvoluntad;
    }

    public void setPtosvoluntad(int ptosvoluntad) {
        this.ptosvoluntad = ptosvoluntad;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public int atkTotal() {
        int atk = 0;
        atk = this.getPoder() + this.ptosvoluntad + this.talento.getAtq() +
                this.getArmaActual1().getModataque() + this.getArmaActual2().getModataque() +
                this.getArmaduraActual().getModataque();
        return atk;
    }

    @Override
    public int defTotal() {
        int def = 0;
        def = this.getPoder() + this.ptosvoluntad + this.talento.getDfs() +
                this.getArmaActual1().getModdef() + this.getArmaActual2().getModdef() +
                this.getArmaduraActual().getModdef();
        return def;
    }
}