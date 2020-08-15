package Model;

import javafx.geometry.Point2D;

import java.util.LinkedList;
import java.util.Queue;

public class Snake {

    private Direction direction;
    private Point2D lastBodyPart;

    private final Queue<Point2D> body;

    public Snake() {
        body = new LinkedList<>();
        lastBodyPart = new Point2D(0.5, 0.5);
        body.add(lastBodyPart);

        direction = Direction.RIGHT;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    private Point2D delta() {
        return switch (direction) {
            case UP -> new Point2D(0, -1);
            case DOWN -> new Point2D(0, 1);
            case LEFT -> new Point2D(-1, 0);
            case RIGHT -> new Point2D(1, 0);
        };
    }

    //todo:: generalize the 14
    protected boolean move() {
//        System.out.println("moving snake");

        Point2D delta = delta();
        Point2D bodyPartOld = body.remove();
        Point2D newBodyPart = bodyPartOld.add(delta.getX() / 14, delta.getY() / 14);

        if (newBodyPart.getX() < -1 || newBodyPart.getX() > 1 || newBodyPart.getY() < -1 || newBodyPart.getY() > 1) {
            System.out.println("Game Over");
            return false;
        }
        body.add(newBodyPart);
        lastBodyPart = bodyPartOld;
        for (int i = 1; i < body.size(); i++) {
            body.add(lastBodyPart);
            newBodyPart = body.remove();
//            bodyPart = bodyPart.add(delta.getX() / 14, delta.getY() / 14);
            lastBodyPart = newBodyPart;
        }
        return true;
    }

    public void grow() {
        System.out.println("growing snake");
        Point2D delta = delta();
        Point2D newBody = lastBodyPart.add(delta.getX() / 14, delta.getY() / 14);
        body.add(newBody);
        lastBodyPart = newBody;
    }

    public Queue<Point2D> getBody() {
        return body;
    }

    public boolean eats(Food food) {

        int foodCol = (int) Math.round(food.getX() * 14);
        int foodRow = (int) Math.round(food.getY() * 14);

        for (Point2D bodyPart : body) {
            int bodyPartCol = (int) Math.round(bodyPart.getX() * 14);
            int bodyPartRow = (int) Math.round(bodyPart.getY() * 14);

            System.out.println("Eating! " + bodyPartCol + " == " + foodCol + ", " + bodyPartRow + " == " + foodRow);
            if (bodyPartRow == foodRow && bodyPartCol == foodCol) {
//                System.out.println("Eating! " + bodyPart.getX() + " - " + food.getX() + " = " + Math.abs(bodyPart.getX() - food.getX()));
                return true;
            }
//            System.out.println("Not eating! " + bodyPart.getX() + " - " + food.getX() + " = " + Math.abs(bodyPart.getX() - food.getX()));
        }
        return false;
    }
}
