package part2.labwork4.second;

import javafx.collections.ObservableList;

public class ObservableListOrderer {
    public static void orderList(ObservableList<Double> list) {
        int size = list.size();
        for (int i = 0, j = 0; j < size; j++) {
            if (list.get(i) < 0) {
                list.add(list.get(i));
                list.remove(i);
            } else {
                i++;
            }
        }
        for (int i = list.size() - 2; i >= 0; i--) {
            if (list.get(i) < 0) {
                list.add(list.get(i));
                list.remove(i);
            } else {
                break;
            }
        }
    }
}
