package View;

import Controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


/**
 * MainView of the game
 */
public class MainView extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("layout.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Snake");
        Scene scene = new Scene(root);
        scene.setOnKeyPressed(actionEvent -> {
            MainController mainController = loader.getController();
            mainController.grid_pane_key_pressed(actionEvent);
        });
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("snake.png"));
        primaryStage.show();
        scene.getRoot().requestFocus(); //TODO:: attribution Icons made by <a href="https://www.flaticon.com/authors/freepik" title="Freepik">Freepik</a> from <a href="https://www.flaticon.com/" title="Flaticon"> www.flaticon.com</a>
    }
}
