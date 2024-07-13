package part2.labwork6.second.model;

import javafx.application.Platform;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PrimeFactorCalculator implements Runnable {
    private Thread primeFactorsThread;
    private final Runnable displayFunc;
    private final Runnable percentageFunc;
    private final Runnable finishFunc;
    private double percentage;
    private boolean suspended;
    private boolean stopped;
    private PrimeFactors primeFactors;
    private long totalNumbers;
    private static final int SLEEP_TIME = 100;

    public PrimeFactorCalculator(Runnable addFunc, Runnable percentageFunc,
                                 Runnable finishFunc) {
        this.displayFunc = addFunc;
        this.percentageFunc = percentageFunc;
        this.finishFunc = finishFunc;
    }

    public PrimeFactors getPrimeFactors() {
        return primeFactors;
    }

    public void setPrimeFactors(PrimeFactors primeFactors) {
        this.primeFactors = primeFactors;
    }

    public synchronized double getPercentage() {
        return percentage;
    }

    private synchronized void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public synchronized boolean isSuspended() {
        return suspended;
    }

    private synchronized void setSuspended(boolean suspended) {
        this.suspended = suspended;
    }

    public synchronized boolean isStopped() {
        return stopped;
    }

    private synchronized void setStopped(boolean stopped) {
        this.stopped = stopped;
    }

    public List<Integer> findPrimeFactorsOf(int number) {
        List<Integer> result = new ArrayList<>();

        for (int k = 2; k <= number; k++) {
            while (number % k == 0) {
                result.add(k);
                number /= k;
            }
        }

        return result;
    }

    public double calculatePercentage(int number) {
        long processedNumbers = number - primeFactors.getFrom() + 1;
        return processedNumbers * 1.0 / totalNumbers;
    }

    @Override
    public void run() {
        for (int number = primeFactors.getFrom(); number <= primeFactors.getTo(); number++) {
            try {
                setPercentage(calculatePercentage(number));
                if (percentageFunc != null) {
                    Platform.runLater(percentageFunc);
                }

                primeFactors.getMap().putIfAbsent(number, findPrimeFactorsOf(number));

                if (displayFunc != null) {
                    Platform.runLater(displayFunc);
                }

                TimeUnit.MILLISECONDS.sleep(SLEEP_TIME);
            } catch (InterruptedException e) {
                while (isSuspended()) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(100);
                    } catch (InterruptedException e1) {
                        if (isStopped()) {
                            break;
                        }
                    }
                }

                if (isStopped()) {
                    break;
                }
            }
        }

        if (finishFunc != null) {
            Platform.runLater(finishFunc);
        }
    }

    public void start() {
        if (primeFactors == null) {
            throw new IllegalStateException("PrimeFactors is not set");
        }
        totalNumbers = primeFactors.getTo() - primeFactors.getFrom() + 1;

        primeFactorsThread = new Thread(this);
        setSuspended(false);
        setStopped(false);
        primeFactorsThread.start();
    }

    public void suspend() {
        setSuspended(true);

        if (primeFactorsThread != null) {
            primeFactorsThread.interrupt();
        }
    }

    public void resume() {
        setSuspended(false);

        if (primeFactorsThread != null) {
            primeFactorsThread.interrupt();
        }
    }

    public void stop() {
        setStopped(true);

        if (primeFactorsThread != null) {
            primeFactorsThread.interrupt();
        }
    }
}
