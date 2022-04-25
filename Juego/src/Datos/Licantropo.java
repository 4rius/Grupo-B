package Datos;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

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
}
