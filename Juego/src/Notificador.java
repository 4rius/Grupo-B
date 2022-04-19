import java.util.ArrayList;

public class Notificador {
    private ArrayList<Cliente> clientes;

    public Notificador() {
        clientes = new ArrayList<Cliente>();
    }

    public void notificar(String mensaje) {
        for (Cliente cliente : clientes) {
            cliente.recibirNotificacion(mensaje);
        }
    }

    public void agregarCliente(Cliente cliente) {
        clientes.add(cliente);
    }



}
