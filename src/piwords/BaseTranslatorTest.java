package piwords;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
    @Ignore
    public void convertBaseTenToBin () {
        final int[][] baseTenInput = {
                {4, 9},
                {5, 5},
                {1, 6},
                {3, 2}
        };
        final int[][] baseTwoOutput = {
                {1, 1, 0, 0, 0, 1},
                {1, 1, 0, 1, 1, 1},
                {1, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0}
        };

        for (int i = 0; i < Math.min(baseTenInput.length, baseTwoOutput.length); i++) {
            assertArrayEquals(
                    baseTwoOutput[i],
                    BaseTranslator.convertBase(
                            baseTenInput[i],
                            10,
                            2,
                            baseTwoOutput[i].length
                    )
            );
        }
    }

    @Test
    @Ignore
    public void convertBaseBinToTen () {
        final int[][] baseTwoInput = {
                {1, 1, 0, 0, 0, 1},
                {1, 1, 0, 1, 1, 1},
                {1, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0}
        };
        final int[][] baseTenOutput = {
                {4, 9},
                {5, 5},
                {1, 6},
                {3, 2}
        };

        for (int i = 0; i < Math.min(baseTwoInput.length, baseTenOutput.length); i++) {
            assertArrayEquals(
                    baseTenOutput[i],
                    BaseTranslator.convertBase(
                            baseTwoInput[i],
                            2,
                            10,
                            baseTenOutput[i].length
                    )
            );
        }
    }

    @Test
    public void convertBaseNegativeDigits () {
        final int[] input = {0, 3, 4, -1, -10};

        assertEquals(
                null,
                BaseTranslator.convertBase(
                        input,
                        VAL_DEFAULT_INPUT_BASE,
                        VAL_DEFAULT_OUTPUT_BASE,
                        input.length / 2
                )
        );
    }

    @Test
    public void convertBaseDigitsTooHigh () {
        final int[] input = {3, 4, 16, 9, 20, 14};

        assertEquals(
                null,
                BaseTranslator.convertBase(
                        input,
                        VAL_DEFAULT_INPUT_BASE,
                        VAL_DEFAULT_OUTPUT_BASE,
                        input.length / 2
                )
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

    @Test
    @Ignore
    public void testArray () {
        final int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        final int[] b = a.clone();

        printIntArray(a);
        printIntArray(b);

        assertTrue(a != b);
    }

    private void printIntArray (final int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.format("%d|", array[i]);
        }
        System.out.println();
    }

    // TODO: Write more tests (Problem 2.a)

}
