

//Shawn Hu Feb 27th

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Upgrade extends Clickable {

	private boolean purchased = false;
    protected static ArrayList<String[]> tierText;

	public Upgrade() {
		super();
		tier = 0;
		text = "default";
		description = "default";

	}
	public Upgrade(int t, Dimension s) {
		super(t, s);
		setTier();
	}
	static void initTier() { //Redo descriptions to have flavor text. Player will be able to tell which is better...
		tierText = new ArrayList<>();
		tierAdd("Pen","Your first writing utensil");
		tierAdd("1","desc");
		tierAdd("2","desc");
		tierAdd("3","desc");
		tierAdd("4","desc");
		
		/*case 2:
			text = "Pencil";
			description = "Unlocks backspace";
			break;
		case 3:
			text = "Membrane Keyboard";
			description = "Unlocks certain letters (Letters that aren't symbols or numbers)";
			break;
		case 4:
			text = "Mechanical Keyboard";
			description = "Unlocks full keyboard (\"Easy Keys\")";
			break;
		case 5:
			text = "eyes";
			description = "Lets you see the whole word";
			break;*/
	}

	protected void click(MouseEvent e) {
		if (!purchased) {
			purchased = true;
			price = (long)Math.pow(tier * BASE_PRICE,tier);

		}

	}
    protected static void tierAdd(String a, String b) {
		String[] s = {a,b};
		tierText.add(s);
    }
	static int getMax() {
		return tierText.size();
	}
	protected void setTier() {
		text = tierText.get(tier)[0];
		description = tierText.get(tier)[1];
	}
}
