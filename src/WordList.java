import java.util.ArrayList;

public class WordList {
	private ArrayList<Word> words;
	private int length;
	public WordList() {
		
	}
	public WordList(int l) {
		length = l;
		words = new ArrayList<>();
		update();
	}
	private void update() {
		while(words.size()<length) {
			words.add(new Word());
		}
	}
	public String toString() {
		String s = "";
		for(Word w: words) {
			s += w.toString() + " ";
		}
		return s;
	}
	public boolean checkComplete(String i) {
		if((words.get(0)+" ").equals(i)) {
			words.remove(0);
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
}
