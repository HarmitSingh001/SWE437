

import java.util.ArrayList;
import java.util.Random;

/**
 * List of all the quotes.
 * @author Mongkoldech Rajapakdee & Jeff Offutt
 *       Date: Nov 2009
 */
public class QuoteList
{
   private ArrayList<Quote> quoteArray;

   // These constants are used in the servlet
   /* package */ static final int SearchAuthorVal = 0;
   /* package */ static final int SearchTextVal   = 1;
   /* package */ static final int SearchBothVal   = 2;
   
   static final int SearchKeywordVal = 3; // keyword search value added

   // For returning a random quote
   private Random randomGen;
   private final int seed = 19580427;

   // Default constructor creates a new list and initializes the random seed
   public QuoteList()
   {
      this.quoteArray = new ArrayList<Quote>();
      randomGen = new Random (seed);
   }

   // Called when a quote is found, added to the array
   public void setQuote (Quote q)
   {
      quoteArray.add (q);
   }

   // Current size of the quote list
   public int getSize()
   {
      return quoteArray.size();
   }

   // Returns the ith quote from the list
   public Quote getQuote (int i)
   {
      return (Quote) quoteArray.get (i);
   }

   /**
    * Search the quotes in the list, based on searchString
    * @param searchString String input for search
    * @param mode search in the author, quotr, or both
    * @return QuoteList containing the search results (may be multiple quotes)
    */
   public QuoteList search (String searchString, int mode)
   {
      QuoteList returnQuote = new QuoteList();
      Quote quote;
      for (int i = 0; i < quoteArray.size(); i++)
      {
         quote = quoteArray.get (i);
         if (mode == SearchAuthorVal && quote.getAuthor().toLowerCase().indexOf (searchString.toLowerCase()) != -1)
         {  // Found a matching author, save it
            // System.out.println ("Matched Author ");
            returnQuote.setQuote (quote);
         } else if (mode == SearchTextVal && quote.getQuoteText().toLowerCase().indexOf (searchString.toLowerCase()) != -1)
         {  // Found a matching quote, save it
            // System.out.println ("Matched Text ");
            returnQuote.setQuote (quote);
         } else if ((mode == SearchBothVal) &&
                    (quote.getAuthor().toLowerCase().indexOf (searchString.toLowerCase()) != -1 ||
                     quote.getQuoteText().toLowerCase().indexOf (searchString.toLowerCase()) != -1))
         {  // Found a matching author or quote, save it
            // System.out.println ("Matched Both ");
            returnQuote.setQuote (quote);
         }else if ((mode == SearchKeywordVal) && (quote.getKeyword().toLowerCase().equals(searchString.toLowerCase()))) {
        	 	// Found a matching keyword, save it
        	 	returnQuote.setQuote (quote);
         }
      }
      return returnQuote;
   }

   /**
    * Retuen a random quote object from the list.
    * @return a random Quote
    */
   public Quote getRandomQuote ()
   {
      return quoteArray.get (randomGen.nextInt (quoteArray.size()));
   }
   
 //1: make another quote list object
 	// take old quote list object with new quote list object
 	// take both, check for double entries, create XML file
   
   //merge two quote lists
   public QuoteList merge (QuoteList list1, QuoteList list2) {
	   list1.quoteArray.removeAll(list2.quoteArray);
	   list1.quoteArray.addAll(list2.quoteArray);
	   return list1;
   }
   
}
