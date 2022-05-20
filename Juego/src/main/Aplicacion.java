package main;

import java.io.IOException;

public final class Aplicacion {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Multiplex multiplex = Multiplex.getInstance();
        multiplex.Start();
    }
}
