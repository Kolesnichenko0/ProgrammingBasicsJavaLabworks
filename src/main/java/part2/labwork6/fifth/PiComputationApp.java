package part2.labwork6.fifth;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class PiComputationApp {
    public Thread createUserInputThread(AtomicInteger termCount, AtomicBoolean running,
                                        Thread piThread, Scanner scanner) {
        return new Thread(() -> {
            while (running.get()) {
                try {
                    if (System.in.available() > 0) {
                        String command = scanner.nextLine();
                        if (command.equalsIgnoreCase("count")) {
                            System.out.println("Number of computed terms: " + termCount.get());
                        } else if (command.equalsIgnoreCase("stop")) {
                            running.set(false);
                            piThread.interrupt();
                            break;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void printFinalResults(PiComputationTask piComputationTask, AtomicInteger termCount) {
        double finalPi = piComputationTask.getPiResult();
        int finalTermCount = termCount.get();
        double achievedEpsilon = Math.abs(finalPi - Math.PI);

        System.out.println("Results:");
        System.out.println("Final value of π: " + finalPi);
        System.out.println("Real value of π:  " + Math.PI);
        System.out.println("Total number of computed terms: " + finalTermCount);
        System.out.printf("Achieved precision (ε): %.15f\n\n", achievedEpsilon);
    }


    public void run() {
        System.out.println("Welcome to Pi Computation App!");
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the precision (ε): ");
        double epsilon = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Enter 'count' to see the number of computed terms " +
                "or 'stop' to stop computation and see the result.");

        AtomicInteger termCount = new AtomicInteger(0);
        AtomicBoolean running = new AtomicBoolean(true);

        PiComputationTask piComputationTask = new PiComputationTask(epsilon, termCount, running);
        Thread piThread = new Thread(piComputationTask);
        piThread.start();

        Thread userInputThread = createUserInputThread(termCount, running, piThread, scanner);
        userInputThread.start();

        try {
            piThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        printFinalResults(piComputationTask, termCount);

        System.out.println("Thank you for using Pi Computation App!");
    }

    public static void main(String[] args) {
        PiComputationApp app = new PiComputationApp();
        app.run();
    }
}
