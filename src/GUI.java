import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
	private JLabel currentWPM;
	private JLabel currentScore;
	private JLabel currentProduction;
	private Font defaultFont;
	private JPanel upgradeList;
	private JPanel typerList;
	private JScrollPane upgradeScroll;
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
	public static String wordWrap(String s) {
		return "<html>" + s + "</html>";
	}
	private static void setLabel(JLabel l, Font f, int x, int y) {
		l.setFont(f);
		l.setBounds(x, y, l.getFontMetrics(f).stringWidth(l.getText()),l.getFontMetrics(f).getHeight());
	}
	private void init() {
		mainPanel = new JPanel(null);
		mainPanel.setOpaque(false);
		menuPanel = new JPanel(null);
		menuPanel.setOpaque(false);
		defaultFont = new Font("Times New Roman", 0,screenScale/4);
		//components.add();
		menuPanel.add(typerScroll = new JScrollPane(typerList = new JPanel(new GridLayout(Typer.getMax(),0)),JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
		menuPanel.add(upgradeScroll = new JScrollPane(upgradeList = new JPanel(new GridLayout(Upgrade.getMax(),0)),JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));

		initUpgrades();
		initTypers();
		mainPanel.add(currentType = new JLabel(""));
		currentType.setOpaque(true);
		mainPanel.add(currentText = new JLabel(""));
		currentText.setOpaque(true);
		currentText.setBackground(new Color(100,100,100,50));
//		currentWPM.setOpaque(true);
		mainPanel.add(currentWPM = new JLabel(""));
		mainPanel.add(currentScore = new JLabel(""));
		mainPanel.add(currentProduction = new JLabel(""));
		updateWPM(0);
		updateScorePoints(0);
		this.add(mainPanel);
		this.add(menuPanel);
		display();
		repaint();
		this.setVisible(true);
	}
	private void initUpgrades() {
		for(int i = 0; i < Upgrade.getMax(); i++ ) {
			upgradeList.add(new Upgrade(i,new Dimension((int)(screenScale*1.5),(int)(screenScale*.75))));
		}
		upgradeList.setBounds(0,0, (int)(screenScale*1.5),(int)(screenScale*.75*Upgrade.getMax()));
		upgradeList.setBackground(Color.green);
		upgradeList.setOpaque(true);

		upgradeScroll.setBounds((int)(screenScale*1.5),screenScale,upgradeList.getWidth()+upgradeScroll.getVerticalScrollBar().getWidth(),screenScale*(ASPECT_HEIGHT-3));
	}
	private void initTypers() {
		for(int i = 0; i < Typer.getMax(); i++ ) {
			typerList.add(new Typer(i,new Dimension((int)(screenScale*1.5),(int)(screenScale*.75))));
		}
		typerList.setBounds(0,0, (int)(screenScale*1.5),(int)(screenScale*.75*Typer.getMax()));
		typerList.setBackground(Color.green);
		typerList.setOpaque(true);

		typerScroll.setBounds(0,screenScale,typerList.getWidth()+typerScroll.getVerticalScrollBar().getWidth(),screenScale*(ASPECT_HEIGHT-3));

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
	public void updateWPM(int w){
		currentWPM.setText("Words Per Minute: " + w);
		repaint();
	}
	public void updateScorePoints(long s){
		currentScore.setText("Score: " + s);
		repaint();
	}
	public void updateProduction(int p) {
		currentProduction.setText("Production: " + (double)p*10 + " wpm");
		repaint();
	}
	private void display() {
		mainPanel.setBounds(0,0,screenScale*(ASPECT_WIDTH-6),screenScale*ASPECT_HEIGHT);
		menuPanel.setBounds(screenScale*(ASPECT_WIDTH-6),0,screenScale*(ASPECT_WIDTH),screenScale*ASPECT_HEIGHT);
		setLabel(currentType,defaultFont, screenScale,screenScale*(ASPECT_HEIGHT-3));
		currentText.setFont(defaultFont);
		currentText.setBounds(screenScale,screenScale,screenScale*4,screenScale*4);
		setLabel(currentScore,defaultFont, screenScale*(ASPECT_WIDTH-10), screenScale);
		setLabel(currentWPM,defaultFont, screenScale*(ASPECT_WIDTH-10), currentScore.getHeight()+currentScore.getY());
		setLabel(currentProduction,defaultFont, screenScale*(ASPECT_WIDTH-10), currentWPM.getHeight()+currentWPM.getY());

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
		b.drawRect(currentScore.getX(), currentScore.getY(), screenScale*4, screenScale*6);
		g.drawImage(buffer, 0, 0, this);
	}

	//See drive for layout info
}