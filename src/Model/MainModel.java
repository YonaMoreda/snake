package Model;

import Controller.MainController;

public class MainModel {
    private Snake snakeModel;
    private MainController controller;

    public Food getFood() {
        return food;
    }

    private Food food;

    public MainModel(MainController controller) {
        this.snakeModel = new Snake();
        this.food = new Food();
        this.controller = controller;
    }

    public Snake getSnakeModel() {
        return snakeModel;
    }

    public boolean moveSnake() {
        boolean isGameOver = snakeModel.move();
        if(snakeModel.eats(controller.getFoodColPos(), controller.getFoodRowPos())) {
            snakeModel.grow();
            food = new Food();
            controller.notifyFoodConsumed();
        }
        return isGameOver;
    }

}
