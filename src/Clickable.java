import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Transparency;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

public class Clickable extends JLabel{
	protected static Game game;
	public String text;
	protected int tier;
	protected final long BASE_PRICE = 50;
	protected long price;
	protected String description;
	protected Dimension size;
	protected static long moneyHave;
	protected boolean visible;
	public Clickable() {

	}
	public static void setGame(Game g) {
		game = g;
	}
	public Clickable(int t, Dimension s) {
		super();
		tier = t;
		size = s;
		this.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				if(visible) {
					click(e);
				}
			}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
		});
	}
	protected void update() {	
		this.setIcon(new ImageIcon(createImage()));
		this.setSize(size);
	}
	public void paint(Graphics g) {
		update();
		super.paint(g);
	}
	protected BufferedImage createImage() {
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice device = env.getDefaultScreenDevice();
		GraphicsConfiguration config = device.getDefaultConfiguration();
		BufferedImage img =  config.createCompatibleImage(size.width, size.height, Transparency.TRANSLUCENT);
		Graphics2D g = img.createGraphics();

		g.setColor(new Color(200,200,200));
		g.fillRect(0,0,size.width, size.height);
		g.setColor(Color.black);
		g.drawRect(0, 0, size.width, size.height);
		if(visible) {
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
		game.clickableClick(this);
	}
	public long getPrice() {
		return price;
	}
	public static void updateMoney(long m) {
		moneyHave = m;
	}
	public void setVisible(boolean v) {
		visible = v;
	}
	public boolean getVisible() {
		return visible;
	}
}
