

//Shawn Hu Feb 27th
//Isaac Wen Feb 28th
/*Upgrades are permanent improvements to your typing experience
 * */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Upgrade extends Clickable {
	public static int multiplier = 1;		//The points per word multiplier
	private boolean purchased = false;		//Whether the upgrade is purchased or not (can only buy once)
	private static int maxTier = 0;			//Max tier, same as tyeer
	protected static ArrayList<String[]> tierText;	//Tier text same as typer
	public Upgrade() {						//Default constructor
		super();
		tier = 0;
		text = "default";
		description = "default";	
	}
	public Upgrade(int t, Dimension s) {	//Constructor sets tier and size
		super(t, s);
		setTier();
		update();
	}
	static void initTier() { 					//Sets descriptions of upgrades
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
	public void update() {					//Updates visibility and calls super update
		visible = (tier <= maxTier);
		super.update();
	}
	protected void click(MouseEvent e) {	//Called when clicked on
		if (!purchased ) {					//If not already purchased
			if(moneyHave >= price) {		//And has money
				purchased = true;			//Set purchased to true
				if(tier >= maxTier) {		//If higher tier set it, (same idea as typer)
					maxTier = tier+1;
				}
				tierer(tier);				//Call tierer which will apply the improvements
				super.click(e);				//Calls super click
			}
			else {
				JOptionPane.showMessageDialog(null, "Not enough money");
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "Already owned");
		}

	}
	private void tierer(int t) {
		switch(t) {
		case 0:			//Pen, doubles the points you get per word
			multiplier = 2;
			break;
		case 1:			//Brain, unjumbles the words (letters instead of symbols)
			game.getWordList().setJumble(false);
			break;
		case 2:			//Eyes, let's player see whole word
			game.getWordList().setHidden(false);
			break;
		}
	}
	protected static void tierAdd(String a, String b) {	//Same as typer class
		String[] s = {a,b};
		tierText.add(s);
	}
	static int getMax() {								//Same idea as typer class as well
		return tierText.size();
	}
	protected void setTier() {							//Sets the text and price (price doesn't change)
		price = (long)(Math.pow(BASE_PRICE/5,2)*(tier+1)/2);
		text = tierText.get(tier)[0];
		description = tierText.get(tier)[1];
	}
	protected BufferedImage createImage() {				//Same as typer class basically
		BufferedImage img = super.createImage();
		Graphics2D g = img.createGraphics();
		if(visible) {
			String name = "";
			switch(tier) {								//Sets appropriate images
			case 0: name="pen";break;
			case 1: name="brain";break;
			case 2: name="eyes"; break;
			default: name="default"; break;
			}
			try {
				BufferedImage i = ImageIO.read(this.getClass().getResourceAsStream("/resource/"+name+".png"));
				g.drawImage(i,0,0,(int)size.getWidth(),(int)size.getHeight(),0,0,i.getWidth(),i.getHeight(),null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(purchased) {
				g.setColor(new Color(0,0,0));			//Shows how many owned
				g.drawString("(Purchased)", g.getFontMetrics().stringWidth(" "), g.getFontMetrics().getHeight()*3);
			}
		}
		return img;
	}
}
