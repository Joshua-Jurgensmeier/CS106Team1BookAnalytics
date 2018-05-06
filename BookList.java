/*
 * A class to represent a list of books.
 */
import java.util.*;

public class BookList {
	// Title, Book object
	HashMap<String, Book> books;
	
	public BookList() {
		books = new HashMap<String, Book>();
	}
	
	public void add(Book book) {
		books.put(book.getTitle(), book);
	}
	
	public void remove(String title) {
		books.remove(title);
	}
	
	public String toString() {
		String output = "";
		for(String title : books.keySet()) {
			output += "\n" + title;
		}
		return output;
	}
	
	public Book get(String title) {
		return books.get(title);
	}
	
	// Put methods for calling methods on books below.
	
}
