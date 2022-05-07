package main;

import main.Datos.Disciplina;
import main.Datos.Modificador;
import main.Datos.Personaje;
import main.Datos.Vampiro;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class OperadorTest {

    @Test
    void editarDatosPersonaje() {
    }

    @Test
    void eliminarPersonaje() throws IOException, ClassNotFoundException {
        Multiplex multiplex = new Multiplex(false);
        Multiplex.getClientes().put("Prueba", new Cliente(null, "Prueba", "nick", "PR12UEB", "123"));
        Cliente cliente = Multiplex.getClientes().get("Prueba");
        Personaje personaje = new Personaje();
        personaje = new Vampiro();
        personaje.setHabilidadEspecial(new Disciplina("murcielago", 2, 2, 2));
        personaje.setModificador(new Modificador("luz solar", 5, 0));
        personaje.setEsbirros(new ArrayList<>());
        personaje.generarEsbirros();
        cliente.setPersonaje(personaje);
        assertEquals(personaje, cliente.getPersonaje());
        Multiplex.getClientes().get("Prueba").setPersonaje(null);
        assertNull(Multiplex.getClientes().get("Prueba").getPersonaje());
    }

    @Test
    void validarDesafios() throws IOException, ClassNotFoundException {
        Multiplex multiplex = new Multiplex(false);
        Multiplex.getClientes().put("Prueba", new Cliente(null, "Prueba", "nick", "PR12UEB", "123"));
        Cliente cliente = Multiplex.getClientes().get("Prueba");
        Personaje personaje = new Personaje();
        personaje = new Vampiro();
        personaje.setHabilidadEspecial(new Disciplina("murcielago", 2, 2, 2));
        personaje.setModificador(new Modificador("luz solar", 5, 0));
        personaje.setEsbirros(new ArrayList<>());
        personaje.generarEsbirros();
        Multiplex.getClientes().put("Prueba2", new Cliente(null, "Prueba2", "nick2", "PR12UEB2", "123"));
        Cliente cliente2 = Multiplex.getClientes().get("Prueba2");
        Personaje personaje2 = new Personaje();
        personaje2 = new Vampiro();
        personaje2.setHabilidadEspecial(new Disciplina("murcielago", 2, 2, 2));
        personaje2.setModificador(new Modificador("luz solar", 5, 0));
        personaje2.setEsbirros(new ArrayList<>());
        personaje2.generarEsbirros();
        cliente.setPersonaje(personaje);
        cliente2.setPersonaje(personaje2);
        Combate combate = new Combate();
        combate.setDuelista1(cliente);
        combate.setDuelista2(cliente2);
        combate.setOro(100);
        combate.setEstado(0);
        Multiplex.getDesafios().add(combate);
        assertEquals(0, Multiplex.getDesafios().get(0).getEstado());
        Multiplex.getDesafios().get(0).setEstado(1);
        assertEquals(1, Multiplex.getDesafios().get(0).getEstado());
    }

    @Test
    void banearJugador() throws IOException, ClassNotFoundException {
        Multiplex multiplex = new Multiplex(false);
        Multiplex.getClientes().put("Prueba", new Cliente(null, "Prueba", "nick", "PR12UEB", "12345678"));
        Cliente cliente = Multiplex.getClientes().get("Prueba");
        assertFalse(cliente.isBanned());
        cliente.setBanned(true);
        assertTrue(cliente.isBanned());
    }

    @Test
    void desbanearJugador() throws IOException, ClassNotFoundException {
        Multiplex multiplex = new Multiplex(false);
        Multiplex.getClientes().put("Prueba", new Cliente(null, "Prueba", "nick", "PR12UEB", "12345678"));
        Cliente cliente = Multiplex.getClientes().get("Prueba");
        cliente.setBanned(true);
        assertTrue(cliente.isBanned());
        cliente.setBanned(false);
        assertFalse(cliente.isBanned());
    }

    @Test
    void editarEquipoPersonaje() {
    }

    @Test
    void eliminarCuenta() throws IOException, ClassNotFoundException {
        Multiplex multiplex = new Multiplex(false);
        Multiplex.getOperadores().put("Prueba", new Operador("Prueba", "nick", "12345678"));
        Operador operador = Multiplex.getOperadores().get("Prueba");
        assertTrue(Multiplex.getOperadores().containsKey("Prueba"));
        Multiplex.getOperadores().remove("Prueba", operador);
        assertFalse(Multiplex.getClientes().containsKey("Prueba"));
    }

    @Test
    void editarModificador() {
    }
}