package Database;

public class Ghoul extends Esbirro{
    private int Dependencia;

    public Ghoul(String nombre, int salud, int dependencia) {
        super(nombre, salud);
        Dependencia = dependencia;
    }

    public int getDependencia() {
        return Dependencia;
    }

    public void setDependencia(int dependencia) {
        Dependencia = dependencia;
    }
}
