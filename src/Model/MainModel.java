package Model;

import Controller.MainController;

/**
 * Main model for storing the snake model and its methods
 */
public class MainModel {
    private Snake snakeModel;
    private MainController controller;
    private Food food;

    public Food getFood() {
        return food;
    }

    public MainModel(MainController controller) {
        this.snakeModel = new Snake();
        this.food = new Food();
        this.controller = controller;
    }

    public Snake getSnakeModel() {
        return snakeModel;
    }

    public boolean moveSnake() {
        boolean isGameOver = snakeModel.moveAStep(MainController.GRID_SIZE);
        if(snakeModel.eats(controller.getFoodColPos(), controller.getFoodRowPos(), MainController.GRID_SIZE)) {
            snakeModel.grow(MainController.GRID_SIZE);
            food = new Food();
            controller.notifyFoodConsumed();
        }
        return isGameOver;
    }
}
