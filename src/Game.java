import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/*Isaac Wen (2018-02-27)
 * Shawn Hu (2018-03-02)
 * Rheana Thomas (2018-03-02)
 * */
public class Game implements KeyListener, Runnable{
	private Thread thread;
	private final static int INTERVAL = 10;
	public final static int WORD_LENGTH = 5;
	private final static int[] LOOPS_COUNT = {300, 600};
	private int[] updateLoops = {0,0};
	private String input;
	private GUI gui;
	private WordList words;
	private int charCount;
	private long points;

	public Game() {
		thread = new Thread(this);
		Clickable.setGame(this);
		Typer.initTier();
		Upgrade.initTier();
		gui = new GUI();
		gui.addKeyListener(this);
		newGame();
	}
	public void newGame() {
		input = "";
		start();
	}
	public WordList getWordList() {
		return words;
	}
	public void start() {
		charCount = 0;
		points = 0;
		words = new WordList(30);
		update();
		thread.start();
	}
	public boolean type(char c) {
		input += c;
		charCount++;
		update();
		return false;
	}
	public void update() {
		gui.updateText(words.toString());
		switch(check()) {
		case 0: gui.updateInput(input,true); break;
		case 1: gui.updateInput(input, false); break;
		case 2:
			points += words.lastWord.getPoints()*Upgrade.multiplier;
			input = "";
			gui.updateInput(input, true);
			break;
		}
		gui.updateScorePoints(points);
		Clickable.updateMoney(points);
		gui.updateProduction(Typer.cProduction);
		words.updateChar(input.length());
	}

	private int check() {//0 = correct, 1 = wrong, 2 = complete
		if(words.checkComplete(input)) {
			return 2;
		}
		if(words.checkContains(input)) {
			return 0;
		}
		return 1;
	}
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch(e.getKeyCode()) {
		case KeyEvent.VK_SHIFT: break;
		case KeyEvent.VK_BACK_SPACE:
			if(input.length()>0) {
				input = input.substring(0, input.length()-1); 
			}
			break;
			//case KeyEvent.VK_SPACE: update(); break;
		case KeyEvent.VK_CONTROL: break;
		case KeyEvent.VK_ESCAPE: break;
		case KeyEvent.VK_CAPS_LOCK: break;
		case KeyEvent.VK_PAUSE: points += 100; break;
		default: type(e.getKeyChar());
		}
		update();
	}
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
	public void run() {
		while(true) {
			try {
				for(int i = 0; i < LOOPS_COUNT.length; i++) {
					if(updateLoops[i]>= LOOPS_COUNT[i]) {
						switch(i) {
						case 0:
							gui.updateWPM((charCount*1000*60)/(WORD_LENGTH*INTERVAL*updateLoops[i]));
							charCount = 0;
							break;
						case 1:
							points += Typer.cProduction;
							break;
						}
						updateLoops[i] = 0;
					}
					else {
						updateLoops[i]++;
					}
				}
				update();
				Thread.sleep(INTERVAL);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public void clickableClick(Clickable c) {
		points -= c.getPrice();
	}
}
