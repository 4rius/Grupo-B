import Aplicación.Multiplex;
import Aplicación.Operation;

import java.io.*;
import java.util.HashMap;

public class Usuario extends Operation {
    private static HashMap<String, Cliente> Clientes;
    private static HashMap<String, Operador> Operadores;

    public Usuario(Multiplex multiplex, HashMap<String, Cliente> clientes, HashMap<String, Operador> operadores) throws IOException, ClassNotFoundException {
        super(multiplex);

        File f = new File("assets/estado.bin");
        if(f.exists()){
            Usuario.deserialize();
        } else {
            this.Clientes = new HashMap<String, Cliente>();
            this.Operadores = new HashMap<String, Operador>();
        }
    }

    private static void deserialize() throws IOException, ClassNotFoundException {
        FileInputStream finputstream = new FileInputStream("assets/estado.bin");
        ObjectInputStream inputstream = new ObjectInputStream(finputstream);
        Usuario.Clientes = (HashMap<String, Cliente>) inputstream.readObject();
        Usuario.Operadores = (HashMap<String, Operador>) inputstream.readObject();
    }

    public static void serialize() throws IOException {
        FileOutputStream foutputstream = new FileOutputStream("assets/estado.bin");
        ObjectOutputStream outputstream = new ObjectOutputStream(foutputstream);
        outputstream.writeObject(Clientes);
        outputstream.writeObject(Operadores);
        outputstream.close();
    }

    public HashMap<String, Cliente> getClientes() {
        return Clientes;
    }

    public void setClientes(HashMap<String, Cliente> clientes) {
        Clientes = clientes;
    }

    public HashMap<String, Operador> getOperadores() {
        return Operadores;
    }

    public void setOperadores(HashMap<String, Operador> operadores) {
        Operadores = operadores;
    }

    @Override
    public void doOperation() {
        
    }
}
