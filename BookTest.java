/*
 * For testing code.
 */
import java.io.File;
import java.io.FileNotFoundException;

public class BookTest {
	
	public static void main(String[] args) throws FileNotFoundException {
//		Book poeBook = new Book(new File("./books/moby.txt"));
		BookList allBooks = initBooks();
		allBooks.printTotalWordCount();
		System.out.println();
		allBooks.printPercentWord("the");
		System.out.println();
		allBooks.printUniqueCount();
		
//		poeBook.ensureWordCount();
//		poeBook.ensureWordList();
//		poeBook.ensureWordSet();
//		System.out.println("Count");
//		System.out.println(poeBook.wordCount);
//		System.out.println("List");
//		System.out.println(poeBook.wordList);
//		System.out.println("Set");
//		System.out.println(poeBook.wordSet);
		
//		System.out.println(poeBook.getTotalWordCount());
//		System.out.println(poeBook.wordCount.get("bob"));
//		System.out.println(poeBook.percentWordCounter("bob"));
//		System.out.println(poeBook.wordCount.get("the"));
//		System.out.println(poeBook.percentWordCounter("the"));
	}
	
	public static BookList initBooks() {
		File booksFolder = new File("./books");
		BookList allBooks = new BookList();
		
		for(File bookFile : booksFolder.listFiles()) {
			allBooks.add(new Book(bookFile));
		}
		
		return allBooks;
	}
}
