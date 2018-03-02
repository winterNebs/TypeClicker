import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;
/*Isaac Wen (2018-02-27)
 * */
public class Game implements KeyListener, ActionListener{
	private Timer timer;			//Timer mainly used for WPM
	private String input;
	private GUI gui;
	private Word currentText;
	public Game() {
		timer = new Timer(10,this);
		newGame();
		gui = new GUI();
		gui.addKeyListener(this);
	}
	public void newGame() {
		input = "";
		start();
	}
	public void start() {
		timer.start();
		currentText = new Word();
	}
	public boolean type(char c) {
		input += c;
		update();
		return false;
	}
	public void update() {
		if(currentText.getLength() == 0) {
			currentText = new Word();
		}
		gui.updateText(currentText.toString());
		System.out.println(input);
		System.out.println(currentText);
		gui.updateInput(input);
	}
	public void actionPerformed(ActionEvent e) {
		//Do something with timer
	}
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch(e.getKeyCode()) {
		case KeyEvent.VK_BACK_SPACE:
			if(input.length()>0) {
				input = input.substring(0, input.length()-1); 
			}
			update();
			break;
		default: type(e.getKeyChar());
		}
	}
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
}
