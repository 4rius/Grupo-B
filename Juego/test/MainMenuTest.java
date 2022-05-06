import main.MainMenu;
import main.Multiplex;
import org.junit.Test;

import java.io.IOException;

class MainMenuTest {

    @Test
    public void registrarJugador() throws IOException, ClassNotFoundException {
        Multiplex multiplex = new Multiplex(false);
        multiplex.Start();
        MainMenu mainMenu = new MainMenu(multiplex);
        mainMenu.registrarJugador();
        assert (Multiplex.getClientes().size() == 1);
    }

    @Test
    public void registrarOperador() throws IOException, ClassNotFoundException {
        Multiplex multiplex = new Multiplex(false);
        multiplex.Start();
        MainMenu mainMenu = new MainMenu(multiplex);
        mainMenu.registrarOperador();
        assert (Multiplex.getOperadores().size() == 1);
    }
}