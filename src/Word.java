import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/*Isaac Wen (2018-02-27)
 * 
 * The word class is exactly what it sounds like, it's a word
 * */

public class Word {

	private String word;																	//The word itself
	private static HashMap<Integer, String> dictionary = new HashMap<Integer, String>();	//Static hashmap of all the words in the English language
	private static ArrayList<Character> symbols = new ArrayList<>();						//Lists of less frequently used character
	private static int dictSize;															//Total size of the regular dictionary
	public static void dictInit() {//Initializes our dictionary from a text file
		BufferedReader in = new BufferedReader(new InputStreamReader(Word.class.getResourceAsStream("resource/words.english")));
		String line;
		try {
			dictSize = 0;
			while((line=in.readLine()) != null) {
				dictionary.put(dictSize++, line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}//Then initializes the symbols from a text file
		in = new BufferedReader(new InputStreamReader(Word.class.getResourceAsStream("resource/symbols")));
		try {
			while((line=in.readLine()) != null) {
				symbols.add(new Character(line.charAt(0)));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public Word() {		//Default constructor, new random word
		randomize();
	}
	public Word(String w) {		//Constructor for custom words
		word = removeSpace(w);
	}
	private void randomize() {			//Generates a random word
		if(WordList.getJumble()) {		//If jumble is true just randomly generate a bunch of garbage
			String s = "";
			Random r = new Random();
			int l = (int) Math.ceil(r.nextGaussian()+Game.WORD_LENGTH);	//Length of garbage is average 5 characters long
			if(l < 1) {
				l = 1;
			}
			for(int i = 0; i < l; i++) {
				s += symbols.get((int)(Math.random()*(symbols.size()-2))+1);
			}
			word = removeSpace(s);
		}
		else {		
			word = dictionary.get((int)(Math.random()*dictSize));	//If not jumble, randomly grab a word from dicitonary
		}
	}
	private String removeSpace(String s) {		//Remove spaces from words
		String split[] = s.split(" ");
		String spaceless = "";
		for(int i = 0; i < split.length; i++){
			spaceless += split[i];
		}
		return spaceless;
	}
	public String toString() {				//Returns the word as a string
		return word;
	}
	public int getLength() {				//Returns number of character
		return word.length();
	}
	public int getPoints() {				//Return the number of points (on average a word is 5 characters, so divide and return)
		int points = word.length()/Game.WORD_LENGTH;
		if(points==0){
			return 1 ;
		}
		return points;
	}
	public boolean contains(String s) {		//Check what is typed so far is correct
		if(s.length()>word.length()) {
			return false;
		}
		return (word.substring(0,s.length()).contains(s));
	}
	public boolean equals(String s) {		//Check if the word itself is right
		return (word.equals(s));
	}
}
