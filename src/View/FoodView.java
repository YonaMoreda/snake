package View;

import javafx.geometry.HPos;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Display / view class for food
 */
public class FoodView extends Rectangle {

    public FoodView(double v, double v1, Color color) {
        super(v, v1, color);
        GridPane.setHalignment(this, HPos.CENTER);
    }
}
