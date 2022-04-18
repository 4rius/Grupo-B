import Datos.Personaje;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;

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

    public void editarPersonaje() throws IOException {
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        System.out.println("Modo edicion de personaje");
        System.out.println("Qué usuario tiene el personaje a modificar?");
        String user = br.readLine();
        if (Multiplex.getClientes().containsKey(user)){
            if (Multiplex.getClientes().get(user).getPersonaje() != null){
                System.out.println("Modificando al personaje de: " + user);
                System.out.println("1. Modificar nombre");
                System.out.println("2. Modificar oro");
                System.out.println("3. Modificar equipo");
                System.out.println("4. Modificar salud");
                System.out.println("5. Modificar poder");
                System.out.println("6. Modificar modificadores");
                System.out.println("7. Cancelar");
                int opcion = Integer.parseInt(System.console().readLine());
                switch (opcion){
                    case 1 -> {
                        System.out.println("Nuevo nombre: ");
                        String nnombre = System.console().readLine();
                        Multiplex.getClientes().get(user).getPersonaje().setNombre(nnombre);
                    }
                    case 2 -> {
                        System.out.println("Nueva cantidad de oro: ");
                        int oro = Integer.parseInt(System.console().readLine());
                        Multiplex.getClientes().get(user).getPersonaje().setOro(oro);
                    }
                    case 3 -> {

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
                    System.out.println("Desafio: " + i + " " + Multiplex.getDesafios().get(i).getDuelista1().getName() + " vs " + Multiplex.getDesafios().get(i).getDuelista2().getName());
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
        System.out.println("Baneo de jugadores");
        System.out.println("Número de registro del usuario a banear: ");
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
        System.out.println("Desbaneo de jugadores");
        System.out.println("Número de registro del usuario a desbanear: ");
        String user = br.readLine();
        if ((Multiplex.getClientes().containsKey(user)) && (Multiplex.getClientes().get(user).isBanned())){
            Multiplex.getClientes().get(user).setBanned(false);
            System.out.println("Usuario desbaneado");
            Multiplex.serialize();
        } else {
            System.out.println("El usuario no existe o no está baneado");
        }
    }

    public void editarEquipo(){}

    public void editarModificador(){}

}
