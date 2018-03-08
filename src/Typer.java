import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
/* Isaac Wen (2018-03-02)
 * Shawn Hu (2018-02-28)
 * */
public class Typer extends Clickable {

	private int numPurchased = 0;
	private int production;
	public static int cProduction = 0;
	protected static ArrayList<String[]> tierText;
	private static int maxTier = 0;
	public Typer() {
	}
	public Typer(int t, Dimension s) {
		super(t, s);
		setTier();
		production = (int) (Math.pow(tier+1, 2)+5*tier);
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
			cProduction += production;
			numPurchased++;	
			if(tier >= maxTier) {
				maxTier = tier+1;
			}
			super.click(e);
			update();
		}
		else {
			JOptionPane.showMessageDialog(null, "Not enough money");
		}
	}
	protected void update() {
		price = (long) (Math.pow((tier+1)*BASE_PRICE/10, 2) + (numPurchased*(tier+1)*BASE_PRICE/5));
		visible = (tier <= maxTier);		
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
	protected BufferedImage createImage() {
		BufferedImage img = super.createImage();
		if(visible) {
			Graphics2D g = img.createGraphics();
			g.setColor(new Color(0,0,0));
			g.drawString("Owned: " + numPurchased, g.getFontMetrics().stringWidth(" "), g.getFontMetrics().getHeight()*3);
		}
		return img;
	}
}


