package main;

import java.io.IOException;

abstract sealed class Operation permits MainMenu, PerformCombat {
    private Multiplex multiplex;

    public Operation(Multiplex multiplex) {
        this.multiplex = multiplex;
    }

    protected Operation() {
    }

    public Multiplex getMultiplex() {
        return multiplex;
    }

    public abstract void doOperation() throws IOException;
}
