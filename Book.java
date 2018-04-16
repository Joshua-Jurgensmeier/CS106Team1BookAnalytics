import java.io.*;
import java.util.*;

public class Book {
	private String title;
	private String author;
	//private BufferedReader bookFile;
	private String bookPath;
	
	// I think I got the name's right...
	public HashSet<String> wordSet; 			// Gordon
	public HashMap<String, Integer> wordCount; // Cassie
	public ArrayList<String> wordList;			// Din
	
	public Book(String bookPath) {
		wordSet = new HashSet<String>();
		wordCount = new HashMap<String, Integer>();
		wordList = new ArrayList<String>();
		this.bookPath = bookPath;
		setTitle();
		System.out.println(title);
		setAuthor();
	}
	
	public String getTitle() {
		return title;
	}
	
	// Returns a scanner of this book.
	public Scanner getScanner() {
		Scanner bookScanner;
		try {
			bookScanner = new Scanner(new BufferedReader(new FileReader(bookPath)));
		} catch(FileNotFoundException e) {
			bookScanner = new Scanner("");
			System.out.println("Error: FileNotFoundException");
		}
		return bookScanner;
	}
	
	// Returns a scanner which is positioned
	// at the first line of the actual text.
	// (Skips past Title/Author)
	public Scanner getScannerAtFirstLine() {
		Scanner bookScanner = getScanner();
		
		while(bookScanner.hasNextLine()) {
			if(bookScanner.nextLine().equals("***BEGIN BOOK***")) {
				//Skip blank line
				bookScanner.nextLine();
				break;
			}
		}
		return bookScanner;
	}
	
	// Reads the title from the book and stores it in title.
	private void setTitle() {
		Scanner bookScanner = getScanner();
		String line = bookScanner.nextLine();
		title = line.replaceFirst("Title: ", "");
		bookScanner.close();
	}

	public String getAuthor() {
		return author;
	}

	// Reads the author from the book and stores it in author.
	private void setAuthor() {
		Scanner bookScanner;
		try {
			bookScanner = new Scanner(new BufferedReader(new FileReader(bookPath)));
		} catch(FileNotFoundException e) {
			bookScanner = new Scanner("");
			System.out.println("Error: FileNotFoundException");
		}
		
		// Skip title and blank line
		bookScanner.nextLine();
		bookScanner.nextLine();
		
		String line = bookScanner.nextLine();
		title = line.replaceFirst("Author: ", "");
		bookScanner.close();
	}
	
	// An example to help you get started:
	// Prints the first line of the actual text.
	public void printFirstLine() {
		Scanner bookScanner = getScannerAtFirstLine();
		String firstLine = bookScanner.nextLine();
		System.out.println(firstLine);
	}
	
	public void printLastLine() {
		Scanner bookScanner = getScannerAtFirstLine();
		String lastLine = "";
		while(bookScanner.hasNextLine()) {
			lastLine = bookScanner.nextLine();
		}
		System.out.println(lastLine);
	}
	
	public String nextWord(Scanner bookScanner) {  
		//gets word from the file and removes punctuation
		return bookScanner.next().replaceAll("[^a-zA-Z-]", ""); 
	}
	
	public void generateWordCount() { 
		Scanner bookScanner = getScannerAtFirstLine(); 
		String word;

		while(bookScanner.hasNext()) { 

			word = nextWord(bookScanner); 
	
			if(wordCount.containsKey(word)){
				wordCount.put(word, wordCount.get(word) + 1); 
			} else { 
				wordCount.put(word, 1); 
			}
		}
	}
	
    public void generateWordSet() { 
    	Scanner bookScanner = getScannerAtFirstLine();
        while(bookScanner.hasNext()) { 
        	wordSet.add(nextWord(bookScanner)); //adds word into the set 
        }
    } 

    public void generateWordList() {
    	Scanner bookScanner = getScannerAtFirstLine();
    	//Traverses through the text file, and removes 
    	//punctuation and adds each word to the list.
    	while(bookScanner.hasNext())
    	{
    		wordList.add(nextWord(bookScanner)); //adds word into the list
    	}
    }
}
