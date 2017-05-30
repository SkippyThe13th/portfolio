/**
 * 
 */
package location;

import java.awt.Component;

import pieces.Board;

/**
 * @author Chase Bengtson
 * A Cave Location
 */
public class Cave extends Location {

	final static int VP = 0;
	final static int HEALTH = 0;
	final static int WOOD = 0;
	final static int STONE = 0;
	final static int METAL = 0;
	final static String IMAGE_LOCATION = "images/Cave.png";

	/**
	 * Constructor for a Cave Location
	 */
	public Cave() {
		this.setName("Cave");
		this.setText("Get 1 metal.");
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
		int metalToAdd = 1;
		
		System.out.println("A Cave was used.");
		theBoard.addAmountOfMetal(metalToAdd);
		
		theBoard.getDieFromRow(rowOfLocation).flagAsUsed();
		die.setVisible(false);
	}

}
