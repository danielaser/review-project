package org.example.command;

import org.example.controllers.ReviewController;

import java.util.Scanner;

public class GetReviewsRestaurant implements ICommand{
    private ReviewController reviewController;

    public GetReviewsRestaurant(ReviewController reviewController) {
        this.reviewController = reviewController;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre del restaurante: ");
        String restaurantName = scanner.nextLine();

        reviewController.getRestaurantReviews(restaurantName);
    }
}
