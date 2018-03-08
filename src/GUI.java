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
 * 
 * The graphical component for the game
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
	public GUI() {				//Default constructor
		super("Type Clicker");	//Calls super class constructor
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //find smallest dimension
		if(screenSize.getWidth()/screenSize.getHeight() <= ASPECT_RATIO) {	//If taller
			screenScale = (int)(screenSize.getHeight()/ASPECT_HEIGHT);		//Set the screenscale 
		}
		else {	//if wider, set screenscale
			screenScale = (int)(screenSize.getWidth()/ASPECT_WIDTH);
		}
		this.setSize((int)(ASPECT_WIDTH * screenScale),(int)(ASPECT_HEIGHT * screenScale));	//Set window size, aspect ratio (16:9)
		this.setDefaultCloseOperation(3);
		this.setLayout(null);
		this.setVisible(false);
		init();				//Initialize 
	}
	public static String wordWrap(String s) {		//Throws an html tag around it so the text wraps
		String a = "";
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == '<') {
				a += "&#60;";						//< is the opening of a html tag so replace it with one that shows up
			}
			else {
				a += s.charAt(i);
			}
		}
		return "<html>" + a + "</html>";
	}
	private static void setLabel(JLabel l, Font f, int x, int y) {		//Sets label bounds based on the font and position
		l.setFont(f);
		l.setBounds(x, y, l.getFontMetrics(f).stringWidth(l.getText()),l.getFontMetrics(f).getHeight());
	}
	private void init() {					//Add panels without layout managers
		mainPanel = new JPanel(null);
		mainPanel.setOpaque(false);
		menuPanel = new JPanel(null);
		menuPanel.setOpaque(false);
		defaultFont = new Font("Times New Roman", 0,screenScale/4);	//Create a default font	
		menuPanel.add(typerScroll = new JScrollPane(typerList = new JPanel(new GridLayout(Typer.getMax(),0)),JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
		menuPanel.add(upgradeScroll = new JScrollPane(upgradeList = new JPanel(new GridLayout(Upgrade.getMax(),0)),JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
		//Add the two scroll panes to the menu panels (these don't update every timer tick)
		initUpgrades();		//Initialize the upgrades list
		initTypers();		//Initialize the typers list
		mainPanel.add(currentType = new JLabel(""));	//Add current input label to the main panel (this updates every timer tick)
		currentType.setOpaque(true);				
		mainPanel.add(currentText = new JLabel("")); 	//Add current text label to the main panel
		currentText.setOpaque(true);

		mainPanel.add(currentWPM = new JLabel(""));		//Adds current wpm label to the main panel
		mainPanel.add(currentScore = new JLabel(""));	//adds current score to the panel
		mainPanel.add(currentProduction = new JLabel(""));	//adds current production
		updateWPM(0);										//Sets wpm to 0 at the start
		updateScorePoints(0);								//sets score to 0 at the start
		this.add(mainPanel);								//Add panels to the frame
		this.add(menuPanel);
		display();										//Update positions etc
		repaint();										//Redraw it
		this.setVisible(true);							//make frame visible
	}
	private void initUpgrades() {			//adds all the upgrades to the scroll pane
		for(int i = 0; i < Upgrade.getMax(); i++ ) {
			upgradeList.add(new Upgrade(i,new Dimension((int)(screenScale*1.5),(int)(screenScale*.75))));
		}
		upgradeList.setBounds(0,0, (int)(screenScale*1.5),(int)(screenScale*.75*Upgrade.getMax()));
		upgradeList.setBackground(Color.green);
		upgradeList.setOpaque(true);
		upgradeScroll.setBounds((int)(screenScale*1.5),screenScale,upgradeList.getWidth()+upgradeScroll.getVerticalScrollBar().getWidth(),screenScale*(ASPECT_HEIGHT-3));
	}
	private void initTypers() {				//Adds all the typers to the scroll pane
		for(int i = 0; i < Typer.getMax(); i++ ) {
			typerList.add(new Typer(i,new Dimension((int)(screenScale*1.5),(int)(screenScale*.75))));
		}
		typerList.setBounds(0,0, (int)(screenScale*1.5),(int)(screenScale*.75*Typer.getMax()));
		typerList.setBackground(Color.green);
		typerList.setOpaque(true);
		typerScroll.setBounds(0,screenScale,typerList.getWidth()+typerScroll.getVerticalScrollBar().getWidth(),screenScale*(ASPECT_HEIGHT-3));

	}
	public void updateInput(String i, boolean correct) {		//Updates the input label
		if(correct) {
			currentType.setBackground(new Color(0,0,0,0));		//Right (no background)
		}
		else {
			currentType.setBackground(new Color(255,0,0,100));	//Wrong (red background)
		}
		currentType.setText(i);									//set text and repaint
		repaint();
	}
	public void updateText(String t) {			//Update text
		currentText.setText(wordWrap(t));
		repaint();
	}
	public void updateWPM(int w){				//update wpm
		currentWPM.setText("Words Per Minute: " + w);
		repaint();
	}
	public void updateScorePoints(long s){		//update score
		currentScore.setText("Points: " + s);
		repaint();
	}
	public void updateProduction(int p) {		//update production (multiply by 10, because it updates every 6 seconds not 60)
		currentProduction.setText("Production: " + (double)p*10 + " wpm");
		repaint();
	}
	private void display() {				//Basically set bounds of everything (multiply by screenscale so its the same on all monitors)
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
		display();				//set positions first	//Create a buffered image for double buffering
		BufferedImage buffer = new BufferedImage(screenScale*ASPECT_WIDTH,screenScale*ASPECT_HEIGHT, BufferedImage.TYPE_INT_ARGB);
		Graphics b = buffer.getGraphics();		//Draw everything onto here then draw this onto the screen
		b.setColor(new Color(220,220,220));		//Set bg color
		b.fillRect(0,0,screenScale*ASPECT_WIDTH,screenScale*ASPECT_HEIGHT);	//fill bg
		
		super.paint(b);		//paint all components (JFrame.paint does that)
		b.setColor(new Color(0,0,0));		//Draws some rectangles for the gui to make the labels look better
		b.drawRect(currentText.getX(), currentText.getY(),currentText.getWidth(), currentText.getHeight());
		b.drawRect(currentType.getX(), currentType.getY()+currentType.getHeight(),screenScale*4, currentType.getHeight());
		b.drawRect(currentScore.getX(), currentScore.getY(), screenScale*4, screenScale*6);

		g.drawImage(buffer, 0, 0, this);	//paint the buffer
	}
}