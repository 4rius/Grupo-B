package Datos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Demonio extends Esbirro implements Serializable {
    private String Pacto;
    private ArrayList<Esbirro> Esbirros;

    public Demonio(String nombre, int salud, String pacto) {
        super(nombre, salud);
        Pacto = pacto;
        Esbirros = new ArrayList<>();
    }

    @Override
    public int getSalud(){
        int hp = 0;
        ArrayList<Esbirro> eList = (ArrayList<Esbirro>) this.getEsbirros();
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

    public void generarEsbirros() {
        int cantidad = (int) (Math.random() * 3) + 1; //se generan entre 1 y 3 esbirros
        for (int i = 0; i < cantidad; i++) {
            int tipo = (int) (Math.random() * 3);
            switch (tipo) {
                case 0 -> {
                    Esbirros.add(new Demonio("Demon", 5, "Cruz de sangre"));
                    ((Demonio) Esbirros.get(Esbirros.size() - 1)).generarEsbirros();
                }
                case 1 -> Esbirros.add(new Ghoul("Ghoul", 5, (int) (Math.random() * 5)));
                case 2 -> {
                    Esbirros.add(new Humano("Humano", 5));
                    int lealtad = (int) (Math.random() * 3);
                    switch (lealtad) {
                        case 0 -> ((Humano) Esbirros.get(Esbirros.size() - 1)).setNivellealtad(Humano.lealtad.low);
                        case 1 -> ((Humano) Esbirros.get(Esbirros.size() - 1)).setNivellealtad(Humano.lealtad.medium);
                        case 2 -> ((Humano) Esbirros.get(Esbirros.size() - 1)).setNivellealtad(Humano.lealtad.high);
                    }
                }
            }
        }
    }
}
