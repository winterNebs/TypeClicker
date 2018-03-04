import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

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
	private JPanel upgradeList;
	private JPanel typerList;
	private JScrollPane typerScroll;
	private JPanel mainPanel;
	private JPanel menuPanel;

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
		this.setDefaultCloseOperation(3);
		this.setLayout(null);
		this.setVisible(false);
		init();
	}
	private String wordWrap(String s) {
		return "<html>" + s + "</html>";
	}
	private void setLabel(JLabel l, Font f, int x, int y) {
		l.setFont(f);
		l.setBounds(x, y, this.getFontMetrics(f).stringWidth(l.getText()),this.getFontMetrics(f).getHeight());
	}
	private void init() {
		mainPanel = new JPanel(null);
		mainPanel.setOpaque(false);
		menuPanel = new JPanel(null);
		menuPanel.setOpaque(false);
		defaultFont = new Font("Times New Roman", 0,screenScale/4);
		menuPanel.add(upgradeList = new JPanel(null));
		//components.add();
		menuPanel.add(typerScroll = new JScrollPane(typerList = new JPanel(new GridLayout(Typer.getMax(),0)),JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));

		initUpgrades();
		initTypers();
		mainPanel.add(currentType = new JLabel(""));

		currentType.setOpaque(true);
		mainPanel.add(currentText = new JLabel(""));
		currentText.setOpaque(true);
		currentText.setBackground(new Color(100,100,100,50));
		this.add(mainPanel);
		this.add(menuPanel);
		display();
		typerScroll.setBounds(typerList.getX(),typerList.getY(),typerList.getWidth()+typerScroll.getVerticalScrollBar().getWidth(),screenScale*(ASPECT_HEIGHT-3));
		typerScroll.revalidate();
		repaint();
		this.setVisible(true);
	}
	private void initUpgrades() {

	}
	private void initTypers() {
		for(int i = 0; i < Typer.getMax(); i++ ) {
			typerList.add(new Typer(i,new Dimension((int)(screenScale*1.5),(int)(screenScale*.75))));
		}
	}
	public void updateInput(String i, boolean correct) {
		if(correct) {
			currentType.setBackground(new Color(0,0,0,0));
		}
		else {
			currentType.setBackground(new Color(255,0,0,100));
		}
		currentType.setText(i);
		//display();
		repaint();
	}
	public void updateText(String t) {
		currentText.setText(wordWrap(t));
		//display();
		repaint();
	}
	private void display() {
		
		mainPanel.setBounds(0,0,screenScale*(ASPECT_WIDTH-6),screenScale*ASPECT_HEIGHT);
		menuPanel.setBounds(screenScale*(ASPECT_WIDTH-6),0,screenScale*(ASPECT_WIDTH),screenScale*ASPECT_HEIGHT);
		setLabel(currentType,defaultFont, screenScale,screenScale*(ASPECT_HEIGHT-3));
		currentText.setFont(defaultFont);
		currentText.setBounds(screenScale,screenScale,screenScale*4,screenScale*4);
		typerList.setBounds(0,0, screenScale*2,(int)(screenScale*.75*Typer.getMax()));
		typerList.setBackground(Color.green);
		typerList.setOpaque(true);

	}
	public void paint(Graphics g) {
		display();
		BufferedImage buffer = new BufferedImage(screenScale*ASPECT_WIDTH,screenScale*ASPECT_HEIGHT, BufferedImage.TYPE_INT_ARGB);
		Graphics b = buffer.getGraphics();
		b.setColor(new Color(220,220,220));
		b.fillRect(0,0,screenScale*ASPECT_WIDTH,screenScale*ASPECT_HEIGHT);
		
		super.paint(b);
		b.setColor(new Color(0,0,0));
		b.drawRect(currentText.getX(), currentText.getY(),currentText.getWidth(), currentText.getHeight());
		b.drawRect(currentType.getX(), currentType.getY()+currentType.getHeight(),screenScale*4, currentType.getHeight());

		g.drawImage(buffer, 0, 0, this);

	}

	//See drive for layout info
}