package Model;

import javafx.geometry.Point2D;

import java.util.Queue;

public class Snake {

    private Point2D headPosition;
    private Direction direction;
    private int length;
    private Queue<Point2D> body;

    public Snake() {
        headPosition = new Point2D(0.5, 0.5);
        direction = Direction.RIGHT;
        length = 1;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    private Point2D delta() {
        return switch (direction) {
            case UP -> new Point2D(0, 1);
            case DOWN -> new Point2D(0, -1);
            case LEFT -> new Point2D(-1, 0);
            case RIGHT -> new Point2D(1, 0);
        };
    }

    public boolean move() {
        Point2D delta = delta();
        for (Point2D bodyPart : body) {
            bodyPart.add(delta.getX() / 7, delta.getY() / 7); // generalize this 7
            if (bodyPart.getX() < -1 || bodyPart.getX() > 1 || bodyPart.getY() < -1 || bodyPart.getY() > 1) {
                return false;
            }
        }
        return true;
    }
}
