package org.example.command;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserMenu {
    private Map<Integer, ICommand> commands = new HashMap<>();

    public void addCommand(int option, ICommand command) {
        commands.put(option, command);
    }

    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        int option;

        while (true) {
            System.out.println("\nSeleccione una opcion:");
            System.out.println("1. Agregar restaurante");
            System.out.println("2. Eliminar restaurante");
            System.out.println("4. Agregar plato a un menú");
            System.out.println("0. Salir");

            option = scanner.nextInt();

            if (option == 0) {
                break;
            }

            ICommand command = commands.get(option);
            if (command != null) {
                command.execute();
            } else {
                System.out.println("Opcion no valida.");
            }
        }
    }
}
