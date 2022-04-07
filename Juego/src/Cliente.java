import Database.Personaje;

public class Cliente {

    private String Nombre;
    private String Nick;
    private String Contraseña;
    private String Identificador;
    private Personaje Personaje;
    private boolean Baneado;

    public Cliente(String nombre, String nick, String contraseña, String identificador, Database.Personaje personaje, boolean baneado) {
        Nombre = nombre;
        Nick = nick;
        Contraseña = contraseña;
        Identificador = identificador;
        Personaje = personaje;
        Baneado = baneado;
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

    public String getIdentificador() {
        return Identificador;
    }

    public void setIdentificador(String identificador) {
        Identificador = identificador;
    }

    public Database.Personaje getPersonaje() {
        return Personaje;
    }

    public void setPersonaje(Database.Personaje personaje) {
        Personaje = personaje;
    }

    public boolean isBaneado() {
        return Baneado;
    }

    public void setBaneado(boolean baneado) {
        Baneado = baneado;
    }
}
