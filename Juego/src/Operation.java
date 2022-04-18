import java.io.IOException;

public abstract class Operation {
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
