/**
 * 
 */
package location;

/**
 * @author Chase Bengtson
 * A Large Bandits Object
 */
public class BanditsLarge extends Location {

	final static private int VP = 4;
	final static private int HEALTH = 5;
	final static private int WOOD = 0;
	final static private int STONE = 0;
	final static private int METAL = 0;
	final static private String IMAGE_LOCATION = "images/BanditsLarge.png";
	

	/**
	 * Constructor for Large Bandit Object
	 */
	public BanditsLarge() {
		this.setName("Bandits");
		this.setText("");
		this.setVP(VP);
		this.setHealth(HEALTH);
		this.setTag("VICTORY");
		this.setPrice(WOOD, STONE, METAL);
		this.setImage(IMAGE_LOCATION);
	}
	


}
