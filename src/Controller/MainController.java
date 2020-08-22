package Controller;

import java.lang.Math;

import Model.Direction;
import Model.MainModel;
import View.FoodView;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Main controller of game snake, event handling for game
 */
public class MainController {

    @FXML
    private StackPane start_screen;
    @FXML
    private GridPane center_grid;
    @FXML
    private StackPane game_over_screen;
    private MainModel mainModel;
    public static int GRID_SIZE = 21;

    private int foodColPos;
    private int foodRowPos;

    @FXML
    public void initialize() {
        mainModel = new MainModel(this);
        foodColPos = (int) Math.round(mainModel.getFood().getX() * (GRID_SIZE - 1));
        foodRowPos = (int) Math.round(mainModel.getFood().getY() * (GRID_SIZE - 1));
        GRID_SIZE = center_grid.getRowCount();
    }

    private void restartGame() {
        mainModel = new MainModel(this);
        foodColPos = (int) Math.round(mainModel.getFood().getX() * (GRID_SIZE - 1));
        foodRowPos = (int) Math.round(mainModel.getFood().getY() * (GRID_SIZE - 1));
        startGame();
    }

    private void startGame() {
        game_over_screen.setVisible(false);
        start_screen.setVisible(false);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    paintSnake();
                    paintFood();
                    if (!mainModel.moveSnake()) {
                        game_over_screen.setVisible(true);
                        timer.cancel();
                    }
                });
            }
        }, 0, 250);
    }

    private void paintSnake() {
        Node node = center_grid.getChildren().get(0);
        center_grid.getChildren().clear();
        center_grid.getChildren().add(node);

        double cellWidth = center_grid.getWidth() / GRID_SIZE;
        double cellHeight = center_grid.getHeight() / GRID_SIZE;

        for (Point2D bodyPart : mainModel.getSnakeModel().getBody()) {
            Rectangle rectangle = new Rectangle(cellWidth - 10, cellHeight - 10, Color.GREEN);
            GridPane.setHalignment(rectangle, HPos.CENTER);
            int col = (int) Math.round(bodyPart.getX() * GRID_SIZE);
            int row = (int) Math.round(bodyPart.getY() * GRID_SIZE);
            center_grid.add(rectangle, col, row);
        }
    }

    public void notifyFoodConsumed() {
        foodColPos = (int) Math.round(mainModel.getFood().getX() * (GRID_SIZE - 1));
        foodRowPos = (int) Math.round(mainModel.getFood().getY() * (GRID_SIZE - 1));
        paintFood();
    }

    public void paintFood() {
        for (Node node : center_grid.getChildren()) {
            if (node instanceof FoodView) {
                center_grid.getChildren().remove(node);
                break;
            }
        }
        double cellWidth = center_grid.getWidth() / GRID_SIZE;
        double cellHeight = center_grid.getHeight() / GRID_SIZE;
        FoodView foodView = new FoodView(cellWidth - 10, cellHeight - 10, Color.RED);
        center_grid.add(foodView, foodColPos, foodRowPos);
    }

    public void grid_pane_key_pressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP, W -> mainModel.getSnakeModel().setDirection(Direction.UP);
            case DOWN, S -> mainModel.getSnakeModel().setDirection(Direction.DOWN);
            case LEFT, A -> mainModel.getSnakeModel().setDirection(Direction.LEFT);
            case RIGHT, D -> mainModel.getSnakeModel().setDirection(Direction.RIGHT);
            case R -> restartGame();
            case Q -> quitGame();
            case ENTER -> startGame();
        }
    }

    private void quitGame() {
        Platform.exit();
    }

    public int getFoodColPos() {
        return foodColPos;
    }

    public int getFoodRowPos() {
        return foodRowPos;
    }
}
