package Datos;

public class Arma extends Equipo{

    private int adosmanos;

    public Arma(String nombre, int modataque, int moddef, int adosmanos) {
        super(nombre, modataque, moddef);
        this.adosmanos = adosmanos;
    }

    public int isAdosmanos() {
        return adosmanos;
    }

    public void setAdosmanos(int adosmanos) {
        this.adosmanos = adosmanos;
    }
}
