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

class PerformCombatTest {
    @BeforeEach
    void setUp() throws IOException, ClassNotFoundException {
        Multiplex multiplex = new Multiplex(false);
        Multiplex.getClientes().put("Usuario1", new Cliente(null, "Usuario1", "Usuario1", "PR11UEB", "123"));
        Multiplex.getClientes().put("Usuario2", new Cliente(null, "Usuario2", "Usuario2", "PR22UEB", "123"));
        Cliente duelista1 = Multiplex.getClientes().get("Usuario1");
        Personaje personaje1;
        personaje1 = new Vampiro();
        personaje1.setHabilidadEspecial(new Disciplina("murcielago", 2, 2, 2));
        personaje1.setModificador(new Modificador("luz solar", 5, 0));
        personaje1.setEsbirros(new ArrayList<>());
        personaje1.generarEsbirros();
        duelista1.setPersonaje(personaje1);
        Cliente duelista2 = Multiplex.getClientes().get("Usuario1");
        Personaje personaje2;
        personaje2 = new Vampiro();
        personaje2.setHabilidadEspecial(new Disciplina("murcielago", 2, 2, 2));
        personaje2.setModificador(new Modificador("luz solar", 5, 0));
        personaje2.setEsbirros(new ArrayList<>());
        personaje2.generarEsbirros();
        duelista2.setPersonaje(personaje2);
    }

    @Test
    void doOperation() {

    }

    @Test
    void saludEsbirros() {

    }

    @Test
    void calcularAtk() {
    }

    @Test
    void calcularDef() {
    }

    @Test
    void rolearDados() {
    }

    @Test
    void comprobarEstado() {
    }
}