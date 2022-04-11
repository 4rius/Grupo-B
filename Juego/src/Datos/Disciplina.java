package Datos;

public class Disciplina extends Habilidad{
    private int CosteSangre;

    public Disciplina(String nombre, int atq, int dfs, int costeSangre) {
        super(nombre, atq, dfs);
        CosteSangre = costeSangre;
    }

    public int getCosteSangre() {
        return CosteSangre;
    }

    public void setCosteSangre(int costeSangre) {
        CosteSangre = costeSangre;
    }
}
