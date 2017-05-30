/**
 * 
 */
package location;

import java.awt.Component;

import pieces.Board;

/**
 * @author Chase Bengtson
 * Class for defining a Cathedral location.
 */
public class Cathedral extends Location {

	final static private int VP = 1;
	final static private int HEALTH = 6;
	final static private int WOOD = 1;
	final static private int STONE = 2;
	final static private int METAL = 2;
	final static private String IMAGE_LOCATION = "images/Cathedral.png";

	/**
	 * Constructor for a Cathedral location.  Initializes all stats to predetermined values.
	 */
	public Cathedral() {
		this.setName("Cathedral");
		this.setText("This location is worth 1 vp for each CULTURE location on this row.");
		this.setVP(VP);
		this.setHealth(HEALTH);
		this.setTag("CULTURE");
		this.setPrice(WOOD, STONE, METAL);
		this.setImage(IMAGE_LOCATION);
	}

	/**
	 * Function for executing the location's card text.
	 */
	public void use(Board theBoard, Component die, int rowOfLocation){
		theBoard.getDieFromRow(rowOfLocation).flagAsUsed();
		die.setVisible(false);
	}
}
