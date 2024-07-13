package part2.labwork6.second;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class PrimeFactorFinderApp extends Application {
    public static final String ICON_PATH = "icon.png";
    public static final String ICON_ERROR_PATH = "icon_error.png";
    public static final String APP_TITLE = "Prime Factor Finder";

    @Override
    public void start(Stage primaryStage) {
        try {
            BorderPane root = (BorderPane) FXMLLoader.load(getClass()
                    .getResource("PrimeFactorForm.fxml"));
            Scene scene = new Scene(root, 500, 565);
            primaryStage.setScene(scene);
            primaryStage.setTitle(APP_TITLE);
            primaryStage.getIcons().add(new Image(getClass().getResourceAsStream(ICON_PATH)));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
