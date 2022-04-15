import java.io.IOException;

public class Aplicacion {

    public void main(String[] args) throws IOException, ClassNotFoundException {
        Multiplex multiplex = new Multiplex(false);
        multiplex.Start();
    }
}
