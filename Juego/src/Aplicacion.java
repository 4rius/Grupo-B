import java.io.IOException;

public class Aplicacion {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Multiplex multiplex = new Multiplex(false);
        multiplex.Start();
    }
}
