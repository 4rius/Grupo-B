import Datos.Personaje;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainMenu extends Operation {

    public MainMenu(Multiplex multiplex, ArrayList<Operation> operationList) {
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
            case 1 -> iniciarSesion();
            case 2 -> registrarse();
            case 3 -> System.out.println("Saliendo...");
            default -> {
                System.out.println("Esa no es una opción válida");
                doOperation();
            }
        }

    }

    private void registrarse() {
    }

    private void iniciarSesion() {
    }

    public void mainMenu(String nick) throws IOException {
        if (nick.equals("null")) {
            System.out.println("Ocurrió un problema con su usuario/contraseña");
            this.doOperation();
        } else {
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
                    case 1 -> {
                        if (Multiplex.getClientes().get(nick).getPersonaje() == null) {
                            System.out.println("Ya hay un personaje registrado, elimínelo para crear uno nuevo");
                        } else {
                            Multiplex.getClientes().get(nick).setPersonaje(new Personaje());
                        }
                    }
                    case 2 -> {
                        Multiplex.getClientes().get(nick).setPersonaje(null);
                        System.out.println("Personaje eliminado");
                    }
                    case 3 -> {
                        if (Multiplex.getClientes().get(nick).getPersonaje() != null) {
                            Multiplex.getClientes().get(nick).seleccionarEquipo();
                        } else {
                            System.out.println("Debes seleccionar un personaje antes de cambiar el equipo");
                        }
                    }
                    case 4 -> {
                        if (Multiplex.getClientes().get(nick).getPersonaje().getOro() > 0) {
                            //Tdoo tuyo Yisus jejeje
                        } else {
                            System.out.println("No tienes oro suficiente para crear un desafío");
                        }
                    }
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
    }