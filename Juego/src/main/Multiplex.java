package main;

import main.Datos.Arma;
import main.Datos.Armadura;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;


public class Multiplex implements Serializable {
    private boolean modo;
    private static HashMap<String, Cliente> clientes; //String del tipo LNNLL
    private static ArrayList<String> registros; //Hace falta porque no se puede acceder al atributo de un objeto en un hashmap
    private static HashMap<String, Operador> operadores;
    private static ArrayList<Arma> listaArmas;
    private static ArrayList<Armadura> listaArmaduras;
    private static ArrayList<Combate> desafios;


    public Multiplex(boolean modo) throws IOException, ClassNotFoundException {
        this.modo = modo;
        listaArmas = new ArrayList<>();
        listaArmaduras = new ArrayList<>();

        File f = new File("././Assets/estado.bin");
        if(f.exists()){
            Multiplex.deserialize();
        } else {
            Multiplex.clientes = new HashMap<>();
            Multiplex.registros = new ArrayList<>();
            Multiplex.operadores = new HashMap<>();
            Multiplex.desafios = new ArrayList<>();
        }

        this.inicializarInventario();
    }

    public static void deserialize() throws IOException, ClassNotFoundException {
        FileInputStream finputstream = new FileInputStream("././Assets/estado.bin");
        ObjectInputStream inputstream = new ObjectInputStream(finputstream);
        Multiplex.clientes = (HashMap<String, Cliente>) inputstream.readObject();
        Multiplex.registros = (ArrayList<String>) inputstream.readObject();
        Multiplex.operadores = (HashMap<String, Operador>) inputstream.readObject();
        Multiplex.desafios = (ArrayList<Combate>) inputstream.readObject();
        inputstream.close();
    }

    public static void serialize() throws IOException {
        FileOutputStream foutputstream = new FileOutputStream("././Assets/estado.bin");
        ObjectOutputStream outputstream = new ObjectOutputStream(foutputstream);
        outputstream.writeObject(Multiplex.clientes);
        outputstream.writeObject(Multiplex.registros);
        outputstream.writeObject(Multiplex.operadores);
        outputstream.writeObject(Multiplex.desafios);
        outputstream.close();
    }

    public static ArrayList<Arma> getListaArmas() {
        return listaArmas;
    }

    public static ArrayList<Armadura> getListaArmaduras() {
        return listaArmaduras;
    }

    public boolean isModo() {
        return modo;
    }

    public void setModo(boolean modo) {
        this.modo = modo;
    }

    public static HashMap<String, Cliente> getClientes() {
        return clientes;
    }

    public static HashMap<String, Operador> getOperadores() {
        return operadores;
    }

    public static ArrayList<Combate> getDesafios() {
        return desafios;
    }

    public static ArrayList<String> getRegistros() {
        return registros;
    }

    public void Start() throws IOException {
        System.out.println("Halfway through!");

        MainMenu menu = new MainMenu(this);
        menu.doOperation();
    }

    public void inicializarInventario() throws IOException {
        File f = new File("./././Assets/Armas.txt");
        File f2 = new File("./././Assets/Armadura.txt");
        Scanner sc = new Scanner(f);
        Pattern p1 = Pattern.compile(" [\\w\\s]+");
        Pattern p2 = Pattern.compile("[0-9]");

        while (sc.hasNextLine()) {
            String nombre = sc.findInLine(p1);
            nombre = nombre.trim();
            sc.nextLine();
            String atq = sc.findInLine(p2);
            sc.nextLine();
            String def = sc.findInLine(p2);
            sc.nextLine();
            String dosManos = sc.findInLine(p1);
            dosManos=dosManos.trim();
            if(sc.hasNextLine()) {
                sc.nextLine();
                sc.nextLine();
            }
            Arma a = new Arma(nombre, Integer.parseInt(atq), Integer.parseInt(def), Boolean.parseBoolean(dosManos));
            listaArmas.add(a);
        }
        sc.close();
        Scanner sc2 = new Scanner(f2);
        while (sc2.hasNextLine()) {
            String nombre = sc2.findInLine(p1);
            sc2.nextLine();
            String atq = sc2.findInLine(p2);
            sc2.nextLine();
            String def = sc2.findInLine(p2);
            if(sc2.hasNextLine()) {
                sc2.nextLine();
                sc2.nextLine();
            }
            Armadura a = new Armadura(nombre, Integer.parseInt(atq), Integer.parseInt(def));
            listaArmaduras.add(a);
        }
        sc2.close();

    }


}
