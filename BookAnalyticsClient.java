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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BookAnalyticsClient {
	public static void main(String[] args) throws FileNotFoundException {
		// Data
		BookList allBooks = initBooks();
		BookList selectedBooks = new BookList();
		Scanner console = new Scanner(System.in);
		ArrayList<String> command = new ArrayList<String>();
		command.add("");
		
		// Print greeting and basic instructions
		System.out.println("Greetings.");
		
		//Main loop while user does not want to quit
		while(command.get(0) != "exit") {
			// Get user selection
			System.out.print(">> ");
			command = getCommand(console);
			
			if(command.get(0) == "help") {
				help();
				
			} else if(command.get(0).equals("list") && command.size() == 2) {
				// list [all/selected]
				if(command.get(1).equals("all")) {
					System.out.println(allBooks);
				} else if(command.get(1).equals("selected")) {
					System.out.println(selectedBooks);
				} else {
					System.out.println("Invalid use of 'list'. Try 'list all' or 'list selected'.");
				}
				
			} else if(command.get(0).equals("add") && command.size() == 2) {
				// add [book number]
				try {
					int bookNum = Integer.parseInt(command.get(1));
					Book toAdd = allBooks.get(bookNum);
					// Only add book if it's not already in there
					if(!selectedBooks.contains(toAdd)) {
						selectedBooks.add(toAdd);
					}
				} catch(NumberFormatException | IndexOutOfBoundsException e) {
					System.out.println("Invalid book number. Type 'list all' for correct number.");
				}
				
			} else if(command.get(0).equals("remove") && command.size() == 2) {
				// remove [book number/all]
				if(command.get(1).equals("all")) {
					selectedBooks.removeAll();
				} else {
					try {
						int bookNum = Integer.parseInt(command.get(1));
						selectedBooks.remove(bookNum);
					} catch(NumberFormatException | IndexOutOfBoundsException e) {
						System.out.println("Invalid book number. Type 'list selected' for correct number.");
					}
				}
			} else if(command.get(0).equals("percent") && command.size() == 2) {
				// percent [word]
				selectedBooks.printPercentWord(command.get(1));
				
			} else if(command.get(0).equals("count") && command.size() == 2) {
				// count [word]
				selectedBooks.printWordCount(command.get(1));;
				
			} else if(command.get(0).equals("total")) {
				// total word count
				selectedBooks.printTotalWordCount();
				
			} else if(command.get(0).equals("unique")) {
				// unique word count
				selectedBooks.printUniqueCount();
				
			} else {
				System.out.println("Invalid command. Type 'help' for usage.");
			}
		}
	}
	
	public static BookList initBooks() {
		File booksFolder = new File("./books");
		BookList allBooks = new BookList();
		
		for(File bookFile : booksFolder.listFiles()) {
			allBooks.add(new Book(bookFile));
		}
		
		return allBooks;
	}
	
	public static ArrayList<String> getCommand(Scanner console) {
		ArrayList<String> command;
		command = new ArrayList<String>(Arrays.asList(console.nextLine().toLowerCase().split("\\s+")));
		return command;
	}
	
	public static void help() {
		System.out.println("A helpful message");
	}
	
	//public static void 
}
/*if(command.get(1) == "all") {
					
					for(Book book : allBooks) {
						
					}
*/