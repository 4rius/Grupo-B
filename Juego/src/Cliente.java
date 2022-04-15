import Datos.Personaje;

import java.io.IOException;
import java.util.ArrayList;

public class Cliente extends Operation{
    private Personaje personaje;
    private String name;
    private String nick;
    private String password;
    private int oro;
    private static String nRegistro;
    private boolean banned;

    public Cliente(Multiplex Multiplex, String name, String nick, String password, Personaje personaje, boolean banned) {
        super(Multiplex);
        this.oro = 500;
        this.name = name;
        this.nick = nick;
        this.password = password;
        this.personaje = personaje;
        this.banned = banned;
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

    public int getOro() {
        return oro;
    }

    public void verHistorial(){
        for(PerformCombat combate: Multiplex.getDesafios()){
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
        for(PerformCombat desafio: Multiplex.getDesafios()){
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

    @Override
    public void doOperation() {

    }

    public Personaje getPeronaje() {
        return personaje;
    }

    public void registrarPersonaje() throws IOException {
        if (Multiplex.getClientes().get(nick).getPersonaje() == null) {
            System.out.println("Ya hay un personaje registrado, elimínelo para crear uno nuevo");
        } else {
            Multiplex.getClientes().get(nick).setPersonaje(new Personaje());
        }
    }

    public void crearDesafio() {
        if (Multiplex.getClientes().get(nick).getPersonaje().getOro() > 0) {
            //Tdoo tuyo Yisus jejeje
        } else {
            System.out.println("No tienes oro suficiente para crear un desafío");
        }
    }
}
