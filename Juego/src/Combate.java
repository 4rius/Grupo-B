public class Combate {

    public void terminado(Cliente ganador, Cliente perdedor) {
        ganador.getNotificador().notificar(ganador.getNick() + " ha ganado la batalla!" + "\n" + "Detalles:");
        perdedor.getNotificador().notificar(perdedor.getNick() + " ha perdido la batalla!" + "\n" + "Detalles:");
        //Esto cuantdo el combate esté implementado lo termino ^4r
    }
}
