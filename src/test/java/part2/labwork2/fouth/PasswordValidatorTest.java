package part2.labwork2.fouth;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PasswordValidatorTest {
    private final PasswordValidator validator = new PasswordValidator();

    @Test
    void isValidPassword() {
        assertTrue(validator.isValidPassword("Aa1*abcdef"));
        assertFalse(validator.isValidPassword("a1*abcdef"));
        assertFalse(validator.isValidPassword("A1*ABCDEF"));
        assertFalse(validator.isValidPassword("Aa*abcdef"));
        assertFalse(validator.isValidPassword("Aa1abcdef"));
        assertFalse(validator.isValidPassword("Aa1*a"));
        assertFalse(validator.isValidPassword("Aa1*ab@"));
    }
}