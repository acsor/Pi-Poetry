package piwords;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class WordFinderTest {

    @Test
    public void basicGetSubstringsTest () {
        String haystack = "abcde";
        String[] needles = {"ab", "abc", "de", "fg"};

        Map<String, Integer> expectedOutput = new HashMap<String, Integer>();
        expectedOutput.put("ab", 0);
        expectedOutput.put("abc", 0);
        expectedOutput.put("de", 3);

        assertEquals(expectedOutput, WordFinder.getSubstrings(haystack,
                needles));
    }

    @Test
	public void emptyHaystackTest () {
    	final String haystack = "";
    	final String[] needles = {"hot", "dog", "cold", "cat"};
    	final Map<String, Integer> expectedResult = new HashMap<>();

    	Assert.assertTrue(
    			expectedResult.equals(WordFinder.getSubstrings(haystack, needles))
		);
	}

	@Test
	public void emptyNeedlesTest () {
    	final String haystack = "dijfdropapoianxiouspqoieglass";
    	final String[] needles = {};
		final Map<String, Integer> expectedResult = new HashMap<>();

		Assert.assertTrue(
				expectedResult.equals(WordFinder.getSubstrings(haystack, needles))
		);
	}

    @Test
	public void needlesNotInTest () {
    	final String haystack = "catpiadfpojqsausageipjump";
    	// final String[] needlesIn = {"cat", "sausage", "jump"};
		final String needlesNotIn[] = {"dog", "sea", "fly", "combat", "code"};
		final Map<String, Integer> subStrings = WordFinder.getSubstrings(haystack, needlesNotIn);

		for (String notIn: needlesNotIn) {
			Assert.assertFalse(
					subStrings.containsKey(notIn)
			);
		}
	}

	@Test
	public void needlesInTest () {
    	final String haystack = "horseapdfioapftraveladifpaoimountainbikebeauty";
    	final String[] needles = {"horse", "pdf", "travel", "mountain", "bike", "beauty"};
    	final Map<String, Integer> expectedResult = new HashMap<>();

    	expectedResult.put("horse", 0);
		expectedResult.put("pdf", 6);
		expectedResult.put("travel", 14);
		expectedResult.put("mountain", 28);
		expectedResult.put("bike", 36);
		expectedResult.put("beauty", 40);

		Assert.assertEquals(
				expectedResult.keySet().size(),
				needles.length
		);
		Assert.assertTrue(
			expectedResult.entrySet().equals(
					WordFinder.getSubstrings(haystack, needles).entrySet()
			)
		);
	}

	/**
	 * Method to test that, in case of repeated needles, only the lowest index is returned.
	 */
	@Test
	public void repeatedNeedleTest () {
    	// "eight" and "moon" are repeated twice
    	final String haystack = "eightapiosfmoonaeightpdofiloungemoonadpoisport";
    	final String[] needles = {"eight", "moon", "lounge", "sport"};
    	final Map<String, Integer> expectedResult = new HashMap<>();

    	expectedResult.put("eight", 0);
		expectedResult.put("moon", 11);
		expectedResult.put("lounge", 26);
		expectedResult.put("sport", 41);

		Assert.assertEquals(
				expectedResult.keySet().size(),
				needles.length
		);
		Assert.assertTrue(
				expectedResult.entrySet().equals(
						WordFinder.getSubstrings(haystack, needles).entrySet()
				)
		);
	}

}
