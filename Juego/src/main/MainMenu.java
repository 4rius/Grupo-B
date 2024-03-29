package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

final class MainMenu extends Operation {

    public MainMenu(Multiplex multiplex) {
        super(multiplex);
    }

    @Override
    public void doOperation() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int opcion = 0;
        while (true) {
            System.out.println("\nBienvenido a SmiteTactics" );
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Registrarse");
            System.out.println("3. Salir");
            System.out.println("Introduzca una opción: ");
            opcion = Integer.parseInt(br.readLine());
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
                case 3 -> {
                    System.out.println("Saliendo...");
                    System.exit(0);
                }
                default -> {
                    System.out.println("Esa no es una opción válida");
                    doOperation();
                }
            }
        }

    }

    private void registrarse() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Se va a registrar un operador (1) o un jugador (2)");
        int opcion = 0;
        opcion = Integer.parseInt(br.readLine());
        switch (opcion) {
            case 1 -> registrarOperador();
            case 2 -> registrarJugador();
        }
    }

    public void registrarJugador() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String Letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        System.out.println("Introduzca su nombre: ");
        String nombre = br.readLine();
        System.out.println("Nick: ");
        String nick = br.readLine();
        while (Multiplex.getClientes().containsKey(nick)) {
            System.out.println("Ese nick ya existe, introduzca otro: ");
            nick = br.readLine();
        }
        System.out.println("Introduzca su contraseña: ");
        String contrasena = br.readLine();
        while (contrasena.length() < 8 || contrasena.length() > 12) {
            System.out.println("La contraseña debe tener entre 8 y 12 caracteres");
            System.out.println("Introduzca su contraseña: ");
            contrasena = br.readLine();
        }
        String registro = null;
        while (Multiplex.getRegistros().contains(registro) || registro == null) {
            StringBuilder sb = new StringBuilder();
            sb.append(Letras.charAt((int) (Math.random() * Letras.length())));
            for (int i = 0; i < 2; i++) {
                sb.append(Integer.parseInt(String.valueOf((int) (9 * Math.random()))));
            }
            for (int i = 0; i < 2; i++) {
                sb.append(Letras.charAt((int) (Math.random() * Letras.length())));
            }
            registro = sb.toString();
        }

        System.out.println("Tu identificación de registro es: " + registro);
        Multiplex.getClientes().put(nick, new Cliente(null, nombre, nick, registro, contrasena));
        Multiplex.getRegistros().add(registro);
        System.out.println("Registrado con éxito, bienvenido, " + nick + "! Ya puedes iniciar sesión");
        Multiplex.serialize();
        this.doOperation();
    }

    public void registrarOperador() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Introduzca su nombre: ");
        String nombre = br.readLine();
        System.out.println("Nick: ");
        String nick = br.readLine();
        while (Multiplex.getClientes().containsKey(nick) || Multiplex.getOperadores().containsKey(nick)) {
            System.out.println("Ese nick ya existe, introduzca otro: ");
            nick = br.readLine();
        }
        System.out.println("Introduzca su contraseña: ");
        String contrasena = br.readLine();
        while (contrasena.length() < 8 || contrasena.length() > 12) {
            System.out.println("La contraseña debe tener entre 8 y 12 caracteres");
            System.out.println("Introduzca su contraseña: ");
            contrasena = br.readLine();
        }

        Multiplex.getOperadores().put(nick, new Operador(nombre, nick, contrasena));
        System.out.println("Registrado con éxito, bienvenido, " + nick + "! Ya puedes iniciar sesión");
        Multiplex.serialize();
        this.doOperation();
    }

    private void iniciarSesion() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Introduzca su nick: ");
        String nick = br.readLine();
        System.out.println("Introduzca su contraseña: ");
        String contrasena = br.readLine();
        if (Multiplex.getClientes().containsKey(nick)){
            Cliente cliente = Multiplex.getClientes().get(nick);
            if (cliente.getPassword().equals(contrasena) && !cliente.isBanned()) {
                    this.mainMenu(1, nick);
            } else if (cliente.isBanned()) {
                System.out.println("El usuario esta baneado");
            } else {
                System.out.println("Contraseña incorrecta");
            }
        } else if (Multiplex.getOperadores().containsKey(nick)) {
            Operador operador = Multiplex.getOperadores().get(nick);
            if (operador.getContraseña().equals(contrasena)) {
                this.mainMenu(2, nick);
            } else {
                System.out.println("Contraseña incorrecta");
            }
        } else {
            System.out.println("Ese usuario no existe");
        }
    }

    public void mainMenu(int tipo, String nick) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Bienvenido " + nick);
        OUTER:
        while (true) {
        int opcion = 0;
            if (tipo == 1) {  //Si es un cliente
                if (Multiplex.getClientes().get(nick).getNotificacion().size() > 0) {
                    System.out.println("Tienes notificaciones pendientes");
                    for (String notificacion : Multiplex.getClientes().get(nick).getNotificacion()) {
                        System.out.println(notificacion);
                    }
                    Multiplex.getClientes().get(nick).getNotificacion().clear();
                    Multiplex.serialize();
                    Multiplex.getClientes().get(nick).verDesafios();
                }
                if (Multiplex.getClientes().get(nick).getSuscripciones().size() > 0) {
                    System.out.println("Sus notificaciones de suscripciones son: \n");
                    for (String s : Multiplex.getClientes().get(nick).getSuscripciones()) {
                        System.out.println(s);
                    }
                    Multiplex.getClientes().get(nick).getSuscripciones().clear();
                    Multiplex.serialize(); //Que no se repitan las notificaciones
                }
                System.out.println("1. Registrar personaje");
                System.out.println("2. Eliminar el personaje actual");
                System.out.println("3. Seleccionar equipo");
                System.out.println("4. Crear desafío");
                System.out.println("5. Ver historial de combates");
                System.out.println("6. Suscribirse a resultados");
                System.out.println("7. Ver ranking global");
                System.out.println("8. Eliminar mi cuenta");
                System.out.println("9. Cerrar sesión");
                System.out.println("Introduzca una opción: ");

                opcion = Integer.parseInt(br.readLine());
                switch (opcion) {
                    case 1 -> Multiplex.getClientes().get(nick).registrarPersonaje();
                    case 2 -> {
                        Multiplex.getClientes().get(nick).setPersonaje(null);
                        System.out.println("Personaje eliminado");
                    }
                    case 3 -> Multiplex.getClientes().get(nick).seleccionarEquipo();
                    case 4 -> Multiplex.getClientes().get(nick).crearDesafio();
                    case 5 -> Multiplex.getClientes().get(nick).verHistorial();
                    case 6 -> Multiplex.getClientes().get(nick).suscribirse();
                    case 7 -> Multiplex.getClientes().get(nick).verRanking();
                    case 8 -> {
                        Multiplex.getClientes().get(nick).eliminarCuenta();
                        if (!Multiplex.getClientes().containsKey(nick)) {
                            break OUTER;
                        }
                    }
                    case 9 -> this.doOperation();
                    default -> {
                        System.out.println("Esa no es una opción válida");
                    }
                }
            } else { //Si es un operador
                System.out.println("1. Editar un personaje");
                System.out.println("2. Eliminar un personaje");
                System.out.println("3. Editar equipo de un personaje");
                System.out.println("4. Validar desafíos pendientes");
                System.out.println("5. Banear jugador");
                System.out.println("6. Desbanear jugador");
                System.out.println("7. Eliminar mi cuenta");
                System.out.println("8. Cerrar sesión");
                System.out.println("Introduzca una opción: ");

                opcion = Integer.parseInt(br.readLine());
                switch (opcion) {
                    case 1 -> Multiplex.getOperadores().get(nick).editarDatosPersonaje();
                    case 2 -> Multiplex.getOperadores().get(nick).eliminarPersonaje();
                    case 3 -> Multiplex.getOperadores().get(nick).editarEquipoPersonaje();
                    case 4 -> Multiplex.getOperadores().get(nick).validarDesafios();
                    case 5 -> Multiplex.getOperadores().get(nick).banearJugador();
                    case 6 -> Multiplex.getOperadores().get(nick).desbanearJugador();
                    case 7 -> {
                        Multiplex.getOperadores().get(nick).eliminarCuenta();
                        if (!Multiplex.getOperadores().containsKey(nick)) {
                            break OUTER;
                        }
                    }
                    case 8 -> this.doOperation();
                    default -> {
                        System.out.println("Esa no es una opción válida");
                    }
                }
            }
        }
    }
}