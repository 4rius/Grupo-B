import Datos.Esbirro;

import java.util.List;

public class PerformCombat extends Operation {
    private Cliente duelista1;
    private Cliente duelista2;
    private int oro; // Oro que apuestan los jugadores
    private int estado;
    private int rondas;
    private String Fecha;
    private Cliente vencedor;
    private boolean Esbirrosvivos;

    public PerformCombat(Multiplex multiplex, Cliente duelista1) {
        super(multiplex);
        this.duelista1 = duelista1;
        try {
            String duelista2 = System.console().readLine("A quién va a desafiar? ");
            if (Multiplex.getClientes().containsKey(duelista2)) {
                this.duelista2 = Multiplex.getClientes().get(duelista2);
                System.out.println("Introduzca el oro que quiere apostar: ");
                int oroo = Integer.parseInt(System.console().readLine());
                if (Multiplex.getClientes().get(duelista1).getOro() >= oroo) {
                    this.oro = oroo;
                } else {
                    System.out.println("No tiene suficiente oro, desafío cancelado");
                }
            } else {
                throw new RuntimeException();
            }
        } catch (RuntimeException e) {
            System.out.println("No existe el cliente, desafío cancelado");
        }
        estado = 0;
        rondas = Integer.parseInt(System.console().readLine());
        Fecha = System.currentTimeMillis() + "";
        vencedor = null;
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
        return estado;
    }

   public void setEstado(int estado) {
        estado = estado;
    }

    public int getRondas() {
        return rondas;
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

    public boolean isEsbirrosvivos() {
        return Esbirrosvivos;
    }

    public int getOro() {
        return oro;
    }

    @Override
    public void doOperation() {
        rondas = 0;
        int hp_Esb1 = saludEsbirros(duelista1);
        int hp_Per1 = duelista1.getPeronaje().getSalud();
        int hp_Esb2 = saludEsbirros(duelista2);
        int hp_Per2 = duelista2.getPeronaje().getSalud();
        while(estado != 0){
            int atk1 = calcularAtk(duelista1);
            int atk2 = calcularAtk(duelista2);
            int def1 = calcularDef(duelista1);
            int def2 = calcularDef(duelista2);
            atk1 = rolearDados(atk1);
            atk2 = rolearDados(atk2);
            def1 = rolearDados(def1);
            def2 = rolearDados(def2);
            if (atk1 >= def2){
                if (hp_Esb1 > 0){
                    hp_Esb1--;
                }
                else {
                    hp_Per1--;
                }
            }

            if (atk2 >= def1){
                if (hp_Esb2 > 0){
                    hp_Esb2--;
                }
                else{
                    hp_Per2--;
                }
            }
            comprobarEstado(hp_Per1, hp_Per2);
            rondas++;
        }
    }

    public int saludEsbirros(Cliente c){
        int hp = 0;
        List<Esbirro> eList = c.getPersonaje().getEsbirros();
        for (Esbirro e: eList) {
            hp = hp + e.getSalud();
        }
        return hp;
    }

    public int calcularAtk(Cliente c){
        int atk = c.getPersonaje().atkTotal();
        return atk;
    }

    public int calcularDef(Cliente c){
        int def = c.getPersonaje().defTotal();
        return def;
    }

    public int rolearDados(int n){
        int k = 0;
        for(int i = 1; i>n; i++){
            int random = (int) Math.floor(Math.random()*6+1);
            if (random == 5 || random == 6){
                k = k++;
            }
        }
        return k;
    }

    public void comprobarEstado(int hp1, int hp2){
        if (hp1 <= 0 || hp2 <= 0){
            if (hp1 > 0){
                vencedor = duelista2;
            }
            else if (hp2 > 0){
                vencedor = duelista1;
            }
            else{
                vencedor = null;
            }
            estado = 0;
        }
    }
}
