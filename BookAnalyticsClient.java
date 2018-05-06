/*
 * The entry point and user interface.
 *
 *
 * 
 * Help:
 * selection
 *     add
 *     remove
 *     list
 *     // Analytics
 *     
 * list
 */

import java.io.*;

public class BookAnalyticsClient {
	public static void main(String[] args) throws FileNotFoundException {
		// Data
		BookList allBooks = initBooks();
		
		System.out.println(allBooks);
		// Program
		
		// Print greeting and basic instructions
		
		//Main loop while user does not want to quit
			// Get user selection
				// 1. Add book to selection
				// 2. Remove book from selection
				// 3. List books
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
