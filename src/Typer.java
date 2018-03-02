import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Typer extends Clickable {
    /**
     * TODO:
     * <p>
     * Counter for number of purchased
     * WPM
     * Some sort of math to calculate price
     * Static cumulative WPM
     */


    private int numPurchased = 0;
    private int WPM;
    private static int cWPM = 0;
    private long moneyHave;

    public Typer() {
        super();
        numPurchased = 0;
        WPM = 0;
        price = 0;
        tier = 0;
    }

    public Typer(int PM, int t, JLabel l, String txt, BufferedImage i, int x, int y) {
        super(l, txt, i, x, y);
        WPM = PM;
        tier = t;
    }

    protected void click(MouseEvent e) {

        ++numPurchased;
        price = (long) (Math.pow(tier * BASE_PRICE, tier) * numPurchased);
        switch (tier) {

            case 1:

                text = "Intern";
                description = "Lowest tier typer, hand writes";

                break;
            case 2:

                text = "TextToSpeech";
                description = "Second tier typer";

                break;
            case 3:

                text = "CellPhone";
                description = "better than text to speech, but still slow";

                break;
            case 4:

                text = "TypeWriter ";
                description = "Better than cellphone, but still kind of trash";

                break;
            case 5:

                text = "Computer";
                description = "Basic typer, increases WPM by low amount, faster than typewriter";

                break;
            case 6:

                text = "Supern Computer";
                description = "Awesome typer, much fast WPM";

                break;
            case 7:

                text = "Quantum Computer";
                description = "Super good typer, much much fast WPM";

                break;
            case 8:

                text = "Inter-dimensional Typer";
                description = "The ultimate, the best, the fastest, annnnnd the most expensive";

                break;

        }

        if (moneyHave >= price) {
            cWPM += WPM;
        } else {
            --numPurchased;
            JOptionPane.showMessageDialog(null, "not enough money");
        }


    }

    public void buy(long mH) {
        moneyHave = mH;
    }

}


