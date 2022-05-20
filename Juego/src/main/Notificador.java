package main;

import java.io.Serializable;
import java.util.ArrayList;

final class Notificador implements Serializable {
    private final ArrayList<Cliente> clientes;

    public Notificador() {
        clientes = new ArrayList<>();
    }

    public void notificar(String mensaje) {
        for (Cliente cliente : clientes) {
            cliente.getSuscripciones().add(mensaje);
        }
    }

    public void agregarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }
}
