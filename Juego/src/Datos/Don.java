package Datos;

public class Don extends Habilidad{
    private int MinRabia;

    public Don(String nombre, int atq, int dfs, int minRabia) {
        super(nombre, atq, dfs);
        MinRabia = minRabia;
    }

    public int getMinRabia() {
        return MinRabia;
    }

    public void setMinRabia(int minRabia) {
        MinRabia = minRabia;
    }
}