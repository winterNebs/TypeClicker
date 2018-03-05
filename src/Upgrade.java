

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
		tierAdd("Brain","Allows you to read");
		tierAdd("Eyes", "You can finally see");
		tierAdd("Pencil","Allows you to erase");
		tierAdd("Membrane Keyboard","Writing was difficult, this one comes with letters. It seems to be missing some keys");
		tierAdd("Mechanical Keyboard","Your first real keyboard");
		tierAdd("Upgrade","Desc");
		tierAdd("Upgrade","Desc");
		tierAdd("Upgrade","Desc");

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
