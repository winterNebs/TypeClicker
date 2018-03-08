import java.util.ArrayList;

public class WordList {
	private ArrayList<Word> words;
	private int length;
	public Word lastWord;
	private static boolean jumble = true;
	private static boolean hidden = true;
	private int chars = 0;
	public WordList() {
		
	}
	public WordList(int l) {
		length = l;
		words = new ArrayList<>();
		update();
	}
	public static boolean getJumble() {
		return jumble;
	}
	public void setJumble(boolean j) {
		jumble = j;
		words.clear();
		update();
	}
	private void update() {
		while(words.size()<length) {
			words.add(new Word());
		}
	}
	public void updateChar(int c) {
		chars = c;
	}
	public String toString() {			
		String s = "";
		if(hidden) {
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
	public boolean checkComplete(String i) {
		if((words.get(0)+" ").equals(i)) {
			lastWord = words.remove(0);
			update();
			return true;
		}
		return false;
	}
	public boolean checkContains(String i) {
		if(words.get(0).contains(i)) {
			return true;
		}
		return false;
	}
	public Word getWord(){
		return words.get(0);
	}
	public void setHidden(boolean h) {
		hidden = h;
	}
}
