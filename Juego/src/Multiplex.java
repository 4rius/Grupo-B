import Datos.Equipo;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;

public class Multiplex {
    private boolean modo;
    private static HashSet<Cliente> clientes;
    private static HashSet<Operador> operadores;
    private static ArrayList<Combate> combates;
    private static HashSet<String> numerosderegistro; //hace falta para determinar si es un operador o cliente
    private static ArrayList<Equipo> inventarios;


    public Multiplex(boolean modo) throws IOException, ClassNotFoundException {
        this.modo = modo;

        File f = new File("Assets/estado.bin");
        if(f.exists()){
            Multiplex.deserialize();
        } else {
            Multiplex.clientes = new HashSet<Cliente>(clientes);
            Multiplex.operadores = new HashSet<Operador>(operadores);
            Multiplex.combates = new ArrayList<Combate>(combates);
            Multiplex.numerosderegistro = new HashSet<String>(numerosderegistro);
        }
    }

    private static void deserialize() throws IOException, ClassNotFoundException {
        FileInputStream finputstream = new FileInputStream("Assets/estado.bin");
        ObjectInputStream inputstream = new ObjectInputStream(finputstream);
        Multiplex.clientes = (HashSet<Cliente>) inputstream.readObject();
        Multiplex.operadores = (HashSet<Operador>) inputstream.readObject();
        Multiplex.combates = (ArrayList<Combate>) inputstream.readObject();
        Multiplex.numerosderegistro = (HashSet<String>) inputstream.readObject();
        inputstream.close();
    }

    public static void serialize() throws IOException {
        FileOutputStream foutputstream = new FileOutputStream("Assets/estado.bin");
        ObjectOutputStream outputstream = new ObjectOutputStream(foutputstream);
        outputstream.writeObject(Multiplex.clientes);
        outputstream.writeObject(Multiplex.operadores);
        outputstream.writeObject(Multiplex.combates);
        outputstream.writeObject(Multiplex.numerosderegistro);
        outputstream.close();
    }

    public boolean isModo() {
        return modo;
    }

    public void setModo(boolean modo) {
        this.modo = modo;
    }

    public static HashSet<Cliente> getClientes() {
        return clientes;
    }

    public static HashSet<Operador> getOperadores() {
        return operadores;
    }

    public static ArrayList<Combate> getCombates() {
        return combates;
    }

    public static HashSet<String> getNumerosderegistro() {
        return numerosderegistro;
    }

    public void Start(){
        System.out.println("Not yet!");
    }
}
