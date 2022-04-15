package Datos;

import java.util.ArrayList;
import java.util.List;

public class Demonio extends Esbirro{
    private String Pacto;
    private ArrayList<Esbirro> Esbirros;

    public Demonio(String nombre, int salud, String pacto, ArrayList<Esbirro> esbirros) {
        super(nombre, salud);
        Pacto = pacto;
        Esbirros = esbirros;
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
