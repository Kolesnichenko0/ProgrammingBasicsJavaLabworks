package part2.labwork5.fourth;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class MethodAnnotationProcessorConsoleApplication {
    private MethodAnnotationProcessor methodAnnotationProcessor;
    private ClassRetriever classRetriever;

    public MethodAnnotationProcessorConsoleApplication() {
        this.methodAnnotationProcessor = new MethodAnnotationProcessor();
        this.classRetriever = new ClassRetriever();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\033[1;32m" + "Enter full class name:" + "\033[0m");
        String className = scanner.nextLine();
        System.out.println("\033[1;32m" + "Enter method name:" + "\033[0m");
        String methodName = scanner.nextLine();
        System.out.println("\033[1;32m" + "Enter method parameter types (comma separated):" + "\033[0m");
        String methodParams = scanner.nextLine();

        try {
            Class<?>[] paramClasses;
            if (methodParams.isEmpty()) {
                paramClasses = new Class<?>[0];
            } else {
                String[] paramTypes = methodParams.split("\\s*,\\s*");
                paramClasses = new Class<?>[paramTypes.length];
                for (int i = 0; i < paramTypes.length; i++) {
                    paramClasses[i] = classRetriever.getClassForName(paramTypes[i]);
                }
            }

            Method method = Class.forName(className).getMethod(methodName, paramClasses);
            methodAnnotationProcessor.showMethodAnnotations(method);
        } catch (ClassNotFoundException e) {
            System.err.println("Class not found: " + e.getMessage());
        } catch (NoSuchMethodException e) {
            System.err.println("Method not found: " + e.getMessage());
        } catch (IllegalAccessException | InvocationTargetException e) {
            System.err.println("Failed to get value for parameter: " + e.getMessage());
        }

    }


    public static void main(String[] args) {
        new MethodAnnotationProcessorConsoleApplication().run();
    }
}
