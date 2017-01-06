package piwords;

import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class PiGeneratorTest {

    private static final int CONST_MAX_BASE = 10;
    private static final int CONST_MAX_EXP = 9;
    private static final int CONST_MAX_MOD = 20;

    private static final int CONST_DEFAULT_MOD = 7;

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
        final int modulo = randomGen.nextInt(CONST_MAX_MOD);

        for (int exp = minExponent; exp <= CONST_MAX_EXP; exp++) {
            for (int base: bases) {
                // System.out.format("%d ^ %d mod %d = %d\n", base, exp, modulo, (int) Math.pow(base, exp) % modulo);
                // System.out.format("PiGenerator.powerMod(%d, %d, %d) = %d\n\n", base, exp, modulo, PiGenerator.powerMod(base, exp, modulo));
                Assert.assertEquals(
                        Math.pow(base, exp) % modulo,
                        PiGenerator.powerMod(base, exp, modulo),
                        0
                );
            }
        }
    }

    @Test
    public void powerModTestTemp () {
        final int base = 9, exp = 15, modulo = 13;

        Assert.assertEquals(
                Math.pow(base, exp) % modulo,
                PiGenerator.powerMod(base, exp, modulo),
                0
        );
    }

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
    public void powerModTestZeroModulo () {
        final int
                base = randomGen.nextInt(CONST_MAX_BASE),
                exp = randomGen.nextInt(CONST_MAX_EXP),
                mod = CONST_DEFAULT_MOD;

        Assert.assertEquals (
                Math.pow(base, exp) % mod,
                PiGenerator.powerMod(base, exp, mod),
                0
        );
    }

    // TO-DO: Write more tests (Problem 1.a, 1.c)

}
