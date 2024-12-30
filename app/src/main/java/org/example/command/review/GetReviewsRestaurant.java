package org.example.command.review;

import org.example.command.ICommand;
import org.example.command.utils.IHandler;
import org.example.controllers.ReviewController;

public class GetReviewsRestaurant implements ICommand {
    private ReviewController reviewController;
    private final IHandler handler;

    public GetReviewsRestaurant(ReviewController reviewController, IHandler handler) {
        this.reviewController = reviewController;
        this.handler = handler;
    }

    @Override
    public void execute() {
        handler.writeLine("Ingrese el nombre del restaurante: ");
        String restaurantName = handler.readLine();

        reviewController.getRestaurantReviews(restaurantName);
    }
}
