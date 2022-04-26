package Datos;

import java.io.Serializable;


public class Vampiro extends Personaje implements Serializable {
    private int ptossangre;
    private int edad;



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
        if (this.ptossangre >= 5){
            atk = atk + 2;
        }
        return atk;
    }

    @Override
    public int defTotal(){
        int def = 0;
        def = this.getPoder() + this.getHabilidadEspecial().getAtq();

        if (this.getArmaActual1()!=null) {
            def = def + this.getArmaActual1().getModdef();
        }
        if (this.getArmaActual2()!=null) {
            def = def + this.getArmaActual1().getModdef();
        }
        if (this.getArmaduraActual()!=null) {
            def = def + this.getArmaduraActual().getModdef();
        }
        if (this.ptossangre >= 5){
            ptossangre = ptossangre - ((Disciplina)this.getHabilidadEspecial()).getCosteSangre();
            def = def + 2;
        }
        return def;
    }

    @Override
    public void golpearAtk(){
        if (ptossangre <= 6) {
            ptossangre += 4;
        }
    }

    @Override
    public void recibirAtk(){
    }

    @Override
    public void resetPuntosHab(){
        ptossangre = 0;
    }
}
