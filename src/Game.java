import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;
/*Isaac Wen (2018-02-27)
 * Shawn Hu (2018-03-02)
 * Rheana Thomas (2018-03-02)
 * */
public class Game implements KeyListener, ActionListener{
	private final static int INTERVAL = 10;
	private final static int WORD_LENGTH = 5;
	private final static int LOOP_COUNT = 500;
	private int updateLoops = 0;
	private Timer timer;			//Timer mainly used for WPM
	private String input;
	private GUI gui;
	private WordList words;
	private int charCount;
	public Game() {
		Typer.initTier();
		Upgrade.initTier();
		timer = new Timer(INTERVAL,this);
		gui = new GUI();
		gui.addKeyListener(this);
		newGame();
	}
	public void newGame() {
		input = "";
		start();
	}
	public void start() {
		timer.start();
		charCount = 0;
		words = new WordList(30);
		update();
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
			newWord();
			gui.updateInput(input, true);
			break;
		}
	}
	private void newWord() {
		//currentText = new Word();
		input = "";
	}
	public void actionPerformed(ActionEvent e) {
		if(updateLoops >= LOOP_COUNT) {
			double wpm = (charCount*1000*60)/(WORD_LENGTH*INTERVAL*updateLoops);
			System.out.println("WPM: " + wpm + ", Chars: " + charCount + ", Loops: " + updateLoops);	
			charCount = 0;
			updateLoops = 0;
		}
		else {
			updateLoops++;
		}
		System.out.println(updateLoops);
		update();
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
		default: type(e.getKeyChar());
		}
		update();
	}
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
}
