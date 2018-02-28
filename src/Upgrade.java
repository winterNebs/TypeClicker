

//Shawn Hu Feb 27th
/**TODO:
 * Is a clickable
 * Some sort of math to calculate price
 * Purchased boolean
 * */
public class Upgrade extends Clickable {

	private boolean purchased;
	private int tier;

	public Upgrade() {
		purchased = false;
		tier = 0;
	}

	public Upgrade(boolean p, int t) {
		purchased = p;
		tier = t;
	}

	private void click(int teir, boolean purchased) {
		if (!purchased) {
			purchased = true;
			switch (teir) {
			case 1: break;
			case 2: break;
			case 3: break;
			case 4: break;
			case 5: break;
			case 6: break;
			case 7: break;

			}
		}
	}
}
