package main;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MultiplexTest {

    @BeforeAll
    static void setup() throws IOException, ClassNotFoundException {
        Multiplex m = new Multiplex(false);
    }

    @Test
    void inicializarInventario() {
        assert(Multiplex.getListaArmaduras().size()>0 && Multiplex.getListaArmas().size()>0);
    }

    @Test
    void serializar() throws IOException {
        File file = new File("././Assets/estado.bin");
        assertFalse(file.exists());
        Multiplex.serialize();
        assertTrue(file.exists());
        file.delete();
    }
}