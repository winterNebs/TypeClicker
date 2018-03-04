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
	private long moneyHave;
    protected static ArrayList<String[]> tierText;

	public Typer() {
	}
	/**Set Buffered Image
	 * 
	 * 
	 * 
	 * 
	 * */
	public Typer(int t, Dimension s) {
		super(t, s);
		setTier();
		update();
	}
	static void initTier() { //Redo descriptions to have flavor text. Player will be able to tell which is better...
		tierText = new ArrayList<>();
		tierAdd("Intern","Hire a poor intern to scribe for you");
		tierAdd("1","desc");
		tierAdd("2","desc");
		tierAdd("3","desc");
		tierAdd("4","desc");
		tierAdd("5","desc");
		tierAdd("6","desc");
		tierAdd("7","desc");
		tierAdd("8","desc");
		tierAdd("9","desc");
		tierAdd("10","desc");
		tierAdd("11","desc");
		tierAdd("12","desc");
		
	/*case 1:
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
		break;*/
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


