import Datos.Esbirro;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class PerformCombat extends Operation {

    private Combate combate;
    private Multiplex multiplex;

    public PerformCombat(Combate combate) {
        this.combate = combate;
    }

    public Combate getCombate() {
        return combate;
    }

    public void setCombate(Combate combate) {
        this.combate = combate;
    }

    @Override
    public void doOperation() {
        combate.setRondas(0);
        int hp_Esb1 = saludEsbirros(combate.getDuelista1());
        int hp_Per1 = combate.getDuelista1().getPersonaje().getSalud();
        int hp_Esb2 = saludEsbirros(combate.getDuelista2());
        int hp_Per2 = combate.getDuelista2().getPersonaje().getSalud();
        while(combate.getEstado() != 3){
            int atk1 = calcularAtk(combate.getDuelista1());
            int atk2 = calcularAtk(combate.getDuelista2());
            int def1 = calcularDef(combate.getDuelista1());
            int def2 = calcularDef(combate.getDuelista2());
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
            combate.setRondas(combate.getRondas() + 1);
        }
        combate.setEsbirrosVivos(this.isEsbirrosVivos(hp_Esb1, hp_Esb2));
    }

    public int saludEsbirros(@NotNull Cliente c){
        int hp = 0;
        if (c.getPersonaje().getEsbirros() != null) {
            ArrayList<Esbirro> eList = (ArrayList<Esbirro>) c.getPersonaje().getEsbirros();
            for (Esbirro e : eList) {
                hp = hp + e.getSalud();
            }
        }
        return hp;
    }

    public int calcularAtk(@NotNull Cliente c){
        return c.getPersonaje().atkTotal();
    }

    public int calcularDef(@NotNull Cliente c){
        return c.getPersonaje().defTotal();
    }

    public int rolearDados(int n){
        int k = 0;
        for(int i = 1; i>n; i++){
            int random = (int) Math.floor(Math.random()*6+1);
            if (random == 5 || random == 6){
                k++;
            }
        }
        return k;
    }

    public void comprobarEstado(int hp1, int hp2){
        if (hp1 <= 0 || hp2 <= 0){
            if (hp1 > 0){
                combate.setVencedor(combate.getDuelista1());
                combate.getDuelista2().getPersonaje().addOro(combate.getOro());
            }
            else if (hp2 > 0){
                combate.setVencedor(combate.getDuelista2());
                combate.getDuelista1().getPersonaje().addOro(combate.getOro());
            }
            else{
                combate.setVencedor(null);
            }
            combate.setEstado(3);
        }
    }

    public boolean isEsbirrosVivos(int hp1, int hp2){
        return hp1 != 0 || hp2 != 0;
    }
}
