package main;

import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class MainMenuTest {

    @Test
    @org.junit.Test
    public void registrarJugador() throws IOException, ClassNotFoundException {
        Multiplex multiplex = new Multiplex(false);
        MainMenu mainMenu = new MainMenu(multiplex);
        Multiplex.getClientes().put("Nickname", new Cliente(null, "Prueba", "Prueba", "PR12UEB", "Contraseña"));
        assertEquals(Multiplex.getClientes().size(), 1);
    }

    @org.junit.Test
    public void registrarOperador() throws IOException, ClassNotFoundException {
        Multiplex multiplex = new Multiplex(false);
        multiplex.Start();
        MainMenu mainMenu = new MainMenu(multiplex);
        mainMenu.registrarOperador();
        assert (Multiplex.getOperadores().size() == 1);
    }
}