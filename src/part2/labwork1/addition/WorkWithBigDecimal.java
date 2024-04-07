package part2.labwork1.addition;

import java.math.BigDecimal;

public class WorkWithBigDecimal {
    public static void main(String[] args) {
        System.out.println("The difference between BigDecimal(double) and BigDecimal.valueOf(double):");
        double doubleValue = 4.76546458;
        BigDecimal value1 = new BigDecimal(doubleValue);
        //BigDecimal.valueOf(double) використовує `Double.toString(double)`
        // для перетворення `double` в рядок, а потім створює `BigDecimal` з цього рядка.
        BigDecimal value2 = BigDecimal.valueOf(doubleValue);
        System.out.println(value1);
        System.out.println(value2);
        value2.abs();

        //https://stackoverflow.com/questions/33563946/what-is-the-difference-between-toplainstring-and-tostring-in-java
        System.out.println("\nBigDecimal(String) and BigDecimal.toPlainString() methods:");
        BigDecimal value3 = new BigDecimal("1234E+4");
        System.out.println(value3.toString());
        System.out.println(value3.toPlainString());
    }
}
