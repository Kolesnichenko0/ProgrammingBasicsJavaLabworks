package part1.labwork4.fourth;

import java.util.List;
import java.util.Optional;

public class NumberListOperationUtils {
    private NumberListOperationUtils() {
    }

    public static <T extends Number> int indexOfFirstZeroElement(List<T> numbers) {
        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i).doubleValue() == 0.0) {
                return i;
            }
        }
        return -1;
    }

    public static <T extends Number> int countNegativeNumbers(List<T> numbers) {
        int count = 0;
        for (T number : numbers) {
            if (number.doubleValue() < 0.0) {
                count++;
            }
        }
        return count;
    }

    public static <T extends Number> Optional<T> getLastNegativeElement(List<T> numbers) {
        T lastNegativeElement = null;
        for (T number : numbers) {
            if (number.doubleValue() < 0.0) {
                lastNegativeElement = number;
            }
        }
        return Optional.ofNullable(lastNegativeElement);
    }
}
