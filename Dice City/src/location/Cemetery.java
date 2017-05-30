/**
 * 
 */
package location;

import java.awt.Component;

import pieces.Board;

/**
 * @author Chase Bengtson
 * A Cemetery Location
 */
public class Cemetery extends Location {

	final static private int VP = 1;
	final static private int HEALTH = 3;
	final static private int WOOD = 1;
	final static private int STONE = 1;
	final static private int METAL = 0;
	final static private String IMAGE_LOCATION = "images/Cemetery.png";

	/**
	 * Constructor for a Cemetery Location
	 */
	public Cemetery() {
		this.setName("Cemetery");
		this.setText("DEACTIVATE: Use the ability of any location on this row.");
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
