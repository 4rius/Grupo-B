package Datos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Personaje {
    private String nombre;
    private int oro;
    private ArrayList<Equipo> inventario;
    private Arma armaActual1;
    private Arma armaActual2;
    private Armadura armaduraActual;
    private int salud;
    private int poder;
    private ArrayList<Modificador> modificadores;
    private ArrayList<Esbirro> esbirros;
    private Habilidad habilidadEspecial;

    public String getNombre() {
        return nombre;
    }

    public int getOro() {
        return oro;
    }

    public List<Equipo> getInventario() {
        return inventario;
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

    public void setInventario(ArrayList<Equipo> inventario) {
        this.inventario = inventario;
    }

    public void setArmaActual(Arma armaActual) {
        this.armaActual1 = armaActual;
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
