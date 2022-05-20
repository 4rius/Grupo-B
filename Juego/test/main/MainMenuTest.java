package main;

import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class MainMenuTest {

    @Test
    public void registrarJugador() throws IOException, ClassNotFoundException {
        Multiplex multiplex = new Multiplex(false);
        assertEquals(Multiplex.getClientes().size(), 0);
        Multiplex.getClientes().put("Nickname", new Cliente(null, "Prueba", "Prueba", "PR12UEB", "Contraseña"));
        assertEquals(Multiplex.getClientes().size(), 1);
        assertTrue(Multiplex.getClientes().containsKey("Nickname"));
    }

    @Test
    public void registrarOperador() throws IOException, ClassNotFoundException {
        Multiplex multiplex = new Multiplex(false);
        assertEquals(Multiplex.getOperadores().size(), 0);
        Multiplex.getOperadores().put("Nickname", new Operador(null, "Prueba", "Contraseña"));
        assertEquals(Multiplex.getOperadores().size(), 1);
        assertTrue(Multiplex.getOperadores().containsKey("Nickname"));
    }
}