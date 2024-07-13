package part2.labwork5.sanbox;

import java.lang.annotation.Annotation;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Test {

//    public static void main(String[] args) {
//        Class<?> cls = java.util.Map.Entry.class;
//        Object o = "fds";
//        for (int i = 0; i < o.getClass().getMethods().length; i++) {
//            System.out.println(o.getClass().getMethods()[i].getName());
//        }
//
//        System.out.println("Simple name: " + cls.getSimpleName());
//        System.out.println("Canonical name: " + cls.getCanonicalName());
////        @SuppressWarnings("unchecked")
//        List<String> list = new ArrayList();
//        list.add("test");
//
//    }

    public static <T> void printElements(T... elements) {
        for (T element : elements) {
            System.out.println(element);
        }
    }

    public static void main(String[] args) {
//        printElements("one", "two", "three");
//        List rawList = new ArrayList();
//        rawList.add("Hello");
//        rawList.add(123);
//
//        List<String> stringList = rawList;
//        for (String s : stringList) {
//            System.out.println(s); // Можливий ClassCastException
//        }

        // Отримуємо клас
        Class<AnnotatedClass> obj = AnnotatedClass.class;

        // Отримуємо всі анотації, які застосовані до класу
        Annotation[] annotations = obj.getAnnotations();

        for (Annotation annotation : annotations) {
            // Виводимо інформацію про тип анотації
            System.out.println("Annotation type: " + annotation.annotationType().getName());
            // Виводимо значення параметра анотації
            if (annotation instanceof MyAnnotation) {
                MyAnnotation myAnnotation = (MyAnnotation) annotation;
                System.out.println("Value: " + myAnnotation.value());
//                java.sql.NumericShaper numericShaper = new java.sql.NumericShaper();
            }
//            Statement stmt = connection.createStatement();
//            java.sql.Statement stmt = conn.createStatement();
//            ResultSet r = stmt.executeQuery("SELECT a, b, c FROM Table");

        }

    }
}
