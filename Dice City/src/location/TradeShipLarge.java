/**
 * 
 */
package location;

/**
 * @author Chase Bengtson
 * A Large Trade Ship Object
 */
public class TradeShipLarge extends Location {

	final static private int VP = 20;
	final static private int HEALTH = 0;
	final static private int WOOD = 4;
	final static private int STONE = 4;
	final static private int METAL = 4;
	final static private String IMAGE_LOCATION = "images/TradeShipLarge.png";

	/**
	 * Constructor for Large Trade Ship Object
	 */
	public TradeShipLarge() {
		this.setName("Trade Ship");
		this.setText("");
		this.setVP(VP);
		this.setHealth(HEALTH);
		this.setTag("VICTORY");
		this.setPrice(WOOD, STONE, METAL);
		this.setImage(IMAGE_LOCATION);
	}
}
