import Aplicación.Multiplex;
import Aplicación.Operation;

import java.util.HashMap;

public class Usuario extends Operation {
    private HashMap<String, Cliente> Clientes;
    private HashMap<String, Operador> Operadores;

    public Usuario(Multiplex multiplex, HashMap<String, Cliente> clientes, HashMap<String, Operador> operadores) {
        super(multiplex);
        Clientes = clientes;
        Operadores = operadores;
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
