package main.Datos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.lang.module.ModuleFinder;
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
    private ArrayList<Modificador> modificadores;
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

    public void setEsbirros(ArrayList<Esbirro> esbirros) {
        this.esbirros = esbirros;
    }

    public void setModificadores(ArrayList<Modificador> modificadores) {
        this.modificadores = modificadores;
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

    public void generarEsbirros() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Cuantos esbirros quieres añadir");
        int tipo;
        int num = Integer.parseInt(br.readLine());
        for (int i = 0; i < num; i++) {
            System.out.println("Que esbirro quieres añadir");
            System.out.println("0.Demonio");
            System.out.println("1.Ghoul");
            System.out.println("2.Humano");
            do{
                tipo = Integer.parseInt(br.readLine());
            }while(tipo < 0 || tipo > 2);

            switch (tipo) {
                case 0 -> {
                    System.out.println("Escriba el nuevo nombre");
                    String n = br.readLine();
                    System.out.println("Escriba el pacto");
                    String pacto = br.readLine();
                    System.out.println("Introduzca la salud entre 1 y 5");
                    int salud = Integer.parseInt(br.readLine());
                    if (salud < 1 || salud > 5) {
                        salud = 1;
                    }
                    esbirros.add(new Demonio(n, salud, pacto));
                    ((Demonio) esbirros.get(esbirros.size() - 1)).generarEsbirros();

                }
                case 1 -> {
                    System.out.println("Escriba el nuevo nombre");
                    String n = br.readLine();
                    System.out.println("Escriba la dependencia entre 1 y 5");
                    int dep = Integer.parseInt(br.readLine());
                    if (dep > 5 || dep < 1) {
                        dep = 1;
                    }
                    System.out.println("Introduzca la salud entre 1 y 5");
                    int salud = Integer.parseInt(br.readLine());
                    if (salud > 5 || salud < 1) {
                        salud = 1;
                    }
                    esbirros.add(new Ghoul(n, salud, dep));

                }
                case 2 -> {
                    System.out.println("Escriba el nuevo nombre");
                    String n = br.readLine();
                    System.out.println("Introduzca la salud entre 1 y 5");
                    int salud;
                    do {
                        salud = Integer.parseInt(br.readLine());
                    } while (salud < 1 || salud > 5);
                    esbirros.add(new Humano(n, salud));
                    System.out.println("Lealtad entre 1 y 3");
                    int lealtad = Integer.parseInt(br.readLine());
                    if (lealtad < 1 || lealtad > 3) {
                        lealtad = 1;
                    }
                    switch (lealtad) {
                        case 1 -> ((Humano) esbirros.get(esbirros.size() - 1)).setNivellealtad(Humano.lealtad.low);
                        case 2 -> ((Humano) esbirros.get(esbirros.size() - 1)).setNivellealtad(Humano.lealtad.medium);
                        case 3 -> ((Humano) esbirros.get(esbirros.size() - 1)).setNivellealtad(Humano.lealtad.high);
                    }
                }
            }
        }
    }
}
