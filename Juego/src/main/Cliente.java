package main;

import main.Datos.*;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;

public class Cliente implements Serializable {

    @Serial
    private static final long serialVersionUID = 2L;
    private Personaje personaje;
    private String name;
    private String nick;
    private final String password;
    private static String nRegistro;
    private boolean banned;
    private final Notificador notificador;
    private int overall; //Para el ranking global
    private final ArrayList<String>  notificacion;
    private final ArrayList<String> suscripciones;
    private int desafiospendientes;

    private String ultimapartidaperdida;


    public Cliente(Personaje personaje, String name, String nick, String nRegistro, String password) {
        this.personaje = personaje;
        this.name = name;
        this.nick = nick;
        this.password = password;
        Cliente.nRegistro = nRegistro;
        this.banned = false;
        this.notificador = new Notificador();
        this.notificacion = new ArrayList<>();
        this.suscripciones = new ArrayList<>();
        this.overall = 0;
        this.desafiospendientes = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPassword() {
        return password;
    }


    public void setPersonaje(Personaje personaje) {
        this.personaje = personaje;
    }

    public Personaje getPersonaje() {
        return personaje;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    public Notificador getNotificador() {
        return notificador;
    }

    public ArrayList<String> getNotificacion() {
        return notificacion;
    }


    public int getOverall() {
        return overall;
    }

    public void setOverall(int overall) {
        this.overall = overall;
    }

    public int getDesafiospendientes() {
        return desafiospendientes;
    }

    public void setDesafiospendientes(int desafiospendientes) {
        this.desafiospendientes = desafiospendientes;
    }

    public String getUltimapartidaperdida() {
        return ultimapartidaperdida;
    }

    public void setUltimapartidaperdida(String ultimapartidaperdida) {
        this.ultimapartidaperdida = ultimapartidaperdida;
    }

    public void verHistorial() {
        System.out.println("Historial de desafíos:\n");
        for (Combate combate : Multiplex.getDesafios()) {
            if ((combate.getDuelista1().getNick().equals(nick) || combate.getDuelista2().getNick().equals(nick)) && combate.getEstado() == 4 || combate.getEstado() == 5) {
                System.out.println(combate.getDuelista1().getNick() + " vs " + combate.getDuelista2().getNick());
                if (combate.getEstado() == 5) {
                    System.out.println("Este desfío fue rechazado por " + combate.getDuelista2().getNick());
                    System.out.println("--------------------------");
                } else {
                    System.out.println("Fecha: " + combate.getFecha());
                    System.out.println("Rondas jugadas: " + combate.getRondas());
                    System.out.println("Ganador: " + combate.getVencedor().getNick());
                    String esbirros = null;
                    if (combate.isEsbirrosVivos() || combate.isEsbirrosVivos2()) {
                        if (combate.isEsbirrosVivos1()) {
                            esbirros = (combate.getDuelista1().getNick() + " Mantuvo esbirros vivos");
                        } else if (combate.isEsbirrosVivos1() && combate.isEsbirrosVivos2()) {
                            System.out.println("Los dos duelistas mantuvieron esbirros vivos");
                        } else {
                            esbirros = (combate.getDuelista2().getNick() + " Mantuvo esbirros vivos");
                        }
                    } else {
                        esbirros = "Hubo una masacre, no quedaron esbirros vivos";
                    }
                    System.out.println(esbirros);
                    System.out.println("Oro apostado: " + combate.getOro());
                    System.out.println("--------------------------");
                }
            }
        }
    }

    public void verDesafios() throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (this.getDesafiospendientes() != 0) {
            System.out.println("Introduzca el nick del usuario del que quiere aceptar/rechazar el desafío: ");
            //1 en espera, 2 en espera de ser aceptado, 3 en ejecución, 4 finalizado, 5 rechazado
            for (Combate desafio : Multiplex.getDesafios()) {
                if (desafio.getEstado() == 1 && Objects.equals(desafio.getDuelista2().getNick(), this.getNick())) { //1 es en espera de ser aceptado por el otro jugador
                    System.out.println(desafio.getDuelista1().getNick() + " vs " + desafio.getDuelista2().getNick());
                    System.out.println("Oro apostado: " + desafio.getOro());
                } else if (desafio.getEstado() == 0) { //0 es en espera de ser aceptado por el operador;
                    System.out.println(desafio.getDuelista1().getNick() + " vs " + desafio.getDuelista2().getNick() + " esta pendiente de ser aceptado por un operador");
                }
            }
            String nick = br.readLine();
            for (Combate desafio : Multiplex.getDesafios()) {
                if (desafio.getDuelista1().getNick().equals(nick) && desafio.getDuelista2().getNick().equals(this.nick) && desafio.getEstado() == 1) {
                    System.out.println("Quiere aceptar el desafio? (1 aceptar, 0 rechazar)");
                    int opcion = Integer.parseInt(br.readLine());
                    if (opcion == 1) {
                        desafio.setEstado(3); //2 es en ejecucion
                        PerformCombat pc = new PerformCombat(desafio);
                        pc.doOperation();
                        desafio = pc.getCombate();
                        desafio.terminado(desafio.getVencedor(), desafio.getPerdedor());
                        System.out.println("El desafio ha finalizado");
                        if (desafio.getVencedor() != null) {
                            System.out.println("El ganador es: " + desafio.getVencedor().getNick());

                        } else {
                            System.out.println("Hay un empate");
                        }
                        System.out.println("La cantidad de oro ganada es: " + desafio.getOro());
                        System.out.println("Se han jugado " + desafio.getRondas() + " rondas");
                        if (desafio.isEsbirrosVivos() || desafio.isEsbirrosVivos2()) {
                            if (desafio.isEsbirrosVivos1()) {
                                System.out.println((desafio.getDuelista1().getNick() + " Mantuvo esbirros vivos"));
                            } else if ( desafio.isEsbirrosVivos1() && desafio.isEsbirrosVivos2()) {
                                System.out.println("Los dos duelistas mantuvieron esbirros vivos");
                            } else {
                                System.out.println(desafio.getDuelista2().getNick() + " Mantuvo esbirros vivos");
                            }
                        } else {
                            System.out.println("Hubo una masacre, no quedaron esbirros vivos");
                        }
                        this.setDesafiospendientes(this.getDesafiospendientes() - 1);
                        LocalDateTime fecha = LocalDateTime.now();
                        desafio.setFecha(fecha.format(formatter));
                        desafio.getPerdedor().setUltimapartidaperdida(fecha.format(formatter));
                        Multiplex.serialize();
                    } else if (opcion == 0) {
                        desafio.setEstado(5); //5 rechazado
                        desafio.setVencedor(desafio.getDuelista1());
                        if (desafio.getDuelista2().getPersonaje().getOro() > 0) {
                            this.getPersonaje().setOro((int) (this.getPersonaje().getOro() - (0.1 * desafio.getOro())));
                            if (this.getPersonaje().getOro() < 0) {
                                this.getPersonaje().setOro(0);
                            }
                        }
                        desafio.getDuelista1().getPersonaje().setOro((int) (desafio.getDuelista1().getPersonaje().getOro() + (0.1 * desafio.getOro())));
                        this.setOverall(this.getOverall() - 1);
                        desafio.getDuelista1().setOverall(desafio.getDuelista1().getOverall() + 1);
                        this.notificador.notificar(this.getNick() + " ha rechazado el desafio de " + desafio.getDuelista1().getNick());
                        desafio.getDuelista1().notificador.notificar(this.getNick() + " ha rechazado el desafio de " + desafio.getDuelista1().getNick());
                        this.setDesafiospendientes(this.getDesafiospendientes() - 1);
                        Multiplex.getDesafios().get(Multiplex.getDesafios().indexOf(desafio)).setEstado(5);
                        LocalDateTime fecha = LocalDateTime.now();
                        desafio.setFecha(fecha.format(formatter));
                        setUltimapartidaperdida(fecha.format(formatter));
                        System.out.println("El desafio ha sido rechazado");
                        Multiplex.serialize();
                    } else {
                        System.out.println("Ese desafío no existe / No está validado / Está rechazado");
                    }

                }
            }
        }
    }

    public void seleccionarEquipo() throws IOException {
        //El usuario va a poder elegir si quiere elegir arma,armadura o ninguna//
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int opt = 0;

        int eleccion;
        if (Multiplex.getClientes().get(nick).getPersonaje() != null) {
            while (opt != 3) {
                int opt1 = 0;

                System.out.println("Que quiere seleccionar");
                System.out.println("1.Arma");
                System.out.println("2.Armadura");
                System.out.println("3.Salir");
                opt = Integer.parseInt(br.readLine());
                switch (opt) {
                    case 1 -> {

                        while (opt1 != 4) {
                            System.out.println("Que desea elegir:");
                            System.out.println("1. Arma principal");
                            System.out.println("2. Arma secundaria");
                            System.out.println("3. Arma a dos manos");
                            System.out.println("4. Salir");
                            opt1 = Integer.parseInt(br.readLine());

                            switch (opt1) {
                                case 1 -> {
                                    System.out.println("-------------------------------------------");
                                    System.out.println("Lista de Armas a una mano");
                                    System.out.println("-------------------------------------------");
                                    for (Arma arma : Multiplex.getListaArmas()) {
                                        if (!arma.isAdosmanos()) {
                                            System.out.println(Multiplex.getListaArmas().indexOf(arma) + ".");
                                            System.out.println("Nombre:" + arma.getNombre());
                                            System.out.println("Ataque: " + arma.getModataque());
                                            System.out.println("Defensa: " + arma.getModdef()+"\n");

                                        }
                                    }
                                    do {
                                        System.out.println("Elija un arma principal: ");
                                        eleccion = Integer.parseInt(br.readLine());
                                    } while (Multiplex.getListaArmas().get(eleccion).isAdosmanos());
                                    getPersonaje().setArmaActual1(Multiplex.getListaArmas().get(eleccion));
                                    Multiplex.serialize();

                                }
                                case 2 -> {
                                    System.out.println("-------------------------------------------");
                                    System.out.println("Lista de Armas a una mano");
                                    System.out.println("-------------------------------------------");
                                    for (Arma arma : Multiplex.getListaArmas()) {
                                        if (!arma.isAdosmanos()) {
                                            System.out.println(Multiplex.getListaArmas().indexOf(arma) + ".");
                                            System.out.println("Nombre:" + arma.getNombre());
                                            System.out.println("Ataque: " + arma.getModataque());
                                            System.out.println("Defensa: " + arma.getModdef()+"\n");


                                        }
                                    }
                                    do {
                                        System.out.println("Elija un arma secundaria: ");
                                        eleccion = Integer.parseInt(br.readLine());
                                    } while (Multiplex.getListaArmas().get(eleccion).isAdosmanos());
                                    getPersonaje().setArmaActual2(Multiplex.getListaArmas().get(eleccion));
                                    Multiplex.serialize();
                                }

                                case 3 -> {
                                    System.out.println("-------------------------------------------");
                                    System.out.println("Lista de Armas a dos manos");
                                    System.out.println("-------------------------------------------");
                                    for (Arma arma : Multiplex.getListaArmas()) {
                                        if (arma.isAdosmanos()) {
                                            System.out.println(Multiplex.getListaArmas().indexOf(arma) + ".");
                                            System.out.println("Nombre:" + arma.getNombre());
                                            System.out.println("Ataque: " + arma.getModataque());
                                            System.out.println("Defensa: " + arma.getModdef()+"\n");

                                        }
                                    }
                                    do {
                                        System.out.println("Elija un arma a dos manos: ");
                                        eleccion = Integer.parseInt(br.readLine());
                                    } while (!Multiplex.getListaArmas().get(eleccion).isAdosmanos());
                                    getPersonaje().setArmaActual1(Multiplex.getListaArmas().get(eleccion));
                                    getPersonaje().setArmaActual2(null);
                                    Multiplex.serialize();
                                }
                            }

                        }
                    }
                    case 2 -> {
                        System.out.println("-------------------------------------------");
                        System.out.println("Lista de Armaduras");
                        System.out.println("-------------------------------------------");
                        for (Armadura armadura : Multiplex.getListaArmaduras()) {
                            System.out.println(Multiplex.getListaArmaduras().indexOf(armadura) + ".");
                            System.out.println("Nombre:" + armadura.getNombre());
                            System.out.println("Ataque: " + armadura.getModataque());
                            System.out.println("Defensa: " + armadura.getModdef() + "\n");
                        }
                        do {
                            System.out.println("Elija una armadura:");
                            eleccion = Integer.parseInt(br.readLine());
                        } while (eleccion < 0 || eleccion > Multiplex.getListaArmaduras().size() - 1);
                        getPersonaje().setArmaduraActual(Multiplex.getListaArmaduras().get(eleccion));
                        Multiplex.serialize();
                    }
                    case 3 -> System.out.println("Volviendo al menú principal");
                    default -> System.out.println("Número incorrecto. Introduzca número del 1 al 3");
                }
            }
        } else {
            System.out.println("Debes tener un personaje antes de poder cambiar el equipo");
        }
    }

    public void registrarPersonaje() throws IOException {
        if (personaje != null) {
            System.out.println("Ya hay un personaje registrado, elimínelo para crear uno nuevo");
        } else {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Elige el tipo de personaje");
            System.out.println("1. Vampiro");
            System.out.println("2. Licantropo");
            System.out.println("3. Cazador");
            int opcion;
            opcion = Integer.parseInt(br.readLine());

            switch (opcion) {
                case 1 -> {
                    this.personaje = new Vampiro();
                    this.personaje.setHabilidadEspecial(new Disciplina("murcielago", 2, 2, 2));
                    this.personaje.setModificador(new Modificador("luz solar", 5, 0));
                    this.personaje.setEsbirros(new ArrayList<>());
                    this.personaje.generarEsbirros();
                }
                case 2 -> {
                    this.personaje = new Licantropo();
                    this.personaje.setHabilidadEspecial(new Don("lobito", 3, 1, 2));
                    this.personaje.setModificador(new Modificador("luna llena", 2, 1));
                    this.personaje.setEsbirros(new ArrayList<>());
                    this.personaje.generarEsbirros();
                }
                case 3 -> {
                    this.personaje = new Cazador();
                    this.personaje.setHabilidadEspecial(new Talento("arco", 0, 0, 13));
                    this.personaje.setModificador(new Modificador("luna llena", 2, 1));
                    this.personaje.setEsbirros(new ArrayList<>());
                    this.personaje.generarEsbirros();
                }
                default -> System.out.println("Error: Opción no identificada");
            }
            System.out.println("Escribe el nombre de tu personaje");
            String nombre = br.readLine();
            this.personaje.setNombre(nombre);
            this.personaje.setSalud(5);
            this.personaje.setPoder((int) (Math.random() * 5 + 1));
            this.personaje.setOro(500);
            System.out.println("Personaje creado correctamente");
            Multiplex.serialize();
        }
    }

    public void crearDesafio() throws IOException {
        if (personaje != null) {
            System.out.println("Usuarios disponibles para desafiar:");
            for (String nick : Multiplex.getClientes().keySet()) {
                if (Multiplex.getClientes().get(nick).getPersonaje() != null && !Multiplex.getClientes().get(nick).isBanned() && !nick.equals(this.nick)) {
                    System.out.println((nick));
                }
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Escribe el nick del usuario que quiere desafiar");
            String nickUsuario = br.readLine();
            System.out.println("Escribe la cantidad de oro que quiere apostar");
            int oroApostado = Integer.parseInt(br.readLine());
            if (this.personaje.getOro() < oroApostado) {
                System.out.println("No tienes suficiente oro");
            } else if (!Multiplex.getClientes().containsKey(nickUsuario)) {
                System.out.println("El usuario no existe");
            } else if (Multiplex.getClientes().get(nickUsuario).getPersonaje() == null) {
                System.out.println("El usuario no tiene un personaje registrado");
            } else if (Multiplex.getClientes().get(nickUsuario).isBanned()) {
                System.out.println("El usuario está baneado");
            } else if (nickUsuario.equals(this.nick)) {
                System.out.println("No puedes desafiarte a ti mismo");
            } else {
                Combate desafio = new Combate();
                desafio.setDuelista1(this);
                desafio.setDuelista2(Multiplex.getClientes().get(nickUsuario));
                desafio.setOro(oroApostado);
                Multiplex.getDesafios().add(desafio);
                System.out.println("Desafio creado correctamente");
                Multiplex.serialize();
            }
        } else {
            System.out.println("No tienes un personaje registrado");
        }
    }

    public void suscribirse() throws IOException { //Cada clioente tiene su propio notificador, los que se suscriban a su perfil recibiran una notificación cada vez que este termine un desafío
        System.out.println("Usuarios disponibles para suscribirse:");
        for (String nick : Multiplex.getClientes().keySet()) {
            if (!nick.equals(this.nick)) {
                System.out.println(nick);
            }
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Escribe el nick del duelista que quieres conocer sus resultados al instante: ");
        String nickname = br.readLine();
        if (Multiplex.getClientes().containsKey(nickname) && !nickname.equals(this.nick)) {
            Multiplex.getClientes().get(nickname).getNotificador().agregarCliente(this);
            Multiplex.serialize();
            System.out.println("Suscrito a los resultados de: " + nickname);
        } else {
            System.out.println("El usuario especificado no existe / No puedes suscribirte a ti mismo");
        }
    }

    public void verRanking() {
        int i = 0;
        int max = 0;
        ArrayList<String> ranking = new ArrayList<>();
        while (i < 3 && i < Multiplex.getClientes().size()) {
            for (String nick : Multiplex.getClientes().keySet()) {
                if (Multiplex.getClientes().get(nick).getOverall() > max && !ranking.contains(nick)) {
                    max = Multiplex.getClientes().get(nick).getOverall();
                    }
                }
            int finalMax = max;
            for (String nick : Multiplex.getClientes().keySet()) {
                if (Multiplex.getClientes().get(nick).getOverall() == finalMax) {
                    ranking.add(nick);
                }
            }
            max = 0;
            i++;
        }
        System.out.println("\nRanking de jugadores:");
        i = 1;
        for (String nick : ranking) {
            System.out.println(i + ". " + nick + " con " + Multiplex.getClientes().get(nick).getOverall() + " punto/s \n");
            i++;
        }
    }

    public void eliminarCuenta() throws IOException {
        System.out.println("Escribe tu contraseña para confirmar la eliminación de tu cuenta");
        System.out.println("Da cualquier otra entrada para cancelar");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String contrasena = br.readLine();
        if (contrasena.equals(this.password)) {
            Multiplex.getClientes().remove(this.nick, this);
            Multiplex.serialize();
            System.out.println("Cuenta eliminada");
        } else {
            System.out.println("Contraseña incorrecta, operación cancelada");
        }
    }

    public ArrayList<String> getSuscripciones() {
        return suscripciones;
    }

}
