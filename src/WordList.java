import java.util.ArrayList;
/*Isaac Wen (2018-02-28)
 * Word list is basically a manager for the words
 * */
public class WordList {
	private ArrayList<Word> words;	//List of words
	private int length;				//Number of words
	public Word lastWord;			//remembers last completed word
	private static boolean jumble = true;	//Special modifier (only symbols)
	private static boolean hidden = true;	//Special modifier (can only see one letter)
	private int chars = 0;					//number of chars for hidden
	public WordList() {		//Default constructor
	}
	public WordList(int l) {//Constructor set length
		length = l;
		words = new ArrayList<>();	//Initialize list of words
		update();
	}
	public static boolean getJumble() {	//Returns jumble state (used to tell word if it needs to be scrambled)
		return jumble;
	}
	public void setJumble(boolean j) {	//Sets the jumble and resets the list
		jumble = j;
		words.clear();
		update();
	}
	private void update() {				//adds more words if missing words
		while(words.size()<length) {
			words.add(new Word());
		}
	}
	public void updateChar(int c) {		//Updates chars for hidden mode
		chars = c;
	}
	public String toString() {			//Returns all the words with spaces between
		String s = "";
		if(hidden) {					//if hidden, only show the next character
			if(chars<=words.get(0).toString().length()) {
				s += (words.get(0).toString()+" ").substring(0,chars+1);
			}
		}
		else {							
			for(Word w: words) {
				s += w.toString() + " ";
			}	
		}
		return s;
	}
	public boolean checkComplete(String i) {	//Checks if the input is equal to the first word
		if((words.get(0)+" ").equals(i)) {		//For completeness
			lastWord = words.remove(0);
			update();
			return true;
		}
		return false;
	}
	public boolean checkContains(String i) {	//Checks if the input contains the word (partially equal)
		return (words.get(0).contains(i));
	}
	public Word getWord(){						//returns first word
		return words.get(0);
	}
	public void setHidden(boolean h) {			//set the hidden
		hidden = h;
	}
}
