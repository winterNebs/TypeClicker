

//Shawn Hu Feb 27th

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 * TODO:
 * Is a clickable
 * Purchased boolean
 */

public class Upgrade extends Clickable {

    private boolean purchased;
    private int tier;
    private String description;
    private final long BASE_PRICE = 100;
    private long price;


    public Upgrade() {
        purchased = false;
        tier = 0;
        text = "default";
        description = "default";
    }

    public Upgrade(boolean p, int t, String d, JLabel l, String txt, BufferedImage i, int x, int y) {
        super(l, txt, i, x, y);
        purchased = p;
        tier = t;
        description = d;
    }

    protected void click(MouseEvent e) {
        if (!purchased) {
            purchased = true;
            price = (long)Math.pow(tier * BASE_PRICE,tier);
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

    }

}
