import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*Isaac Wen (2018-02-27)
 * */
public class GUI extends JFrame{
	private int screenScale;
	private static final int ASPECT_WIDTH = 16;
	private static final int ASPECT_HEIGHT = 9;
	private static final double ASPECT_RATIO = (ASPECT_WIDTH/ASPECT_HEIGHT);
	private JLabel currentType;
	private JLabel currentText;
	private Font defaultFont;
	private JPanel panel;
	private ArrayList<Component> components = new ArrayList<>();

	public GUI() {
		super("Type Clicker");	
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		//find smallest dimension
		if(screenSize.getWidth()/screenSize.getHeight() <= ASPECT_RATIO) {//If taller
			screenScale = (int)(screenSize.getHeight()/ASPECT_HEIGHT);
		}		//set screenscale
		else {	//wider
			screenScale = (int)(screenSize.getWidth()/ASPECT_WIDTH);
		}
		this.setSize((int)(ASPECT_WIDTH * screenScale),(int)(ASPECT_HEIGHT * screenScale));
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(null);
		init();
	}
	private String wordWarp(String s) {
		return "<html>" + s + "</html>";
	}
	private void setLabel(JLabel l, Font f, int x, int y) {
		l.setFont(f);
		l.setBounds(new Rectangle(x, y, this.getFontMetrics(f).stringWidth(l.getText()),this.getFontMetrics(f).getAscent()));
	}
	private void init() {
		this.setVisible(false);
		defaultFont = new Font("Times New Roman", 0,screenScale/4);
		
		components.add(currentType = new JLabel(""));
		components.add(currentText = new JLabel(""));
		for(Component i : components) {
			this.add(i);
		}
		update(null,null);
		this.setVisible(true);
	}
	private void update(String i, String t) {
		if(i != null) {
			currentType.setText(i);
		}
		if(t != null) {
			System.out.println(t);
			currentText.setText(t);
		}
		display();
	}
	public void updateInput(String i) {
		update(i,null);
	}
	public void updateText(String t) {
		update(null,t);
	}
	private void display() {
		setLabel(currentType,defaultFont, screenScale,screenScale*(ASPECT_HEIGHT-1));
		setLabel(currentText,defaultFont, screenScale,screenScale);
		repaint();
	}
	public void paint(Graphics g) {
		super.paint(g);
	}
	//See drive for layout info
}