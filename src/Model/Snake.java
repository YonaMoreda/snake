package Model;

import javafx.geometry.Point2D;

public class Snake {

    private Point2D headPosition;
    private Direction direction;
    private int length;

    public Snake() {
        headPosition = new Point2D(0.5, 0.5);
        direction = Direction.RIGHT;
        length = 0;
    }
}
