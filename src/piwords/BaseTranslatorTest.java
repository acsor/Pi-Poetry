package piwords;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class BaseTranslatorTest {

    private static final int VAL_DEFAULT_INPUT_BASE = 16;
    private static final int VAL_DEFAULT_OUTPUT_BASE = 26;

    private final Random randomGen;

    public BaseTranslatorTest () {
        randomGen = new Random();
    }

    @Test
    public void basicBaseTranslatorTest () {
        // Expect that .01 in base-2 is .25 in base-10
        // (0 * 1/2^1 + 1 * 1/2^2 = .25)
        int[] input = {0, 1};
        int[] expectedOutput = {2, 5};

        assertArrayEquals(
                expectedOutput,
                BaseTranslator.convertBase(input, 2, 10, 2)
        );
    }

    @Test
    public void convertBaseNegativeDigits () {
        final int[] input = {0, 3, 4, -1, -10};

        assertEquals(
                BaseTranslator.convertBase(
                        input,
                        VAL_DEFAULT_INPUT_BASE,
                        VAL_DEFAULT_OUTPUT_BASE,
                        input.length / 2
                ),
                null
        );
    }

    @Test
    public void convertBaseDigitsTooHigh () {
        final int[] input = {3, 4, 16, 9, 20, 14};

        assertEquals(
                BaseTranslator.convertBase(
                        input,
                        VAL_DEFAULT_INPUT_BASE,
                        VAL_DEFAULT_OUTPUT_BASE,
                        input.length / 2
                ),
                null
        );
    }

    @Test
    public void convertBaseInputBaseTooLittle () {
        final int[] input = {4, 14, 15, 4, 3, 10, 4, 0, 3};

        assertEquals(
                BaseTranslator.convertBase(
                        input,
                        1,
                        VAL_DEFAULT_OUTPUT_BASE,
                        input.length / 2
                ),
                null
        );
    }

    @Test
    public void convertBaseOutputBaseTooLittle () {
        final int[] input = {4, 14, 15, 4, 3, 10, 4, 0, 3};

        assertEquals(
                BaseTranslator.convertBase(
                        input,
                        VAL_DEFAULT_INPUT_BASE,
                        1,
                        input.length / 2
                ),
                null
        );
    }

    @Test
    public void convertBasePrecisionTooLittle () {
        final int[] input = {4, 14, 15, 4, 3, 10, 4, 0, 3};

        assertEquals(
                BaseTranslator.convertBase(
                        input,
                        VAL_DEFAULT_INPUT_BASE,
                        VAL_DEFAULT_OUTPUT_BASE,
                        0
                ),
                null
        );
    }

    // TODO: Write more tests (Problem 2.a)

}
