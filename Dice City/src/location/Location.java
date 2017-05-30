package location;

import java.awt.Component;
import java.net.URL;
import java.util.Arrays;

import pieces.Board;
/**
 * Locations are placed on players' boards in order to increase or maximize resource production.  They are "used" by dice that occupy the same location
 * on the board's grid as them.
 * @author Chase Bengtson
 *
 */
public class Location {

	private String name;
	private String text;
	private int vp;
	private int health;
	private String tag;
	private boolean deactivated;
	//The price array represents the cost of buying the location.  Each element refers to the number of a specific resource needed.  The order is as
	//follows: {wood, stone, metal}.
	private int[] price = new int[3];
	private String imageLocation;
	
	/**
	 * Constructor for a location object
	 * @param name This is the name of the location
	 * @param text This is the text that explains the function or ability of the location.
	 * @param vp This is the amount of victory points awarded for this location being present in a player's city at the end of the game.
	 * @param health This is the health of the building, or how many swords are required to deactivate it in the event of another player attacking it.
	 * @param type This is the type of the location, which should be one of the following: "harvest", "culture", "military", or "economy".
	 */
	public Location(String name, String text, int vp, int health, String tag, int[] price, String imageLocation){
		this.name = name;
		this.text = text;
		this.vp = vp;
		this.health = health;
		this.tag = tag;
		//Copy the array passed into the array
		this.price = Arrays.copyOf(price, 3);
		this.deactivated = false;
		this.imageLocation = imageLocation;
	}
	
	public Location(){
		
	}
	
	/**
	 * Returns the name of the location as it would be printed on the actual card.
	 * @return name This is the name of the card, stored as a String
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * Sets the location name to desired string.
	 * @param newName The new name.
	 */
	public void setName(String newName){
		this.name = newName;
	}
	
	/**
	 * Returns the text of the location, which describes the location's ability/function;
	 * @return
	 */
	public String getText(){
		return this.text;
	}
	
	/**
	 * Sets the location text to desired string.
	 * @param newText The new text.
	 */
	public void setText(String newText){
		this.text = newText;
	}
	
	/**
	 * Returns the victory points associated with owning this location at the end of the game.
	 * @return The number of victory points.
	 */
	public int getVP(){
		return this.vp;
	}
	
	/**
	 * Sets the location victory points to the desired value.
	 * @param newVP The location's new worth in victory points.
	 */
	public void setVP(int newVP){
		this.vp = newVP;
	}
	
	/**
	 * Returns the health of a location, or the amount of swords a player must use to deactivate it.
	 * @return The amount of health/number of swords.
	 */
	public int getHealth(){
		return this.health;
	}
	
	/**
	 * Sets the location health to the desired value
	 * @param newHealth The new amount of health.
	 */
	public void setHealth(int newHealth){
		this.health = newHealth;
	}
	
	/**
	 * Returns the type of the location.
	 * @return The type of the location.
	 */
	public String getTag(){
		return this.tag;
	}
	
	/**
	 * Sets the location's tag to the desired string.
	 * @param newTag
	 */
	public void setTag(String newTag){
		this.tag = newTag;
	}
	
	/**
	 * Returns the array that describes the price to purchase/build the location.
	 * @return The price to purchase/build the location.
	 */
	public int[] getPrice(){
		return this.price;
	}
	
	/**
	 * Sets the price array of the location to the desired values.
	 * @param wood The amount of wood needed to build the location.
	 * @param metal The amount of metal needed to build the location.
	 * @param stone The amount of stone needed to build the location.
	 */
	public void setPrice(int wood, int stone, int metal){
		this.price[0] = wood;
		this.price[1] = stone;
		this.price[2] = metal;
	}
	
	/**
	 * Returns the location of the image of the object.
	 * @return the file locaiton of the image.
	 */
	public String getImage(){
		return imageLocation;
	}
	
	/**
	 * Sets the location of the image to be used by the location.
	 * @param imageLocation The address of the image.
	 */
	public void setImage(String imageLocation){
		this.imageLocation = imageLocation;
	}
	
	/**
	 * Returns the state of the building in terms of deactivated v.s. activated.  True if deactivated, false otherwise.
	 * @return True if deactivated, false otherwise.
	 */
	public boolean isDeactivated(){
		return this.deactivated;
	}
	
	/**
	 * Toggles the deactivated state of a location.  Similar to placing or removing a deactivated token.
	 */
	public void deactivate(){
		if(this.deactivated == true){
			this.deactivated = false;
		}
		else{
			this.deactivated = true;
		}
	}
	
	/**
	 * Uses the ability of the location.
	 */
	public void use(Board theBoard, Component die, int row){
		
	}
}
