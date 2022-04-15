import Datos.Arma;
import Datos.Armadura;
import Datos.Equipo;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.*;


public class Multiplex {
    private boolean modo;
    private static HashMap<String, Cliente> clientes; //String del tipo LNNLL
    private static ArrayList<String> nicknames; //Hace falta porque no se puede acceder al atributo de un objeto en un hashmap
    private static HashMap<String, Operador> operadores;
    private static ArrayList<Equipo> inventario;
    private static ArrayList<PerformCombat> desafios;


    public Multiplex(boolean modo) throws IOException, ClassNotFoundException {
        this.modo = modo;
        inventario = new ArrayList<Equipo>();

        File f = new File("Assets/estado.bin");
        if(f.exists()){
            Multiplex.deserialize();
        } else {
            Multiplex.clientes = new HashMap<>(clientes);
            Multiplex.nicknames = new ArrayList<>(nicknames);
            Multiplex.operadores = new HashMap<>(operadores);
            Multiplex.desafios = new ArrayList<PerformCombat>(desafios);
        }

        this.inicializarInventario();
    }

    private static void deserialize() throws IOException, ClassNotFoundException {
        FileInputStream finputstream = new FileInputStream("Assets/estado.bin");
        ObjectInputStream inputstream = new ObjectInputStream(finputstream);
        Multiplex.clientes = (HashMap<String, Cliente>) inputstream.readObject();
        Multiplex.nicknames = (ArrayList<String>) inputstream.readObject();
        Multiplex.operadores = (HashMap<String, Operador>) inputstream.readObject();
        Multiplex.desafios = (ArrayList<PerformCombat>) inputstream.readObject();
        inputstream.close();
    }

    public static void serialize() throws IOException {
        FileOutputStream foutputstream = new FileOutputStream("Assets/estado.bin");
        ObjectOutputStream outputstream = new ObjectOutputStream(foutputstream);
        outputstream.writeObject(Multiplex.clientes);
        outputstream.writeObject(Multiplex.nicknames);
        outputstream.writeObject(Multiplex.operadores);
        outputstream.writeObject(Multiplex.desafios);
        outputstream.close();
    }

    public ArrayList<Equipo> getInventario() {
        return inventario;
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

    public static ArrayList<PerformCombat> getDesafios() {
        return desafios;
    }

    public static ArrayList<String> getNicknames() {
        return nicknames;
    }

    public void Start() throws IOException {
        System.out.println("Not yet!");
        this.inicializarInventario();
        MainMenu menu = new MainMenu(this);
        menu.doOperation();
    }

    private void inicializarInventario() throws IOException {
        File f = new File("Assets/Armas.txt");
        File f2 = new File("Assets/Armadura.txt");
        Scanner sc = new Scanner(f);
        Pattern p1 = Pattern.compile("[a-zA-Z]");
        Pattern p2 = Pattern.compile("[0-9]");
        while (sc.hasNextLine()) {
            String nombre = sc.findInLine(p1);
            sc.nextLine();
            String atq = sc.findInLine(p2);
            sc.nextLine();
            String def = sc.findInLine(p2);
            sc.nextLine();
            String dosManos = sc.findInLine(p2);
            sc.nextLine();
            Arma a = new Arma(nombre, Integer.parseInt(atq), Integer.parseInt(def), Boolean.parseBoolean(dosManos));
            inventario.add(a);
        }
        sc.close();
        Scanner sc2 = new Scanner(f2);
        while (sc2.hasNextLine()) {
            String nombre = sc2.findInLine(p1);
            sc2.nextLine();
            String atq = sc2.findInLine(p2);
            sc2.nextLine();
            String def = sc2.findInLine(p2);
            sc2.nextLine();
            Armadura a = new Armadura(nombre, Integer.parseInt(atq), Integer.parseInt(def));
            inventario.add(a);
        }
        sc2.close();

    }

}
