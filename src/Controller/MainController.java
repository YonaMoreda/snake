package Controller;

import java.lang.Math;

import Model.Direction;
import Model.MainModel;
import Model.Snake;
import View.FoodView;
import com.sun.tools.javac.Main;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Timer;
import java.util.TimerTask;

public class MainController {

    @FXML
    private GridPane center_grid;
    private MainModel mainModel;
    //    private Snake snakeModel;
    private int GRID_SIZE = 14;

    int foodColPos;
    int foodRowPos;


    @FXML
    public void initialize() {

        mainModel = new MainModel(this);

        foodColPos = (int) Math.round(mainModel.getFood().getX() * GRID_SIZE);
        foodRowPos = (int) Math.round(mainModel.getFood().getY() * GRID_SIZE);

        GRID_SIZE = center_grid.getRowCount();


        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    paintSnake();
                    paintFood();
                    if (!mainModel.moveSnake()) {
                        timer.cancel();
                    }
                });
            }
        }, 0, 250);
    }

//    private void setSceneKeyPressedAction() {

//    }

    private void paintSnake() {
        Node node = center_grid.getChildren().get(0);
        center_grid.getChildren().clear();
        center_grid.getChildren().add(node);

        double cellWidth = center_grid.getWidth() / GRID_SIZE;
        double cellHeight = center_grid.getHeight() / GRID_SIZE;
//        System.out.println("painting snake: " + center_grid.getWidth() + " " + center_grid.getHeight());

        for (Point2D bodyPart : mainModel.getSnakeModel().getBody()) {
            Rectangle rectangle = new Rectangle(cellWidth - 10, cellHeight - 10, Color.GREEN);
            GridPane.setHalignment(rectangle, HPos.CENTER);
            int col = (int) Math.round(bodyPart.getX() * 14);
            int row = (int) Math.round(bodyPart.getY() * 14);
//            System.out.println(col + " " + row);
            center_grid.add(rectangle, col, row); //c, r
        }
    }

    public void notifyFoodConsumed() {
        foodColPos = (int) Math.round(mainModel.getFood().getX() * GRID_SIZE);
        foodRowPos = (int) Math.round(mainModel.getFood().getY() * GRID_SIZE);
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

        if (keyEvent.getCode().equals(KeyCode.R)) {
            System.out.println("Restart");
        }
        switch (keyEvent.getCode()) {
            case UP, W -> {
                mainModel.getSnakeModel().setDirection(Direction.UP);
//                System.out.println("UP");
            }
            case DOWN, S -> {
                mainModel.getSnakeModel().setDirection(Direction.DOWN);
//                System.out.println("DOWN");
            }
            case LEFT, A -> {
                mainModel.getSnakeModel().setDirection(Direction.LEFT);
//                System.out.println("LEFT");
            }
            case RIGHT, D -> {
                mainModel.getSnakeModel().setDirection(Direction.RIGHT);
//                System.out.println("RIGHT");
            }
        }

    }
}
