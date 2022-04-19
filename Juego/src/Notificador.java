import java.io.Serializable;
import java.util.ArrayList;

public class Notificador implements Serializable {
    private ArrayList<Cliente> clientes;

    public Notificador() {
        clientes = new ArrayList<>();
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
