package Datos;

public class Arma extends Equipo{

    private boolean adosmanos;

    public Arma(String nombre, int modataque, int moddef, boolean  adosmanos) {
        super(nombre, modataque, moddef);
        this.adosmanos = adosmanos;
    }

    public boolean isAdosmanos() {
        return adosmanos;
    }

    public void setAdosmanos(boolean adosmanos) {
        this.adosmanos = adosmanos;
    }
}
