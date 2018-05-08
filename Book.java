import java.io.*;
import java.util.*;


public class Book {
	private String title;
	private String author;
	private File bookFile;
	
	public HashSet<String> wordSet;
	public HashMap<String, Integer> wordCount;
	public ArrayList<String> wordList;
	
	public Book(File bookFile) {
		wordSet = new HashSet<String>();
		wordCount = new HashMap<String, Integer>();
		wordList = new ArrayList<String>();
		this.bookFile = bookFile;
		setTitle();
		//System.out.println(title);
		setAuthor();
	}
	
	public String getTitle() {
		return title;
	}
	
	// Returns a scanner of this book.
	public Scanner getScanner() {
		Scanner bookScanner;
		try {
			bookScanner = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(bookFile), "UTF-8")));
		} catch(FileNotFoundException e) {
			bookScanner = new Scanner("");
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			bookScanner = new Scanner("");
			e.printStackTrace();
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
		bookScanner = getScanner();
		
		// Skip title and blank line
		bookScanner.nextLine();
		bookScanner.nextLine();
		
		String line = bookScanner.nextLine();
		author = line.replaceFirst("Author: ", "");
		bookScanner.close();
	}
	
	public String nextWord(Scanner bookScanner) {  
		//gets word from the file and removes punctuation
		return bookScanner.next().replaceAll("[^a-zA-Z-]", "").toLowerCase(); 
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
    	// Add if wordList exists                     !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
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
    
    public int getTotalWordCount() {
    	return wordList.size();
    }
    
    public double percentWordCounter(String search) { 
    	try {
    		// Calculates the ratio, rounds decimal places and then scales the decimal to a percent
    		return Math.round(((double) wordCount.get(search) / getTotalWordCount()) * 1000.0)/10.0;
    	} catch(NullPointerException e) {
    		return 0;
    	}
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
}
