import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

//Shawn Hu, Feb 27th
/**TODO:
 * Have some sort of output/draw feature. This can be either returning or drawing directly. (JComponent/JLabel)
 * Handle clicks somehow
 * Size/Pos for drawing
 * Image for the icon
 * */
public class Clickable {
    protected String text;
    protected JLabel label;
    protected BufferedImage image;
    protected int tier;
    protected final long BASE_PRICE = 100;
    protected long price;
    protected String description;

    public Clickable() {
        text = "default";
        image = null;
    }
    public Clickable(JLabel l, String t, BufferedImage i, int x, int y) {
        label = l;
        text = t;
        image = i;
        label.setLocation(x,y);
        l.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                click(e);
            }
            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
        });
    }
    protected void click(MouseEvent e) {
    	//Click stuff
    }
}
