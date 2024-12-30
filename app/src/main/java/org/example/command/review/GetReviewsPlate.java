package org.example.command.review;

import org.example.command.ICommand;
import org.example.controllers.ReviewController;

import java.util.Scanner;

public class GetReviewsPlate implements ICommand {
    private ReviewController reviewController;

    public GetReviewsPlate(ReviewController reviewController) {
        this.reviewController = reviewController;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre del restaurante: ");
        String restaurantName = scanner.nextLine();

        System.out.print("Ingrese el nombre del plato: ");
        String plateName = scanner.nextLine();

        reviewController.getPlateReviews(restaurantName, plateName);
    }
}
