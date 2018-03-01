import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;

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
	private ArrayList<JComponent> components;
	
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
		init();
	}
	private String wordWarp(String s) {
		return "<html>" + s + "</html>";
	}
	private void init() {
		components = new ArrayList<>();
		defaultFont = new Font("Times New Roman", 0,screenScale/4);
		components.add(currentType = new JLabel());
		currentType.setFont(defaultFont);
		currentType.setText("");
		for(JComponent i : components) {
			this.add(i);
		}
		update("");
		this.setVisible(true);
	}
	public void update(String i) {
		currentType.setText(i);
		display();
	}
	private void display() {
		currentType.setLocation(new Point(screenScale,screenScale*(ASPECT_HEIGHT-1)));
		repaint();
	}
	public void paint(Graphics g) {
		super.paint(g);
	}
	//See drive for layout info
}