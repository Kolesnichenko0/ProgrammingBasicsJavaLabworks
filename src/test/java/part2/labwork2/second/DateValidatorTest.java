package part2.labwork2.second;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DateValidatorTest {
    private final DateValidator dateValidator = new DateValidator();

    @Test
    void isValidDate() {
        assertTrue(dateValidator.isValidDate("01.01.2022"));
        assertFalse(dateValidator.isValidDate("876-01-2022"));
    }
}