package piwords;

public class DigitsToStringConverter {

    /**
     * <p>
     * Given a list of digits, a base, and an mapping of digits of that base to
     * chars, convert the list of digits into a character string by applying the
     * mapping to each digit in the input.
     * </p>
     * <p>
     * If digits[i] >= base or digits[i] < 0 for any i, consider the input
     * invalid, and return null.<br>
     * If alphabet.length != base, consider the input invalid, and return null.
     * </p>
     *
     * @param digits   A list of digits to encode. This object is not mutated.
     * @param base     The base the digits are encoded in.
     * @param alphabet The mapping of digits to chars. This object is not
     *                 mutated.
     * @return A String encoding the input digits with alphabet.
     */
    public static String convertDigitsToString (int[] digits, int base, char[] alphabet) {
        if (alphabet.length != base) {
            return null;
        }

        final StringBuilder sb = new StringBuilder(digits.length);

        for (int i: digits) {
            if (i < 0 || i >= base) {
                return null;
            }
            sb.append(alphabet[i]);
        }

        return sb.toString();
    }

}
