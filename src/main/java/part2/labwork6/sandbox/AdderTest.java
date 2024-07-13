package part2.labwork6.sandbox;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Adder {
    long sum = 0;

    synchronized public void add(long value) {
        this.sum += value;
        System.out.println(Thread.currentThread().getName() + " " + sum);
    }
}

class AdderThread extends Thread {
    private Adder counter = null;

    public AdderThread(String name, Adder counter) {
        super(name);
        this.counter = counter;
    }

    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                counter.add(i);
                Thread.sleep(10);
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class AdderTest {

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(1);
        service.execute(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        service.execute(() -> System.out.println("Second"));
        service.execute(() -> System.out.println("Third"));
        service.execute(() -> System.out.println("Thirdваі"));
        service.execute(() -> System.out.println("Thirdаів"));
        service.shutdown();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        long nanos = System.nanoTime();
        System.out.println(nanos);

        Adder adder = new Adder();
        Thread threadA = new AdderThread("A", adder);
        Thread threadB = new AdderThread("B", adder);
        threadA.start();
        threadB.start();
    }

}
