import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PerformCombat extends Operation {
    private Cliente duelista1;
    private Cliente duelista2;
    private int Estado;
    private int Rondas;
    private String Fecha;
    private Cliente Vencedor;
    private boolean Esbirrosvivos;

    public PerformCombat(Multiplex multiplex, Cliente duelista1) {
        super(multiplex);
        this.duelista1 = duelista1;
        try {
            String duelista2 = System.console().readLine("A quién va a desafiar? ");
            if (Multiplex.getClientes().containsKey(duelista2)) {
                this.duelista2 = Multiplex.getClientes().get(duelista2);
            } else {
                throw new RuntimeException();
            }
        } catch (RuntimeException e) {
            System.out.println("No existe el cliente, desafío cancelado");
        }
        Estado = 0;
        Rondas = Integer.parseInt(System.console().readLine());
        Fecha = System.currentTimeMillis() + "";
        Vencedor = null;
        Esbirrosvivos = true; //Aqui hay que llamar  a algo que compruebe cuantos esbirros quedan
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
