import java.util.Scanner;

public class Main {
	public static void main(String args[]) {
		QuoteSaxParser parser = new QuoteSaxParser("/Users/Harry/Desktop/spr18/swe437/quotes/quotes.xml");
		QuoteList quotelist = parser.getQuoteList();
		System.out.println("Random Quote of the Day: ");
		Quote quote = quotelist.getRandomQuote();
		System.out.println(quote.getQuoteText());
		System.out.println("\t-" + quote.getAuthor());
		System.out.println("Menu:");
		System.out.println("To get another Quote enter: Q or q");
		System.out.println("To search for an Author enter: A or a");
		System.out.println("To search for a Quote enter: S or s");
		System.out.println("To do a general search enter: G or g");
		System.out.println("To Exit enter: E or e");
		Scanner scan = new Scanner(System.in);
		boolean cont = true;
		String input;
		while (cont) {
			Scanner next = new Scanner(System.in);
			input = scan.next();
			if (input.equals("Q") || input.equals("q")) {
				Quote randomquote = quotelist.getRandomQuote();
				System.out.println(randomquote.getQuoteText());
				System.out.println("\t-" + randomquote.getAuthor());
			} else if (input.equals("A") || input.equals("a")) {
				System.out.println("Enter The Name of the Author: ");
				String Author = next.nextLine();
				Quote randquote = quotelist.search(Author, 0).getQuote(0);
				System.out.println(randquote.getQuoteText());
				System.out.println("\t-" + randquote.getAuthor());
			} else if (input.equals("S") || input.equals("s")) {
				System.out.println("Enter The Quote: ");
				String Quote = next.nextLine();
				Quote randquote = quotelist.search(Quote, 1).getQuote(0);
				System.out.println(randquote.getQuoteText());
				System.out.println("\t-" + randquote.getAuthor());
			} else if (input.equals("G") || input.equals("g")) {
				System.out.println("Enter The Quote: ");
				String Quote = next.nextLine();
				System.out.println("Enter The Name of the Author: ");
				String Author = next.nextLine();
				Quote randquote = quotelist.search(Quote, 2).getQuote(0);
				Quote randquote2 = quotelist.search(Author, 2).getQuote(0);
				if(randquote.equals(randquote2)) {
					System.out.println(randquote.getQuoteText());
					System.out.println("\t-" + randquote.getAuthor());
				}
			} else if (input.equals("E") || input.equals("e")) {
				cont = false;
				System.out.println("Exiting: Goodbye!");
				scan.close();
				next.close();
				System.exit(0);
			} else {
				System.out.println("Please Select a valid option");
				System.out.println("Menu:");
				System.out.println("To get another Quote enter: Q or q");
				System.out.println("To search for an Author enter: A or a");
				System.out.println("To search for a Quote enter: S or s");
				System.out.println("To do a general search enter: G or g");
				System.out.println("To Exit enter: E or e");
			}
		}
	}
}
