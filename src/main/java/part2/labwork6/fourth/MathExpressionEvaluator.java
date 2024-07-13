package part2.labwork6.fourth;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.PolyglotException;

public class MathExpressionEvaluator {
    private final Context context;

    public MathExpressionEvaluator() {
        this.context = Context.newBuilder("js")
                .option("engine.WarnInterpreterOnly", "false")
                .build();
    }

    public void evaluateExpression(String expression) {
        try {
            context.eval("js", "print('Result: ' + (" + expression + ") + '\\n');");
        } catch (PolyglotException e) {
            System.out.println("Error evaluating the expression: " + e.getMessage());
        }
    }

    public void close() {
        context.close();
    }
}