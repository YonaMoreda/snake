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
        if (!areOpposites(this.direction, direction)) {
            this.direction = direction;
        }
    }

    private boolean areOpposites(Direction direction, Direction direction1) {
        if (direction.equals(Direction.UP) && direction1.equals(Direction.DOWN)) {
            return true;
        }
        if (direction.equals(Direction.DOWN) && direction1.equals(Direction.UP)) {
            return true;
        }
        if (direction.equals(Direction.LEFT) && direction1.equals(Direction.RIGHT)) {
            return true;
        }
        if (direction.equals(Direction.RIGHT) && direction1.equals(Direction.LEFT)) {
            return true;
        }
        return false;
    }

    private Point2D delta() {
        return switch (direction) {
            case UP -> new Point2D(0, -1);
            case DOWN -> new Point2D(0, 1);
            case LEFT -> new Point2D(-1, 0);
            case RIGHT -> new Point2D(1, 0);
        };
    }

    //todo:: generalize the number 14
    protected boolean move() {
        Point2D delta = delta();
        Point2D bodyPartOld = body.remove();
        Point2D newBodyPart = bodyPartOld.add(delta.getX() / 21, delta.getY() / 21);

        if (newBodyPart.getX() < 0 || newBodyPart.getX() > 1 || newBodyPart.getY() < 0 || newBodyPart.getY() > 1) {
            System.out.println("Game Over! collision");
            return false;
        }

        if (collides(newBodyPart, body)) {
            System.out.println("Game Over");
            return false;
        }
        body.add(newBodyPart);
        lastBodyPart = bodyPartOld;
        for (int i = 1; i < body.size(); i++) {
            body.add(lastBodyPart);
            newBodyPart = body.remove();
            lastBodyPart = newBodyPart;
        }
        return true;
    }

    private boolean collides(Point2D newBodyPart, Queue<Point2D> body) {
        for (Point2D bodyPart : body) {
            if(newBodyPart.getX() == bodyPart.getX() && newBodyPart.getY() == bodyPart.getY()) {
                return true;
            }
        }
        return false;
    }

    public void grow() {
        Point2D delta = delta();
        Point2D newBody = lastBodyPart.add(delta.getX() / 21, delta.getY() / 21);
        body.add(newBody);
        lastBodyPart = newBody;
    }

    public Queue<Point2D> getBody() {
        return body;
    }

    public boolean eats(int foodCol, int foodRow) {
        for (Point2D bodyPart : body) {
            int bodyPartCol = (int) Math.round(bodyPart.getX() * 21);
            int bodyPartRow = (int) Math.round(bodyPart.getY() * 21);
            if (bodyPartRow == foodRow && bodyPartCol == foodCol) {
                return true;
            }
        }
        return false;
    }
}
