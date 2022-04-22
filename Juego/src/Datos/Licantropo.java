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
        atk = this.getPoder() + this.getHabilidadEspecial().getAtq() +
                this.getArmaActual1().getModataque() + getArmaActual2().getModataque() +
                this.getArmaduraActual().getModataque();
        if (this.rabia >= ((Don)this.getHabilidadEspecial()).getMinRabia()){
            atk = atk + this.rabia;
        }
        return atk;
    }

    @Override
    public int defTotal(){
        int def = 0;
        def = this.getPoder() + this.getHabilidadEspecial().getDfs() +
                this.getArmaActual1().getModdef() + getArmaActual2().getModdef() +
                this.getArmaduraActual().getModdef();
        if (this.rabia >= ((Don)this.getHabilidadEspecial()).getMinRabia()){
            def = def + this.rabia;
        }
        return def;
    }
}
