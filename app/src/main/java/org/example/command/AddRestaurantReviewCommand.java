package org.example.command;

import org.example.controllers.RestaurantController;
import org.example.controllers.ReviewController;

import java.util.Scanner;

public class AddRestaurantReviewCommand implements ICommand{
    private ReviewController reviewController;

    public AddRestaurantReviewCommand(ReviewController reviewController) {
        this.reviewController = reviewController;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre del restaurante: ");
        String restaurantName = scanner.nextLine();
        System.out.print("Ingrese la calificacion de 1.0 a 5.0: ");
        Double rating = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Ingrese un comentario: ");
        String comment = scanner.nextLine();

        reviewController.addReview(restaurantName, rating, comment);
    }
}
