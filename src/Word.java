import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

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
	private static HashMap<Integer, String> dictionary = new HashMap<Integer, String>();
	private static ArrayList<Character> symbols = new ArrayList<>();
	private static int dictSize;
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
		in = new BufferedReader(new InputStreamReader(Word.class.getResourceAsStream("resource/symbols")));
		try {
			while((line=in.readLine()) != null) {
				symbols.add(new Character(line.charAt(0)));
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
		if(WordList.getJumble()) {
			String s = "";
			Random r = new Random();
			int l = (int) Math.ceil(r.nextGaussian()+Game.WORD_LENGTH);
			if(l < 1) {
				l = 1;
			}
			for(int i = 0; i < l; i++) {
				s += symbols.get((int)(Math.random()*(symbols.size()-2))+1);
			}
			word = removeSpace(s);
			System.out.println(word);
		}
		else {		
			word = dictionary.get((int)(Math.random()*dictSize));
		}
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
		int points = word.length()/Game.WORD_LENGTH;
		if(points==0){
			return 1 ;
		}
		return points;
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
