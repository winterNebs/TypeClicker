import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/*Isaac Wen (2018-02-27)
 * */

public class Word {
/**TODO:
 * Static list of all words
 * Number of letters in word (not really that necessary lol)
 * Actual value of word
 * Static Constants of special bonus characters?
 * 
 * */
	private String word;
	private int points;
	static HashMap<Integer, String> dictionary = new HashMap<Integer, String>();
	static int dictSize;
	public static void dictInit() {
		BufferedReader in = new BufferedReader(new InputStreamReader(Word.class.getResourceAsStream("resource/words.english")));
		String line;
		try {
			dictSize = 0;
			while((line=in.readLine()) != null) {
				dictionary.put(dictSize++, line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public Word() {
		randomize();
	}
	public Word(String w) {
		word = removeSpace(w);
	}
	private void randomize() {
		word = dictionary.get((int)(Math.random()*dictSize));
	}
	private String removeSpace(String s) {
		String split[] = s.split(" ");
		String spaceless = "";
		for(int i = 0; i < split.length; i++){
			spaceless += split[i];
		}
		return spaceless;
	}
	public String toString() {
		return word;
	}
	public int getLength() {
		return word.length();
	}
	public int getPoints() {
		return getLength(); //Fancy math later;
	}
	public boolean contains(String s) {
		if(s.length()>word.length()) {
			return false;
		}
		return (word.substring(0,s.length()).contains(s));
	}
	public boolean equals(String s) {
		return (word.equals(s));
	}
}
