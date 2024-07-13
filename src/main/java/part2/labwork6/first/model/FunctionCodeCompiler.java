package part2.labwork6.first.model;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FunctionCodeCompiler {
    private FunctionPlotter functionPlotter;
    public static final String BASE_PATH = "storage/part2/labwork6/first";
    public static final String GENERATED_PATH = BASE_PATH + "/generated-src";
    public static final String COMPILED_PATH = BASE_PATH + "/compiled-src";

    public FunctionCodeCompiler(FunctionPlotter functionPlotter) {
        this.functionPlotter = functionPlotter;
    }

    public void generateAndCompileCode() {
        FunctionPlotter.checkNotNull(functionPlotter.getFunctionData(), "functionData");
        String className = functionPlotter.getFunctionData().getClassName();
        File sourceFile = new File(GENERATED_PATH, className + ".java");

        if (!sourceFile.exists()) {
            try {
                generateSourceCode(sourceFile, className);
                if (!compileSourceCode(sourceFile)) {
                    throw new RuntimeException("Couldn't compile the code.");
                }
            } catch (IOException e) {
                throw new RuntimeException("Couldn't generate code: " + e.getMessage());
            }
        }
    }

    private void generateSourceCode(File sourceFile, String className) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(sourceFile))) {
            out.println("public class " + className + " {");

            out.println("    private static double f(double x) {");
            out.println("        return " + functionPlotter.getFunctionData().getFunctionF() + ";");
            out.println("    }");

            out.println("    private static double g(double x) {");
            out.println("        return " + functionPlotter.getFunctionData().getFunctionG() + ";");
            out.println("    }");

            out.println("    public static double h(double a, double b, double x) {");
            out.println("        return a * f(x) - g(b * x);");
            out.println("    }");

            out.println("}");
        }
    }

    private boolean compileSourceCode(File sourceFile) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        return compiler.run(null, null, null, "-d",
                COMPILED_PATH, sourceFile.getPath()) == 0;
    }

}
