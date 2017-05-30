/**
 * 
 */
package location;

/**
 * @author Chase Bengtson
 * A Medium Trade Ship Object
 */
public class TradeShipMed extends Location {

	final static private int VP = 10;
	final static private int HEALTH = 0;
	final static private int WOOD = 3;
	final static private int STONE = 3;
	final static private int METAL = 3;
	final static private String IMAGE_LOCATION = "images/TradeShipMedium.png";

	/**
	 * Constructor for Medium Trade Ship Object
	 */
	public TradeShipMed() {
		this.setName("Trade Ship");
		this.setText("");
		this.setVP(VP);
		this.setHealth(HEALTH);
		this.setTag("VICTORY");
		this.setPrice(WOOD, STONE, METAL);
		this.setImage(IMAGE_LOCATION);
	}

}
