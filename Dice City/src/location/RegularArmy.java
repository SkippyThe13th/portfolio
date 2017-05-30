/**
 * 
 */
package location;

import java.awt.Component;

import pieces.Board;

/**
 * @author Chase Bengtson
 * A Regular Army Location
 */
public class RegularArmy extends Location {

	final static private int VP = 1;
	final static private int HEALTH = 3;
	final static private int WOOD = 0;
	final static private int STONE = 0;
	final static private int METAL = 2;
	final static private String IMAGE_LOCATION = "images/RegularArmy.png";

	/**
	 * Constructor for a Regular Army Location
	 */
	public RegularArmy() {
		this.setName("Regular Army");
		this.setText("Add 2 STRENGTH to your army.");
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
		int amountToAdd = 2;
		
		System.out.println("A Regular Army was used.");
		theBoard.addAmountOfSwords(amountToAdd);
		theBoard.getDieFromRow(rowOfLocation).flagAsUsed();
		die.setVisible(false);
	}

}
