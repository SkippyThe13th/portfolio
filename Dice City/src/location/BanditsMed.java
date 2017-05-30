/**
 * 
 */
package location;

/**
 * @author Chase Bengtson
 * A Medium Bandits Object
 */
public class BanditsMed extends Location {

	final static private int VP = 3;
	final static private int HEALTH = 4;
	final static private int WOOD = 0;
	final static private int STONE = 0;
	final static private int METAL = 0;
	final static private String IMAGE_LOCATION = "images/BanditsMedium.png";

	/**
	 * Constructor for Medium Bandit Object
	 */
	public BanditsMed() {
		this.setName("Bandits");
		this.setText("");
		this.setVP(VP);
		this.setHealth(HEALTH);
		this.setTag("VICTORY");
		this.setPrice(WOOD, STONE, METAL);
		this.setImage(IMAGE_LOCATION);	
	}
}
