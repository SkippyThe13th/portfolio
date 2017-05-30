/**
 * 
 */
package location;

import java.awt.Component;

import pieces.Board;

/**
 * @author Chase Bengtson
 * A Quarry Location
 */
public class Quarry extends Location {

	final static private int VP = 0;
	final static private int HEALTH = 2;
	final static private int WOOD = 0;
	final static private int STONE = 2;
	final static private int METAL = 0;
	final static private String IMAGE_LOCATION = "images/Quarry.png";

	/**
	 * Constructor for a Quarry Location
	 */
	public Quarry() {
		this.setName("Quarry");
		this.setText("Get 2 stone.");
		this.setVP(VP);
		this.setHealth(HEALTH);
		this.setTag("HARVEST");
		this.setPrice(WOOD, STONE, METAL);
		this.setImage(IMAGE_LOCATION);
	}
	
	/**
	 * Function for using the Location's ability
	 */
	public void use(Board theBoard, Component die, int rowOfLocation){
		int amountToAdd = 2;
		
		System.out.println("A Quarry was used.");
		theBoard.addAmountOfStone(amountToAdd);
		theBoard.getDieFromRow(rowOfLocation).flagAsUsed();
		die.setVisible(false);
	}

}
