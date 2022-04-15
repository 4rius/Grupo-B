import Datos.Personaje;

import java.util.ArrayList;

public class Cliente extends Operation{
    private Personaje personaje;
    private String name;
    private String nick;
    private String password;
    private static String nRegistro;
    private boolean banned;
    private ArrayList<Combate> combatespersonales;

    public Cliente(Multiplex Multiplex, String name, String nick, String password, Personaje personaje, boolean banned, ArrayList<Combate> combatespersonales, ArrayList<PerformCombat> desafios) {
        super(Multiplex);
        this.name = name;
        this.nick = nick;
        this.password = password;
        this.personaje = personaje;
        this.banned = banned;
        this.combatespersonales = combatespersonales;
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

    public ArrayList<Combate> getCombatespersonales() {
        return combatespersonales;
    }

    public void verHistorial(){
        for(Combate combate: this.combatespersonales){
            System.out.println(combate.toString());
        }
    }

    public void verDesafios(){
        for(PerformCombat desafio: Multiplex.getDesafios()){
            System.out.println(desafio.toString());
        }
    }

    void seleccionarEquipo() {
        System.out.println("Falta contenido para implementar esto");
    }

    @Override
    public void doOperation() {

    }

    public Personaje getPeronaje() {
        return personaje;
    }

}
