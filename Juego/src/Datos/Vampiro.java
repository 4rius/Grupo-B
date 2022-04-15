package Datos;

import java.io.IOException;
import java.util.List;

public class Vampiro extends Personaje{
    private int ptossangre;
    private int edad;


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