package Datos;

import java.io.IOException;
import java.util.List;

public class Vampiro extends Personaje{
    private int ptossangre;
    private int edad;
    private Disciplina disciplina;


    public int getPtossangre() {
        return ptossangre;
    }

    public void setPtossangre(int ptossangre) {
        this.ptossangre = ptossangre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public int atkTotal(){
        int atk = 0;
        atk = this.getPoder() + this.disciplina.getAtq() +
                this.getArmaActual1().getModataque() + this.getArmaActual2().getModataque() +
                this.getArmaduraActual().getModataque();
        if (this.ptossangre >= 5){
            ptossangre = ptossangre - this.disciplina.getCosteSangre();
            atk = atk + 2;
        }
        return atk;
    }
}