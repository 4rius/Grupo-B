package Datos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Personaje {
    private String nombre;
    private int oro;
    private Arma armaActual1;
    private Arma armaActual2;
    private Armadura armaduraActual;
    private int salud;
    private int poder;
    private ArrayList<Modificador> modificadores = new ArrayList<>();
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

    public ArrayList<Modificador> getModificadores() {
        return modificadores;
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

    public void setModificadores(ArrayList<Modificador> modificadores) {
        this.modificadores = modificadores;
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
}
