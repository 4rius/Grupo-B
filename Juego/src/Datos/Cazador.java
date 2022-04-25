package Datos;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

public class Cazador extends Personaje implements Serializable {
    private int ptosvoluntad;
    private int edad;

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
        atk = this.getPoder() + this.ptosvoluntad + this.getHabilidadEspecial().getAtq();
        if (this.getArmaActual1()!=null) {
            atk = atk + this.getArmaActual1().getModataque();
        }
        if (this.getArmaActual2()!=null) {
            atk = atk + this.getArmaActual1().getModataque();
        }
        if (this.getArmaduraActual()!=null) {
            atk = atk + this.getArmaduraActual().getModataque();
        }
        return atk;
    }

    @Override
    public int defTotal() {
        int def = 0;
        def = this.getPoder() + this.ptosvoluntad + this.getHabilidadEspecial().getDfs();
        if (this.getArmaActual1()!=null) {
            def = def + this.getArmaActual1().getModdef();
        }
        if (this.getArmaActual2()!=null) {
            def = def + this.getArmaActual1().getModdef();
        }
        if (this.getArmaduraActual()!=null) {
            def = def + this.getArmaduraActual().getModdef();
        }
        return def;
    }
}