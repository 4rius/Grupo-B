import Datos.Personaje;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainMenu extends Operation {

    public MainMenu(Multiplex multiplex) {
        super(multiplex);
    }

    @Override
    public void doOperation() {
        System.out.println("Bienvenido al competidor del mismisimo The Witcher");
        System.out.println("1. Iniciar sesión");
        System.out.println("2. Registrarse");
        System.out.println("3. Salir");
        System.out.println("Introduzca una opción: ");
        int opcion = 0;
        opcion = Integer.parseInt(System.console().readLine());
        switch (opcion) {
            case 1 -> {
                try {
                    iniciarSesion();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            case 2 -> {
                try {
                    registrarse();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            case 3 -> System.out.println("Saliendo...");
            default -> {
                System.out.println("Esa no es una opción válida");
                doOperation();
            }
        }

    }

    private void registrarse() throws IOException {
        System.out.println("Se va a registrar un operador (1) o un jugador (2)");
        int opcion = 0;
        opcion = Integer.parseInt(System.console().readLine());
        switch (opcion) {
            case 1 -> registrarOperador();
            case 2 -> registrarJugador();
        }
    }

    private void registrarJugador() throws IOException {
        String Letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        System.out.println("Introduzca su nombre: ");
        String nombre = System.console().readLine();
        System.out.println("Nick: ");
        String nick = System.console().readLine();
        while (Multiplex.getNicknames().contains(nick)) {
            System.out.println("Ese nick ya existe, introduzca otro: ");
            nick = System.console().readLine();
        }
        System.out.println("Introduzca su contraseña: ");
        String contrasena = System.console().readLine();
        String registro = null;
        while (Multiplex.getClientes().containsKey(registro) || registro == null) { //LNNLL
            StringBuilder sb = new StringBuilder();
            sb.append((int) (Letras.length() * Math.random()));
            for (int i = 0; i < 2; i++) {
                sb.append(Math.random() * 9);
            }
            for (int i = 0; i < 2; i++) {
                sb.append(Letras.charAt((int) (Math.random() * Letras.length())));
            }
            registro = sb.toString();
        }

        System.out.println("Tu identificación de registro es: " + registro);
        Multiplex.getClientes().put(registro, new Cliente(nombre, nick, registro, contrasena));
        Multiplex.getNicknames().add(nick);
        System.out.println("Registrado con éxito, bienvenido, " + nick);
        Multiplex.serialize();
        this.doOperation();
    }

    private void registrarOperador() throws IOException {
        System.out.println("Introduzca su nombre: ");
        String nombre = System.console().readLine();
        System.out.println("Nick: ");
        String nick = System.console().readLine();
        while (Multiplex.getNicknames().contains(nick)) {
            System.out.println("Ese nick ya existe, introduzca otro: ");
            nick = System.console().readLine();
        }
        System.out.println("Introduzca su contraseña: ");
        String contrasena = System.console().readLine();

        Multiplex.getOperadores().put(nick, new Operador(this.getMultiplex(), nombre, nick, contrasena));
        Multiplex.getNicknames().add(nick);
        System.out.println("Registrado con éxito, bienvenido, " + nick);
        Multiplex.serialize();
        this.doOperation();
    }

    private void iniciarSesion() throws IOException {
        System.out.println("Introduzca su identificador/nick: ");
        String nick = System.console().readLine();
        System.out.println("Introduzca su contraseña: ");
        String contrasena = System.console().readLine();
        if (Multiplex.getClientes().containsKey(nick)) {
            Cliente cliente = Multiplex.getClientes().get(nick);
            if (cliente.getPassword().equals(contrasena)) {
                this.mainMenu(nick);
            } else {
                System.out.println("Contraseña incorrecta");
            }
        } else if (Multiplex.getOperadores().containsKey(nick)) {
            Operador operador = Multiplex.getOperadores().get(nick);
            if (operador.getContraseña().equals(contrasena)) {
                this.mainMenu(nick);
            } else {
                System.out.println("Contraseña incorrecta");
            }
        } else {
            System.out.println("Ese usuario no existe");
        }
    }

    public void mainMenu(String nick) throws IOException {
            System.out.println("Bienvenido " + nick);
            if (Multiplex.getClientes().containsKey(nick)) {  //Si es un cliente
                System.out.println("1. Registrar personaje");
                System.out.println("2. Eliminar el personaje actual");
                System.out.println("3. Seleccionar equipo");
                System.out.println("4. Crear desafío");
                System.out.println("5. Ver desafíos pendientes");
                System.out.println("6. Ver historial de combates");
                System.out.println("Introduzca una opción: ");
                int opcion = 0;
                opcion = Integer.parseInt(System.console().readLine());
                switch (opcion) {
                    case 1 -> Multiplex.getClientes().get(nick).registrarPersonaje();
                    case 2 -> {
                        Multiplex.getClientes().get(nick).setPersonaje(null);
                        System.out.println("Personaje eliminado");
                    }
                    case 3 -> Multiplex.getClientes().get(nick).seleccionarEquipo();
                    case 4 -> Multiplex.getClientes().get(nick).crearDesafio();
                    case 5 -> Multiplex.getClientes().get(nick).verDesafios();
                    case 6 -> Multiplex.getClientes().get(nick).verHistorial();
                    default -> {
                        System.out.println("Esa no es una opción válida");
                    }
                }
            } else { //Si es un operador
                System.out.println("1. Editar personaje");
                System.out.println("2. Eliminar personaje");
                System.out.println("3. Editar equipo");
                System.out.println("4. Editar modificador");
                System.out.println("5. Editar esbirros");
                System.out.println("5. Validar desafíos pendientes");
                System.out.println("6. Banear jugador");
                System.out.println("7. Desbanear jugador");
                System.out.println("Introduzca una opción: ");
                int opcion = 0;
                opcion = Integer.parseInt(System.console().readLine());
                switch (opcion) {
                    case 1 -> Multiplex.getOperadores().get(nick).editarPersonaje();
                    case 2 -> Multiplex.getOperadores().get(nick).eliminarPersonaje();
                    case 3 -> Multiplex.getOperadores().get(nick).editarEquipo();
                    case 4 -> Multiplex.getOperadores().get(nick).editarModificador();
                    case 5 -> Multiplex.getOperadores().get(nick).validarDesafios();
                    case 6 -> Multiplex.getOperadores().get(nick).banearJugador();
                    case 7 -> Multiplex.getOperadores().get(nick).desbanearJugador();
                    default -> {
                        System.out.println("Esa no es una opción válida");
                    }
                    }
                }
        }
    }