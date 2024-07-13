package part2.labwork4.fourth;

import javafx.application.Application;
import javafx.stage.Stage;

public class DictionaryApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        DictionaryView view = new DictionaryView();
        DictionaryModel model = new DictionaryModel();
        DictionaryController controller = new DictionaryController(view, model);

        model.addWord("hello", "привіт");
        model.addWord("goodbye", "до побачення");
        model.addWord("please", "будь ласка");
        model.addWord("no", "ні");
        model.addWord("yes", "так");

        view.setController(controller);
        view.createUI(primaryStage);
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
