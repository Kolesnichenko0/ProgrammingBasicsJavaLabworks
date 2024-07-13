package part2.labwork4.sandbox;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.geometry.Pos;
import javafx.scene.control.Button;

public class Main extends Application {

    @Override
    public  void start(Stage primaryStage) throws Exception {
        // Встановлюємо заголовок вікна:
        primaryStage.setTitle("First Java FX Application");

        // Створюємо кореневий контейнер і встановлюємо центрування
        // дочірніх елементів
        FlowPane rootNode = new FlowPane();
        rootNode.setAlignment(Pos.CENTER);

        // Створюємо сцену і встановлюємо її в "підмостки"
        Scene scene = new Scene(rootNode, 300, 200);
        primaryStage.setScene(scene);

        // Створюємо кнопку, визначаємо дію при її натисканні
        // і вставляємо кнопку в кореневій контейнер:
        Button button = new Button("Press me");
        button.setOnAction(event -> button.setText("Do not press me"));
        rootNode.getChildren().add(button);

        // Показуємо вікно:
        primaryStage.show();
    }

    public  static  void main(String[] args) {
        launch(args);
    }
}

