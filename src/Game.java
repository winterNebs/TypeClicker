import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;
/*Isaac Wen (2018-02-27)
 * */
public class Game implements KeyListener, ActionListener{
	Timer timer;			//Timer mainly used for WPM
	public Game() {
		timer = new Timer(10,this);
	}
	public void start() {
		timer.start();
	}
	public boolean type(char c) {
		//Check for typo
		return false;
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
		// TODO Auto-generated method stub
		
	}
}
