import Datos.Modificador;
import Datos.Personaje;

import java.io.*;

public class Operador implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private String Nombre;
    private String Nick;
    private String Contraseña;

    /**
     * Constructor noargs para la serialización
     *
     */
    public Operador(String nombre, String nick, String contraseña) {
        Nombre = nombre;
        Nick = nick;
        Contraseña = contraseña;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getNick() {
        return Nick;
    }

    public void setNick(String nick) {
        Nick = nick;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String contraseña) {
        Contraseña = contraseña;
    }

    public void editarDatosPersonaje() throws IOException {
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        System.out.println("Modo edicion de datos del personaje");
        System.out.println("Escriba el nombre de usuario del personaje a editar");
        String user = br.readLine();
        if (Multiplex.getClientes().containsKey(user)){
            if (Multiplex.getClientes().get(user).getPersonaje() != null){
                System.out.println("Modificando al personaje de: " + user);
                System.out.println("1. Modificar nombre");
                System.out.println("2. Modificar oro");
                System.out.println("3. Modificar salud");
                System.out.println("4. Modificar poder");
                System.out.println("5. Modificar habilidadEspecial");
                System.out.println("5. Cancelar");
                int opcion = Integer.parseInt(br.readLine());
                switch (opcion){
                    case 1 -> {
                        System.out.println("Nuevo nombre: ");
                        String nombre = System.console().readLine();
                        Multiplex.getClientes().get(user).getPersonaje().setNombre(nombre);
                    }
                    case 2 -> {
                        System.out.println("Nueva cantidad de oro: ");
                        int oro = Integer.parseInt(System.console().readLine());
                        Multiplex.getClientes().get(user).getPersonaje().setOro(oro);
                    }
                    case 3 -> {
                        System.out.println("Nueva cantidad de salud: ");
                        int salud = Integer.parseInt(System.console().readLine());
                        Multiplex.getClientes().get(user).getPersonaje().setSalud(salud);
                    }
                    case 4 -> {
                        System.out.println("Nueva cantidad de poder: ");
                        int poder = Integer.parseInt(System.console().readLine());
                        Multiplex.getClientes().get(user).getPersonaje().setPoder(poder);
                    }
                    case 5 -> {

                    }
                    default -> System.out.println("Saliendo / Opción no válida");
                }

            } else {
                System.out.println("El usuario no tiene un personaje");
            }
        } else {
            System.out.println("El usuario no existe");
        }
    }

    public void eliminarPersonaje() throws IOException {
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        System.out.println("Eliminación de personajes");
        System.out.println("Qué usuario tiene el personaje a eliminar?");
        String user = br.readLine();
        if (Multiplex.getClientes().containsKey(user)){
            if (Multiplex.getClientes().get(user).getPersonaje() != null){
                System.out.println("Eliminando al personaje de: " + user);
                Multiplex.getClientes().get(user).setPersonaje(null);
                Multiplex.serialize();
            } else {
                System.out.println("El usuario no tiene un personaje");
            }
        } else {
            System.out.println("El usuario no existe");
        }
    }

    public void validarDesafios() throws IOException {
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        if (Multiplex.getDesafios().size() > 0){
            System.out.println("Desafios a validar: ");
            for (int i = 0; i < Multiplex.getDesafios().size(); i++){
                if (Multiplex.getDesafios().get(i).getEstado() == 0){
                    System.out.println("Desafio: " + i + ". " + Multiplex.getDesafios().get(i).getDuelista1().getName() + " vs " + Multiplex.getDesafios().get(i).getDuelista2().getName());
                }
            }
            System.out.println("Numero del desafío a validar: ");
            int opcion = Integer.parseInt(br.readLine());
            if (opcion < Multiplex.getDesafios().size()){
                Multiplex.getDesafios().get(opcion).setEstado(1);
                System.out.println("Desafío validado");
                Multiplex.getDesafios().get(opcion).getDuelista1().recibirNotificacion("La batalla contra" + Multiplex.getDesafios().get(opcion).getDuelista2().getNick() + "ha sido validada");
                Multiplex.getDesafios().get(opcion).getDuelista2().recibirNotificacion(Multiplex.getDesafios().get(opcion).getDuelista1().getNick() + "Te ha desafiado, ¡ve a desafíos pendientes para aceptarlo!");
                Multiplex.serialize();
            }
        } else {
            System.out.println("No hay desafios pendientes");
        }
    }

    public void banearJugador() throws IOException {
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        System.out.println("Baneo de jugadores - Usuarios registrados:");
        for (String nick : Multiplex.getClientes().keySet()){
            if (!Multiplex.getClientes().get(nick).isBanned()) {
                System.out.println(nick);
            }
        }
        System.out.println("Nick del usuario a banear: ");
        String user = br.readLine();
        if (Multiplex.getClientes().containsKey(user)){
            Multiplex.getClientes().get(user).setBanned(true);
            System.out.println("Usuario baneado");
            Multiplex.serialize();
        } else {
            System.out.println("El usuario no existe");
        }
    }

    public void desbanearJugador() throws IOException {
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        System.out.println("Desbaneo de jugadores - Usuarios baneados:");
        for (String nick : Multiplex.getClientes().keySet()){
            if (Multiplex.getClientes().get(nick).isBanned()) {
                System.out.println(nick);
            }
        }
        System.out.println("Nick del usuario a desbanear: ");
        String user = br.readLine();
        if ((Multiplex.getClientes().containsKey(user)) && (Multiplex.getClientes().get(user).isBanned())){
            Multiplex.getClientes().get(user).setBanned(false);
            System.out.println("Usuario desbaneado");
            Multiplex.serialize();
        } else {
            System.out.println("El usuario no existe o no está baneado");
        }
    }

    public void editarEquipoPersonaje() throws IOException {
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        System.out.println("Modo edicion de equipo del personaje");
        System.out.println("Escriba el nombre de usuario del personaje a editar");
        String user = br.readLine();
        if (Multiplex.getClientes().containsKey(user)){
            if (Multiplex.getClientes().get(user).getPersonaje() != null){
                System.out.println("Modificando al personaje de: " + user);
                System.out.println("1. Modificar armas");
                System.out.println("2. Editar modificadores");
                System.out.println("3. Editar esbirros");
                System.out.println("4. Cancelar");
                int opcion = Integer.parseInt(br.readLine());
                switch (opcion){

                    default -> System.out.println("Saliendo / Opción no válida");
                }

            } else {
                System.out.println("El usuario no tiene un personaje");
            }
        } else {
            System.out.println("El usuario no existe");
        }
    }

    public void eliminarCuenta() throws IOException {
        System.out.println("Menú de elininación de cuentas");
        System.out.println("1. Eliminar mi cuenta");
        System.out.println("2. Eliminar la cuenta de otro cliente");
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        int opcion = Integer.parseInt(br.readLine());
        switch (opcion){
            case 1: {
                System.out.println("De verdad desea eliminar su cuenta? (S/N)");
                String respuesta = br.readLine();
                if (respuesta.equals("S")){
                    System.out.println("Introduzca su contraseña para confirmar");
                    System.out.println("Da cualquier otra entrada para cancelar");
                    String pass = br.readLine();
                    if (pass.equals(Multiplex.getClientes().get(this.getNick()).getPassword())){
                        Multiplex.getOperadores().remove(this.getNick(), this);
                        Multiplex.serialize();
                        System.out.println("Cuenta eliminada");
                        break;
                    } else {
                        System.out.println("Contraseña incorrecta, operación cancelada");
                    }
                } else if (respuesta.equals("N")){
                    System.out.println("Cancelado");
                    break;
                } else {
                    System.out.println("Respuesta no válida");
                    break;
                }

            }
            case 2: {
                System.out.println("Usuarios registrados: ");
                for (String nick : Multiplex.getClientes().keySet()){
                    System.out.println(nick);
                }
                System.out.println("Escriba el nombre de usuario del cliente a eliminar");
                String user = br.readLine();
                if (Multiplex.getClientes().containsKey(user)){
                    System.out.println("De verdad desea eliminar la cuenta de " + user + "? (S/N)");
                    String respuesta = br.readLine();
                    if (respuesta.equals("S")){
                        System.out.println("Eliminando cuenta...");
                        Multiplex.getClientes().remove(user, Multiplex.getClientes().get(user));
                        Multiplex.serialize();
                        System.out.println("Cuenta eliminada");
                        break;
                    } else if (respuesta.equals("N")){
                        System.out.println("Cancelado");
                        break;
                    } else {
                        System.out.println("Respuesta no válida");
                        break;
                    }
                }
            }
        }
    }

   /* public void editarModificador() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (Cliente cliente: Multiplex.getClientes().values()){
            System.out.println(cliente.getNick());
        }
        System.out.println("Selecciona el Nick del cliente del que desea editar el modificador: ");
        String opt = br.readLine();
        if (Multiplex.getClientes().containsKey(opt)){
            if (Multiplex.getClientes().get(opt).getPersonaje()!=null) {
                Modificador mod = Multiplex.getClientes().get(opt).getPersonaje().getModificador();
                System.out.println("Modificador Seleccionado: ");
                System.out.println("Nombre: " + mod.getNombre());
                System.out.println("Fuerza: " + mod.getMod());
                if (mod.isTipomod() == 0) {
                    System.out.println("Debilidad");
                } else {
                    System.out.println("Fortaleza");
                }
                int tipo;
                do {
                    System.out.println("Es una fortaleza(1) o una debilidad(0): ");
                    tipo = Integer.parseInt(br.readLine());
                    if (tipo == 1) {
                        System.out.println("Has elegido una fortaleza");
                        Multiplex.getClientes().get(opt).getPersonaje().getModificador().setTipomod(tipo);
                    } else if (tipo == 0) {
                        System.out.println("Has elegido una debilidad");
                        Multiplex.getClientes().get(opt).getPersonaje().getModificador().setTipomod(tipo);
                    } else {
                        System.out.println("Numero incorrecto. Elija un número del 0 al 1");
                    }
                } while ((tipo != 1) && (tipo != 0));

                System.out.println("Seleccione el nombre del modificador: ");
                mod.setNombre(br.readLine());
                int fuerza;
                do {
                    System.out.println("Seleccione la fuerza del modificador: ");
                    fuerza = Integer.parseInt(br.readLine());
                    if ((fuerza >= 1) && (fuerza <= 5)) {
                        Multiplex.getClientes().get(opt).getPersonaje().getModificador().setMod(fuerza);
                    } else {
                        System.out.println("Numero incorrecto. Elija un número del 1 al 5");
                    }
                } while ((fuerza > 5) || (fuerza < 1));
                Multiplex.serialize();
            }else{
                System.out.println("No tiene ningún personaje creado");
            }
        }else{
            System.out.println("Nick Incorrecto. El cliente no existe.");
        }
    }
        */




}
