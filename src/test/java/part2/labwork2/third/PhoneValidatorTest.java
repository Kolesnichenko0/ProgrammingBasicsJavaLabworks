package part2.labwork2.third;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class PhoneValidatorTest {
    private final PhoneValidator phoneValidator = new PhoneValidator();

    @Test
    void testIsValidPhone() {
        assertTrue(phoneValidator.isValidPhone("+380671234567"));
        assertTrue(phoneValidator.isValidPhone("380671234567"));
        assertTrue(phoneValidator.isValidPhone("0671234567"));
        assertTrue(phoneValidator.isValidPhone("671234567"));
        assertTrue(phoneValidator.isValidPhone("+38067-123-45-67"));

        assertFalse(phoneValidator.isValidPhone("+38067123456"));
        assertFalse(phoneValidator.isValidPhone("+38067-123-45-6"));
        assertFalse(phoneValidator.isValidPhone("+3806712345678"));
        assertFalse(phoneValidator.isValidPhone("+380661234567"));
    }
}