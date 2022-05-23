package main.Datos;

import java.io.Serializable;

public class Licantropo extends Personaje implements Serializable {
    private int rabia;

    public int getRabia() {
        return rabia;
    }

    public void setRabia(int rabia) {
        rabia = rabia;
    }

    @Override
    public int atkTotal(){
        int atk = 0;
        atk = this.getPoder() + this.getHabilidadEspecial().getAtq();
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
        if (this.rabia >= ((Don)this.getHabilidadEspecial()).getMinRabia()){
            atk = atk + this.rabia;
        }
        return atk;
    }

    @Override
    public int defTotal(){
        int def = 0;
        def = this.getPoder() + this.getHabilidadEspecial().getDfs();
        for (Modificador mod : this.getModificadores()) {
            if (mod.isTipomod() == 1) {
                def += mod.getMod();
            }
        }
        if (this.getArmaActual1()!=null) {
            def = def + this.getArmaActual1().getModataque();
        }
        if (this.getArmaActual2()!=null) {
            def = def + this.getArmaActual1().getModataque();
        }
        if (this.getArmaduraActual()!=null) {
            def = def + this.getArmaduraActual().getModataque();
        }
        if (this.rabia >= ((Don)this.getHabilidadEspecial()).getMinRabia()){
            def = def + this.rabia;
        }
        return def;
    }

    @Override
    public void recibirAtk() {
        if (rabia < 3) {
            rabia += 1;
        }
    }

    @Override
    public void golpearAtk() {
    }

    @Override
    public void resetPuntosHab(){
        rabia = 0;
    }
}
