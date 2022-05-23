package main.Datos;

import java.io.Serializable;

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
        for (Modificador mod : this.getModificadores()) {
            if (mod.isTipomod() == 0) {
                atk += mod.getMod();
            }
        }
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
        for (Modificador mod : this.getModificadores()) {
            if (mod.isTipomod() == 1) {
                def += mod.getMod();
            }
        }
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

    @Override
    public void recibirAtk() {
        if (ptosvoluntad < 3) {
            ptosvoluntad += 1;
        }
    }

    @Override
    public void golpearAtk() {
    }

    @Override
    public void resetPuntosHab(){
        ptosvoluntad = 0;
    }
}