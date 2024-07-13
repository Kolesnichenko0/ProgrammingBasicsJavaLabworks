package part2.labwork2.fifth;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringProcessorTest {
    private final StringProcessor processor = new StringProcessor();

    @Test
    void testWithValidInput() {
        String input = "abc456def789am5455445nm45434343";
        String[] expected = {"abc", "def", "am", "nm"};

        String[] result = processor.getSubstrings(input);

        assertArrayEquals(expected, result, "The result should " +
                "match the expected output for valid input");
    }
}