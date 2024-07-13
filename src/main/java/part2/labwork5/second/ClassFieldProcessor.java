package part2.labwork5.second;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ClassFieldProcessor {

    public Field[] getAllFields(String className) throws ClassNotFoundException {
        Class<?> c = Class.forName(className);
        return c.getDeclaredFields();
    }

    public void showFields(Field[] fields) {
        for (Field field : fields) {
            System.out.printf("\033[1;32m" + "Field: %s" + "\033[0m" + "\n\tInformation:\n\t\tType: %s\n\t\tModifiers: %s\n",
                    field.getName(),
                    field.getType().getCanonicalName(),
                    Modifier.toString(field.getModifiers()));
        }
    }

    public void setFieldsAccessible(Field[] fields) {
        for (Field field : fields) {
            field.setAccessible(true);
        }
    }

    public void showStaticFieldValues(Field[] fields) throws IllegalAccessException {
        for (Field field : fields) {
            if (Modifier.isStatic(field.getModifiers())) {
                Object value = field.get(null);
                System.out.printf("\033[1;33m" + "Static field: %s\n" + "\033[0m"
                        + "\tValue: %s\n", field.getName(), value);
            }
        }
    }

    public void showAllFieldValues(Object obj, Field[] fields) throws IllegalAccessException {
        for (Field field : fields) {
            Object value = field.get(Modifier.isStatic(field.getModifiers()) ? null : obj);
            System.out.printf("\033[1;33m" + "Field: %s\n" + "\033[0m"
                    + "\tValue: %s\n", field.getName(), value);
        }
    }
}
