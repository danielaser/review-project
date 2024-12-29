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
            System.out.println("2. Editar informacion de un restaurante");
            System.out.println("3. Eliminar restaurante");
            System.out.println("4. Ver restaurantes disponibles");
            System.out.println("5. Agregar plato a un menu");
            System.out.println("6. Editar menu");
            System.out.println("7. Eliminar plato de un menu");
            System.out.println("8. Ver menu de un restaurante");
            System.out.println("9. Crear review para restaurante");
            System.out.println("10. Crear review para plato");
            System.out.println("11. Ver lista de reviews de un restaurante");
            System.out.println("12. Ver lista de reviews de un plato");
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
