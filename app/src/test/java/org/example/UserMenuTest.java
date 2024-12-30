package org.example;

import org.example.command.UserMenu;
import org.example.command.menu.AddPlateCommand;
import org.example.command.menu.DeletePlateCommand;
import org.example.command.menu.EditMenuCommand;
import org.example.command.menu.GetMenuCommand;
import org.example.command.restaurant.AddRestaurantCommand;
import org.example.command.restaurant.DeleteRestaurantCommand;
import org.example.command.restaurant.EditRestaurantCommand;
import org.example.command.restaurant.GetRestaurantsCommand;
import org.example.command.review.AddPlateReviewCommand;
import org.example.command.review.AddRestaurantReviewCommand;
import org.example.command.review.GetReviewsPlate;
import org.example.command.review.GetReviewsRestaurant;
import org.example.command.utils.IHandler;
import org.example.controllers.MenuController;
import org.example.controllers.RestaurantController;
import org.example.controllers.ReviewController;
import org.example.models.Restaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.Map;
import static org.mockito.Mockito.*;

class UserMenuTest {

    private RestaurantController mockRestaurantController;
    private MenuController mockMenuController;
    private ReviewController mockReviewController;
    private UserMenu menu;
    private IHandler mockHandler;

    @BeforeEach
    void setUp() {
        mockHandler = mock(IHandler.class);
        mockRestaurantController = mock(RestaurantController.class);
        mockMenuController = mock(MenuController.class);
        mockReviewController = mock(ReviewController.class);

        menu = new UserMenu(mockHandler);
    }

    private void  verifyMenuOptions(InOrder inOrder){
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
    }

    @Test
    @DisplayName("Caso 1 del Menu: Agregar Restaurante")
    void testMenu1() {
        when(mockHandler.readLine())
                .thenReturn("1", "Margaret", "Calle Flor", "Lebrija", "0");

        menu.addCommand(1, new AddRestaurantCommand(mockRestaurantController, mockHandler));
        menu.showMenu();

        InOrder inOrder = inOrder(mockHandler, mockRestaurantController);
        verifyMenuOptions(inOrder);

        inOrder.verify(mockHandler).writeLine("Ingrese el nombre del restaurante: ");
        inOrder.verify(mockHandler).writeLine("Ingrese la direccion: ");
        inOrder.verify(mockHandler).writeLine("Ingrese la ciudad: ");

        inOrder.verify(mockRestaurantController).addRestaurant("Margaret", "Calle Flor", "Lebrija");
    }

    @Test
    @DisplayName("Caso 2 del Menu:  Eliminar restaurante")
    void testMenu2() {
        Map<String, Restaurant> mockRestaurantMap = Map.of(
                "Restaurante A", new Restaurant("Restaurante A", "Calle 1", "Ciudad A")
        );

        when(mockRestaurantController.getRestaurants()).thenReturn(mockRestaurantMap);
        when(mockHandler.readLine())
                .thenReturn("2", "Restaurante A", "Nuevo Restaurante A", "Nueva Calle 1", "Nueva Ciudad A", "0");

        menu.addCommand(2, new EditRestaurantCommand(mockRestaurantController, mockHandler));
        menu.showMenu();

        InOrder inOrder = inOrder(mockHandler, mockRestaurantController);

        verifyMenuOptions(inOrder);

        inOrder.verify(mockHandler).writeLine("Lista de restaurantes:");
        inOrder.verify(mockHandler).writeLine("- Restaurante A");
        inOrder.verify(mockHandler).writeLine("Ingrese el nombre del restaurante que desea editar: ");
        inOrder.verify(mockHandler).writeLine("Ingrese el nuevo nombre del restaurante: ");
        inOrder.verify(mockHandler).writeLine("Ingrese la nueva direccion: ");
        inOrder.verify(mockHandler).writeLine("Ingrese la ciudad actualizada ");

        inOrder.verify(mockRestaurantController).editRestaurant("Restaurante A", "Nuevo Restaurante A", "Nueva Calle 1", "Nueva Ciudad A");
    }
    @Test
    @DisplayName("Caso 3 del Menu: Editar informacion de un restaurante")
    void testMenu3() {
        Map<String, Restaurant> mockRestaurantMap = Map.of(
                "Restaurante A", new Restaurant("Restaurante A", "Calle 1", "Ciudad A")
        );

        when(mockRestaurantController.getRestaurants()).thenReturn(mockRestaurantMap);
        when(mockHandler.readLine()).thenReturn("3", "Restaurante A", "0");

        menu.addCommand(3, new DeleteRestaurantCommand(mockRestaurantController, mockHandler));

        menu.showMenu();

        InOrder inOrder = inOrder(mockHandler, mockRestaurantController);

        verifyMenuOptions(inOrder);

        inOrder.verify(mockHandler).writeLine("Lista de restaurantes:");
        inOrder.verify(mockHandler).writeLine("- Restaurante A");
        inOrder.verify(mockHandler).writeLine("Ingrese el nombre del restaurante que desea borrar: ");

        inOrder.verify(mockRestaurantController).deleteRestaurant("Restaurante A");
    }

    @Test
    @DisplayName("Caso 4 del Menu: Ver restaurantes disponibles")
    void testMenu4() {
        Map<String, Restaurant> mockRestaurantMap = Map.of(
                "Restaurante A", new Restaurant("Restaurante A", "Direccion A", "Ciudad A")
        );
        when(mockRestaurantController.getRestaurants()).thenReturn(mockRestaurantMap);

        when(mockHandler.readLine()).thenReturn("4", "0");

        menu.addCommand(4, new GetRestaurantsCommand(mockRestaurantController, mockHandler));

        menu.showMenu();

        InOrder inOrder = inOrder(mockHandler, mockRestaurantController);

        verifyMenuOptions(inOrder);

        inOrder.verify(mockHandler).writeLine("Lista de restaurantes disponibles:");
        inOrder.verify(mockHandler).writeLine("\nNombre: Restaurante A");
        inOrder.verify(mockHandler).writeLine("Direccion: Direccion A");
        inOrder.verify(mockHandler).writeLine("Ciudad: Ciudad A");
        inOrder.verify(mockHandler).writeLine("-----------------------");
    }

    @Test
    @DisplayName("Caso 5 del Menu: Agregar plato a un menu")
    void testMenu5() {
        when(mockHandler.readLine()).thenReturn("5", "Restaurante A", "Plato Especial", "29.99", "0");

        menu.addCommand(5, new AddPlateCommand(mockMenuController, mockHandler));
        menu.showMenu();

        InOrder inOrder = inOrder(mockHandler, mockMenuController);

        verifyMenuOptions(inOrder);

        inOrder.verify(mockHandler).writeLine("Ingrese el nombre del restaurante: ");
        inOrder.verify(mockHandler).writeLine("Ingrese el nombre del plato: ");
        inOrder.verify(mockHandler).writeLine("Ingrese el valor: ");

        inOrder.verify(mockMenuController).addPlateToRestaurant("Restaurante A", "Plato Especial", 29.99);
    }

    @Test
    @DisplayName("Caso 6 del Menu: Editar menu")
    void testMenu6() {
        String restaurantName = "Restaurante A";
        String plateName = "Plato Especial";
        String newPlateName = "Plato Especial Editado";
        Double newPrice = 35.99;

        when(mockHandler.readLine())
                .thenReturn("6", restaurantName, plateName, newPlateName, newPrice.toString(), "0");

        menu.addCommand(6, new EditMenuCommand(mockMenuController, mockHandler));
        menu.showMenu();

        InOrder inOrder = inOrder(mockHandler, mockMenuController);

        verifyMenuOptions(inOrder);

        inOrder.verify(mockHandler).writeLine("Ingrese el nombre del restaurante del cual desea editar su menu: ");
        inOrder.verify(mockHandler).writeLine("Ingrese el nombre del plato que desea editar: ");
        inOrder.verify(mockHandler).writeLine("Ingrese el nuevo nombre del plato: ");
        inOrder.verify(mockHandler).writeLine("Ingrese el nuevo precio del plato: ");

        inOrder.verify(mockMenuController).editPlateToRestaurant(restaurantName, plateName, newPlateName, newPrice);
    }

    @Test
    @DisplayName("Caso 7 del Menu: Eliminar plato de un menu")
    void testMenu7() {
        String restaurantName = "Restaurante A";
        String plateName = "Plato Especial";

        when(mockHandler.readLine())
                .thenReturn("7", restaurantName, plateName, "0");

        menu.addCommand(7, new DeletePlateCommand(mockMenuController, mockHandler));
        menu.showMenu();

        InOrder inOrder = inOrder(mockHandler, mockMenuController);

        verifyMenuOptions(inOrder);

        inOrder.verify(mockHandler).writeLine("Ingrese el nombre del restaurante al que pertenece el plato: ");
        inOrder.verify(mockHandler).writeLine("Ingrese el nombre del plato que desea eliminar: ");

        inOrder.verify(mockMenuController).deletePlateToRestaurant(restaurantName, plateName);
    }

    @Test
    @DisplayName("Caso 8 del Menu: Ver menu de un restaurante")
    void testMenu8(){
        String restaurantName = "Restaurante A";

        when(mockHandler.readLine())
                .thenReturn("8", restaurantName, "0");

        menu.addCommand(8, new GetMenuCommand(mockMenuController, mockHandler));
        menu.showMenu();

        InOrder inOrder = inOrder(mockHandler, mockMenuController);

        verifyMenuOptions(inOrder);

        inOrder.verify(mockHandler).writeLine("Ingrese el nombre del restaurante para ver el menu: ");

        inOrder.verify(mockMenuController).getMenuInRestaurant(restaurantName);
    }

    @Test
    @DisplayName("Caso 9 del Menu: Crear review para restaurante")
    void testMenu9(){
        String restaurantName = "Restaurante A";
        Double rating = 4.5;
        String comment = "Excelente comida y servicio.";

        when(mockHandler.readLine())
                .thenReturn("9", restaurantName, rating.toString(), comment, "0");

        menu.addCommand(9, new AddRestaurantReviewCommand(mockReviewController, mockHandler));
        menu.showMenu();

        InOrder inOrder = inOrder(mockHandler, mockReviewController);

        verifyMenuOptions(inOrder);

        inOrder.verify(mockHandler).writeLine("Ingrese el nombre del restaurante: ");
        inOrder.verify(mockHandler).writeLine("Ingrese la calificacion de 1.0 a 5.0: ");
        inOrder.verify(mockHandler).writeLine("Ingrese un comentario: ");

        inOrder.verify(mockReviewController).addReview(restaurantName, rating, comment);
    }

    @Test
    @DisplayName("Caso 10 del Menu: Crear review para plato")
    void testMenu10(){
        String restaurantName = "Restaurante A";
        String plateName = "Plato Especial";
        Double rating = 4.5;
        String comment = "Excelente sabor y presentación.";

        when(mockHandler.readLine())
                .thenReturn("10", restaurantName, plateName, rating.toString(), comment, "0");

        menu.addCommand(10, new AddPlateReviewCommand(mockReviewController, mockHandler));
        menu.showMenu();

        InOrder inOrder = inOrder(mockHandler, mockReviewController);

        verifyMenuOptions(inOrder);

        inOrder.verify(mockHandler).writeLine("Ingrese el nombre del restaurante: ");
        inOrder.verify(mockHandler).writeLine("Ingrese el nombre del plato: ");
        inOrder.verify(mockHandler).writeLine("Ingrese la calificacion de 1.0 a 5.0: ");
        inOrder.verify(mockHandler).writeLine("Ingrese un comentario: ");

        inOrder.verify(mockReviewController).addPlateReview(restaurantName, plateName, rating, comment);
    }

    @Test
    @DisplayName("Caso 11 del Menu: Ver lista de reviews de un restaurante")
    void testMenu11(){
        String restaurantName = "Restaurante A";

        when(mockHandler.readLine())
                .thenReturn("11", restaurantName, "0");

        menu.addCommand(11, new GetReviewsRestaurant(mockReviewController, mockHandler));
        menu.showMenu();

        InOrder inOrder = inOrder(mockHandler, mockReviewController);

        verifyMenuOptions(inOrder);

        inOrder.verify(mockHandler).writeLine("Ingrese el nombre del restaurante: ");

        inOrder.verify(mockReviewController).getRestaurantReviews(restaurantName);
    }

    @Test
    @DisplayName("Caso 12 del Menu: Ver lista de reviews de un plato")
    void testMenu12(){
        String restaurantName = "Restaurante A";
        String plateName = "Plato X";

        when(mockHandler.readLine())
                .thenReturn("12", restaurantName, plateName, "0");

        menu.addCommand(12, new GetReviewsPlate(mockReviewController, mockHandler));
        menu.showMenu();

        InOrder inOrder = inOrder(mockHandler, mockReviewController);

        verifyMenuOptions(inOrder);

        inOrder.verify(mockHandler).writeLine("Ingrese el nombre del restaurante: ");
        inOrder.verify(mockHandler).writeLine("Ingrese el nombre del plato: ");

        inOrder.verify(mockReviewController).getPlateReviews(restaurantName, plateName);
    }

    @Test
    @DisplayName("Caso 0 del Menu: Salir")
    void testMenu0(){
        when(mockHandler.readLine()).thenReturn("0");

        menu.addCommand(0, new GetReviewsRestaurant(mockReviewController, mockHandler));
        menu.showMenu();

        InOrder inOrder = inOrder(mockHandler);

        verifyMenuOptions(inOrder);

        inOrder.verify(mockHandler).writeLine("Gracias. Vuelva pronto!");

        verify(mockHandler, times(1)).readLine();
    }

    @Test
    @DisplayName("Caso 13 del Menu: Se equivoca el usuario")
    void testMenu13(){
        when(mockHandler.readLine()).thenReturn("15", "0");

        menu.addCommand(0, new GetReviewsRestaurant(mockReviewController, mockHandler));
        menu.showMenu();

        InOrder inOrder = inOrder(mockHandler);

        verifyMenuOptions(inOrder);

        inOrder.verify(mockHandler).writeLine("Opcion no valida.");

        verifyMenuOptions(inOrder);

        inOrder.verify(mockHandler).writeLine("Gracias. Vuelva pronto!");

        verify(mockHandler, times(2)).readLine();
    }
}