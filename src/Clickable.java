import javax.swing.*;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
//Isaac Wen (2018-03-02)
//Shawn Hu, Feb 27th
import java.util.ArrayList;

/**TODO:
 * Have some sort of output/draw feature. This can be either returning or drawing directly. (JComponent/JLabel)
 * Handle clicks somehow
 * Size/Pos for drawing
 * Image for the icon
 * */
public class Clickable {
	public String text;
    protected JLabel label;
    protected int tier;
    protected final long BASE_PRICE = 100;
    protected long price;
    protected String description;
    protected Rectangle bounds;
    protected static ArrayList<String[]> tierText;
    public Clickable() {

    }
    public Clickable(Rectangle b) {
        label = new JLabel();
        bounds = b;
        label.addMouseListener(new MouseListener() {
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
    	 label.setIcon(new ImageIcon(createImage()));
         label.setBounds(bounds);
    }
    protected BufferedImage createImage() {
    	BufferedImage img = new BufferedImage(bounds.width,bounds.height,BufferedImage.TYPE_INT_ARGB);
    	Graphics2D g = img.createGraphics();
    	g.setColor(Color.gray);
    	g.fillRect(0,0,bounds.width, bounds.height);
    	g.setColor(Color.black);
    	g.drawRect(0, 0, bounds.width, bounds.height);
    	g.drawString(text, 0, bounds.width/2);
    	return img;
    }
    protected void click(MouseEvent e) {
    	//Click stuff
    }
    protected static void tierAdd(String a, String b) {
		String[] s = {a,b};
		tierText.add(s);
    }
}
