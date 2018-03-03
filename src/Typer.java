import javax.swing.*;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
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
	private long moneyHave;

	public Typer() {
		super();
		numPurchased = 0;
		WPM = 0;
		price = 0;
		tier = 0;
	}
	/**Set Buffered Image
	 * 
	 * 
	 * 
	 * 
	 * */
	public Typer(int t, Rectangle b) {
		super(b);
		tier = t;
		tier();
		update();
	}
	static void initTier() {
		tierText = new ArrayList<>();
		tierText.add(tierAdd("Intern","Hire a poor intern to scribe for you"));
	}
	private static String[] tierAdd(String a, String b) {
		String[] s = {a,b};
		return s;
	}
	private void tier() {	//Redo descriptions to have flavor text. Player will be able to tell which is better...

		switch (tier) {
		case 1:
			text = "Intern";
			description = "Lowest tier typer, hand writes";
			break;
		case 2:
			text = "TextToSpeech";
			description = "Second tier typer";
			break;
		case 3:
			text = "CellPhone";
			description = "better than text to speech, but still slow";
			break;
		case 4:
			text = "TypeWriter ";
			description = "Better than cellphone, but still kind of trash";
			break;
		case 5:
			text = "Computer";
			description = "Basic typer, increases WPM by low amount, faster than typewriter";
			break;
		case 6:
			text = "Supern Computer";
			description = "Awesome typer, much fast WPM";
			break;
		case 7:
			text = "Quantum Computer";
			description = "Super good typer, much much fast WPM";
			break;
		case 8:
			text = "Inter-dimensional Typer";
			description = "The ultimate, the best, the fastest, annnnnd the most expensive";
			break;
		}
	}

	protected void click(MouseEvent e) {
		if(moneyHave >= price) {
			cWPM += WPM;
			numPurchased++;	
		}
		else {
			JOptionPane.showMessageDialog(null, "not enough money");
		}
	}
	public void update(long money) {
		moneyHave = money;
		price = (long) (Math.pow(tier * BASE_PRICE, tier) * numPurchased);
		super.update();
	}
	public void update() {
		price = (long) (Math.pow(tier * BASE_PRICE, tier) * numPurchased);
		super.update();
	}
}


