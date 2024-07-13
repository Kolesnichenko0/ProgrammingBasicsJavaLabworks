package part2.labwork4.sandbox;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.*;
import javafx.stage.Stage;

import java.util.Arrays;

public class ObservableListDemo extends Application {
    @Override
    public void start(Stage primaryStage) {
        final ObservableList<Integer> list = FXCollections.observableArrayList(1, 2, 4, 8);
        list.addListener((ListChangeListener<Integer>) change -> {
            System.out.println("Detected a change! ");
            while (change.next()) {
                if (change.wasAdded()) {
                    System.out.println("Items were added " + change.getAddedSubList());
                }
                if (change.wasRemoved()) {
                    System.out.println("Items were removed " + change.getRemoved());
                }
                System.out.println("onChanged() called");
            }
        });
        ObservableList<Integer> newList = FXCollections.observableArrayList(list);
        newList.add(16);   // This will trigger a change
        newList.add(32);   // This will trigger another change
        newList.add(64);   // This will trigger another change

        list.setAll(newList); // This will trigger a single change event
    }

    public static void main(String[] args) {
        launch(args);
    }
}