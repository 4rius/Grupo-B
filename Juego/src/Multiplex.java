import Datos.Equipo;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.File;

public class Multiplex {
    private boolean modo;
    private static HashMap<String, Cliente> clientes; //String del tipo LNNLL
    private static HashMap<String, Operador> operadores;
    private static ArrayList<Combate> combates;
    private static ArrayList<Equipo> inventario;
    private static ArrayList<PerformCombat> desafios;


    public Multiplex(boolean modo) throws IOException, ClassNotFoundException {
        this.modo = modo;

        File f = new File("assets/estado.bin");
        if(f.exists()){
            Multiplex.deserialize();
        } else {
            Multiplex.clientes = new HashMap<>(clientes);
            Multiplex.operadores = new HashMap<>(operadores);
            Multiplex.combates = new ArrayList<Combate>(combates);
        }
    }

    private static void deserialize() throws IOException, ClassNotFoundException {
        FileInputStream finputstream = new FileInputStream("assets/estado.bin");
        ObjectInputStream inputstream = new ObjectInputStream(finputstream);
        Multiplex.clientes = (HashMap<String, Cliente>) inputstream.readObject();
        Multiplex.operadores = (HashMap<String, Operador>) inputstream.readObject();
        Multiplex.combates = (ArrayList<Combate>) inputstream.readObject();
        inputstream.close();
    }

    public static void serialize() throws IOException {
        FileOutputStream foutputstream = new FileOutputStream("assets/estado.bin");
        ObjectOutputStream outputstream = new ObjectOutputStream(foutputstream);
        outputstream.writeObject(Multiplex.clientes);
        outputstream.writeObject(Multiplex.operadores);
        outputstream.writeObject(Multiplex.combates);
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

    public static ArrayList<Combate> getCombates() {
        return combates;
    }

    public static ArrayList<PerformCombat> getDesafios() {
        return desafios;
    }

    public void Start(){
        System.out.println("Not yet!");
    }
}
