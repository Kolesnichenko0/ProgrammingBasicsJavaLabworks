package part2.labwork6.third;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class IntegerProducer implements Runnable {
    private final BlockingQueue<Integer> queue;
    private final Random random = new Random();
    private static final int LOWER_BOUND = 100;
    private static final int UPPER_BOUND = 200;
    private static final int SLEEP_TIME = 500;

    public IntegerProducer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        Thread.currentThread().setName("Producer Thread");
        try {
            while (true) {
                int number = LOWER_BOUND + random.nextInt(UPPER_BOUND - LOWER_BOUND);
                queue.put(number);
                System.out.println(Thread.currentThread().getName() + " produced: " + number);
                Thread.sleep(SLEEP_TIME);
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " was interrupted.");
        }
    }
}
