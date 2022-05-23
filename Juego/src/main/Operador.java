package main;

import main.Datos.*;

import java.io.*;
import java.util.ArrayList;

final class Operador implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private String Nombre;
    private String Nick;
    private final String Contraseña;

    public Operador(String nombre, String nick, String contraseña) {
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



    public void editarDatosPersonaje() throws IOException {
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        System.out.println("Modo edicion de datos del personaje");
        System.out.println("Escriba el nombre de usuario del personaje a editar");
        for(String nick : Multiplex.getClientes().keySet()){
            System.out.println(nick);
        }
        String user = br.readLine();
        if (Multiplex.getClientes().containsKey(user)){
            if (Multiplex.getClientes().get(user).getPersonaje() != null){
                System.out.println("Modificando al personaje de: " + user);
                System.out.println("1. Modificar nombre");
                System.out.println("2. Modificar oro");
                System.out.println("3. Modificar salud");
                System.out.println("4. Modificar poder");
                System.out.println("5. Modificar habilidadEspecial");
                System.out.println("6. Cancelar");
                int opcion = Integer.parseInt(br.readLine());
                switch (opcion){
                    case 1 -> {
                        System.out.println("Nuevo nombre: ");
                        String nombre = br.readLine();
                        Multiplex.getClientes().get(user).getPersonaje().setNombre(nombre);
                        Multiplex.serialize();
                    }
                    case 2 -> {
                        System.out.println("Nueva cantidad de oro: ");
                        int oro = Integer.parseInt(br.readLine());
                        Multiplex.getClientes().get(user).getPersonaje().setOro(oro);
                        Multiplex.serialize();

                    }
                    case 3 -> {
                        System.out.println("Nueva cantidad de salud: ");
                        int salud = Integer.parseInt(br.readLine());
                        Multiplex.getClientes().get(user).getPersonaje().setSalud(salud);
                        Multiplex.serialize();
                    }
                    case 4 -> {
                        System.out.println("Nueva cantidad de poder: ");
                        int poder = Integer.parseInt(br.readLine());
                        Multiplex.getClientes().get(user).getPersonaje().setPoder(poder);
                        Multiplex.serialize();
                    }
                    case 5 -> {
                        int coste;
                        System.out.println("Nueva habilidad especial: ");
                        System.out.println("Escriba el nuevo nombre de la habilidad especial");
                        String nombre = br.readLine();
                        System.out.println("Introduzca el ataque de la habilidad especial");
                        int atq = Integer.parseInt(br.readLine());
                        System.out.println("Introduzca la  defensa de la habilidad especial");
                        int def = Integer.parseInt(br.readLine());
                        if(Multiplex.getClientes().get(user).getPersonaje().getHabilidadEspecial() instanceof Disciplina){
                            System.out.println("Indique el coste de sangre de la habilidad especial");
                            coste = Integer.parseInt(br.readLine());
                            Multiplex.getClientes().get(user).getPersonaje().setHabilidadEspecial(new Disciplina(nombre, atq,def, coste));
                            }
                        else if(Multiplex.getClientes().get(user).getPersonaje().getHabilidadEspecial() instanceof Don){
                            System.out.println("Indique el minimo de rabia de la habilidad especial");
                            coste = Integer.parseInt(br.readLine());
                            Multiplex.getClientes().get(user).getPersonaje().setHabilidadEspecial(new Disciplina(nombre, atq,def, coste));

                        }else{
                            System.out.println("Indique a que edad el cazador adquirió la habilidad especial");
                            coste = Integer.parseInt(br.readLine());
                            Multiplex.getClientes().get(user).getPersonaje().setHabilidadEspecial(new Disciplina(nombre, atq,def, coste));
                        }
                        Multiplex.serialize();

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

    public void eliminarPersonaje() throws IOException {
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        System.out.println("Eliminación de personajes");
        System.out.println("Qué usuario tiene el personaje a eliminar?");
        String user = br.readLine();
        if (Multiplex.getClientes().containsKey(user)){
            if (Multiplex.getClientes().get(user).getPersonaje() != null){
                System.out.println("Eliminando al personaje de: " + user);
                Multiplex.getClientes().get(user).setPersonaje(null);
                Multiplex.serialize();
            } else {
                System.out.println("El usuario no tiene un personaje");
            }
        } else {
            System.out.println("El usuario no existe");
        }
    }

    public void validarDesafios() throws IOException {
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        if (Multiplex.getDesafios().size() > 0){
            System.out.println("Desafios a validar: ");
            for (int i = 0; i < Multiplex.getDesafios().size(); i++){
                if (Multiplex.getDesafios().get(i).getEstado() == 0){
                    System.out.println("Desafio: " + i + ". " + Multiplex.getDesafios().get(i).getDuelista1().getNick() + " vs " + Multiplex.getDesafios().get(i).getDuelista2().getNick());
                    System.out.println("Fortalezas y debilidades del usuario desafiante: ");
                    for (int j = 0; j < Multiplex.getDesafios().get(i).getDuelista1().getPersonaje().getModificadores().size(); j++){
                        System.out.println(Multiplex.getDesafios().get(i).getDuelista1().getPersonaje().getModificadores().get(j).getNombre() + ": " + Multiplex.getDesafios().get(i).getDuelista1().getPersonaje().getModificadores().get(j).getMod());
                    }
                    System.out.println("Fortalezas y debilidades del usuario desafiado: ");
                    for (int j = 0; j < Multiplex.getDesafios().get(i).getDuelista2().getPersonaje().getModificadores().size(); j++){
                        System.out.println(Multiplex.getDesafios().get(i).getDuelista2().getPersonaje().getModificadores().get(j).getNombre() + ": " + Multiplex.getDesafios().get(i).getDuelista2().getPersonaje().getModificadores().get(j).getMod());
                    }
                    System.out.println("Puede modificar estas en sus menús correspondientes");
                    System.out.println("Última vez que el usuario desafiado perdió un desafío: " + Multiplex.getDesafios().get(i).getDuelista2().getUltimapartidaperdida()); //Para saber si hay que banearlo
                }
            }
            System.out.println("Numero del desafío a validar: ");
            int opcion = Integer.parseInt(br.readLine());
            if (opcion < Multiplex.getDesafios().size()){
                Multiplex.getDesafios().get(opcion).setEstado(1);
                System.out.println("Desafío validado");
                Multiplex.getDesafios().get(opcion).getDuelista2().getNotificacion().add(Multiplex.getDesafios().get(opcion).getDuelista1().getNick() + " Te ha desafiado!");
                Multiplex.getDesafios().get(opcion).getDuelista2().setDesafiospendientes(Multiplex.getDesafios().get(opcion).getDuelista2().getDesafiospendientes() + 1);
                Multiplex.serialize();
            }
        } else {
            System.out.println("No hay desafios pendientes");
        }
    }

    public void banearJugador() throws IOException {
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        System.out.println("Baneo de jugadores - Usuarios registrados no baneados:");
        for (String nick : Multiplex.getClientes().keySet()){
            if (!Multiplex.getClientes().get(nick).isBanned()) {
                System.out.println(nick);
            }
        }
        System.out.println("Nick del usuario a banear: ");
        String user = br.readLine();
        if (Multiplex.getClientes().containsKey(user)){
            Multiplex.getClientes().get(user).setBanned(true);
            System.out.println("Usuario baneado");
            Multiplex.serialize();
        } else {
            System.out.println("El usuario no existe");
        }
    }

    public void desbanearJugador() throws IOException {
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        System.out.println("Desbaneo de jugadores - Usuarios baneados:");
        for (String nick : Multiplex.getClientes().keySet()){
            if (Multiplex.getClientes().get(nick).isBanned()) {
                System.out.println(nick);
            }
        }
        System.out.println("Nick del usuario a desbanear: ");
        String user = br.readLine();
        if ((Multiplex.getClientes().containsKey(user)) && (Multiplex.getClientes().get(user).isBanned())){
            Multiplex.getClientes().get(user).setBanned(false);
            System.out.println("Usuario desbaneado");
            Multiplex.serialize();
        } else {
            System.out.println("El usuario no existe o no está baneado");
        }
    }

    public void editarEquipoPersonaje() throws IOException {
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        System.out.println("Modo edicion de equipo del personaje");
        System.out.println("Escriba el nombre de usuario del personaje a editar");
        for(String nick : Multiplex.getClientes().keySet()){
            if(Multiplex.getClientes().get(nick).getPersonaje() != null){
                System.out.println(nick);
            }
        }
        String user = br.readLine();
        if (Multiplex.getClientes().containsKey(user)){
            int opt = 0;

            int eleccion;
            if (Multiplex.getClientes().get(user).getPersonaje() != null) {

                while (opt != 5) {
                    int opt1 = 0;

                    System.out.println("Que quiere cambiar o añadir?");
                    System.out.println("1.Arma");
                    System.out.println("2.Armadura");
                    System.out.println("3.Editar modificadores");
                    System.out.println("4.Esbirros");
                    System.out.println("5.Salir");
                    opt = Integer.parseInt(br.readLine());
                    switch (opt) {
                        case 1 -> {

                            while (opt1 != 4) {
                                System.out.println("Que desea elegir:");
                                System.out.println("1. Arma principal");
                                System.out.println("2. Arma secundaria");
                                System.out.println("3. Arma a dos manos");
                                System.out.println("4. Salir");
                                opt1 = Integer.parseInt(br.readLine());

                                switch (opt1) {
                                    case 1 -> {
                                        System.out.println("-------------------------------------------");
                                        System.out.println("Lista de Armas a una mano");
                                        System.out.println("-------------------------------------------");
                                        for (Arma arma : Multiplex.getListaArmas()) {
                                            if (!arma.isAdosmanos()) {
                                                System.out.println(Multiplex.getListaArmas().indexOf(arma) + ".");
                                                System.out.println("Nombre:" + arma.getNombre());
                                                System.out.println("Ataque: " + arma.getModataque());
                                                System.out.println("Defensa: " + arma.getModdef()+"\n");

                                            }
                                        }
                                        do {
                                            System.out.println("Elija un arma principal: ");
                                            eleccion = Integer.parseInt(br.readLine());
                                        } while (Multiplex.getListaArmas().get(eleccion).isAdosmanos());
                                        Multiplex.getClientes().get(user).getPersonaje().setArmaActual1(Multiplex.getListaArmas().get(eleccion));
                                        Multiplex.serialize();

                                    }
                                    case 2 -> {
                                        System.out.println("-------------------------------------------");
                                        System.out.println("Lista de Armas a una mano");
                                        System.out.println("-------------------------------------------");
                                        for (Arma arma : Multiplex.getListaArmas()) {
                                            if (!arma.isAdosmanos()) {
                                                System.out.println(Multiplex.getListaArmas().indexOf(arma) + ".");
                                                System.out.println("Nombre:" + arma.getNombre());
                                                System.out.println("Ataque: " + arma.getModataque());
                                                System.out.println("Defensa: " + arma.getModdef()+"\n");


                                            }
                                        }
                                        do {
                                            System.out.println("Elija un arma secundaria: ");
                                            eleccion = Integer.parseInt(br.readLine());
                                        } while (Multiplex.getListaArmas().get(eleccion).isAdosmanos());
                                        Multiplex.getClientes().get(user).getPersonaje().setArmaActual2(Multiplex.getListaArmas().get(eleccion));
                                        Multiplex.serialize();
                                    }

                                    case 3 -> {
                                        System.out.println("-------------------------------------------");
                                        System.out.println("Lista de Armas a dos manos");
                                        System.out.println("-------------------------------------------");
                                        for (Arma arma : Multiplex.getListaArmas()) {
                                            if (arma.isAdosmanos()) {
                                                System.out.println(Multiplex.getListaArmas().indexOf(arma) + ".");
                                                System.out.println("Nombre:" + arma.getNombre());
                                                System.out.println("Ataque: " + arma.getModataque());
                                                System.out.println("Defensa: " + arma.getModdef()+"\n");

                                            }
                                        }
                                        do {
                                            System.out.println("Elija un arma a dos manos: ");
                                            eleccion = Integer.parseInt(br.readLine());
                                        } while (!Multiplex.getListaArmas().get(eleccion).isAdosmanos());
                                        Multiplex.getClientes().get(user).getPersonaje().setArmaActual1(Multiplex.getListaArmas().get(eleccion));
                                        Multiplex.getClientes().get(user).getPersonaje().setArmaActual2(null);
                                        Multiplex.serialize();
                                    }
                                }

                            }
                        }
                        case 2 -> {
                            System.out.println("-------------------------------------------");
                            System.out.println("Lista de Armaduras");
                            System.out.println("-------------------------------------------");
                            for (Armadura armadura : Multiplex.getListaArmaduras()) {
                                System.out.println(Multiplex.getListaArmaduras().indexOf(armadura) + ".");
                                System.out.println("Nombre:" + armadura.getNombre());
                                System.out.println("Ataque: " + armadura.getModataque());
                                System.out.println("Defensa: " + armadura.getModdef()+"\n");

                            }
                            do {
                                System.out.println("Elija una armadura:");
                                eleccion = Integer.parseInt(br.readLine());
                            } while (eleccion < 0 || eleccion > Multiplex.getListaArmaduras().size() - 1);
                            Multiplex.getClientes().get(user).getPersonaje().setArmaduraActual(Multiplex.getListaArmaduras().get(eleccion));
                            Multiplex.serialize();
                        }
                        case 3 -> editarModificador(user);
                        case 4 -> editarEsbirros(user);
                        case 5->    System.out.println("Volviendo al menú principal");
                        default -> System.out.println("Número incorrecto. Introduzca número del 1 al 5");
                    }
                }
            } else {
                System.out.println("El usuario no tiene un personaje registrado");
            }
        } else {
            System.out.println("El usuario no existe");
        }
    }

    private void editarEsbirros(String user) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Quieres añadir o eliminar los esbirros a este jugador?\n1. Eliminar\n2. Añadir");
        int eleccion = Integer.parseInt(br.readLine());
        switch (eleccion) {
            case 1 -> {
                Multiplex.getClientes().get(user).getPersonaje().getEsbirros().clear();
                System.out.println("El personaje ya no tiene esbirros");
                Multiplex.serialize();
            }
            case 2 -> {
                System.out.println("Qué tipo de esbirro quieres añadir?\n1. Demonio\n2. Ghoul\n3. Humano");
                int eleccion2 = Integer.parseInt(br.readLine());
                switch (eleccion2) {
                    case 1 -> {
                        System.out.println("Escriba el nuevo nombre");
                        String n = br.readLine();
                        System.out.println("Escriba el pacto");
                        String pacto = br.readLine();
                        System.out.println("Introduzca la salud entre 1 y 5");
                        int salud;
                        do{
                             salud = Integer.parseInt(br.readLine());
                        } while (salud<1 || salud>5);


                        Multiplex.getClientes().get(user).getPersonaje().getEsbirros().add(new Demonio(n, salud, pacto));

                        System.out.println("El personaje tiene un nuevo esbirro");
                        Multiplex.serialize();
                    }
                    case 2 -> {
                        System.out.println("Escriba el nuevo nombre");
                        String n = br.readLine();
                        System.out.println("Escriba la dependencia entre 1 y 5");
                        int dep;
                        do{
                            dep = Integer.parseInt(br.readLine());
                        } while (dep<1 || dep>5);
                        System.out.println("Introduzca la salud entre 1 y 5");
                        int salud;
                        do{
                            salud = Integer.parseInt(br.readLine());
                        } while (salud<1 || salud>5);

                        Multiplex.getClientes().get(user).getPersonaje().getEsbirros().add(new Ghoul(n, salud, dep));
                        System.out.println("El personaje tiene un nuevo esbirro");
                        Multiplex.serialize();
                    }
                    case 3 -> {
                        System.out.println("Escriba el nuevo nombre");
                        String n = br.readLine();
                        System.out.println("Introduzca la salud entre 1 y 5");
                        int salud;
                        do{
                            salud = Integer.parseInt(br.readLine());
                        } while (salud<1 || salud>5);
                        Multiplex.getClientes().get(user).getPersonaje().getEsbirros().add(new Humano(n, salud));
                        int lealtad;
                        do{
                            lealtad = Integer.parseInt(br.readLine());
                        } while (lealtad<1 || lealtad>5);
                        switch (lealtad) {
                            case 1 -> ((Humano) Multiplex.getClientes().get(user).getPersonaje().getEsbirros().get(Multiplex.getClientes().get(user).getPersonaje().getEsbirros().size() - 1)).setNivellealtad(Humano.lealtad.low);
                            case 2 -> ((Humano) Multiplex.getClientes().get(user).getPersonaje().getEsbirros().get(Multiplex.getClientes().get(user).getPersonaje().getEsbirros().size() - 1)).setNivellealtad(Humano.lealtad.medium);
                            case 3 -> ((Humano) Multiplex.getClientes().get(user).getPersonaje().getEsbirros().get(Multiplex.getClientes().get(user).getPersonaje().getEsbirros().size() - 1)).setNivellealtad(Humano.lealtad.high);
                        }
                        System.out.println("El personaje tiene un nuevo esbirro");
                        Multiplex.serialize();
                    }
                }
            }

        }
    }

    public void eliminarCuenta() throws IOException {
        System.out.println("Escribe tu contraseña para confirmar la eliminación de tu cuenta");
        System.out.println("Da cualquier otra entrada para cancelar");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String contrasena = br.readLine();
        if (contrasena.equals(this.Contraseña)) {
            Multiplex.getOperadores().remove(this.getNick(), this);
            Multiplex.serialize();
            System.out.println("Cuenta eliminada");
        } else {
            System.out.println("Contraseña incorrecta, operación cancelada");
        }
    }

    public void editarModificador(String user) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Quieres añadir o modificar un modificador?\n1. Añadir\n2. Modificar\n3. Ver\n4. Salir" );
        int eleccion = Integer.parseInt(br.readLine());
        switch (eleccion) {
            case 1 -> {
                System.out.println("Escribe el nombre del modificador");
                String nombre = br.readLine();
                System.out.println("Escribe el valor del modificador");
                int valor = Integer.parseInt(br.readLine());
                System.out.println("Escribe el tipo del modificador");
                System.out.println("1->Fortaleza||2->Debilidad");
                int tipo = Integer.parseInt(br.readLine());
                if (0 < tipo && tipo < 3) {
                    Multiplex.getClientes().get(user).getPersonaje().getModificadores().add(new Modificador(nombre, valor, tipo));
                    Multiplex.serialize();
                } else {
                    System.out.println("Tipo de modificador incorrecto");
                }
            }
            case 2 -> {
                int valor = 0;
                ArrayList<Modificador> modificadores = Multiplex.getClientes().get(user).getPersonaje().getModificadores();
                System.out.println("Elige un modificador para editar");
                for (int i = 0; i < modificadores.size(); i++) {
                    System.out.println(i + ". " + modificadores.get(i).getNombre());
                    System.out.println("Valor: " + modificadores.get(i).getMod());
                }
                int opcion = Integer.parseInt(br.readLine());
                System.out.println("Escriba el nuevo nombre del modificador");
                String n = br.readLine();
                modificadores.get(opcion).setNombre(n);
                System.out.println("Escribe el nuevo valor del modificador entre 1 y 5");
                do{
                valor = Integer.parseInt(br.readLine());
                } while (valor<0 && valor>5);
                modificadores.get(opcion).setMod(valor);
                System.out.println("Escribe el nuevo tipo del modificador");
                System.out.println("1->Fortaleza||2->Debilidad");
                do{
                    valor = Integer.parseInt(br.readLine());
                } while (valor<1 && valor>2);
                Multiplex.serialize();

            }
            case 3->{
                ArrayList<Modificador> modificadores = Multiplex.getClientes().get(user).getPersonaje().getModificadores();
                System.out.println("Elige un modificador para editar");
                for (int i = 0; i < modificadores.size(); i++) {
                    System.out.println("");
                    System.out.println(i + ". " + modificadores.get(i).getNombre());
                    System.out.println("Valor: " + modificadores.get(i).getMod());
                    if (modificadores.get(i).isTipomod()==1){
                        System.out.println("Tipo: Fortaleza");

                    }
                    else {
                        System.out.println("Tipo: Debilidad");
                    }
                }
                System.out.println(" ");

            }
            case 4-> System.out.println("Volviendo al menu");

        }
    }
    }





