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
        if (precision < 0) {
            return null;
        }
        final int[] result = new int[precision];

        for (int i = 0; i < precision; i++) {
            result[i] = piDigit(i + 1); // Passing i + 1 to piDigit() because we don't want to compute the leading 3.
        }

        return result;
    }

    /**
     * <p>
     * Computes a ^ b mod m.<br>
     * This is the implementation of the author of this code, but it doesn't work.
     * In order to advance to the other points, the one defined below written by another person is used.
     * </p>
     *
     * <p>If a < 0, b < 0, or m < 0, return -1.</p>
     *
     * @param a the base
     * @param b the exponent
     * @param m the modulo
     * @return a ^ b mod m
     */
    public static int powerModUnselected (int a, int b, int m) {
        if (a < 0 || b < 0 || m < 0) {
            return -1;
        }

        final String bBinaryExpansion = Integer.toBinaryString(b);
        long result = 1, power;
        int ithBit;

        a = a % m;

        for (int i = 0; i < bBinaryExpansion.length(); i++) {
            ithBit = Integer.valueOf(String.valueOf(bBinaryExpansion.charAt(i)));

            if (ithBit == 1) {
                power = (long) Math.pow(a, calculateExponent(bBinaryExpansion, i)) % m;

                result = (result * power) % m;
            }
        }

        return (int) result % m;
    }

    public static int powerMod (int a, int b, int m) {
        int baseInt = a;
        int resInt = 1;

        if (a < 0 || b < 0 || m <= 0) {
            return -1;
        }

        assert m * m <= Long.MAX_VALUE;

        if (m == 1) {
            return 0;
        }

        long res = 1;

        a = a % m;
        baseInt = baseInt % m;

        while (b > 0) {
            if (b % 2 != 0) {
                res = ((res % m) * (a % m)) % m;

                resInt = (resInt * baseInt) % m;
            }

            b = b / 2;
            a = (a * a) % m;

            baseInt = (baseInt * baseInt) % m;
        }

        return (int) res;
    }

    private static double calculateExponent (final String bitString, int index)  {
        return Math.pow(2, bitString.length() - 1 - index);
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
