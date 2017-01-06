package piwords;

public class PiGenerator {

    /**
     * Returns precision hexadecimal digits of the fractional part of pi.
     * Returns digits in most significant to least significant order.
     * <p>
     * If precision < 0, return null.
     *
     * @param precision The number of digits after the decimal place to
     *                  retrieve.
     * @return precision digits of pi in hexadecimal.
     */
    public static int[] computePiInHex (int precision) {
        // TODO: Implement (Problem 1.d)
        return new int[0];
    }

    /**
     * Computes a ^ b mod m
     * <p>
     * If a < 0, b < 0, or m < 0, return -1.
     *
     * @param a
     * @param b
     * @param m
     * @return a^b mod m
     */
    public static int powerMod (int a, int b, int m) {
        /*
        let binB equal to the binary expansion of b (the exponent)
        iterate through binB with index variable i
        when binB[i] == 1, we can:
            calculate the power with base base and exponent 2 ^ i
            calculate mod = power % mod
            multiply mod to the result
         */
        if (a < 0 || b < 0 || m < 0) {
            return -1;
        } else if (a == 0) {
            return m;
        }

        final char[] binaryExpansion = Integer.toBinaryString(b).toCharArray();
        int result = 1;
        int power;

        for (int i = binaryExpansion.length - 1; i >= 0; i--) {
            if (Integer.parseInt(String.valueOf(binaryExpansion[i])) == 1) {
                power = calculatePower(a, i, binaryExpansion);

                if (result == 0) {
                    result = power % m;
                } else {
                    result *= power % m;
                }
            }
        }

        return result % m;
    }

    private static int calculatePower (int base, int index, char[] binaryString) {
        final int realIndex = binaryString.length - 1 - index;

        return (int) Math.pow (
                base,
                Math.pow(2, realIndex)
        );
    }

    /**
     * Computes the nth digit of Pi in base-16.
     * <p>
     * If n < 0, return -1.
     *
     * @param n The digit of Pi to retrieve in base-16.
     * @return The nth digit of Pi in base-16.
     */
    public static int piDigit (int n) {
        if (n < 0) return -1;

        n -= 1;
        double x = 4 * piTerm(1, n) - 2 * piTerm(4, n) -
                piTerm(5, n) - piTerm(6, n);
        x = x - Math.floor(x);

        return (int) (x * 16);
    }

    private static double piTerm (int j, int n) {
        // Calculate the left sum
        double s = 0;
        for (int k = 0; k <= n; ++k) {
            int r = 8 * k + j;
            s += powerMod(16, n - k, r) / (double) r;
            s = s - Math.floor(s);
        }

        // Calculate the right sum
        double t = 0;
        int k = n + 1;
        // Keep iterating until t converges (stops changing)
        while (true) {
            int r = 8 * k + j;
            double newt = t + Math.pow(16, n - k) / r;
            if (t == newt) {
                break;
            } else {
                t = newt;
            }
            ++k;
        }

        return s + t;
    }
}
