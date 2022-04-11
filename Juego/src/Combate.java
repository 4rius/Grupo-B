import java.util.ArrayList;

public class Combate {
    private ArrayList<PerformCombat> combates;

    public Combate(ArrayList<PerformCombat> combates) {
        this.combates = combates;
    }

    public void verHistorial(String NRegistro){
        for(PerformCombat combate: combates){
            if(Cliente.getnRegistro().equals(NRegistro)){
                System.out.println(combate.getDuelista1());
                System.out.println(combate.getDuelista2());
                System.out.println(combate.getVencedor());
                System.out.println(combate.getFecha());
                System.out.println(combate.getRondas());
            }
        }
    }
}
