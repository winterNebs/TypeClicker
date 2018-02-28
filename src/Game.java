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
	}
	public boolean type(char c) {
		input += c;
		update();
		return false;
	}
	public void update() {
		System.out.println(input);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		//Do something with timer
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		type(e.getKeyChar());
	}
}
