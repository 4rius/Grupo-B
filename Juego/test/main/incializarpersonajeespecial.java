package main;

import main.Datos.*;

import java.io.IOException;
import java.util.ArrayList;

public class incializarpersonajeespecial { //Inicializa 1 operador y 1 cliente con las especificaciones indicadas
    public static void main(String[] args) throws IOException, ClassNotFoundException {
    Operador operador = new Operador("Geralt", "Operator", "12345678");
    Cliente cliente = new Cliente(null, "Client", "Cliente", "LL12L", "12345678");
    Multiplex mp = Multiplex.getInstance();
    mp.inicializarInventario();
    Personaje personaje = new Licantropo();
    personaje = new Licantropo();
    personaje.setHabilidadEspecial(new Don("Aullido primario", 1, 2, 1));
    personaje.setModificadores(new ArrayList<>());
    personaje.getModificadores().add(new Modificador("Luna llena", 4, 0));
    personaje.getModificadores().add(new Modificador("Armas de plata", 4, 1));
    personaje.getModificadores().add(new Modificador("Ultrasonidos", 1, 1));
    personaje.setEsbirros(new ArrayList<>());
    personaje.getEsbirros().add(new Humano("Igor", 1));
    ((Humano)personaje.getEsbirros().get(0)).setNivellealtad(Humano.lealtad.medium);
    personaje.getEsbirros().add(new Demonio("Baphomet",2, "Esta es la descripcion del pacto"));
    ((Demonio)personaje.getEsbirros().get(1)).getEsbirros().add(new Ghoul("Noro", 1, 3));
    personaje.setOro(100);
    personaje.setNombre("Sigmur");
    personaje.setSalud(5);
    personaje.setArmaActual1(Multiplex.getListaArmas().get(0));
    personaje.setArmaduraActual(Multiplex.getListaArmaduras().get(0));
    cliente.setPersonaje(personaje);
    Multiplex.getOperadores().put("Operator",operador);
    Multiplex.getClientes().put("Cliente",cliente);
    Multiplex.serialize();
    }
}
