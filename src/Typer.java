import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
/* Isaac Wen (2018-03-02)
 * Shawn Hu (2018-02-28)
 * 
 * Typer inherits clickable
 * A typer is something that helps you get points over time.
 * Unlike an upgrade you can buy multiple typers to get points
 * */
public class Typer extends Clickable {

	private int numPurchased = 0;						//Number of each typer purchased
	private int production;								//Amount of WPM each produce (NOTE: not actually wpm its word per 6 seconds)
	public static int cProduction = 0;					//Total production from typers
	protected static ArrayList<String[]> tierText;		//An array with all the text/descriptions for the typers
	private static int maxTier = 0;						//Highest tier typer
	public Typer() {									//Blank default constructor
	}
	public Typer(int t, Dimension s) {					//Sets tier and size
		super(t, s);
		setTier();										//Sets text and description based on tier
		production = (int) (Math.pow(tier+1, 2)+5*tier);//Sets the production based on some formula
		update();
	}
	static void initTier() {		//Sets name and description for each typer
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

	protected void click(MouseEvent e) {		//Called when clicked on
		if(moneyHave >= price) {				//Checks if player has enough money
			cProduction += production;			//Adds the production to the total production
			numPurchased++;						//Increases the number purchased
			if(tier >= maxTier) {				//If the tier of the thing purchased is higher than the max, set it to the max	
				maxTier = tier+1;
			}
			super.click(e);						//Call super class click
			update();							//update
		}
		else {									//If not enough money, say not enough money
			JOptionPane.showMessageDialog(null, "Not enough money");
		}
	}
	protected void update() {					//Update price and visibility and call super (price increases the more you buy)
		price = (long) (Math.pow((tier+1)*BASE_PRICE/10, tier+1) + (numPurchased*(tier+1)*BASE_PRICE/5));
		visible = (tier <= maxTier);			//Basically only show next tier. (Hides higher tiers until you unlock the one before)
		super.update();							
	}
	protected static void tierAdd(String a, String b) {
		String[] s = {a,b};						//Adds a tier to the list
		tierText.add(s);
	}
	static int getMax() {						//Returns the highest possible tier
		return tierText.size();
	}
	protected void setTier() {					//Sets the text and description based on tier
		text = tierText.get(tier)[0];
		description = tierText.get(tier)[1];
	}
	protected BufferedImage createImage() {		//Adds numbered purchased and image to the super class's method
		BufferedImage img = super.createImage();
		if(visible) {
			Graphics2D g = img.createGraphics();
			g.setColor(new Color(0,0,0));
			g.drawString("Owned: " + numPurchased, g.getFontMetrics().stringWidth(" "), g.getFontMetrics().getHeight()*3);
			String name = "";
			switch(tier) {
			case 0: name="intern";break;		//Adds appropriate image
			case 1: name="cellphone"; break;
			default: name="default"; break;
			}
			try {
				BufferedImage i = ImageIO.read(this.getClass().getResourceAsStream("/resource/"+name+".png"));
				g.drawImage(i,0,0,(int)size.getWidth(),(int)size.getHeight(),0,0,i.getWidth(),i.getHeight(),null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return img;
	}
}


