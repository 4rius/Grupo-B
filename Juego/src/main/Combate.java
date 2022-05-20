package main;

import java.io.Serial;
import java.io.Serializable;

public class Combate implements Serializable {

    @Serial
    private static final long serialVersionUID = 3L;

    private Cliente duelista1;
    private Cliente duelista2;
    private int oro;
    private int estado;
    private int rondas;
    private String Fecha;
    private Cliente vencedor;
    private boolean esbirrosVivos;
    private boolean esbirrosVivos1;
    private boolean esbirrosVivos2;

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

    public boolean isEsbirrosVivos1() {
        return esbirrosVivos1;
    }

    public boolean isEsbirrosVivos2() {
        return esbirrosVivos2;
    }

    public void setEsbirrosVivos1(boolean esbirrosVivos1) {
        this.esbirrosVivos1 = esbirrosVivos1;
    }

    public void setEsbirrosVivos2(boolean esbirrosvivos2) {
        this.esbirrosVivos2 = esbirrosvivos2;
    }

    public void terminado(Cliente ganador, Cliente perdedor) {;
        ganador.getNotificador().notificar("Ha terminado un desafío al que estás suscrito, \n" + ganador.getNick() + " ha ganado la batalla contra " + perdedor.getNick() + "\nse han jugado " + this.getRondas() + " rondas" + "\nse ha apostado " + this.getOro() + " oro\n");
        perdedor.getNotificador().notificar("Ha terminado un desafío al que estás suscrito, \n" + ganador.getNick() + " ha ganado la batalla contra " + perdedor.getNick() + "\n e han jugado " + this.getRondas() + " rondas" + "\nse ha apostado " + this.getOro() + " oro\n");
        ganador.setOverall(ganador.getOverall() + 1);
        perdedor.setOverall(perdedor.getOverall() - 1);
        ganador.getPersonaje().setOro(ganador.getPersonaje().getOro() + this.getOro());
        perdedor.getPersonaje().setOro(perdedor.getPersonaje().getOro() - this.getOro());
        if (perdedor.getPersonaje().getOro() < 0) {
            perdedor.getPersonaje().setOro(0);
        }
    }


    public Cliente getPerdedor() {
        if (duelista1.getNick().equals(vencedor.getNick())) {
            return duelista2;
        } else {
            return duelista1;
        }
    }
}
