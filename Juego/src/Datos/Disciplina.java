package Datos;

import java.io.Serializable;

public class Disciplina extends Habilidad implements Serializable {
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
