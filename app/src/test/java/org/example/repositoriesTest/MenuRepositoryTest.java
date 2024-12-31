package org.example.repositoriesTest;

import org.example.models.Menu;
import org.example.models.Plate;
import org.example.models.Restaurant;
import org.example.repositories.MenuRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MenuRepositoryTest {

    private MenuRepository menuRepository;
    private Restaurant restaurant;
    private Menu menu;

    @BeforeEach
    void setUp() {
        menuRepository = MenuRepository.getInstance();
        restaurant = mock(Restaurant.class);
        menu = mock(Menu.class);

        when(restaurant.getRestaurantName()).thenReturn("Restaurante Ejemplo");
    }

    @AfterEach
    void tearDown() {
        menuRepository.reset();
    }

    @Test
    @DisplayName("Test agregar un menú")
    void testAddMenu() {
        Restaurant restaurantMock = mock(Restaurant.class);
        when(restaurantMock.getRestaurantName()).thenReturn("Restaurante Ejemplo");

        Menu menu = mock(Menu.class);
        when(menu.getRestaurant()).thenReturn(restaurantMock);

        menuRepository.addMenu(menu);

        Set<Plate> plates = menuRepository.getPlatesByRestaurantName("Restaurante Ejemplo");
        assertTrue(plates.isEmpty(), "El menú debería haberse agregado correctamente.");
    }

    @Test
    @DisplayName("Test intentar agregar un menú duplicado")
    void testAddMenuDuplicate() {
        Restaurant restaurantMock = mock(Restaurant.class);
        when(restaurantMock.getRestaurantName()).thenReturn("Restaurante Ejemplo");

        Menu menu = mock(Menu.class);
        when(menu.getRestaurant()).thenReturn(restaurantMock);

        menuRepository.addMenu(menu);
        menuRepository.addMenu(menu);

        Set<Plate> plates = menuRepository.getPlatesByRestaurantName("Restaurante Ejemplo");
        assertTrue(plates.isEmpty(), "El menú no debería haberse agregado nuevamente.");
    }

    @Test
    @DisplayName("Test eliminar un plato del menú")
    void testDeletePlateFromMenu() {
        Restaurant restaurant = new Restaurant("Restaurante Ejemplo");

        Menu menu = new Menu();
        menu.setRestaurant(restaurant);

        Plate plate = new Plate("Plato de Ejemplo", 20.0);
        menu.addPlate(plate);

        menuRepository.addMenu(menu);

        menuRepository.deletePlateFromMenu(restaurant, "Plato de Ejemplo");

        Set<Plate> plates = menuRepository.getPlatesByRestaurantName("Restaurante Ejemplo");
        assertTrue(plates.isEmpty(), "El plato debería haber sido eliminado.");
    }

    @Test
    @DisplayName("Test intentar eliminar un plato de un menú que no existe")
    void testDeletePlateFromMenuMenuNotFound() {
        menuRepository.deletePlateFromMenu(restaurant, "Plato No Existente");

        Set<Plate> plates = menuRepository.getPlatesByRestaurantName("Restaurante Ejemplo");
        assertTrue(plates.isEmpty(), "El menú no fue encontrado, no debe eliminar nada.");
    }

    @Test
    @DisplayName("Test editar un plato en el menú")
    void testEditPlateInMenu() {
        Restaurant restaurant = new Restaurant("Restaurante Ejemplo");

        Menu menu = new Menu();
        menu.setRestaurant(restaurant);

        Plate plate = new Plate("Plato Antiguo", 20.0);
        menu.addPlate(plate);

        menuRepository.addMenu(menu);

        boolean success = menuRepository.editPlateInMenu("Restaurante Ejemplo", "Plato Antiguo", "Plato Nuevo", 25.0);

        assertTrue(success, "La edición del plato debería haber sido exitosa.");

        Set<Plate> plates = menuRepository.getPlatesByRestaurantName("Restaurante Ejemplo");
        assertTrue(plates.stream().anyMatch(p -> p.getPlateName().equals("Plato Nuevo") && p.getPrice() == 25.0),
                "El plato debería haber sido actualizado a 'Plato Nuevo' con precio 25.0.");
    }

    @Test
    @DisplayName("Test editar un plato en un menú que no existe")
    void testEditPlateInMenuMenuNotFound() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        boolean success = menuRepository.editPlateInMenu("Restaurante Inexistente", "Plato Inexistente", "Plato Nuevo", 25.0);

        assertFalse(success, "La edición del plato no debería haber sido exitosa.");

        assertTrue(outputStream.toString().contains("Menu no encontrado para el restaurante: Restaurante Inexistente"),
                "El mensaje de error debería haber sido impreso cuando el menú no es encontrado.");

        System.setOut(System.out);
    }

    @Test
    @DisplayName("Test intentar editar un plato que no existe en el menú")
    void testEditPlateInMenuPlateNotFound() {
        Restaurant restaurant = new Restaurant("Restaurante Ejemplo");

        Menu menu = new Menu();
        menu.setRestaurant(restaurant);

        Plate plate = new Plate("Plato Antiguo", 20.0);
        menu.addPlate(plate);

        menuRepository.addMenu(menu);

        boolean success = menuRepository.editPlateInMenu("Restaurante Ejemplo", "Plato Inexistente", "Plato Nuevo", 25.0);

        assertFalse(success, "La edición del plato no debería haber sido exitosa.");
    }

    @Test
    @DisplayName("Test obtener los platos de un restaurante")
    void testGetPlatesByRestaurantName() {
        Restaurant restaurant = new Restaurant("Restaurante Ejemplo");

        Menu menu = new Menu();
        menu.setRestaurant(restaurant);

        Plate plate = new Plate("Plato 1", 15.0);
        menu.addPlate(plate);

        menuRepository.addMenu(menu);

        Set<Plate> plates = menuRepository.getPlatesByRestaurantName("Restaurante Ejemplo");

        assertNotNull(plates, "Se esperaba una lista de platos.");
        assertFalse(plates.isEmpty(), "La lista de platos no debería estar vacía.");
    }

    @Test
    @DisplayName("Test obtener platos de un restaurante con menú inexistente")
    void testGetPlatesByRestaurantNameMenuNotFound() {
        Set<Plate> plates = menuRepository.getPlatesByRestaurantName("Restaurante Inexistente");
        assertTrue(plates.isEmpty(), "La lista de platos debería estar vacía si no se encuentra el menú.");
    }
}
