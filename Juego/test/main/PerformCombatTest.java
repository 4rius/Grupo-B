package main;

import main.Datos.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

class PerformCombatTest {
    @BeforeEach
    void setUp() throws IOException, ClassNotFoundException {
        Multiplex multiplex = Multiplex.clearinstance();
        Multiplex.getClientes().put("Usuario1", new Cliente(null, "Usuario1", "Usuario1", "PR11UEB", "123"));
        Multiplex.getClientes().put("Usuario2", new Cliente(null, "Usuario2", "Usuario2", "PR22UEB", "123"));
        Personaje personaje1;
        personaje1 = new Vampiro();
        personaje1.setHabilidadEspecial(new Disciplina("murcielago", 2, 2, 2));
        personaje1.getModificadores().add(new Modificador("fuerza", 2, 0));
        personaje1.setEsbirros(new ArrayList<>());
        personaje1.generarEsbirros();
        Multiplex.getClientes().get("Usuario1").setPersonaje(personaje1);
        Cliente cliente2 = Multiplex.getClientes().get("Usuario1");
        Personaje personaje2;
        personaje2 = new Licantropo();
        personaje2.setHabilidadEspecial(new Don("lobo", 2, 2, 2));
        personaje2.getModificadores().add(new Modificador("fuerza", 2, 0));
        personaje2.setEsbirros(new ArrayList<>());
        personaje2.generarEsbirros();
        Multiplex.getClientes().get("Usuario2").setPersonaje(personaje2);
    }

    @Test
    void doOperation() {
        Combate c = new Combate();
        c.setDuelista1(Multiplex.getClientes().get("Usuario1"));
        c.setDuelista2(Multiplex.getClientes().get("Usuario2"));
        PerformCombat instance = new PerformCombat(c);
        instance.doOperation();
        assert(c.getRondas()>0);
        assert(c.getEstado()==4);
    }

    @Test
    void saludEsbirros() throws IOException, ClassNotFoundException {
        Combate c = new Combate();
        c.setDuelista1(Multiplex.getClientes().get("Usuario1"));
        c.setDuelista2(Multiplex.getClientes().get("Usuario2"));
        PerformCombat instance = new PerformCombat(c);
        int hp = instance.saludEsbirros(c.getDuelista1());
        assert(hp >= 0);
    }

    @Test
    void calcularAtk() {
        Combate c = new Combate();
        c.setDuelista1(Multiplex.getClientes().get("Usuario1"));
        c.setDuelista2(Multiplex.getClientes().get("Usuario2"));
        PerformCombat instance = new PerformCombat(c);
        int atk = instance.calcularAtk(c.getDuelista1());
        assert(atk >= 0);
    }

    @Test
    void calcularDef() {
        Combate c = new Combate();
        c.setDuelista1(Multiplex.getClientes().get("Usuario1"));
        c.setDuelista2(Multiplex.getClientes().get("Usuario2"));
        PerformCombat instance = new PerformCombat(c);
        int def = instance.calcularDef(c.getDuelista1());
        assert(def >= 0);
    }

    @Test
    void rolearDados() {
        Combate c = new Combate();
        c.setDuelista1(Multiplex.getClientes().get("Usuario1"));
        c.setDuelista2(Multiplex.getClientes().get("Usuario2"));
        PerformCombat instance = new PerformCombat(c);
        int k = instance.rolearDados((int) (Math.random() * 5 + 1));
        assert(k >= 0);
    }

    @Test
    void comprobarEstado() {
        Combate c = new Combate();
        c.setDuelista1(Multiplex.getClientes().get("Usuario1"));
        c.setDuelista2(Multiplex.getClientes().get("Usuario2"));
        PerformCombat instance = new PerformCombat(c);
        instance.comprobarEstado(1,0);
        assert(c.getEstado() == 4);
        assert(c.getVencedor() == c.getDuelista1());
        instance.comprobarEstado(0,1);
        assert(c.getEstado() == 4);
        assert(c.getVencedor() == c.getDuelista2());
        instance.comprobarEstado(0,0);
        assert(c.getEstado() == 4);
        assert(c.getVencedor() == null);
    }
}