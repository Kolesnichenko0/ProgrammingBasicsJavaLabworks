package part2.labwork4.second;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class ObservableListOrdererProgram {
    public static void main(String[] args) {
        ObservableList<Double> list = FXCollections.observableArrayList();
        list.addListener((ListChangeListener<? super Double>) c -> {
            System.out.println("\033[1;32m" + "Current state of the list:" + "\033[0m");
            System.out.println(list);
        });
        System.out.println("\033[1;33m" + "Adding elements to the list..." + "\033[0m");
        list.addAll(1.0, -12.0, 2.0, 37.0, -6.0, 8.0, -11.0);
        System.out.println("\033[1;33m" + "Ordering the list..." + "\033[0m");
        ObservableListOrderer.orderList(list);
        System.out.println("\033[1;33m" + "Clearing the list..." + "\033[0m");
        list.clear();
    }
}
