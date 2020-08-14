package Controller;

import java.lang.Math;

import Model.Direction;
import Model.MainModel;
import Model.Snake;
import com.sun.tools.javac.Main;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
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

    @FXML
    public void initialize() {

        mainModel = new MainModel();
        GRID_SIZE = center_grid.getRowCount();
        setSceneKeyPressedAction();

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    paintSnake();

                    if (!mainModel.moveSnake()) {
                        timer.cancel();
                    }
                });
            }
        }, 0, 1000);
    }

    private void setSceneKeyPressedAction() {
        center_grid.setOnKeyPressed(actionEvent -> {
            if (actionEvent.getCode().equals(KeyCode.R)) {
                System.out.println("Restart");
            }
            switch (actionEvent.getCode()) {
                case A -> {
                    mainModel.getSnakeModel().setDirection(Direction.UP);
                    System.out.println("LMAO");
                }
                case DOWN -> mainModel.getSnakeModel().setDirection(Direction.DOWN);
                case LEFT -> mainModel.getSnakeModel().setDirection(Direction.LEFT);
                case RIGHT -> mainModel.getSnakeModel().setDirection(Direction.RIGHT);
            }
        });
    }

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

    private void paintFood() {
        int col = (int) Math.floor(mainModel.getFood().getX() * GRID_SIZE);
        int row = (int) Math.floor(mainModel.getFood().getY() * GRID_SIZE);
        double cellWidth = center_grid.getWidth() / GRID_SIZE;
        double cellHeight = center_grid.getHeight() / GRID_SIZE;
        Rectangle rectangle = new Rectangle(cellWidth - 10, cellHeight - 10, Color.GREEN);
        GridPane.setHalignment(rectangle, HPos.CENTER);
        center_grid.add(rectangle, col, row);
    }
}
