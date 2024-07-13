package part2.labwork6.third;

import java.util.Scanner;
import java.util.concurrent.*;

public class AverageComputationApp {
    private static final int QUEUE_CAPACITY = 4;

    public int getPositiveInteger(Scanner scanner) {
        int number;
        do {
            while (!scanner.hasNextInt()) {
                System.out.println("That's not a valid integer! Please enter a positive integer.");
                scanner.next();
            }
            number = scanner.nextInt();
            if (number <= 0) {
                System.out.println("The number must be positive. Please try again.");
            }
        } while (number <= 0);
        return number;
    }

    public void run() {
        System.out.println("Welcome to Average Computation App!");

        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(QUEUE_CAPACITY);
        IntegerProducer producer = new IntegerProducer(queue);
        AverageCalculator consumer = new AverageCalculator(queue);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of seconds for the program to run: ");
        int timeIntervalSeconds = getPositiveInteger(scanner);

        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);

        producerThread.start();
        consumerThread.start();

        try {
            TimeUnit.SECONDS.sleep(timeIntervalSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        producerThread.interrupt();
        consumerThread.interrupt();

        try {
            producerThread.join();
            consumerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nThank you for using Average Computation App!");
    }

    public static void main(String[] args) {
        AverageComputationApp app = new AverageComputationApp();
        app.run();
    }
}