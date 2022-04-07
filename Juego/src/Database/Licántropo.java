package Database;

import java.util.List;

public class Licántropo extends Personaje{
    private int Rabia;

    public Licántropo(String nombre, int oro, List<Equipo> inventario, Arma armaActual, Armadura armaduraActual, int salud, int poder, List<Modificador> modificadores, List<Esbirro> esbirros, Habilidad habilidadEspecial, int rabia) {
        super(nombre, oro, inventario, armaActual, armaduraActual, salud, poder, modificadores, esbirros, habilidadEspecial);
        Rabia = rabia;
    }

    public int getRabia() {
        return Rabia;
    }

    public void setRabia(int rabia) {
        Rabia = rabia;
    }
}
