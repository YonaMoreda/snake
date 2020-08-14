package Model;

public class MainModel {
    private Snake snakeModel;

    public Food getFood() {
        return food;
    }

    private Food food;

    public MainModel() {
        this.snakeModel = new Snake();
        this.food = new Food();
    }

    public Snake getSnakeModel() {
        return snakeModel;
    }

    public boolean moveSnake() {
        boolean isGameOver = snakeModel.move();
        if(snakeModel.eats(food)) {
            food = new Food();
        }
        return isGameOver;
    }

}
