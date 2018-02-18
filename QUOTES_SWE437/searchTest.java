import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class searchTest {
	QuoteList quotes = new QuoteList(); // find a way to fill list with quotes in xml
	// quotes will be the object we are testing [e.g. quotes.search(validText,
	// mode)]

	QuoteList returnList = new QuoteList(); // return value

	int mode;

	String validText = "ENTER VALID QUOTE TEXT";
	String validAuthor = "ENTER VALID AUTHOR";
	String validAny = "ENTER A VALID AUTHOR OR TEXT";
	String invalidString = "ENTER INVALID TEXT";

	/*
	 * Test 1 needs a mode of 0 (search author), and the string should be a valid
	 * author in the QuoteList. Test 2 needs a mode of 1 (search text), and the
	 * string should be a valid piece of text in the QuoteList. Test 3 needs a mode
	 * of 2 (search text and author), and the string should be a valid piece of text
	 * or author. Test 4 can be ANY mode, but the string should not be a valid
	 * author or valid piece of text in the QuoteList.
	 */

	@Test
	public void testSearch1() {
		mode = 0;
		returnList = quotes.search(validText, mode);
		int size = returnList.getSize();
		assertTrue(size != 0);
	}

	@Test
	public void testSearch2() {
		mode = 1;
		returnList = quotes.search(validAuthor, mode);
		int size = returnList.getSize();
		assertTrue(size != 0);
	}

	@Test
	public void testSearch3() {
		mode = 2;
		returnList = quotes.search(validAny, mode);
		int size = returnList.getSize();
		assertTrue(size != 0);
	}

	@Test
	public void testSearch4() {
		mode = 0;
		returnList = quotes.search(invalidString, mode);
		int size = returnList.getSize();
		assertTrue(size == 0);
	}
}
