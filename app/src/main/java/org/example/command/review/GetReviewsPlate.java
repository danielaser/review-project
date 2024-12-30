package org.example.command.review;

import org.example.command.ICommand;
import org.example.command.utils.IHandler;
import org.example.controllers.ReviewController;

public class GetReviewsPlate implements ICommand {
    private ReviewController reviewController;
    private final IHandler handler;

    public GetReviewsPlate(ReviewController reviewController, IHandler handler) {
        this.reviewController = reviewController;
        this.handler = handler;
    }

    @Override
    public void execute() {
        handler.writeLine("Ingrese el nombre del restaurante: ");
        String restaurantName = handler.readLine();

        handler.writeLine("Ingrese el nombre del plato: ");
        String plateName = handler.readLine();

        reviewController.getPlateReviews(restaurantName, plateName);
    }
}
