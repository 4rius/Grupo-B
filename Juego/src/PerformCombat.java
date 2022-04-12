public class PerformCombat extends Operation {
    private Cliente duelista1;
    private Cliente duelista2;
    private int Estado;
    private int Rondas;
    private String Fecha;
    private Cliente Vencedor;
    private boolean Esbirrosvivos;

    public PerformCombat(Multiplex multiplex, Cliente duelista1, Cliente duelista2, int estado, int rondas, String fecha, Cliente vencedor, boolean esbirrosvivos) {
        super(multiplex);
        this.duelista1 = duelista1;
        this.duelista2 = duelista2;
        Estado = estado;
        Rondas = rondas;
        Fecha = fecha;
        Vencedor = vencedor;
        Esbirrosvivos = esbirrosvivos;
    }

    public Cliente getDuelista1() {
        return duelista1;
    }

    public void setDuelista1(Cliente duelista1) {
        this.duelista1 = duelista1;
    }

    public Cliente getDuelista2() {
        return duelista2;
    }

    public void setDuelista2(Cliente duelista2) {
        this.duelista2 = duelista2;
    }

    public int getEstado() {
        return Estado;
    }

    public void setEstado(int estado) {
        Estado = estado;
    }

    public int getRondas() {
        return Rondas;
    }

    public void setRondas(int rondas) {
        Rondas = rondas;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public Cliente getVencedor() {
        return Vencedor;
    }

    public void setVencedor(Cliente vencedor) {
        Vencedor = vencedor;
    }

    public boolean isEsbirrosvivos() {
        return Esbirrosvivos;
    }

    public void setEsbirrosvivos(boolean esbirrosvivos) {
        Esbirrosvivos = esbirrosvivos;
    }

    @Override
    public void doOperation() {

    }
}
