import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Transparency;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
/* Isaac Wen (2018-03-02)
 * Shawn Hu (2018-02-28)
 * 
 * Clickable is the base for any clickable items (typers & upgrades)
 * */
public class Clickable extends JLabel{
	protected static Game game;				//Used to pass click events
	public String text;						//Text of the clickable item
	protected int tier;						//What tier the clickable is
	protected final long BASE_PRICE = 50;	//Base price subject to balacing changes
	protected long price;					//Price of the clickable item
	protected String description;			//Tool tip for the clickable
	protected Dimension size;				//The size fo the clickable item
	protected static long moneyHave;		//The amount of money the player has
	protected boolean visible;				//Whether the player can see the clickable
	public Clickable() {					//Default constructor that is empty
	}
	public static void setGame(Game g) {
		game = g;							//Sets the game (to pass clicks)
	}
	public Clickable(int t, Dimension s) {	//Sets the tier and size
		super();
		tier = t;
		size = s;
		this.addMouseListener(new MouseListener() {		//Adds a mouse listener to the clickable item
			public void mouseClicked(MouseEvent e) {
				if(visible) {							//Only can click if visible.
					click(e);							
				}
			}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
		});
	}
	protected void update() {							//Updates the image and size
		this.setIcon(new ImageIcon(createImage()));
		this.setSize(size);
	}
	public void paint(Graphics g) {						//Updates then paints
		update();
		super.paint(g);
	}
	protected BufferedImage createImage() {				//Basically draws the text, sets the tooltip as the description, and draws the image
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice device = env.getDefaultScreenDevice();
		GraphicsConfiguration config = device.getDefaultConfiguration();
		BufferedImage img =  config.createCompatibleImage(size.width, size.height, Transparency.TRANSLUCENT);
		Graphics2D g = img.createGraphics();

		g.setColor(new Color(200,200,200));
		g.fillRect(0,0,size.width, size.height);
		g.setColor(Color.black);
		g.drawRect(0, 0, size.width, size.height);
		if(visible) {									//If it isn't visible set it to a mysterious description and text
			g.drawString(text, g.getFontMetrics().stringWidth(" "), g.getFontMetrics().getHeight());
			this.setToolTipText(description);
			g.drawString("Price: " + price, g.getFontMetrics().stringWidth(" "), g.getFontMetrics().getHeight()*2);
		}
		else {
			g.drawString("?", g.getFontMetrics().stringWidth(" "), g.getFontMetrics().getHeight());
			this.setToolTipText("Unknown");
		}
		return img;
	}
	protected void click(MouseEvent e) {
		game.clickableClick(this);			//Let the game know that there has been a click
	}
	public long getPrice() {
		return price;						//returns price of clickable
	}
	public static void updateMoney(long m) {//Updates the current amount of money
		moneyHave = m;
	}
	public void setVisible(boolean v) {		//Sets visibility
		visible = v;
	}
	public boolean getVisible() {			//gets visibility
		return visible;
	}
}
