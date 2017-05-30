/**
 * 
 */
package location;

import java.awt.Component;

import pieces.Board;

/**
 * @author Chase Bengtson
 * Blacksmith Location object
 */
public class Blacksmith extends Location {

	final static private int VP = 2;
	final static private int HEALTH = 4;
	final static private int WOOD = 0;
	final static private int STONE = 1;
	final static private int METAL = 2;
	final static private String IMAGE_LOCATION = "images/Blacksmith.png";

	/**
	 * Constructor for a Blacksmith Location
	 */
	public Blacksmith() {
		this.setName("Blacksmith");
		this.setText("All your ARMY locations provide an additional 1 STRENGTH.");
		this.setVP(VP);
		this.setHealth(HEALTH);
		this.setPrice(WOOD, STONE, METAL);
		this.setTag("MILITARY");
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
