package part2.labwork4.fourth;

import java.util.HashMap;
import java.util.Map;

public class DictionaryModel {
    private Map<String, String> dictionary;

    public DictionaryModel() {
        dictionary = new HashMap<>();
    }

    public void addWord(String english, String ukrainian) {
        dictionary.put(english, ukrainian);
    }

    public String findWord(String english) {
        return dictionary.get(english);
    }

    public String getAllWords() {
        StringBuilder allWords = new StringBuilder();
        for (Map.Entry<String, String> entry : dictionary.entrySet()) {
            allWords.append(entry.getKey()).append(" - ").append(entry.getValue()).append("\n");
        }
        return allWords.toString();
    }
}
