/**
 * 
 */
package location;

import java.awt.Component;

import pieces.Board;

/**
 * @author Owner Bengtson
 *
 */
public class Traveller extends Location {
	
	final static int VP = 0;
	final static int HEALTH = 0;
	final static int WOOD = 0;
	final static int STONE = 0;
	final static int METAL = 0;
	final static String IMAGE_LOCATION = "images/Traveller.png";

	/**
	 * Constructor for a Traveller Location
	 */
	public Traveller() {
		this.setName("Traveller");
		this.setText("Reroll this die");
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
		System.out.println("A Traveller was used.");
		
		Location[][] city = theBoard.getCity();
		int row = 0;
		
		for(Location[] aRow: city){
			
			for(Location aLocation: aRow){
				if(aLocation == this){
					theBoard.getDieFromRow(row).roll();
					theBoard.getDieFromRow(row).flagAsNotUsed();
				}else{
				}
			}
			row++;
		}
	}
}
