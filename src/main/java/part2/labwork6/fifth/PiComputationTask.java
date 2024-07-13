package part2.labwork6.fifth;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class PiComputationTask implements Runnable {
    private final double epsilon;
    private double piResult;
    private final AtomicInteger termCount;
    private final AtomicBoolean running;

    public PiComputationTask(double epsilon, AtomicInteger termCount, AtomicBoolean running) {
        this.epsilon = epsilon;
        this.piResult = 0.0;
        this.termCount = termCount;
        this.running = running;
    }

    public double getPiResult() {
        return piResult;
    }

    @Override
    public void run() {
        System.out.println("\nStarting π computation...");

        double term;
        int termIndex = 0;

        while (Math.abs(piResult - Math.PI) >= epsilon) {
            term = 4.0 * Math.pow(-1, termIndex) / (2 * termIndex + 1);
            piResult += term;
            termCount.incrementAndGet();
            termIndex++;

            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                if (!running.get()) {
                    System.out.println("π computation interrupted.\n");
                    return;
                }
            }
        }

        running.set(false);
        System.out.println("π computation finished.\n");
    }
}
