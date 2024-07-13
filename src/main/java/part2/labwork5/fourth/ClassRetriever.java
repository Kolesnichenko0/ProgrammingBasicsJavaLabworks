package part2.labwork5.fourth;

import java.util.HashMap;
import java.util.Map;

public class ClassRetriever {
    private final Map<String, Class<?>> primitiveTypeMap;

    public ClassRetriever() {
        this.primitiveTypeMap = new HashMap<>();
        primitiveTypeMap.put("boolean", boolean.class);
        primitiveTypeMap.put("byte", byte.class);
        primitiveTypeMap.put("short", short.class);
        primitiveTypeMap.put("int", int.class);
        primitiveTypeMap.put("long", long.class);
        primitiveTypeMap.put("float", float.class);
        primitiveTypeMap.put("double", double.class);
        primitiveTypeMap.put("char", char.class);
    }

    public Class<?> getClassForName(String className) throws ClassNotFoundException {
        if (className.endsWith("[]")) {
            String baseTypeName = className.substring(0, className.length() - 2);
            Class<?> baseType = primitiveTypeMap.get(baseTypeName);
            if (baseType != null) {
                return java.lang.reflect.Array.newInstance(baseType, 0).getClass();
            } else {
                return Class.forName("[L" + baseTypeName + ";");
            }
        } else {
            Class<?> primitiveType = primitiveTypeMap.get(className);
            if (primitiveType != null) {
                return primitiveType;
            } else {
                return Class.forName(className);
            }
        }
    }
}
