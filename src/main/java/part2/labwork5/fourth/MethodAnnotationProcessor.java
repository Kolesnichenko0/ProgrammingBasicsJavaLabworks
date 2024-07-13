package part2.labwork5.fourth;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodAnnotationProcessor {

    private void showAnnotationParameters(Annotation annotation, Class<? extends Annotation> annotationType) throws InvocationTargetException, IllegalAccessException {
        Method[] annotationMethods = annotationType.getDeclaredMethods();
        if (annotationMethods.length > 0) {
            System.out.println("\t\tParameters: ");
            for (Method annotationMethod : annotationMethods) {
                Object value = annotationMethod.invoke(annotation);
                System.out.println("\t\t\tParameter: " + annotationMethod.getName());
                System.out.println("\t\t\tValue: " + value);
            }
        }
    }

    private void showMetaAnnotations(Class<? extends Annotation> annotationType) {
        Annotation[] metaAnnotations = annotationType.getAnnotations();
        if (metaAnnotations.length > 0) {
            System.out.println("\t\tMeta-Annotations:");
            for (Annotation metaAnnotation : metaAnnotations) {
                System.out.println("\t\t\tMeta-Annotation name:" + metaAnnotation.annotationType().getName());
            }
        }
    }

    private void showAnnotationInfo(Annotation annotation) throws InvocationTargetException, IllegalAccessException {
        Class<? extends Annotation> annotationType = annotation.annotationType();
        System.out.println("\tAnnotation name: " + annotationType.getName());
        showAnnotationParameters(annotation, annotationType);
        showMetaAnnotations(annotationType);
    }

    public void showMethodAnnotations(Method method) throws InvocationTargetException, IllegalAccessException {
        System.out.println("\033[1;34m" + "\nResult:" + "\033[0m");
        Annotation[] annotations = method.getAnnotations();
        if (annotations.length == 0) {
            System.out.println("No annotations with RUNTIME retention policy present on method:\n" + method.toGenericString());
            return;
        }
        System.out.printf("\033[1;33m" + "Method: %s\n" + "\033[0m", method.toGenericString());
        System.out.println("Annotations:");
        for (Annotation annotation : annotations) {
            showAnnotationInfo(annotation);
        }
        System.out.println("\033[1;33m" + "\nNote: Only annotations with RUNTIME retention policy were displayed." + "\033[0m");
    }
}
