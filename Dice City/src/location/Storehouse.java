/**
 * 
 */
package location;

import java.awt.Component;

import pieces.Board;

/**
 * @author Chase Bengtson
 * A Storehouse Location
 */
public class Storehouse extends Location {

	final static private int VP = 1;
	final static private int HEALTH = 3;
	final static private int WOOD = 1;
	final static private int STONE = 1;
	final static private int METAL = 0;
	final static private String IMAGE_LOCATION = "images/Storehouse.png";

	/**
	 * Constructor for a Storehouse Location
	 */
	public Storehouse() {
		this.setName("Storehouse");
		this.setText("Pay 1 of any resource.  Get 3 of that resource.");
		this.setVP(VP);
		this.setHealth(HEALTH);
		this.setPrice(WOOD, STONE, METAL);
		this.setTag("ECONOMY");
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
