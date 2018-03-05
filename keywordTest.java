import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.*;
import org.junit.jupiter.api.Test;

class keywordTest {

	
	QuoteSaxParser parser = new QuoteSaxParser("/Users/Harry/Desktop/spr18/swe437/QUOTES_SWE437/quotes.xml");
	QuoteList quotes = parser.getQuoteList();	
	
	
	@Test
	// TDD Test 1 check if there is a keyword present
	public void testKeyword1() {
		boolean isthere = false;
		String keyword = quotes.getRandomQuote().getKeyword();
		isthere = !(keyword.isEmpty());
		assertTrue("Keyword found", isthere == true);
	}
	
	@Test
	// TDD Test 2 check if the calculation of keyword works
	public void testKeyword2() {
		Quote quote = new Quote();
		quote.setAuthor("Dr.java");
		quote.setQuoteText("hello dear world");
		String keyword = quote.getKeyword();
		assertTrue("Keyword Calculation success", keyword.equals("dear"));
	}
	
	@Test
	// TDD Test 3 check if the search for keyword works
	public void testKeyword3() {
		int mode = 3;
		QuoteList retquote = quotes.search("obfuscation!", mode);
		String quoteText = retquote.getQuote(0).getQuoteText();
		assertTrue("Keyword Search success", quoteText.equals("Eschew obfuscation!"));
	}

}
