public abstract class Operation {
    private Multiplex multiplex;

    public Operation(Multiplex multiplex) {
        this.multiplex = multiplex;
    }

    public Multiplex getMultiplex() {
        return multiplex;
    }

    public abstract void doOperation();
}
