package org.example.command.review;

import org.example.command.ICommand;
import org.example.command.utils.IHandler;
import org.example.controllers.ReviewController;

public class AddPlateReviewCommand implements ICommand {
    private ReviewController reviewController;
    private final IHandler handler;

    public AddPlateReviewCommand(ReviewController reviewController, IHandler handler) {
        this.reviewController = reviewController;
        this.handler = handler;
    }

    @Override
    public void execute() {
        handler.writeLine("Ingrese el nombre del restaurante: ");
        String restaurantName = handler.readLine();

        handler.writeLine("Ingrese el nombre del plato: ");
        String plateName = handler.readLine();

        handler.writeLine("Ingrese la calificacion de 1.0 a 5.0: ");
        Double rating = Double.parseDouble(handler.readLine());

        handler.writeLine("Ingrese un comentario: ");
        String comment = handler.readLine();

        reviewController.addPlateReview(restaurantName, plateName, rating, comment);
    }
}
