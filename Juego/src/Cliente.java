import Datos.*;
import Datos.Vampiro;

import java.io.IOException;

public class Cliente{
    private Personaje personaje;
    private String name;
    private String nick;
    private String password;;
    private static String nRegistro;
    private boolean banned;

    private Notificador notificador;

    public Cliente(String name, String nick, String nRegistro, String password) {
        this.name = name;
        this.nick = nick;
        this.nRegistro = nRegistro;
        this.password = password;
        this.banned = false;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public static String getnRegistro() {
        return nRegistro;
    }

    public void setnRegistro(String nRegistro) {
        this.nRegistro = nRegistro;
    }

    public void setPersonaje(Personaje personaje) {
        this.personaje = personaje;
    }

    public Personaje getPersonaje(){ return personaje;}

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = false;
    }

    public Notificador getNotificador() {
        return notificador;
    }

    public void verHistorial(){
        for(Combate combate: Multiplex.getDesafios()){
            if(combate.getDuelista1().getNick().equals(nick) && combate.getEstado() == 4) {
                System.out.println(combate.getDuelista1().getNick() + " vs " + combate.getDuelista2().getNick());
                System.out.println("Fecha: " + combate.getFecha());
                System.out.println("Rondas jugadas: " + combate.getRondas());
                System.out.println("Ganador: " + combate.getVencedor());
            }
        }
    }

    public void verDesafios(){
        //1 en espera, 2 en espera de ser aceptado, 3 en ejecución, 4 finalizado
        for(Combate desafio: Multiplex.getDesafios()){
            if (desafio.getEstado() == 1) { //1 es en espera de ser aceptado
                System.out.println("Introduzca el nick del usuario del que quiere aceptar el desafío: ");
                System.out.println(desafio.getDuelista1().getNick() + " vs " + desafio.getDuelista2().getNick());
                System.out.println("Oro apostado: " + desafio.getOro());
            } else if (desafio.getEstado() == 0) { //2 es en espera de ser aceptado;
                System.out.println(desafio.getDuelista1().getNick() + " vs " + desafio.getDuelista2().getNick() + " esta pendiente de ser aceptado por un operador");
            }
        }
    }

    void seleccionarEquipo() {
        if (Multiplex.getClientes().get(nick).getPersonaje() != null) {
            //Falta por implementar
        } else {
            System.out.println("Debes seleccionar un personaje antes de cambiar el equipo");
        }
    }

    public Personaje getPeronaje() {
        return personaje;
    }

    public void registrarPersonaje() throws IOException {
        if (personaje != null) {
            System.out.println("Ya hay un personaje registrado, elimínelo para crear uno nuevo");
        } else {
            System.out.println("Elige el tipo de personaje");
            System.out.println("1. Vampiro");
            System.out.println("2. Licantropo");
            System.out.println("3. Cazador");
            int opcion = 0;
            opcion = Integer.parseInt(System.console().readLine());

            switch(opcion){
                case 1 -> {
                    this.personaje = new Vampiro();
                    this.personaje.setHabilidadEspecial(new Disciplina("murcielago", 2,2,2 ));
                    this.personaje.getModificadores().add(new Modificador("luz solar", 5, 0));
                }
                case 2 -> {
                    this.personaje = new Licantropo();
                    this.personaje.setHabilidadEspecial(new Don("lobito", 3,1,2));
                    this.personaje.getModificadores().add(new Modificador("luna llena", 2, 1));
                }

                case 3 -> {
                    this.personaje = new Cazador();
                    this.personaje.setHabilidadEspecial(new Talento("arco", 0, 0, 13));
                }
                default -> {
                    System.out.println("Error: Opción no identificada");
                    break;
                }
            }
            System.out.println("Escribe el nombre de tu personaje");
            String nombre = System.console().readLine();
            this.personaje.setNombre(nombre);
            this.personaje.setSalud(5);
            int r = (int) (Math.random()*5 + 1);
            this.personaje.setPoder(r);
            this.personaje.setOro(500);
            System.out.println("Personaje creado correctamente");
        }
    }

    public void crearDesafio() {
        if (Multiplex.getClientes().get(nick).getPersonaje().getOro() > 0) {
            //Tdoo tuyo Yisus jejeje
        } else {
            System.out.println("No tienes oro suficiente para crear un desafío");
        }
    }

    public void suscribirse() { //Cada clioente tiene su propio notificador, los que se suscriban a su perfil recibiran una notificación cada vez que este termine un desafío
        System.out.println("Escribe el nick del duelista que quieres conocer sus resultados al instante: ");
        String nick = System.console().readLine();
        if (Multiplex.getClientes().containsKey(nick)) {
            Multiplex.getClientes().get(nick).getNotificador().agregarCliente(this);
            System.out.println("Suscrito a los resultados de: " + nick);
        } else {
            System.out.println("El usuario especificado no existe");
        }
    }

    public void recibirNotificacion(String mensaje) {
        System.out.println(mensaje);
    }
}
