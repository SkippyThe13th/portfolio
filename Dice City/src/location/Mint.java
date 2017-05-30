/**
 * 
 */
package location;

import java.awt.Component;

import pieces.Board;

/**
 * @author Chase Bengtson
 * A Mint Location
 */
public class Mint extends Location {

	final static private int VP = 3;
	final static private int HEALTH = 5;
	final static private int WOOD = 0;
	final static private int STONE = 2;
	final static private int METAL = 2;
	final static private String IMAGE_LOCATION = "images/Mint.png";

	/**
	 * Constructor for a Mint Location
	 */
	public Mint() {
		this.setName("Mint");
		this.setText("All HARVEST locations provide an additional resource when used.");
		this.setVP(VP);
		this.setHealth(HEALTH);
		this.setTag("ECONOMY");
		this.setPrice(WOOD, STONE, METAL);
		this.setImage(IMAGE_LOCATION);
	}
	
	/**
	 * Function to use the ability of the Locaiton
	 */
	public void use(Board theBoard, Component die, int rowOfLocation){
		theBoard.getDieFromRow(rowOfLocation).flagAsUsed();
		die.setVisible(false);
	}

}
