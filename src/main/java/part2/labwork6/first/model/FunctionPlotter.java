package part2.labwork6.first.model;

import javafx.application.Platform;
import part2.labwork6.first.model.FunctionCodeCompiler;
import part2.labwork6.first.model.FunctionData;
import part2.labwork6.first.model.FunctionParameters;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.SortedMap;
import java.util.TreeMap;

public class FunctionPlotter implements Runnable {
    private final FunctionCodeCompiler functionCodeCompiler;
    private FunctionParameters functionParameters;
    private FunctionData functionData;
    private final Runnable showErrorFunc;
    private final Runnable displayFunc;
    private String errorMessage;
    private final SortedMap<Double, Double> points;

    public FunctionPlotter(Runnable showErrorFunction, Runnable displayFunc) {
        this.showErrorFunc = showErrorFunction;
        this.displayFunc = displayFunc;
        functionCodeCompiler = new FunctionCodeCompiler(this);
        points = new TreeMap<>();
    }

    public void setFunctionParameters(FunctionParameters functionParameters) {
        this.functionParameters = functionParameters;
    }

    public FunctionData getFunctionData() {
        return functionData;
    }

    public void setFunctionData(FunctionData functionData) {
        this.functionData = functionData;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public SortedMap<Double, Double> getPoints() {
        return points;
    }

    public static void checkNotNull(Object object, String objectName) {
        if (object == null) {
            throw new IllegalStateException(objectName + " is not set");
        }
    }

    private Class<?> loadCompiledClass() {
        try {
            File compiledDirectory = new File(FunctionCodeCompiler.COMPILED_PATH);
            URLClassLoader classLoader = new URLClassLoader(
                    new URL[]{compiledDirectory.toURI().toURL()});
            return Class.forName(functionData.getClassName(), true, classLoader);
        } catch (Exception e) {
            throw new RuntimeException("Couldn't load the compiled class: " + e.getMessage());
        }
    }

    private Method getMethodFromClass(Class<?> compiledClass) {
        try {
            return compiledClass.getMethod("h", double.class, double.class, double.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Couldn't get the method 'h' from the class: " + e.getMessage());
        }
    }

    private void runMethodForRange(Method method) {
        points.clear();
        try {
            for (double x = functionParameters.getFrom();
                 x <= functionParameters.getTo(); x += functionParameters.getStep()) {
                double y = (double) method.invoke(null, functionParameters.getA(), functionParameters.getB(), x);
                points.putIfAbsent(x, y);
            }
        } catch (Exception e) {
            throw new RuntimeException("Couldn't run the method for the range of values" + e.getMessage());
        }
    }

    public void run() {
        try {
            functionCodeCompiler.generateAndCompileCode();
            Class<?> compiledClass = loadCompiledClass();
            Method method = getMethodFromClass(compiledClass);
            runMethodForRange(method);

            if (displayFunc != null) {
                Platform.runLater(displayFunc);
            }
        } catch (RuntimeException e) {
            setErrorMessage(e.getMessage());
            Platform.runLater(showErrorFunc);
        }
    }

    public void start() {
        checkNotNull(functionData, "functionData");
        checkNotNull(functionParameters, "functionParameters");
        Thread plotterThread = new Thread(this);
        plotterThread.start();
    }
}
