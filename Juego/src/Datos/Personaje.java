package Datos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Personaje {
    private String nombre;
    private int oro;
    private ArrayList<Equipo> inventario;
    private Arma armaActual;
    private Armadura armaduraActual;
    private int salud;
    private int poder;
    private ArrayList<Modificador> modificadores;
    private ArrayList<Esbirro> esbirros;
    private Habilidad habilidadEspecial;

    public Personaje(String nombre, int oro, List<Equipo> inventario, Arma armaActual, Armadura armaduraActual, int salud, int poder, List<Modificador> modificadores, List<Esbirro> esbirros, Habilidad habilidadEspecial) throws IOException {
        this.nombre = System.console().readLine();
        this.oro = 500;
        this.inventario = new ArrayList<Equipo>();
        this.armaActual = null;
        this.armaduraActual = null;
        this.salud = 5;
        this.poder = (int) (Math.random() * 4 + 1);
        this.modificadores = new ArrayList<Modificador>();
        this.esbirros = new ArrayList<Esbirro>();
        this.habilidadEspecial = null;
    }

    public String getNombre() {
        return nombre;
    }

    public int getOro() {
        return oro;
    }

    public List<Equipo> getInventario() {
        return inventario;
    }

    public Arma getArmaActual() {
        return armaActual;
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

    public List<Modificador> getModificadores() {
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
        this.armaActual = armaActual;
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
}
