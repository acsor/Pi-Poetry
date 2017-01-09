package piwords;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DigitsToStringConverterTest {

    @Test
    public void basicNumberSerializerTest () {
        int[] input = {0, 1, 2, 3}; // Input is a 4 digit number, 0.123 represented in base 4
        char[] alphabet = {'d', 'c', 'b', 'a'}; // Want to map 0 -> "d", 1 -> "c", 2 -> "b", 3 -> "a"
        final String expectedOutput = "dcba";

        assertEquals(
                expectedOutput,
                DigitsToStringConverter.convertDigitsToString(input, 4, alphabet)
        );
    }

    @Test
    public void testDigitsNegative () {
        int[] input = {10, -10, 4, -949};
        char[] alphabet = "abcdefghijk".toCharArray();

        assertEquals(
                null,
                DigitsToStringConverter.convertDigitsToString(
                        input,
                        alphabet.length,
                        alphabet
                )
        );
    }

    @Test
    public void testDigitsOverflow () {
        int[] input = {10, 10, 4, 11};
        char[] alphabet = "abcdefghijk".toCharArray();

        assertEquals (
                null,
                DigitsToStringConverter.convertDigitsToString(
                        input,
                        alphabet.length,
                        alphabet
                )
        );
    }

    // TODO: Write more tests (Problem 3.a)

}
