import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class searchTest {
	QuoteSaxParser parser = new QuoteSaxParser("/Users/Harry/Desktop/spr18/swe437/QUOTES_SWE437/quotes.xml");

	QuoteList quotes = parser.getQuoteList();
	
	QuoteList returnList = new QuoteList(); // return value

	int mode;

	/*
	 * Test 1 needs a mode of 0 (search author), and the string should be a valid
	 * author in the QuoteList. Test 2 needs a mode of 1 (search text), and the
	 * string should be a valid piece of text in the QuoteList. Test 3 needs a mode
	 * of 2 (search text and author), and the string should be a valid piece of text
	 * or author. Test 4 can be ANY mode, but the string should not be a valid
	 * author or valid piece of text in the QuoteList.
	 */

	@Test
	// test for search author this should pass with true
	public void testSearch1() {
		mode = 0;
		returnList = quotes.search("Richard Nixon", mode);
		int size = returnList.getSize();
		assertTrue(size != 0);
	}
	
	@Test
	// test for search author this should pass with false
	public void testSearch2() { 
		mode = 0;
		returnList = quotes.search("HARVEY DENT", mode);
		int size = returnList.getSize();
		assertTrue(size == 0);
	}

	@Test
	// test for search text this should pass with true
	public void testSearch3() { 
		mode = 1;
		returnList = quotes.search("For every problem there is one solution which is simple, neat, and wrong.", mode);
		int size = returnList.getSize();
		assertTrue(size != 0);
	}

	@Test
	// test for search text this should pass with false
	public void testSearch4() { 
		mode = 1;
		returnList = quotes.search("HEHEHEHEHEH HAHAHAAHAH HAHAHA", mode);
		int size = returnList.getSize();
		assertTrue(size == 0);
	}

	
	@Test
	// test for search general, finding author based, should come back with true
	public void testSearch5() {
		mode = 2;
		returnList = quotes.search("Richard Nixon", mode);
		int size = returnList.getSize();
		assertTrue(size != 0);
	}

	
	@Test
	// test for search general, finding text based, should come back with true
	public void testSearch6() {
		mode = 2;
		returnList = quotes.search("Don't tell me how hard you work. Tell me how much you get done.", mode);
		int size = returnList.getSize();
		assertTrue(size != 0);
	}
	
	@Test
	// test for search general, finding text based, should come back with false
	public void testSearch7() {
		mode = 2;
		returnList = quotes.search("Magic is in the air", mode);
		int size = returnList.getSize();
		assertTrue(size == 0);
	}
	
	@Test
	// test for search general, finding author based, should come back with true
	public void testSearch8() {
		mode = 2;
		returnList = quotes.search("mike mickey", mode);
		int size = returnList.getSize();
		assertTrue(size == 0);
	}
	
}
