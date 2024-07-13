package part2.labwork5.second;

import java.lang.reflect.Field;
import java.lang.reflect.InaccessibleObjectException;
import java.util.Scanner;

public class ClassFieldProcessorConsoleApplication {
    private ClassFieldProcessor classFieldProcessor;

    public ClassFieldProcessorConsoleApplication() {
        this.classFieldProcessor = new ClassFieldProcessor();
    }

    private void testShowAllFieldValues() throws IllegalAccessException, ClassNotFoundException {

        System.out.println("\033[1;32m" + "\nCreating test class and its instance..." + "\033[0m");

        class TestClass {
            public int publicField = 1;
            private String privateField = "test";
            protected double protectedField = 2.0;
            int defaultField = 3;
        }

        TestClass testObj = new TestClass();

        System.out.println("\033[1;34m" + "Getting fields of the test class..." + "\033[0m");
        Field[] testFields = testObj.getClass().getDeclaredFields();

        System.out.println("\033[1;34m" + "Setting fields accessible..." + "\033[0m");
        classFieldProcessor.setFieldsAccessible(testFields);

        System.out.println("\033[1;34m" + "Showing all field values of the test class..." + "\033[0m");
        classFieldProcessor.showAllFieldValues(testObj, testFields);
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\033[1;32m" + "Enter full class name:" + "\033[0m");
        String className = scanner.nextLine();

        try {
            System.out.println("\033[1;34m" + "Getting all fields..." + "\033[0m");
            Field[] fields = classFieldProcessor.getAllFields(className);

            System.out.println("\033[1;34m" + "Showing information about fields..." + "\033[0m");
            classFieldProcessor.showFields(fields);

            System.out.println("\033[1;34m" + "Setting fields accessible..." + "\033[0m");
            classFieldProcessor.setFieldsAccessible(fields);

            System.out.println("\033[1;34m" + "Showing static field values..." + "\033[0m");
            classFieldProcessor.showStaticFieldValues(fields);

//            testShowAllFieldValues();
        } catch (ClassNotFoundException e) {
            System.err.println("Class not found: " + className);
        } catch (InaccessibleObjectException e) {
            System.err.println("Failed to set a field accessible: " + e.getMessage());
        } catch (IllegalAccessException e) {
            System.err.println("Failed to access a field: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new ClassFieldProcessorConsoleApplication().run();
    }
}
