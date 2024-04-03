package part1.labwork4.fifth;

import java.util.Set;
import java.util.HashSet;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Scanner;
import java.util.Arrays;

public class Sentence {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String sentence = scanner.nextLine();
        Set<Character> delimiters = new HashSet<>(
                Arrays.asList(' ', '.', ',', ':', ';', '?', '!', '-', '(', ')', '\"'));

        SortedSet<String> words = new TreeSet<>();

        StringBuilder currentWord = new StringBuilder();

        for (int i = 0; i < sentence.length(); i++) {
            char currentChar = sentence.charAt(i);
            if (!delimiters.contains(currentChar)) {
                currentWord.append(currentChar);
            } else {
                if (!currentWord.isEmpty()) {
                    words.add(currentWord.toString().toLowerCase());
                    currentWord.setLength(0);
                }
            }
        }

        if (!currentWord.isEmpty()) {
            words.add(currentWord.toString().toLowerCase());
        }

        System.out.println(words);
    }
}
