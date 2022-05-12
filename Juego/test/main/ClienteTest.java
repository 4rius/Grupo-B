package main;

import main.Datos.Disciplina;
import main.Datos.Modificador;
import main.Datos.Personaje;
import main.Datos.Vampiro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    @BeforeEach
    void setUp() throws IOException, ClassNotFoundException {
        Multiplex multiplex = new Multiplex(false);
        Multiplex.getClientes().put("Prueba", new Cliente(null, "Prueba", "nick", "PR12UEB", "123"));
    }

    @Test
    void seleccionarEquipo() {
        Cliente cliente = Multiplex.getClientes().get("Prueba");
        Personaje personaje = new Personaje();
        personaje = new Vampiro();
        personaje.setHabilidadEspecial(new Disciplina("murcielago", 2, 2, 2));
        personaje.setModificador(new Modificador("luz solar", 5, 0));
        personaje.setEsbirros(new ArrayList<>());
        personaje.generarEsbirros();
        cliente.setPersonaje(personaje);
        cliente.getPersonaje().setArmaActual1(Multiplex.getListaArmas().get(0));
        cliente.getPersonaje().setArmaActual2(Multiplex.getListaArmas().get(1));
        cliente.getPersonaje().setArmaduraActual(Multiplex.getListaArmaduras().get(7));
        assertNotNull(cliente.getPersonaje().getArmaActual1());
        assertNotNull(cliente.getPersonaje().getArmaActual2());
        assertNotNull(cliente.getPersonaje().getArmaduraActual());

    }

    @Test
    void registrarPersonaje(){
        Cliente cliente = Multiplex.getClientes().get("Prueba");
        Personaje personaje = new Personaje();
        personaje = new Vampiro();
        personaje.setHabilidadEspecial(new Disciplina("murcielago", 2, 2, 2));
        personaje.setModificador(new Modificador("luz solar", 5, 0));
        personaje.setEsbirros(new ArrayList<>());
        personaje.generarEsbirros();
        cliente.setPersonaje(personaje);
        assertNotNull(cliente.getPersonaje());
    }

    @Test
    void crearDesafio(){
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
        assertNotNull(cliente.getPersonaje());
        assertNotNull(cliente2.getPersonaje());
        assertEquals(0, Multiplex.getDesafios().size());
        Combate combate = new Combate();
        combate.setDuelista1(cliente);
        combate.setDuelista2(cliente2);
        combate.setOro(100);
        combate.setEstado(0);
        Multiplex.getDesafios().add(combate);
        assertEquals(1, Multiplex.getDesafios().size());
    }

    @Test
    void suscribirse()  {
        //Simula que Cliente 2 se suscribe a los resultados de Cliente 1, y después que Cliente 1 se suscribe a los resultados de Cliente 2
        Multiplex.getClientes().put("Prueba2", new Cliente(null, "Prueba2", "nick2", "PR12UEB2", "123"));
        Cliente cliente = Multiplex.getClientes().get("Prueba");
        Cliente cliente2 = Multiplex.getClientes().get("Prueba2");
        assertEquals(0, cliente.getNotificador().getClientes().size());
        assertEquals(0, cliente2.getNotificador().getClientes().size());
        cliente.getNotificador().agregarCliente(cliente2);
        cliente2.getNotificador().agregarCliente(cliente);
        assertEquals(1, cliente.getNotificador().getClientes().size());
        assertEquals(1, cliente2.getNotificador().getClientes().size());
    }

    @Test
    void eliminarCuenta() {
        Cliente cliente = Multiplex.getClientes().get("Prueba");
        assertEquals(1, Multiplex.getClientes().size());
        assertTrue(Multiplex.getClientes().containsKey("Prueba"));
        Multiplex.getClientes().remove("Prueba");
        assertEquals(0, Multiplex.getClientes().size());
        assertFalse(Multiplex.getClientes().containsKey("Prueba"));
    }
}