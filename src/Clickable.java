import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

/**TODO:
 * Have some sort of output/draw feature. This can be either returning or drawing directly. (JComponent/JLabel)
 * Handle clicks somehow
 * Size/Pos for drawing
 * Image for the icon
 * */
public class Clickable extends JLabel{
	public String text;
    protected int tier;
    protected final long BASE_PRICE = 100;
    protected long price;
    protected String description;
    protected Dimension size;
    public Clickable() {

    }
    public Clickable(int t, Dimension s) {
        super();
        tier = t;
        size = s;
        this.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                click(e);
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
    	BufferedImage img = new BufferedImage(size.width,size.height,BufferedImage.TYPE_INT_ARGB);
    	Graphics2D g = img.createGraphics();
    	g.setColor(new Color(200,200,200));
    	g.fillRect(0,0,size.width, size.height);
    	g.setColor(Color.black);
    	g.drawRect(0, 0, size.width, size.height);
    	g.drawString(text, 0, size.width/2);
    	return img;
    }
    protected void click(MouseEvent e) {
    	//Click stuff
    }
}
