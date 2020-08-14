package Model;

import com.sun.javafx.scene.paint.GradientUtils;
import javafx.geometry.Point2D;

import java.util.LinkedList;
import java.util.Queue;

public class Snake {

    private Direction direction;
    private int length;

    private final Queue<Point2D> body;

    public Snake() {
        body = new LinkedList<>();
        body.add(new Point2D(0.5, 0.5));
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

    //todo:: generalize the 14
    protected boolean move() {
        System.out.println("moving snake");
        Point2D delta = delta();
        for (int i = 0; i < body.size(); i++) {
            Point2D bodyPart = body.remove();
//            System.out.println("bodyPart before " + bodyPart);
            bodyPart = bodyPart.add(delta.getX() / 14, delta.getY() / 14);
//            System.out.println("bodyPart after " + bodyPart);
            if (bodyPart.getX() < -1 || bodyPart.getX() > 1 || bodyPart.getY() < -1 || bodyPart.getY() > 1) {
                System.out.println("Game Over");
                return false;
            }
            body.add(bodyPart);
        }
        return true;
    }

    public void grow() {
        switch (direction) {
            case UP -> {
//                Point2D newBodyPart = new Point2D()
            }
        }
    }

    public Queue<Point2D> getBody() {
        return body;
    }

    public boolean eats(Food food) {
        for(Point2D bodyPart : body) {

        }
        return false;
    }
}
