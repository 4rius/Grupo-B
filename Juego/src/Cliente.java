import Datos.Personaje;

public class Cliente {
    private String name;
    private String nick;
    private String password;
    private static String nRegistro;
    private Personaje personaje;
    private boolean banned;

    public Cliente(String name, String nick, String password, String nRegistro, Personaje personaje, boolean banned) {
        this.name = name;
        this.nick = nick;
        this.password = password;
        this.nRegistro = nRegistro;
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

    public Personaje getPersonaje() {
        return personaje;
    }

    public void setPersonaje(Personaje personaje) {
        this.personaje = personaje;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = false;
    }
}
