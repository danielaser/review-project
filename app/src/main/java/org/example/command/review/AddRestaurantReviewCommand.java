package org.example.command.review;

import org.example.command.ICommand;
import org.example.command.utils.IHandler;
import org.example.controllers.ReviewController;

public class AddRestaurantReviewCommand implements ICommand {
    private ReviewController reviewController;
    private final IHandler handler;

    public AddRestaurantReviewCommand(ReviewController reviewController, IHandler handler) {
        this.reviewController = reviewController;
        this.handler = handler;
    }

    @Override
    public void execute() {
        handler.writeLine("Ingrese el nombre del restaurante: ");
        String restaurantName = handler.readLine();

        handler.writeLine("Ingrese la calificacion de 1.0 a 5.0: ");
        Double rating = Double.parseDouble(handler.readLine());

        handler.writeLine("Ingrese un comentario: ");
        String comment = handler.readLine();

        reviewController.addReview(restaurantName, rating, comment);
    }
}
