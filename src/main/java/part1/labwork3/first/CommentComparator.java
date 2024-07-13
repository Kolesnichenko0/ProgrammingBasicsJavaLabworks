package part1.labwork3.first;

import java.util.Comparator;

/**
 * The class that implements the Comparator interface for sorting by comment alphabetically
 */
public class CommentComparator implements Comparator<Day> {
    /**
     * Compares two days based on comment strings.
     *
     * @param day1 The first day to compare.
     * @param day2 The second day to compare.
     * @return Integer representing the comparison result.
     */
    @Override
    public int compare(Day day1, Day day2) {
        return day1.getComment().compareTo(day2.getComment());
    }
}
