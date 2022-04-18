package Datos;

import java.io.IOException;
import java.util.List;

public class Licantropo extends Personaje{
    private int rabia;
    private Don don;

    public int getRabia() {
        return rabia;
    }

    public void setRabia(int rabia) {
        rabia = rabia;
    }

    @Override
    public int atkTotal(){
        int atk = 0;
        atk = this.getPoder() + this.don.getAtq() +
                this.getArmaActual1().getModataque() + getArmaActual2().getModataque() +
                this.getArmaduraActual().getModataque();
        if (this.rabia >= this.don.getMinRabia()){
            atk = atk + this.rabia;
        }
        return atk;
    }
}
