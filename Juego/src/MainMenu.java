import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainMenu extends Operation {
    private final ArrayList<Operation> OperationList;

    public MainMenu(Multiplex multiplex, ArrayList<Operation> operationList) {
        super(multiplex);
        OperationList = operationList;
    }

    @Override
    public void doOperation() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Bienvenido al competidor del mismisimo The Witcher");
        System.out.println("1. Iniciar sesión");
        System.out.println("2. Registrarse");
        System.out.println("3. Salir");
        System.out.println("Introduzca una opción: ");
        int opcion = 0;
        try { //Se hace asi porque es heredada de Operation
            opcion = Integer.parseInt(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        switch (opcion) {
            case 1 -> iniciarSesion();
            case 2 -> registrarse();
            case 3 -> System.out.println("Saliendo...");
            default -> {
                System.out.println("Eso no es una opción válida");
                doOperation();
            }
        }

    }

    private void registrarse() {
    }

    private void iniciarSesion() {
    }

    public void mainMenu(String nick){
        if (nick.equals("null")) {
            System.out.println("Ocurrió un problema con su usuario/contraseña");
            this.doOperation();
        } else {
            System.out.println("Bienvenido " + nick);
            if (Multiplex.getNumerosderegistro().contains(nick)) {  //Si es un cliente
                //Imprimir todas las operaciones marcadas como cliente
            } else { //Si es un operador
                //Imprimir todas las operaciones marcadas como operador
            }

        }
    }
}
