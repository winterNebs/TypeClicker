import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Typer extends Clickable{
/**TODO:
 *
 * Counter for number of purchased
 * WPM
 * Some sort of math to calculate price
 * Static cumulative WPM
 * */


    private int numPurchased = 0;
    private int WPM;
    private static int cWPM = 0;
    private long moneyHave;

    public Typer(){
        super();
        numPurchased = 0;
        WPM = 0;
        price = 0;
        tier = 0;
    }

    public Typer(int PM, int t,JLabel l, String txt, BufferedImage i, int x, int y){
        super(l, txt, i, x, y);
        WPM = PM;
        tier = t;
    }

    protected void click(MouseEvent e) {


            price = (long)(Math.pow(tier * BASE_PRICE,tier) * numPurchased);
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

            if(moneyHave >= price){
                ++numPurchased;
                cWPM += WPM;
            }


        }

        public void buy(long mH){
            moneyHave = mH;
        }
    }


