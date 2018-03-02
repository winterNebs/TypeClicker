import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
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
	private boolean correct;
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
		this.setVisible(false);
		init();
	}
	private String wordWarp(String s) {
		return "<html>" + s + "</html>";
	}
	private void setLabel(JLabel l, Font f, int x, int y) {
		l.setFont(f);
		l.setBounds(x, y, this.getFontMetrics(f).stringWidth(l.getText()),this.getFontMetrics(f).getHeight());
	}
	private void init() {
		defaultFont = new Font("Times New Roman", 0,screenScale/4);
		
		components.add(currentType = new JLabel(""));
		currentType.setOpaque(true);
		components.add(currentText = new JLabel(""));
		currentText.setOpaque(true);
		currentText.setBackground(new Color(100,100,100,50));
		for(Component i : components) {
			this.add(i);
		}
		update();
		this.setVisible(true);
	}
	public void update() {
		display();
	}
	public void updateInput(String i, boolean correct) {
		if(correct) {
			currentType.setBackground(new Color(0,0,0,0));
		}
		else {
			currentType.setBackground(new Color(255,0,0,100));
		}
		currentType.setText(i);
		update();
	}
	public void updateText(String t) {
		currentText.setText(t);
		update();
	}
	private void display() {
		setLabel(currentType,defaultFont, screenScale,screenScale*(ASPECT_HEIGHT-3));
		setLabel(currentText,defaultFont, screenScale,screenScale);
		repaint();
	}
	public void paint(Graphics g) {
		BufferedImage buffer = new BufferedImage(screenScale*ASPECT_WIDTH,screenScale*ASPECT_HEIGHT,BufferedImage.TYPE_INT_ARGB);
		Graphics2D b = buffer.createGraphics();
	    b.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
        b.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        b.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

		super.paint(b);
		b.setColor(new Color(0,0,0));
		b.drawRect(currentText.getLocation().x, currentText.getLocation().y,currentText.getLocation().x+screenScale*4, currentText.getLocation().y+screenScale*4);
		b.dispose();
		Graphics2D g2dComponent = (Graphics2D) g;
		g2dComponent.drawImage(buffer, null, 0, 0); 
	}
	
	//See drive for layout info
}