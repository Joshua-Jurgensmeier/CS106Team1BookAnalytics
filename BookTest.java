/*
 * For testing code.
 */
import java.io.File;
import java.io.FileNotFoundException;

public class BookTest {
	
	public static void main(String[] args) throws FileNotFoundException {
		Book poeBook = new Book(new File("./books/2000league.txt"));
			
		poeBook.generateWordCount();
		poeBook.generateWordList();
		poeBook.generateWordSet();
		System.out.println("Count");
		System.out.println(poeBook.wordCount);
		System.out.println("List");
		System.out.println(poeBook.wordList);
		System.out.println("Set");
		System.out.println(poeBook.wordSet);
	}
}
