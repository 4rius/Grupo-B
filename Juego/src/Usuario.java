import Aplicación.Multiplex;
import Aplicación.Operation;

import java.util.List;

public class Usuario extends Operation {
    private List<Cliente> Clientes;
    private List<Operador> Operadores;

    public Usuario(Multiplex multiplex, List<Cliente> clientes, List<Operador> operadores) {
        super(multiplex);
        Clientes = clientes;
        Operadores = operadores;
    }

    public List<Cliente> getClientes() {
        return Clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        Clientes = clientes;
    }

    public List<Operador> getOperadores() {
        return Operadores;
    }

    public void setOperadores(List<Operador> operadores) {
        Operadores = operadores;
    }

    @Override
    public void doOperation() {
        
    }
}
