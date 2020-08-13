package Controller;

import Model.Snake;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MainController {

    @FXML
    private GridPane center_grid;
    private Snake snakeModel;

    @FXML
    public void initialize() {
        snakeModel = new Snake();
        paintSnake();
    }

    private void paintSnake() {
        for (Point2D bodyPart : snakeModel.getBody()) {
            Rectangle rectangle = new Rectangle(20, 20, Color.GREEN);
            int col = (int) (bodyPart.getX() * 14);
            int row = (int) (bodyPart.getY() * 14);
            System.out.println(col + " " + row);
            center_grid.add(rectangle, col, row); //c, r
        }

    }
}
