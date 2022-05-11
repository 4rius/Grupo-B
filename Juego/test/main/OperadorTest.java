package main;

import main.Datos.Disciplina;
import main.Datos.Modificador;
import main.Datos.Personaje;
import main.Datos.Vampiro;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class OperadorTest {

    @BeforeEach
    void setUp() throws IOException, ClassNotFoundException {
        Multiplex multiplex = new Multiplex(false);
        Multiplex.getClientes().put("Prueba", new Cliente(null, "Prueba", "nick", "PR12UEB", "123"));
    }

    @Test
    void editarDatosPersonaje() {
        Cliente cliente = Multiplex.getClientes().get("Prueba");
        Personaje personaje = new Personaje();
        personaje = new Vampiro();
        personaje.setHabilidadEspecial(new Disciplina("murcielago", 2, 2, 2));
        personaje.setModificador(new Modificador("luz solar", 5, 0));
        personaje.setEsbirros(new ArrayList<>());
        personaje.generarEsbirros();
        personaje.setNombre("Prueba");
        personaje.setOro(100);
        personaje.setSalud(5);
        personaje.setPoder((int) (Math.random() * 5 + 1));
        cliente.setPersonaje(personaje);
        assertEquals(personaje, cliente.getPersonaje());
        assertEquals("Prueba", cliente.getPersonaje().getNombre());
        assertEquals(100, cliente.getPersonaje().getOro());
        assertEquals(personaje.getHabilidadEspecial(), cliente.getPersonaje().getHabilidadEspecial());
        cliente.getPersonaje().setHabilidadEspecial(new Disciplina("sombra", 2, 2, 2));
        cliente.getPersonaje().setModificador(new Modificador("luz lunar", 5, 1));
        assertEquals(personaje.getHabilidadEspecial(), cliente.getPersonaje().getHabilidadEspecial());
        assertEquals(personaje.getModificador(), cliente.getPersonaje().getModificador());
        cliente.getPersonaje().setNombre("SoyLaPrueba");
        assertEquals("SoyLaPrueba", cliente.getPersonaje().getNombre());
        cliente.getPersonaje().setOro(200);
        assertEquals(200, cliente.getPersonaje().getOro());

    }

    @Test
    void eliminarPersonaje(){
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
    void validarDesafios(){
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
    void banearJugador(){
        Cliente cliente = Multiplex.getClientes().get("Prueba");
        assertFalse(cliente.isBanned());
        cliente.setBanned(true);
        assertTrue(cliente.isBanned());
    }

    @Test
    void desbanearJugador() {
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
    void eliminarCuenta() {
        Multiplex.getOperadores().put("Prueba", new Operador("Prueba", "nick", "12345678"));
        Operador operador = Multiplex.getOperadores().get("Prueba");
        assertTrue(Multiplex.getOperadores().containsKey("Prueba"));
        Multiplex.getOperadores().remove("Prueba", operador);
        assertFalse(Multiplex.getOperadores().containsKey("Prueba"));
    }

    @Test
    void editarModificador() {
        Multiplex.getClientes().get("Prueba").setPersonaje(new Vampiro());
        Personaje personaje = Multiplex.getClientes().get("Prueba").getPersonaje();
        personaje.setModificador(new Modificador("luz solar", 5, 0));
        assertEquals(5, personaje.getModificador().getMod());
        assertEquals("luz solar", personaje.getModificador().getNombre());
        personaje.setModificador(new Modificador("luz lunar", 0, 1));
        assertEquals(0, personaje.getModificador().getMod());
        assertEquals("luz lunar", personaje.getModificador().getNombre());
        personaje.getModificador().setMod(5);
        assertEquals(5, personaje.getModificador().getMod());
    }
}