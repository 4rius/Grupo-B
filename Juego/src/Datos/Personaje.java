package Datos;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Personaje implements Serializable {
    private String nombre;
    private int oro;
    private Arma armaActual1;
    private Arma armaActual2;
    private Armadura armaduraActual;
    private int salud;
    private int poder;
    private Modificador modificador;
    private ArrayList<Esbirro> esbirros;
    private Habilidad habilidadEspecial;

    public String getNombre() {
        return nombre;
    }

    public int getOro() {
        return oro;
    }


    public Arma getArmaActual1() {
        return armaActual1;
    }
    public Arma getArmaActual2() {
        return armaActual2;
    }

    public Armadura getArmaduraActual() {
        return armaduraActual;
    }

    public int getSalud() {
        return salud;
    }

    public int getPoder() {
        return poder;
    }

    public Modificador getModificador() {
        return modificador;
    }

    public List<Esbirro> getEsbirros() {
        return esbirros;
    }

    public Habilidad getHabilidadEspecial() {
        return habilidadEspecial;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setOro(int oro) {
        this.oro = oro;
    }

    public void addOro(int o){
        this.oro = this.oro + o;
    }

    public void setArmaActual1(Arma armaActual1) {
        this.armaActual1 = armaActual1;
    }
    public void setArmaActual2(Arma armaActual2) {
        this.armaActual2 = armaActual2;
    }

    public void setArmaduraActual(Armadura armaduraActual) {
        this.armaduraActual = armaduraActual;
    }

    public void setSalud(int salud) {
        this.salud = salud;
    }

    public void setPoder(int poder) {
        this.poder = poder;
    }

    public void setModificador(Modificador modificador) {
        this.modificador = modificador;
    }

    public void setEsbirros(ArrayList<Esbirro> esbirros) {
        this.esbirros = esbirros;
    }

    public void setHabilidadEspecial(Habilidad habilidadEspecial) {
        this.habilidadEspecial = habilidadEspecial;
    }

    public int atkTotal() {
        return 0;
    }

    public int defTotal() {
        return 0;
    }

    public void recibirAtk() {
    }

    public void golpearAtk() {
    }

    public void resetPuntosHab(){
    }

    public void generarEsbirros() {
        int cantidad = (int) (Math.random() * 5) + 1; // Se generan entre 1 y 5 esbirros
        for (int i = 0; i < cantidad; i++) {
            int tipo = (int) (Math.random() * 3);  //se elige entre 3 tipos de esbirros
            switch (tipo) {
                case 0 -> {
                    esbirros.add(new Demonio("Demon", 5, "Cruz de sangre"));
                    ((Demonio) esbirros.get(esbirros.size() - 1)).generarEsbirros();
                }
                case 1 -> esbirros.add(new Ghoul("Ghoul", 5, (int) (Math.random() * 5)));
                case 2 -> {
                    esbirros.add(new Humano("Humano", 5));
                    int lealtad = (int) (Math.random() * 3);
                    switch (lealtad) {
                        case 0 -> ((Humano) esbirros.get(esbirros.size() - 1)).setNivellealtad(Humano.lealtad.low);
                        case 1 -> ((Humano) esbirros.get(esbirros.size() - 1)).setNivellealtad(Humano.lealtad.medium);
                        case 2 -> ((Humano) esbirros.get(esbirros.size() - 1)).setNivellealtad(Humano.lealtad.high);
                    }
                }
            }
            }
    }
}
