package org.example.command;

import org.example.command.utils.IHandler;

import java.util.HashMap;
import java.util.Map;

public class UserMenu {
    private Map<Integer, ICommand> commands = new HashMap<>();
    private final IHandler handler;

    public UserMenu(IHandler handler) {
        this.handler = handler;
    }

    public void addCommand(int option, ICommand command) {
        commands.put(option, command);
    }

    public void showMenu() {

        while (true) {
            handler.writeLine("\nSeleccione una opcion:");
            handler.writeLine("1. Agregar restaurante");
            handler.writeLine("2. Editar informacion de un restaurante");
            handler.writeLine("3. Eliminar restaurante");
            handler.writeLine("4. Ver restaurantes disponibles");
            handler.writeLine("5. Agregar plato a un menu");
            handler.writeLine("6. Editar menu");
            handler.writeLine("7. Eliminar plato de un menu");
            handler.writeLine("8. Ver menu de un restaurante");
            handler.writeLine("9. Crear review para restaurante");
            handler.writeLine("10. Crear review para plato");
            handler.writeLine("11. Ver lista de reviews de un restaurante");
            handler.writeLine("12. Ver lista de reviews de un plato");
            handler.writeLine("0. Salir");

            int option;
            try {
                option = Integer.parseInt(handler.readLine());
            } catch (NumberFormatException e) {
                handler.writeLine("Ingrese opcion valida");
                continue;
            }

            if (option == 0) {
                handler.writeLine("Gracias. Vuelva pronto!");
                break;
            }

            ICommand command = commands.get(option);
            if (command != null) {
                command.execute();
            } else {
                handler.writeLine("Opcion no valida.");
            }
        }
    }
}
