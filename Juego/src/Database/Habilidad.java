package Database;

public class Habilidad {
    private String Nombre;
    private int Atq;
    private int Dfs;

    public Habilidad(String nombre, int atq, int dfs) {
        Nombre = nombre;
        Atq = atq;
        Dfs = dfs;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public int getAtq() {
        return Atq;
    }

    public void setAtq(int atq) {
        Atq = atq;
    }

    public int getDfs() {
        return Dfs;
    }

    public void setDfs(int dfs) {
        Dfs = dfs;
    }
}
