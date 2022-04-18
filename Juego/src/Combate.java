public class Combate {

    private Cliente duelista1;
    private Cliente duelista2;
    private int oro;
    private int estado;
    private int rondas;
    private String Fecha;
    private Cliente vencedor;
    private boolean esbirrosVivos;

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

    public int getOro() {
        return oro;
    }

    public void setOro(int oro) {
        this.oro = oro;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getRondas() {
        return rondas;
    }

    public void setRondas(int rondas) {
        this.rondas = rondas;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public Cliente getVencedor() {
        return vencedor;
    }

    public void setVencedor(Cliente vencedor) {
        this.vencedor = vencedor;
    }

    public boolean isEsbirrosVivos() {
        return esbirrosVivos;
    }

    public void setEsbirrosVivos(boolean esbirrosVivos) {
        this.esbirrosVivos = esbirrosVivos;
    }

    public void terminado(Cliente ganador, Cliente perdedor) {
        ganador.getNotificador().notificar(ganador.getNick() + " ha ganado la batalla!" + "\n" + "Detalles:");
        perdedor.getNotificador().notificar(perdedor.getNick() + " ha perdido la batalla!" + "\n" + "Detalles:");
        //Esto cuantdo el combate esté implementado lo termino ^4r
    }
}
