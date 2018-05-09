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
	
	// Used to ensure that a book isn't added multiple times.
	public boolean contains(Book book) {
		return titleSet.contains(book.getTitle());
	}
	
	// Displays each book and its number
	public String toString() {
		String output = "";
		for(int i = 0; i < books.size(); i++) {
			output += "\n" + i + ") " + books.get(i).getTitle() + " by " + books.get(i).getAuthor();
		}
		return output;
	}
	
	// These methods call the respective method of each book object in the list
	// and display the result.
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
		System.out.println("# times '" + search + "' appears in book");
		for(Book b : books) {
    		System.out.println(b.countWord(search) + "\t|\t" + b.getTitle());
    	}
	}
	
	public void replace(String oldWord, String newWord) {
		for(Book b : books) {
    		b.replaceWord(oldWord, newWord);
    	}
	}
	
	public void printRandomWord() {
		System.out.println("Random word from book");
		for(Book b : books) {
    		System.out.println(b.randomWord() + "\t|\t" + b.getTitle());
    	}
	}
}
