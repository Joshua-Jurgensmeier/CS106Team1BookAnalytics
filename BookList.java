/*
 * A class to represent a list of books.
 */
import java.util.*;

public class BookList {
	// used to store books and easily access them by index.
	ArrayList<Book> books;
	// Used to efficiently check if a books is in this list.
	HashSet<String> titleSet;
	
	public BookList() {
		books = new ArrayList<Book>();
		titleSet = new HashSet<String>();
	}
	
	public void add(Book book) {
		books.add(book);
		titleSet.add(book.getTitle());
	}
	
	public void remove(int index) {
		titleSet.remove(books.get(index).getTitle());
		books.remove(index);
	}
	
	public Book get(int index) {
		return books.get(index);
	}
	
	public void removeAll() {
		books.clear();
		titleSet.clear();
	}
	
	public boolean contains(Book book) {
		return titleSet.contains(book.getTitle());
	}
	
	public String toString() {
		String output = "";
		for(int i = 0; i < books.size(); i++) {
			output += "\n" + i + ") " + books.get(i).getTitle();
		}
		return output;
	}
	
	// Put methods for calling methods on books below.
	public void printTotalWordCount() {
		System.out.println("# total words");
		for(Book b : books) {
    		System.out.println(b.getTotalWordCount() + "\t|\t" + b.getTitle());
    	}
    }
	
	public void printUniqueCount() {
		System.out.println("# unique words");
		for(Book b : books) {
    		System.out.println(b.getUniqueWordCount() + "\t|\t" + b.getTitle());
    	}
	}
	
	public void printPercentWord(String search) {
		System.out.println("% of book is '" + search + "'");
		for(Book b : books) {
    		System.out.println(b.percentWord(search) + "%\t|\t" + b.getTitle());
    	}
	}
	
	public void printWordCount(String search) {
		
	}
}
