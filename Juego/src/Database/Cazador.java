package Database;

import java.util.List;

public class Cazador extends Personaje{
    private int ptosvoluntad;
    private int edad;

    public Cazador(String nombre, int oro, List<Equipo> inventario, Arma armaActual, Armadura armaduraActual, int salud, int poder, List<Modificador> modificadores, List<Esbirro> esbirros, Habilidad habilidadEspecial, int ptosvoluntad, int edad) {
        super(nombre, oro, inventario, armaActual, armaduraActual, salud, poder, modificadores, esbirros, habilidadEspecial);
        this.ptosvoluntad = ptosvoluntad;
        this.edad = edad;
    }

    public int getPtosvoluntad() {
        return ptosvoluntad;
    }

    public void setPtosvoluntad(int ptosvoluntad) {
        this.ptosvoluntad = ptosvoluntad;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}
