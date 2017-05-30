/**
 * 
 */
package location;

import java.awt.Component;

import pieces.Board;

/**
 * @author Chase Bengtson
 * The class for a Barracks location.
 */
public class Barracks extends Location {

	final static private int VP = 2;
	final static private int HEALTH = 4;
	final static private int WOOD = 1;
	final static private int STONE = 2;
	final static private int METAL = 0;
	final static String IMAGE_LOCATION = "images/Barracks.png";

	/**
	 * Constructor for initializing a Barracks location.  Sets all stats to predetermined values.
	 */
	public Barracks() {
		this.setName("Barracks");
		this.setText("Place a REGULAR ARMY location anywhere in your city (without paying its cost).");
		this.setVP(VP);
		this.setHealth(HEALTH);
		this.setTag("MILITARY");
		this.setPrice(WOOD, STONE, METAL);
		this.setImage(IMAGE_LOCATION);
	}

	/**
	 * Function for executing the card text of the location.
	 */
	public void use(Board theBoard, Component die, int rowOfLocation){
		
		theBoard.getDieFromRow(rowOfLocation).flagAsUsed();
		die.setVisible(false);
		
	}
}
