package Datos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Demonio extends Esbirro implements Serializable {
    private String Pacto;
    private ArrayList<Esbirro> Esbirros;

    public Demonio(String nombre, int salud, String pacto, ArrayList<Esbirro> esbirros) {
        super(nombre, salud);
        Pacto = pacto;
        Esbirros = esbirros;
    }

    @Override
    public int getSalud(){
        int hp = 0;
        ArrayList<Esbirro> eList = new ArrayList(this.getEsbirros());
        for (Esbirro e: eList) {
             hp = hp + e.getSalud();
        }
        return hp;
    }

    public String getPacto() {
        return Pacto;
    }

    public void setPacto(String pacto) {
        Pacto = pacto;
    }

    public List<Esbirro> getEsbirros() {
        return Esbirros;
    }

    public void setEsbirros(ArrayList<Esbirro> esbirros) {
        Esbirros = esbirros;
    }
}
