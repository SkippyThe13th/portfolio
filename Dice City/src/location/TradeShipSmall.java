/**
 * 
 */
package location;

/**
 * @author Chase Bengtson
 * A Small Trade Ship Object
 */
public class TradeShipSmall extends Location {

	final static private int VP = 5;
	final static private int HEALTH = 0;
	final static private int WOOD = 2;
	final static private int STONE = 2;
	final static private int METAL = 2;
	final static private String IMAGE_LOCATION = "images/TradeShipSmall.png";

	/**
	 * Constructor for Small Trade Ship Object
	 */
	public TradeShipSmall() {
		this.setName("Trade Ship");
		this.setText("");
		this.setVP(VP);
		this.setHealth(HEALTH);
		this.setTag("VICTORY");
		this.setPrice(WOOD, STONE, METAL);
		this.setImage(IMAGE_LOCATION);
	}

}
