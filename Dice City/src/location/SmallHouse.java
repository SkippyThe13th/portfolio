/**
 * 
 */
package location;

import java.awt.Component;

import pieces.Board;

/**
 * @author Chase Bengtson
 * A Small House Location
 */
public class SmallHouse extends Location {

	final static int VP = 0;
	final static int HEALTH = 0;
	final static int WOOD = 0;
	final static int STONE = 0;
	final static int METAL = 0;
	final static String IMAGE_LOCATION = "images/SmallHouse.png";

	/**
	 * Constructor for a Small House Location
	 */
	public SmallHouse() {
		this.setName("Small House");
		this.setText("Get 1 VP.");
		this.setVP(VP);
		this.setHealth(HEALTH);
		this.setTag("BOARD");
		this.setPrice(WOOD, STONE, METAL);
		this.setImage(IMAGE_LOCATION);
	}
	
	/**
	 * Function to use the ability of the Location
	 */
	public void use(Board theBoard, Component die, int rowOfLocation){
		int amountToAdd = 1;
		
		System.out.println("A Small House was used.");
		theBoard.addAmountOfVP(amountToAdd);
		theBoard.getDieFromRow(rowOfLocation).flagAsUsed();
		die.setVisible(false);
	}

}
