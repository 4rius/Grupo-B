import Datos.*;
import Datos.Vampiro;

import java.io.*;
import java.util.ArrayList;

public class Cliente implements Serializable {

    @Serial
    private static final long serialVersionUID = 2L;
    private Personaje personaje;
    private String name;
    private String nick;
    private String password;;
    private static String nRegistro;
    private boolean banned;
    private final Notificador notificador;
    private int overall; //Para el ranking global


    public Cliente(Personaje personaje, String name, String nick, String nRegistro, String password) {
        this.personaje = personaje;
        this.name = name;
        this.nick = nick;
        this.password = password;
        Cliente.nRegistro = nRegistro;
        this.banned = false;
        this.notificador = new Notificador();
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
        this.banned = banned;
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

    public void verDesafios() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //1 en espera, 2 en espera de ser aceptado, 3 en ejecución, 4 finalizado
        for(Combate desafio: Multiplex.getDesafios()){
            if (desafio.getEstado() == 1) { //1 es en espera de ser aceptado por el otro jugador
                System.out.println("Introduzca el nick del usuario del que quiere aceptar el desafío: ");
                System.out.println(desafio.getDuelista1().getNick() + " vs " + desafio.getDuelista2().getNick());
                System.out.println("Oro apostado: " + desafio.getOro());
            } else if (desafio.getEstado() == 0) { //0 es en espera de ser aceptado por el operador;
                System.out.println(desafio.getDuelista1().getNick() + " vs " + desafio.getDuelista2().getNick() + " esta pendiente de ser aceptado por un operador");
            }
        }
        String nick = br.readLine();
        for(Combate desafio: Multiplex.getDesafios()){
            if(desafio.getDuelista1().getNick().equals(this.nick) && desafio.getDuelista2().getNick().equals(nick) && desafio.getEstado() == 1){
                System.out.println("El desafio ha sido aceptado, comenzando la batalla");
                desafio.setEstado(2); //2 es en ejecucion
                //Aquí hay que llamar a peformcombat y luego actualizar el resultado de este desafío
            } else {
                System.out.println("Ese desafío no existe / no está validado");
            }
        }
    }

    public void seleccionarEquipo() throws IOException {
        if (Multiplex.getClientes().get(nick).getPersonaje() != null) {

            System.out.println();
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int i=0;
            for (Equipo mochila: Multiplex.getInventario()){
                System.out.println("Equipo " + i + ":");
                System.out.println("Nombre:"+mochila.getNombre());
                System.out.println("Ataque: "+mochila.getModataque());
                System.out.println("Defensa: "+mochila.getModdef());
                if (mochila instanceof Arma) {
                    Arma mochila2 = (Arma) mochila;
                    System.out.println("Arma a dos manos: "+mochila2.isAdosmanos());
                }
                System.out.println();
                i+=1;
            }
            System.out.println("Elige una armadura y dos armas de una mano o un arma de dos manos:");
            boolean armadura = false;
            boolean arma1 = false;
            boolean arma2 = false;
            while (( !armadura) || (!arma1) || (!arma2)) {
                int opt;
                do { //Controlar que el usuario elige un numero dentro de los rangos establecidos
                    System.out.println("Seleccione un numero del 0 al " + (i - 1));
                    opt = Integer.parseInt(br.readLine());
                } while (opt < 0 || opt > i - 1);

                if (Multiplex.getInventario().get(opt) instanceof Armadura) {
                    if (!armadura) {
                        getPersonaje().setArmaduraActual((Armadura) Multiplex.getInventario().get(opt));
                        System.out.println("Armadura seleccionada.");
                        armadura = true;
                    } else {
                        System.out.println("Ya tienes una Armadura seleccionada.");
                    }
                } else {
                    getPersonaje().setArmaActual1((Arma) Multiplex.getInventario().get(opt));
                    if ((getPersonaje().getArmaActual1().isAdosmanos()) && (!arma1)) {
                        getPersonaje().setArmaActual2(null);
                        System.out.println("Arma a dos manos seleccionada");
                        arma1 = true;
                        arma2 = true;
                    } else if ((getPersonaje().getArmaActual1().isAdosmanos()) && (arma1)) {
                        System.out.println("Arma a una mano equipada. Tu segunda arma solo puede ir a una mano");
                    } else if (!arma1) {
                        System.out.println("Arma 1 seleccionada.");
                        arma1 = true;
                        System.out.println("Seleccione otro equipo: ");
                    } else if (!arma2) {
                        System.out.println("Arma 2 seleccionada.");
                        arma2 = true;
                    } else if (arma1 && arma2) {
                        System.out.println("Ya tienes dos armas equipadas.");
                    }
                }
            }
        } else {
            System.out.println("Debes seleccionar un personaje antes de cambiar el equipo");
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
            int opcion = 0;
            opcion = Integer.parseInt(br.readLine());

            switch(opcion){
                case 1 -> {
                    this.personaje = new Vampiro();
                    this.personaje.setHabilidadEspecial(new Disciplina("murcielago", 2,2,2 ));
                    this.personaje.setModificador(new Modificador("luz solar", 5, 0));
                }
                case 2 -> {
                    this.personaje = new Licantropo();
                    this.personaje.setHabilidadEspecial(new Don("lobito", 3,1,2));
                    this.personaje.setModificador(new Modificador("luna llena", 2, 1));
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
            String nombre = br.readLine();
            this.personaje.setNombre(nombre);
            this.personaje.setSalud(5);
            int r = (int) (Math.random()*5 + 1);
            this.personaje.setPoder(r);
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
            if (this.personaje.getOro() < oroApostado){
                System.out.println("No tienes suficiente oro");
            } else if (Multiplex.getClientes().get(nickUsuario).getPersonaje() == null){
                System.out.println("El usuario no tiene un personaje registrado");
            } else if (Multiplex.getClientes().get(nickUsuario).isBanned()){
                System.out.println("El usuario está baneado");
            } else if (nickUsuario.equals(this.nick)){
                System.out.println("No puedes desafiarte a ti mismo");
            }
            else {
                Combate desafio = new Combate();
                desafio.setDuelista1(this);
                desafio.setDuelista2(Multiplex.getClientes().get(nickUsuario));
                desafio.setOro(oroApostado);
                Multiplex.getDesafios().add(desafio);
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
            System.out.println("Suscrito a los resultados de: " + nickname);
        } else {
            System.out.println("El usuario especificado no existe / No puedes suscribirte a ti mismo");
        }
    }


    public void recibirNotificacion(String mensaje) {
        System.out.println(mensaje);
    }

    public void verRanking() {
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
}
