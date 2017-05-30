/**
 * 
 */
package location;

import java.awt.Component;

import pieces.Board;

/**
 * @author Chase Bengtson
 * A Well Location
 */
public class Well extends Location {

	final static int VP = 1;
	final static int HEALTH = 3;
	final static int WOOD = 0;
	final static int STONE = 1;
	final static int METAL = 1;
	final static private String IMAGE_LOCATION = "images/Well.png";

	/**
	 * Constructor for a Well Location
	 */
	public Well() {
		this.setName("Well");
		this.setText("Reroll this die and any number of your other unused dice.");
		this.setVP(VP);
		this.setHealth(HEALTH);
		this.setTag("UTILITY");
		this.setPrice(WOOD, STONE, METAL);
		this.setImage(IMAGE_LOCATION);
	}
	
	/**
	 * Function for using the Location's ability
	 */
	public void use(Board theBoard, Component die, int rowOfLocation){
		theBoard.getDieFromRow(rowOfLocation).flagAsUsed();
		die.setVisible(false);
	}

}
