package Datos;

import java.util.List;

public class Demonio extends Esbirro{
    private String Pacto;
    private List<Esbirro> Esbirros;

    public Demonio(String nombre, int salud, String pacto, List<Esbirro> esbirros) {
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

    public void setEsbirros(List<Esbirro> esbirros) {
        Esbirros = esbirros;
    }
}
