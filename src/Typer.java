import javax.swing.*;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
/* Isaac Wen (2018-03-02)
 * Shawn Hu (2018-02-28)
 * */
public class Typer extends Clickable {
	/**
	 * TODO:
	 * <p>
	 * Counter for number of purchased
	 * WPM
	 * Some sort of math to calculate price
	 * Static cumulative WPM
	 */
	private int numPurchased = 0;
	private int WPM;
	private static int cWPM = 0;
    protected static ArrayList<String[]> tierText;

	public Typer() {
	}
	public Typer(int t, Dimension s) {
		super(t, s);
		setTier();
		update();
	}
	static void initTier() { //Redo descriptions to have flavor text. Player will be able to tell which is better...
		tierText = new ArrayList<>();
		tierAdd("Intern","Hire a poor intern to scribe for you");
		tierAdd("Text to Speech","It's a little faster");
		tierAdd("Cellphone","Work those thumbs");
		tierAdd("Typewriter","The more fingers the better");
		tierAdd("Computer","Now automated");
		tierAdd("Super Computer","The faster alternativce");
		tierAdd("Quantum Computer","Desc");
		tierAdd("Interdimensional Typer","It's out of this world");
		tierAdd("8","desc");
		tierAdd("9","desc");
		tierAdd("10","desc");
		tierAdd("11","desc");
		tierAdd("12","desc");
	}

	protected void click(MouseEvent e) {
		if(moneyHave >= price) {
			cWPM += WPM;
			numPurchased++;	
			super.click(e);
			update();
		}
		else {
			JOptionPane.showMessageDialog(null, "not enough money");
		}
	}
	protected void update() {
		price = (long) (Math.pow(tier * BASE_PRICE, tier) * (numPurchased+1));
		super.update();
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


