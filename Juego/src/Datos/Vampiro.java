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
        atk = this.getPoder() + this.getHabilidadEspecial().getAtq() +
                this.getArmaActual1().getModataque() + this.getArmaActual2().getModataque() +
                this.getArmaduraActual().getModataque();
        if (this.ptossangre >= 5){
            atk = atk + 2;
        }
        return atk;
    }

    @Override
    public int defTotal(){
        int def = 0;
        def = this.getPoder() + this.getHabilidadEspecial().getDfs()+
                this.getArmaActual1().getModdef() + this.getArmaActual2().getModdef() +
                this.getArmaduraActual().getModdef();
        if (this.ptossangre >= 5){
            ptossangre = ptossangre - ((Disciplina)this.getHabilidadEspecial()).getCosteSangre();
            def = def + 2;
        }
        return def;
    }


}
