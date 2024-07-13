package part2.labwork6.third;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.concurrent.BlockingQueue;

public class AverageCalculator implements Runnable {
    private final BlockingQueue<Integer> queue;
    private final List<Integer> consumedNumbers = new ArrayList<>();
    private static final int SLEEP_TIME = 1500;

    public AverageCalculator(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    public void printResults() {
        System.out.println("\nFinal Results:");
        System.out.println("Total numbers consumed: " + consumedNumbers.size());
        System.out.println("Numbers consumed: " + consumedNumbers);
        OptionalDouble optionalAverage = consumedNumbers.stream()
                .mapToDouble(Integer::doubleValue)
                .average();

        if (optionalAverage.isPresent()) {
            double average = optionalAverage.getAsDouble();
            System.out.println("Final Average: " + average);
        } else {
            System.out.println("No numbers were consumed.");
        }
    }

    @Override
    public void run() {
        Thread.currentThread().setName("Consumer Thread");
        int count = 0;
        double sum = 0;

        try {
            while (true) {
                int number = queue.take();
                consumedNumbers.add(number);
                sum += number;
                count++;
                double average = sum / count;
                System.out.println(Thread.currentThread().getName() + " consumed: " + number + ", Average: " + average);
                Thread.sleep(SLEEP_TIME);
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " was interrupted.");
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            printResults();
        }
    }
}