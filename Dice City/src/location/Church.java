/**
 * 
 */
package location;

import java.awt.Component;

import pieces.Board;

/**
 * @author Chase Bengtson
 * A Church Location
 */
public class Church extends Location {

	final static private int VP = 2;
	final static private int HEALTH = 4;
	final static private int WOOD = 1;
	final static private int STONE = 1;
	final static private int METAL = 1;
	final static private String IMAGE_LOCATION = "images/Church.png";

	/**
	 * Constructor for a Church Location
	 */
	public Church() {
		this.setName("Church");
		this.setText("Use the ability of any location in your city with a die on it.");
		this.setVP(VP);
		this.setHealth(HEALTH);
		this.setTag("UTILITY");
		this.setPrice(WOOD, STONE, METAL);
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
