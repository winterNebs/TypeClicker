

//Shawn Hu Feb 27th

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JOptionPane;

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
		if (!purchased && moneyHave >= price) {
			purchased = true;
			super.click(e);
		}
		else {
			JOptionPane.showMessageDialog(null, "no can do");
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
		price = (long)Math.pow(tier * BASE_PRICE,tier);
		text = tierText.get(tier)[0];
		description = tierText.get(tier)[1];
	}
	protected BufferedImage createImage() {
		BufferedImage img = super.createImage();
		Graphics2D g = img.createGraphics();
		if(purchased) {
			g.setColor(new Color(0,0,0));
			g.drawString("(Purchased)", g.getFontMetrics().stringWidth(" "), g.getFontMetrics().getHeight()*3);
		}
		return img;
	}
}
