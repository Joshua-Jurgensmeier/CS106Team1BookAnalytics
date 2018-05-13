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
		// Data structures are not generated until they are needed.
		wordSet = new HashSet<String>();
		wordCount = new HashMap<String, Integer>();
		wordList = new ArrayList<String>();
		this.bookFile = bookFile;
		// Get title and author from file.
		setTitle();
		setAuthor();
	}
	
	public String getTitle() {
		return title;
	}
	
	// Returns a scanner of this book.
	public Scanner getScanner() {
		Scanner bookScanner;
		try {
			// Slightly rediculous, yet necessary way to get a utf-8 scanner of .txt file
			bookScanner = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(bookFile), "UTF-8")));
		} catch(FileNotFoundException | UnsupportedEncodingException e) {
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
		//gets next word from the file, removes punctuation, and lowers case.
		return bookScanner.next().replaceAll("[^a-zA-Z-]", "").toLowerCase(); 
	}
	
	// Generates the wordCount map if it is empty
	public void ensureWordCount() {
		if(wordCount.isEmpty()) {
			if(!wordList.isEmpty()) {
				// Generate wordCount from wordList
				String word;
				Iterator<String> itr = wordList.iterator();

				while (itr.hasNext()) {
					word = itr.next();
					if (wordCount.containsKey(word)) {
						wordCount.put(word, wordCount.get(word) + 1);
					} else {
						wordCount.put(word, 1);
					}
				}
			} else {
				// Generate wordCount from scanner
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
		}
	}
	
	// Generates the wordSet if it is empty
    public void ensureWordSet() {
    	if(wordSet.isEmpty()) {
    		if(!wordList.isEmpty()) {
    			// Generate wordSet with wordList
    			wordSet = new HashSet<String>(wordList);
    			
    		} else if(!wordCount.isEmpty()) {
    			// Generate wordSet with wordCount map
    			wordSet = new HashSet<String>(wordCount.keySet());
    			
    		} else {
		    	Scanner bookScanner = getScannerAtFirstLine();
		        while(bookScanner.hasNext()) { 
		        	wordSet.add(nextWord(bookScanner)); //adds word into the set 
		        }
    		}   
    	}
    }
    
    // Generates the wordList if it is empty
    public void ensureWordList() {
    	if(wordList.isEmpty()) {
    		Scanner bookScanner = getScannerAtFirstLine();
        	//Traverses through the text file, and removes 
        	//punctuation and adds each word to the list.
        	while(bookScanner.hasNext())
        	{
        		wordList.add(nextWord(bookScanner)); //adds word into the list
        	}
    	}
    }
    
    // Requires wordList
    public int getTotalWordCount() {
    	ensureWordList();
    	return wordList.size();
    }
    
    // Requires wordSet
    public int getUniqueWordCount() {
    	ensureWordSet();
    	return wordSet.size();
    }
    
    // Requires wordCount
    public int countWord(String search) {
    	ensureWordCount();
		if(wordCount.containsKey(search)) {
			return wordCount.get(search);
		} else {
			return 0;
    	}
    }
    
    // Requires wordCount
    public double percentWord(String search) {
    	ensureWordCount();
    	try {
    		// Calculates the ratio, rounds decimal places and then scales the decimal to a percent
    		return Math.round(((double) wordCount.get(search) / getTotalWordCount()) * 1000.0)/10.0;
    	} catch(NullPointerException e) {
    		return 0;
    	}
    }
    
    // Loops through book, prints a copy of wordList with oldWord replaced with newWord
    public void replaceWord(String oldWord, String newWord) {
    	ensureWordList();
		ArrayList<String> wordList2 = new ArrayList<String>(wordList); 
		wordList2.addAll(wordList);
		int count = 0;
	
		for(int i = 0; i < wordList2.size() - 1; i++) {
			if(wordList2.get(i).equals(oldWord)) {
				wordList2.remove(i);
				wordList2.add(i, newWord);
				count++;		
			}
		}		
		System.out.println(wordList2);	
		System.out.println("\"" + oldWord + "\" was replaced with \"" + newWord + "\" " + count  + " times!");
		
	}
    
    // Returns a random word from wordList
    public String randomWord() {
    	ensureWordList();
	    Random rand = new Random();
	    String randWord = wordList.get(rand.nextInt(wordList.size()));
	    return randWord;
    }
}
