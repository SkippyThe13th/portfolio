/**
 * 
 */
package location;

/**
 * @author Chase Bengtson
 * A Small Bandit Object.
 */
public class BanditsSmall extends Location {

	final static private int VP = 2;
	final static private int HEALTH = 3;
	final static private int WOOD = 0;
	final static private int STONE = 0;
	final static private int METAL = 0;
	final static private String IMAGE_LOCATION = "images/BanditsSmall.png";

	/**
	 * Constructor for Small Bandit Object
	 */
	public BanditsSmall() {
		this.setName("Bandits");
		this.setText("");
		this.setVP(VP);
		this.setHealth(HEALTH);
		this.setTag("VICTORY");
		this.setPrice(WOOD, STONE, METAL);
		this.setImage(IMAGE_LOCATION);
	}

}
