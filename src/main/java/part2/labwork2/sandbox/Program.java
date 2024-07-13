package part2.labwork2.sandbox;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Program {
    public static void main(String[] args) {
//        // Створення зразка
//        Pattern pattern = Pattern.compile("[a-z]");

//
//        // Створення матчера
//        Matcher matcher = pattern.matcher("hello world");
//
//        // Перевірка, чи відповідає вхідний рядок зразку
//        while (matcher.find()) {
//            System.out.println("Matched text: " + matcher.group());
//        }
        // Створення зразка
//        System.out.println( "Створення \u000B зразка" );
//        Pattern pattern = Pattern.compile("[a-z]+");
//
//        // Створення матчера
//        Matcher matcher = pattern.matcher("helloworld");
//
//        // Перевірка, чи відповідає весь вхідний рядок зразку
//        if (matcher.matches()) {
//            System.out.println("The input string matches the pattern.");
//        } else {
//            System.out.println("The input string does not match the pattern.");
//        }
        // Створення зразка
//        System.out.println("\u0007");
        Pattern pattern = Pattern.compile("\\s+");

        // Створення матчера
        Matcher matcher = pattern.matcher("  These are Ivanov and Petrov and another Ivanov  ");

        // Пошук усіх відповідностей в рядку
        while (matcher.find()) {
            System.out.println("Matched text: " + matcher.group());
        }
    }
}
