package Database;

public class Modificador {
    private String Nombre;
    private int mod;
    private boolean tipomod;

    public Modificador(String nombre, int mod, boolean tipomod) {
        Nombre = nombre;
        this.mod = mod;
        this.tipomod = tipomod;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public int getMod() {
        return mod;
    }

    public void setMod(int mod) {
        this.mod = mod;
    }

    public boolean isTipomod() {
        return tipomod;
    }

    public void setTipomod(boolean tipomod) {
        this.tipomod = tipomod;
    }
}
