package org.example;

import org.example.command.ICommand;
import org.example.command.UserMenu;
import org.example.command.restaurant.AddRestaurantCommand;
import org.example.command.utils.IHandler;
import org.example.controllers.RestaurantController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.mockito.Mockito.*;

class UserMenuTest {

    private RestaurantController mockRestaurantController;  // Mock del controlador de restaurante
    private UserMenu menu;
    private IHandler mockHandler;

    @BeforeEach
    void setUp() {
        mockHandler = mock(IHandler.class);
        mockRestaurantController = mock(RestaurantController.class);

        menu = new UserMenu(mockHandler);

        menu.addCommand(1, new AddRestaurantCommand(mockRestaurantController, mockHandler));
    }

    @Test
    @DisplayName("Caso 1 del Menú: Agregar Restaurante")
    void testMenu1() {
        when(mockHandler.readLine())
                .thenReturn("1", "Margaret", "Calle Flor", "Lebrija", "0");

        menu.showMenu();

        InOrder inOrder = inOrder(mockHandler, mockRestaurantController);

        inOrder.verify(mockHandler).writeLine("\nSeleccione una opcion:");
        inOrder.verify(mockHandler).writeLine("1. Agregar restaurante");
        inOrder.verify(mockHandler).writeLine("2. Editar informacion de un restaurante");
        inOrder.verify(mockHandler).writeLine("3. Eliminar restaurante");
        inOrder.verify(mockHandler).writeLine("4. Ver restaurantes disponibles");
        inOrder.verify(mockHandler).writeLine("5. Agregar plato a un menu");
        inOrder.verify(mockHandler).writeLine("6. Editar menu");
        inOrder.verify(mockHandler).writeLine("7. Eliminar plato de un menu");
        inOrder.verify(mockHandler).writeLine("8. Ver menu de un restaurante");
        inOrder.verify(mockHandler).writeLine("9. Crear review para restaurante");
        inOrder.verify(mockHandler).writeLine("10. Crear review para plato");
        inOrder.verify(mockHandler).writeLine("11. Ver lista de reviews de un restaurante");
        inOrder.verify(mockHandler).writeLine("12. Ver lista de reviews de un plato");
        inOrder.verify(mockHandler).writeLine("0. Salir");
        // ... (los demás textos del menú)

        inOrder.verify(mockHandler).writeLine("Ingrese el nombre del restaurante: ");
        inOrder.verify(mockHandler).writeLine("Ingrese la direccion: ");
        inOrder.verify(mockHandler).writeLine("Ingrese la ciudad: ");

        inOrder.verify(mockRestaurantController).addRestaurant("Margaret", "Calle Flor", "Lebrija");
    }
}