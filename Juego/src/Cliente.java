import Database.Personaje;

public class Cliente {

    private String Nombre;
    private final String Nick;
    private String Contraseña;
    private final String NRegistro;
    private Personaje Personaje;
    private boolean Baneado;

    public Cliente(String nombre, String nick, String contraseña, String identificador, Database.Personaje personaje, boolean baneado) {
        Nombre = nombre;
        Nick = nick;
        Contraseña = contraseña;
        NRegistro = identificador;
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

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String contraseña) {
        Contraseña = contraseña;
    }

    public String getNRegistro() {
        return NRegistro;
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

    public void registrarPersonaje(Personaje personaje){
        Personaje = personaje;
    }

    public void eliminarPersonaje(Personaje personaje){
        Personaje = null;
    }
}
