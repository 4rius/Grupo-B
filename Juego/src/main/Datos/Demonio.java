package main.Datos;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

    public void generarEsbirros() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Cuantos esbirros quieres añadir");
        int tipo;
        int num = Integer.parseInt(System.console().readLine());
        for (int i = 0; i < num; i++) {
            System.out.println("Que esbirro quieres añadir");
            System.out.println("1.Demonio");
            System.out.println("2.Ghoul");
            System.out.println("3.Humano");
            do{
            tipo = Integer.parseInt(System.console().readLine());
            }while(tipo < 1 || tipo > 3);

            switch (tipo) {
                case 0 -> {
                    System.out.println("Escriba el nuevo nombre");
                    String n = br.readLine();
                    System.out.println("Escriba el pacto");
                    String pacto = br.readLine();
                    System.out.println("Introduzca la salud entre 1 y 5");
                    int salud;
                    do{
                        salud = Integer.parseInt(br.readLine());
                    } while (salud<1 || salud>5);
                    Esbirros.add(new Demonio(n, salud, pacto));
                    ((Demonio) Esbirros.get(Esbirros.size() - 1)).generarEsbirros();

                }
                case 1 -> {
                    System.out.println("Escriba el nuevo nombre");
                    String n = br.readLine();
                    System.out.println("Escriba la dependencia entre 1 y 5");
                    int dep;
                    do{
                        dep = Integer.parseInt(br.readLine());
                    } while (dep<1 || dep>5);
                    System.out.println("Introduzca la salud entre 1 y 5");
                    int salud;
                    do{
                        salud = Integer.parseInt(br.readLine());
                    } while (salud<1 || salud>5);
                    Esbirros.add(new Ghoul(n, salud, dep));

                }
                case 2 -> {
                    System.out.println("Escriba el nuevo nombre");
                    String n = br.readLine();
                    System.out.println("Introduzca la salud entre 1 y 5");
                    int salud;
                    do{
                        salud = Integer.parseInt(br.readLine());
                    } while (salud<1 || salud>5);
                    Esbirros.add(new Humano(n, salud));
                    int lealtad;
                    do{
                        lealtad = Integer.parseInt(br.readLine());
                    } while (lealtad<1 || lealtad>5);
                    switch (lealtad) {
                        case 1 -> ((Humano) Esbirros.get(Esbirros.size() - 1)).setNivellealtad(Humano.lealtad.low);
                        case 2 -> ((Humano) Esbirros.get(Esbirros.size() - 1)).setNivellealtad(Humano.lealtad.medium);
                        case 3 -> ((Humano) Esbirros.get(Esbirros.size() - 1)).setNivellealtad(Humano.lealtad.high);
                    }
                }
            }
        }
    }
}
