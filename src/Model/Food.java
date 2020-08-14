package Model;

import javafx.geometry.Point2D;

public class Food extends Point2D {

    public Food() {
        super(0,0);
        int gridSize = 14;

        this.add(Math.random(), Math.random());
    }
}
