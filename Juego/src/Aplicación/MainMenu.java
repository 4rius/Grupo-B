package Aplicación;

import java.util.List;

public class MainMenu extends Operation{
    private List<Operation> OperationList;

    public MainMenu(Multiplex multiplex, List<Operation> operationList) {
        super(multiplex);
        OperationList = operationList;
    }

    @Override
    public void doOperation() {

    }
}
