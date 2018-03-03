

//Shawn Hu Feb 27th

import javax.swing.*;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 * TODO:
 * Is a clickable
 * Purchased boolean
 */

public class Upgrade extends Clickable {
	protected final int MAX_TIER = 5;

	private boolean purchased = false;

	public Upgrade() {
		super();
		tier = 0;
		text = "default";
		description = "default";

	}
	public Upgrade(int t, Rectangle b) {
		super(b);
		tier = t;
		tier();
	}
	private void tier() {
		switch (tier) {
		case 1:
			text = "Pen";
			description = "Default, cannot use backspace";
			break;
		case 2:
			text = "Pencil";
			description = "Unlocks backspace";
			break;
		case 3:
			text = "Membrane Keyboard";
			description = "Unlocks certain letters (Letters that aren't symbols or numbers)";
			break;
		case 4:
			text = "Mechanical Keyboard";
			description = "Unlocks full keyboard (\"Easy Keys\")";
			break;
		case 5:
			text = "eyes";
			description = "Lets you see the whole word";
			break;
		}
	}
	protected void click(MouseEvent e) {
		if (!purchased) {
			purchased = true;
			price = (long)Math.pow(tier * BASE_PRICE,tier);

		}

	}

}
