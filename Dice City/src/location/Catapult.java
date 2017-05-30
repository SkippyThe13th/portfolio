/**
 * 
 */
package location;

import java.awt.Component;

import pieces.Board;

/**
 * @author Chase Bengtson
 * A Catapult Location
 */
public class Catapult extends Location {

	final static private int VP = 2;
	final static private int HEALTH = 4;
	final static private int WOOD = 2;
	final static private int STONE = 0;
	final static private int METAL = 1;
	final static private String IMAGE_LOCATION = "images/Catapult.png";

	/**
	 * Constructor for a Catapult Location
	 */
	public Catapult() {
		this.setName("Catapult");
		this.setText("DEACTIVATE: Add 3 STRENGTH to your army.");
		this.setVP(VP);
		this.setHealth(HEALTH);
		this.setTag("MILITARY");
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
