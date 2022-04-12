package Datos;

import java.io.IOException;
import java.util.List;

public class Vampiro extends Personaje{
    private int ptossangre;
    private int edad;

    public Vampiro(String nombre, int oro, List<Equipo> inventario, Arma armaActual, Armadura armaduraActual, int salud, int poder, List<Modificador> modificadores, List<Esbirro> esbirros, Habilidad habilidadEspecial, int ptossangre, int edad) throws IOException {
        super(nombre, oro, inventario, armaActual, armaduraActual, salud, poder, modificadores, esbirros, habilidadEspecial);
        this.ptossangre = ptossangre;
        this.edad = edad;
    }

    public int getPtossangre() {
        return ptossangre;
    }

    public void setPtossangre(int ptossangre) {
        this.ptossangre = ptossangre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}
