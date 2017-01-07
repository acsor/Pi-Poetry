package piwords;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class PiGeneratorTest {

    private static final int CONST_MAX_BASE = 30;
    private static final int CONST_MAX_EXP = 9;
    private static final int CONST_MAX_MOD = 20;

    private static final int CONST_DEFAULT_MOD = 7;

    private static final String DIR_ROOT_FILES_PI = "data";
    private static final String FILE_PI_SMALL = "pi-hex.62500.txt";
    private static final String FILE_PI_LARGE = "pi-hex.1000000.txt";

    private Random randomGen;

    public PiGeneratorTest () {
        randomGen = new Random();
    }

    @Test
    public void basicPowerModTest () {
        // 5^7 mod 23 = 17
        assertEquals(17, PiGenerator.powerMod(5, 7, 23));
    }

    @Test
    public void powerModTest () {
        final int[] bases = randomGen.ints(4, 1, CONST_MAX_BASE).toArray();
        final int minExponent = 0;
        final int modulo = randomGen.nextInt(CONST_MAX_MOD) + 1; // The +1 is a dirty workaround for preventing zero moduli

        for (int exp = minExponent; exp <= CONST_MAX_EXP; exp++) {
            for (int base: bases) {
                // System.out.format("%d ^ %d mod %d = %d\n", base, exp, modulo, (int) Math.pow(base, exp) % modulo);
                // System.out.format("powerMod(%d, %d, %d) = %d\n\n", base, exp, modulo, PiGenerator.powerMod(base, exp, modulo));
                Assert.assertEquals(
                        Math.pow(base, exp) % modulo,
                        PiGenerator.powerMod(base, exp, modulo),
                        0
                );
            }
        }
    }

    /**
    @Test
    public void powerModTestTemp () {
        final int base = 20, exp = 3, modulo = 8;

        System.out.format("%d ^ %d mod %d = %d\n", base, exp, modulo, (int) Math.pow(base, exp) % modulo);
        System.out.format("powerMod(%d, %d, %d) = %d\n\n", base, exp, modulo, PiGenerator.powerMod(base, exp, modulo));

        Assert.assertEquals(
                Math.pow(base, exp) % modulo,
                PiGenerator.powerMod(base, exp, modulo),
                0
        );
    }
    */

    @Test
    public void powerModTestZeroExponent () {
        final int
        base = randomGen.nextInt(CONST_MAX_BASE),
        exp = 0,
        mod = CONST_DEFAULT_MOD;

        Assert.assertEquals(
                Math.pow(base, exp) % mod,
                PiGenerator.powerMod(base, exp, mod),
                0
        );
    }

    @Test
    public void powerModTestOneModulo () {
        final int
                base = randomGen.nextInt(CONST_MAX_BASE),
                exp = randomGen.nextInt(CONST_MAX_EXP),
                mod = 1;

        Assert.assertEquals (
                Math.pow(base, exp) % mod,
                PiGenerator.powerMod(base, exp, mod),
                0
        );
    }

    @Test
    public void computePiInHexTest () {
        final int precision = 200;
        final int[] readDigits = readHexPiDigits(precision);
        final int[] computedDigits = PiGenerator.computePiInHex(precision);

        // printIntArray(readDigits);
        // printIntArray(computedDigits);

        Assert.assertArrayEquals(
                readDigits,
                computedDigits
        );
    }

    @Test
    public void computePiInHexTestNeg () {
        Assert.assertTrue(
                PiGenerator.computePiInHex(-1) == null
        );
    }

    /**
     * Reads precision PI digits from file.
     * The files from which the digits are extracted are required to contain digits greater than 9 in letter form (that is,
     * an A indicates 10, a B 11, C 12 and so on).
     * The array returned contains digits in decimal form (that is, a 10 for A, an 11 for B, a 12 for C, ...).
     * @param precision The number of PI digits to read.
     * @return array of precision PI digits expressed in decimal expansion.
     */
    private int[] readHexPiDigits (int precision) {
        final int[] result = new int[precision];

        final String piFile = (precision <= 62500) ? FILE_PI_SMALL: FILE_PI_LARGE;
        final String piFileFullPath = String.format("%s/%s", DIR_ROOT_FILES_PI, piFile);
        Scanner reader = null;

        try {
            reader = new Scanner(new File(piFileFullPath));
            reader.useDelimiter("");

            for (int i = 0; i < result.length && reader.hasNextInt(16); i++) {
                result[i] = reader.nextInt(16);
            }
        } catch (FileNotFoundException e) {
            System.err.format("File \"%s\" not found.", piFileFullPath);
        } finally {
            if (reader != null) {
                reader.close();
            }
        }

        return result;
    }

    private void printIntArray (int[] array) {
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                System.out.format("%d|", array[i]);
            }
        }
        System.out.println();
    }

    // TO-DO: Write more tests (Problem 1.a, 1.c)

}
