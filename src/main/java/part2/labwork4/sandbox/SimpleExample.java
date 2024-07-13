package part2.labwork4.sandbox;


import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.*;

public class SimpleExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Головне вікно");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Встановлюємо менеджер розташування
        frame.setLayout(new FlowLayout());

        // Додаємо кнопку до вікна
        frame.getContentPane().add(new JButton("Натисни мене"));

        // Використовуємо pack() для автоматичного налаштування розміру вікна під розмір компонентів
        frame.pack();

        // Робимо вікно видимим
        frame.setVisible(true);

        // Пізніше ви можете додати ще одну кнопку
        frame.getContentPane().add(new JButton("Натисни мене знову"));

        // І знову використовуємо pack(), щоб вікно автоматично змінило свій розмір, щоб вмістити нову кнопку
        frame.pack();
    }
}