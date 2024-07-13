package part2.labwork6.sandbox;

public class StartNotepad {
    public static void main(String [] args) throws java.io.IOException {
        String[] command = {"notepad"}; // у масиві також можуть бути аргументи
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        processBuilder.start();
    }

}

