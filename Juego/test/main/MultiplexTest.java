package main;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MultiplexTest {

    @Test
    void inicializarInventario() throws IOException, ClassNotFoundException {
        Multiplex m = new Multiplex(false);
        assert(Multiplex.getListaArmaduras().size()>0 && Multiplex.getListaArmas().size()>0);
    }
}