/**
 * 
 */
package location;

import java.awt.Component;

import pieces.Board;

/**
 * @author Chase Bengtson
 * A Town Hall Location
 */
public class TownHall extends Location {

	final static private int VP = 3;
	final static private int HEALTH = 5;
	final static private int WOOD = 2;
	final static private int STONE = 2;
	final static private int METAL = 1;
	final static private String IMAGE_LOCATION = "images/TownHall.png";

	/**
	 * Constructor for a Town Hall Location
	 */
	public TownHall() {
		this.setName("Town Hall");
		this.setText("Use the ability of any location in your city.");
		this.setVP(VP);
		this.setHealth(HEALTH);
		this.setPrice(WOOD, STONE, METAL);
		this.setTag("UTILITY");
		this.setImage(IMAGE_LOCATION);
	}
	
	/**
	 * Function to use the ability of the Location
	 */
	public void use(Board theBoard, Component die, int rowOfLocation){
		theBoard.getDieFromRow(rowOfLocation).flagAsUsed();
		die.setVisible(false);
	}
}
