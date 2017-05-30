/**
 * 
 */
package location;

import java.awt.Component;

import pieces.Board;

/**
 * @author Chase Bengtson
 * A Militia Location
 */
public class Militia extends Location {

	final static int VP = 0;
	final static int HEALTH = 0;
	final static int WOOD = 0;
	final static int STONE = 0;
	final static int METAL = 0;
	final static String IMAGE_LOCATION = "images/Militia.png";

	/**
	 * Constructor for a Militia Location
	 */
	public Militia() {
		this.setName("Forest");
		this.setText("Add 1 STRENGTH to your army.");
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
		
		System.out.println("A Militia was used.");
		theBoard.addAmountOfSwords(amountToAdd);
		theBoard.getDieFromRow(rowOfLocation).flagAsUsed();
		die.setVisible(false);
	}

}
