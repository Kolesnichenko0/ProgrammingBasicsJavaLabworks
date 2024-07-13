package part2.labwork6.first.model;

public class FunctionData {
    private final String functionF;
    private final String functionG;
    private final int id;
    private final String className;

    public FunctionData(String functionF, String functionG, int id) {
        this.functionF = functionF;
        this.functionG = functionG;
        this.id = id;
        this.className = FunctionDataFactory.BASE_CLASS_NAME + id;
    }

    public String getFunctionF() {
        return functionF;
    }

    public String getFunctionG() {
        return functionG;
    }

    public int getId() {
        return id;
    }

    public String getClassName() {
        return className;
    }
}
