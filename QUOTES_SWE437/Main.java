import java.util.Scanner;
import java.io.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Main {
	
	static QuoteList quotelist = new QuoteList();
	
	public static void main(String args[]) {
		QuoteSaxParser parser = new QuoteSaxParser("/Users/Harry/Desktop/spr18/swe437/QUOTES_SWE437/quotes.xml");
		quotelist = parser.getQuoteList();
		System.out.println("Random Quote of the Day: ");
		Quote quote = quotelist.getRandomQuote();
		System.out.println(quote.getQuoteText());
		System.out.println("\t-" + quote.getAuthor());
		startupMenu();
		Scanner scan = new Scanner(System.in);
		boolean cont = true;
		String input;
		while (cont) {
			Scanner next = new Scanner(System.in);
			input = scan.next();
			if (input.equals("Q") || input.equals("q")) { // generate random quote
				Quote randomquote = quotelist.getRandomQuote();
				System.out.println(randomquote.getQuoteText());
				System.out.println("\t-" + randomquote.getAuthor());
			} else if (input.equals("A") || input.equals("a")) { // author search
				System.out.println("Enter The Name of the Author: ");
				String Author = next.nextLine();
				Quote randquote = quotelist.search(Author, 0).getQuote(0);
				System.out.println(randquote.getQuoteText());
				System.out.println("\t-" + randquote.getAuthor());
			} else if (input.equals("S") || input.equals("s")) { // quote search
				System.out.println("Enter The Quote: ");
				String Quote = next.nextLine();
				Quote randquote = quotelist.search(Quote, 1).getQuote(0);
				System.out.println(randquote.getQuoteText());
				System.out.println("\t-" + randquote.getAuthor());
			} else if (input.equals("G") || input.equals("g")) {	// general search
				System.out.println("Enter The Quote: ");
				String Quote = next.nextLine();
				System.out.println("Enter The Name of the Author: ");
				String Author = next.nextLine();
				Quote randquote = quotelist.search(Quote, 2).getQuote(0);
				Quote randquote2 = quotelist.search(Author, 2).getQuote(0);
				if (randquote.equals(randquote2)) {
					System.out.println(randquote.getQuoteText());
					System.out.println("\t-" + randquote.getAuthor());
				}
			} else if (input.equals("D") || input.equals("d")) { // add quote
				System.out.println("Input the Quote you wish to add: ");
				String Quote = next.nextLine();
				System.out.println("Input the Author of the Quote: ");
				String Author = next.nextLine();
				//duplicate quote check
				if(!checkQuoteList(Quote)) {
					try {
						xmlWriter(Quote, Author);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					// if the quote exists it will be shown to user
					System.out.println("Quote Already Exists!: ");
					Quote randquote = quotelist.search(Quote, 1).getQuote(0);
					System.out.println(randquote.getQuoteText());
					System.out.println("\t-" + randquote.getAuthor() + "\n");
					startupMenu();
				}
				updatelist();
			} else if (input.equals("K") || input.equals("k")) { // keyword search
				System.out.println("Input the Keyword to search for: ");
				String keyword = next.nextLine();
				
				
				
			} else if (input.equals("E") || input.equals("e")) { // exit program
				cont = false;
				System.out.println("Exiting: Goodbye!");
				scan.close();
				next.close();
				System.exit(0);
			} else {
				System.out.println("Please Select a valid option");
				startupMenu();
			}
		}
	}

	//start up menu for the program
	public static void startupMenu() {
		System.out.println("Menu:");
		System.out.println("To get another Quote enter: Q or q");
		System.out.println("To search for an Author enter: A or a");
		System.out.println("To search for a Quote enter: S or s");
		System.out.println("To do a General Search enter: G or g");
		System.out.println("To add another Quote enter: D or d");
		System.out.println("To do a Keyword Search enter: K or k" );
		System.out.println("To Exit enter: E or e");
	}
	
	// will go through quotelist to make sure no duplicates are entered
	private static boolean checkQuoteList(String quote) {
		for(int i = 0; i < quotelist.getSize(); i++) {
			Quote quotetemp = quotelist.getQuote(i);
			if(quote.equals(quotetemp.getQuoteText())) {
				return true;
			}
		}
		return false;
	}
	
	// to update the quotelist
	private static void updatelist() {
		QuoteSaxParser parser = new QuoteSaxParser("/Users/Harry/Desktop/spr18/swe437/QUOTES_SWE437/quotes.xml");
		quotelist = parser.getQuoteList();
	}

	public static void xmlWriter(String quote, String author) throws Exception {
		String filename = "/Users/Harry/Desktop/spr18/swe437/QUOTES_SWE437/quotes.xml";
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document doc = docBuilder.parse(filename);

		Node quoteList = doc.getFirstChild();
		Element newQuote = doc.createElement("quote");

		//return back to main method, with menu for user
		if (quote.isEmpty()) {
			System.out.println("No quote was entered!, returning to main menu....\n");
			startupMenu();
			return;
		}

		//return back to main method, with menu for user
		if (author.isEmpty()) {
			System.out.println("No author was entered!, returning to main menu....\n");
			startupMenu();
			return;
		}

		Element endQuote = doc.createElement("quote-text");
		endQuote.appendChild(doc.createTextNode(quote));
		Element newAuthor = doc.createElement("author");
		newAuthor.appendChild(doc.createTextNode(author));

		newQuote.appendChild(endQuote);
		newQuote.appendChild(newAuthor);
		quoteList.appendChild(newQuote);

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(filename));
		transformer.transform(source, result);

		System.out.println("Added Entry!");
	}

}
