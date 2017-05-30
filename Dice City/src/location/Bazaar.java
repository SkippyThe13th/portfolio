/**
 * 
 */
package location;

import java.awt.Component;

import pieces.Board;

/**
 * @author Chase Bengtson
 * A Bazaar Location
 */
public class Bazaar extends Location {

	final static private int VP = 1;
	final static private int HEALTH = 3;
	final static private int WOOD = 1;
	final static private int STONE = 0;
	final static private int METAL = 1;
	final static private String IMAGE_LOCATION = "images/Bazaar.png";

	/**
	 * Constructor for a Bazaar.
	 */
	public Bazaar() {
		this.setName("Bazaar");
		this.setText("DEACTIVATE: Get 1 WOOD, 1 STONE, 1 METAL");
		this.setVP(VP);
		this.setHealth(HEALTH);
		this.setTag("ECONOMY");
		this.setPrice(WOOD, STONE, METAL);
		this.setImage(IMAGE_LOCATION);
	}

	/**
	 * Function to use the ability of the location.
	 */
	public void use(Board theBoard, Component die, int rowOfLocation){
		theBoard.getDieFromRow(rowOfLocation).flagAsUsed();
		die.setVisible(false);
	}
}
