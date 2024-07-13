package part2.labwork6.first.model;

import java.util.HashMap;
import java.util.Map;

public class FunctionDataFactory {
    private final Map<String, FunctionData> cache = new HashMap<>();
    private static int idCounter = 1;
    public static final String BASE_CLASS_NAME = "FunctionH";

    public FunctionData getFunctionData(String fx, String gx) {
        String key = fx + gx;
        if (!cache.containsKey(key)) {
            cache.put(key, new FunctionData(fx, gx, idCounter++));
        }
        return cache.get(key);
    }
}
