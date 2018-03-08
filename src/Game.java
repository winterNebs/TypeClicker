import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/*Isaac Wen (2018-02-27)
 * Shawn Hu (2018-03-02)
 * Rheana Thomas (2018-03-02)
 * TypeClicker is basically a typing game where you earn points and buy upgrades.
 * There are also typers which help you get more points
 * */
public class Game implements KeyListener, Runnable{
	private Thread thread;								//Thread used for timer
	private final static int INTERVAL = 10;				//Interval (time in between updates)
	public final static int WORD_LENGTH = 5;			//Average length of word(
	private final static int[] LOOPS_COUNT = {300, 600};//Basically how many loops need to pass before an update gets triggered for a certain event 
	private int[] updateLoops = {0,0};					//Keeps track of how many loops have passed. (first one is for wpm, second one is for adding production)
	private String input;				//Current input
	private GUI gui;					//The gui for the game
	private WordList words;				//List of all the words to be typed
	private int charCount;				//How many characters typed used for wpm
	private long points;				//How many points the player has

	public Game() {						//Default constructor
		thread = new Thread(this);		//Initializes everything and adds a keylistener
		Clickable.setGame(this);
		Typer.initTier();
		Upgrade.initTier();
		gui = new GUI();
		gui.addKeyListener(this);
		newGame();						//Starts a new game/reset
	}
	public void newGame() {
		input = "";						//Resets everything
		charCount = 0;					
		points = 0;
		words = new WordList(30);
		update();
		thread.start();
	}
	public WordList getWordList() {
		return words;					//gets the wordlist
	}

	public void type(char c) {		//To update the input and wpm and gui when something is typed
		input += c;
		charCount++;
		update();
	}
	public void update() {					//Updates the gui
		gui.updateText(words.toString());	//Updates the text to be typed
		switch(check()) {					//Updates the input on the gui with a color if it is wrong
		case 0: gui.updateInput(input,true); break;
		case 1: gui.updateInput(input, false); break;
		case 2:								//Add points if the word is correct
			points += words.lastWord.getPoints()*Upgrade.multiplier;
			input = "";
			gui.updateInput(input, true);
			break;
		}
		gui.updateScorePoints(points);		//update points on the gui
		Clickable.updateMoney(points);		//Sends current points to the clickable class
		gui.updateProduction(Typer.cProduction);	//Sends cumulative production to the gui
		words.updateChar(input.length());			//tells the wordlist how many letters are typed (for hidden mode, so that it can only show the next char)
	}

	private int check() {//0 = correct, 1 = wrong, 2 = complete
		if(words.checkComplete(input)) {	//Check input vs current word
			return 2;
		}
		if(words.checkContains(input)) {
			return 0;
		}
		return 1;
	}
	public void keyPressed(KeyEvent e) {	//keylistener, listens for input
		switch(e.getKeyCode()) {
		case KeyEvent.VK_SHIFT: break;	//Makes some inputs do nothing
		case KeyEvent.VK_BACK_SPACE:
			if(input.length()>0) {
				input = input.substring(0, input.length()-1); 
			}
			break;
		case KeyEvent.VK_CONTROL: break;
		case KeyEvent.VK_ESCAPE: break;
		case KeyEvent.VK_CAPS_LOCK: break;
		case KeyEvent.VK_F1: break;
		case KeyEvent.VK_F2: break;
		case KeyEvent.VK_F3: break;
		case KeyEvent.VK_F4: break;
		case KeyEvent.VK_F5: break;
		case KeyEvent.VK_F6: break;
		case KeyEvent.VK_F7: break;
		case KeyEvent.VK_F8: break;
		case KeyEvent.VK_F9: break;
		case KeyEvent.VK_F10: break;
		case KeyEvent.VK_F11: break;
		case KeyEvent.VK_F12: break;
		case KeyEvent.VK_INSERT: break;
		case KeyEvent.VK_DELETE: break;
		case KeyEvent.VK_TAB: break;
		case KeyEvent.VK_ALT: break;
		case KeyEvent.VK_LEFT: break;
		case KeyEvent.VK_UP: break;
		case KeyEvent.VK_RIGHT: break;
		case KeyEvent.VK_DOWN: break;
		default:
				type(e.getKeyChar());	//If it's a valid key type it
		}
		update();
	}
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
	public void run() {			//Thread update loop
		while(true) {
			try {
				for(int i = 0; i < LOOPS_COUNT.length; i++) {	//Goes through the update loops
					if(updateLoops[i]>= LOOPS_COUNT[i]) {		//If the current loop is equal to the max loop
						switch(i) {
						case 0:									//For wpm calculate wpm and reset chars
							gui.updateWPM((charCount*1000*60)/(WORD_LENGTH*INTERVAL*updateLoops[i]));
							charCount = 0;
							break;
						case 1:									//Add production
							points += Typer.cProduction;
							break;
						}
						updateLoops[i] = 0;						//Reset the loop
					}
					else {
						updateLoops[i]++;						//If not, add 1
					}
				}
				update();										//update gui
				Thread.sleep(INTERVAL);							//Sleep for interval
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public void clickableClick(Clickable c) {
		points -= c.getPrice();									//If something gets bought, remove price
	}
}
