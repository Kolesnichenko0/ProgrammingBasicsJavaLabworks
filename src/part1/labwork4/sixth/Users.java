package part1.labwork4.sixth;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class Users {
    public static void main(String[] args) {
        SortedMap<String, String> users = new TreeMap<>();
        users.put("krabos", "5lm2");
        users.put("gla23", "Fl19832");
        users.put("tree12", "Ll3n25158");
        users.put("prosto2", "3Oo2Mh362");
        users.put("robert", "g32145");
        for (Map.Entry<?, String> entry : users.entrySet()) {
            String password = entry.getValue();
            if (password.length() > 6) {
                System.out.println("Username: " + entry.getKey() + ". Password: " + entry.getValue());
            }
        }
        for (Map.Entry<?, ?> entry : users.entrySet())
            System.out.println(entry.getKey() + " " + entry.getValue());
    }
}
