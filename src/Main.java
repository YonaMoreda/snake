import View.MainView;
import javafx.application.Application;

/**
 * Main starter class
 */

//FIXME:: food is consumed too early, food spawns outside grid, input lag
public class Main {
    public static void main(String[] args) {
        Application.launch(MainView.class, args);
    }
}
