package part2.labwork6.sandbox;


public class StateTest {

    public static void main(String[] args) throws InterruptedException {
        Thread testThread = new Thread();
        System.out.println(testThread.getState()); // NEW
        testThread.start();
        System.out.println(testThread.getState()); // RUNNABLE
        testThread.interrupt(); // перериваємо потік
        Thread.sleep(1000);      // потрібен час для завершення потоку
        System.out.println(testThread.getState()); // TERMINATED
    }
}
