import Datos.Personaje;

public class Operador extends Operation{
    private String Nombre;
    private String Nick;
    private String Contraseña;

    public Operador(Multiplex Multiplex, String nombre, String nick, String contraseña) {
        super(Multiplex);
        Nombre = nombre;
        Nick = nick;
        Contraseña = contraseña;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getNick() {
        return Nick;
    }

    public void setNick(String nick) {
        Nick = nick;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String contraseña) {
        Contraseña = contraseña;
    }

    public void editarPersonaje(){
        System.out.println("Modo edicion de personaje");
        System.out.println("Qué usuario tiene el personaje a modificar?");
        String user = System.console().readLine();
        if (Multiplex.getClientes().containsKey(user)){
            if (Multiplex.getClientes().get(user).getPersonaje() != null){
                System.out.println("Modificando al personaje de: " + user);
                System.out.println("1. Modificar nombre");
                System.out.println("2. Modificar oro");
                System.out.println("3. Modificar equipo");
                System.out.println("4. Modificar salud");
                System.out.println("5. Modificar poder");
                System.out.println("6. Modificar modificadores");
                System.out.println("7. Cancelar");
                int opcion = Integer.parseInt(System.console().readLine());
                switch (opcion){
                    case 1 -> {
                        System.out.println("Nuevo nombre: ");
                        String nnombre = System.console().readLine();
                        Multiplex.getClientes().get(user).getPersonaje().setNombre(nnombre);
                    }
                    case 2 -> {
                        System.out.println("Nueva cantidad de oro: ");
                        int oro = Integer.parseInt(System.console().readLine());
                        Multiplex.getClientes().get(user).getPersonaje().setOro(oro);
                    }
                    case 3 -> {

                    }
                    default -> System.out.println("Saliendo / Opción no válida");
                }

            } else {
                System.out.println("El usuario no tiene un personaje");
            }
        } else {
            System.out.println("El usuario no existe");
        }
    }

    public void eliminarPersonaje(){
        System.out.println("Eliminación de personajes");
        System.out.println("Qué usuario tiene el personaje a eliminar?");
        String user = System.console().readLine();
        if (Multiplex.getClientes().containsKey(user)){
            if (Multiplex.getClientes().get(user).getPersonaje() != null){
                System.out.println("Eliminando al personaje de: " + user);
                Multiplex.getClientes().get(user).setPersonaje(null);
            } else {
                System.out.println("El usuario no tiene un personaje");
            }
        } else {
            System.out.println("El usuario no existe");
        }
    }

    public void validarDesafios(){
        if (Multiplex.getDesafios().size() > 0){
            System.out.println("Desafios a validar: ");
            for (int i = 0; i < Multiplex.getDesafios().size(); i++){
                if (Multiplex.getDesafios().get(i).getEstado() == 0){
                    System.out.println("Desafio: " + i + " " + Multiplex.getDesafios().get(i).getDuelista1().getName() + " vs " + Multiplex.getDesafios().get(i).getDuelista2().getName());
                }
            }
            System.out.println("Numero del desafío a validar: ");
            int opcion = Integer.parseInt(System.console().readLine());
            if (opcion < Multiplex.getDesafios().size()){
                Multiplex.getDesafios().get(opcion).setEstado(1);
                System.out.println("Desafío validado");
            }
        } else {
            System.out.println("No hay desafios pendientes");
        }
    }

    @Override
    public void doOperation() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
