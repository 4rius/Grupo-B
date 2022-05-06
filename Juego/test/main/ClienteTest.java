package main;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    @Test
    void registrarPersonaje() throws IOException, ClassNotFoundException {
        Multiplex multiplex = new Multiplex(false);
        Multiplex.getClientes().put("Prueba", new Cliente(null, "Prueba", "nick", "PR12UEB", "123"));
        Cliente cliente = Multiplex.getClientes().get("Prueba");
        cliente.registrarPersonaje();
        assertEquals(cliente.getPersonaje() != null, true);
    }

    @Test
    void crearDesafio() {
    }

    @Test
    void suscribirse() {
    }

    @Test
    void eliminarCuenta() {
    }
}