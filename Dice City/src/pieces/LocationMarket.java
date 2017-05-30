/**
 * 
 */
package pieces;

import location.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * @author Chase Bengtson
 * The Location market is the collection of purchasable locations available to all players during their turn.
 * The deck is represented with an ArrayList of Locations, the first 8 of which are purchasable by players.
 */
public class LocationMarket {
	
	private final static int BASIC_LOCATION_COUNT = 15;
	
	//The deck of "buildable" locations.  The first 8 items are the ones
	//available for purchase.
	private ArrayList<Location> locationDeck = new ArrayList<Location>(); 
	private int lumberMillCount = BASIC_LOCATION_COUNT;
	private int quarryCount = BASIC_LOCATION_COUNT;
	private int mineCount = BASIC_LOCATION_COUNT;
	private int armyCount = BASIC_LOCATION_COUNT;
	
	/**
	 * Constructor for a new Location Market.  Creates a list that consists of 3 copies of each location.
	 */
	public LocationMarket(){
		
		//Add 3 of every "buildable" location to the deck.
		for(int i=0;i<3;i++){
			this.locationDeck.add(new Barracks());
			this.locationDeck.add(new Bazaar());
			this.locationDeck.add(new Blacksmith());
			this.locationDeck.add(new Catapult());
			this.locationDeck.add(new Cathedral());
			this.locationDeck.add(new Cemetery());
			this.locationDeck.add(new Church());
			this.locationDeck.add(new FestivalHall());
			this.locationDeck.add(new GrandStatue());
			this.locationDeck.add(new GreatWall());
			this.locationDeck.add(new Manor());
			this.locationDeck.add(new MarketPlace());
			this.locationDeck.add(new MerchantGuild());
			this.locationDeck.add(new Mint());
			this.locationDeck.add(new Stables());
			this.locationDeck.add(new Storehouse());
			this.locationDeck.add(new TownHall());
			this.locationDeck.add(new TrainingCamp());
			this.locationDeck.add(new WatchTower());
			this.locationDeck.add(new Well());
		}
		
	}

	/**
	 * Shuffles the deck of "buildable" locations.
	 */
	public void shuffleLocationDeck(){
		Random randomSource = new Random();
		
		Collections.shuffle(this.locationDeck, randomSource);

	}
	
	/**
	 * Returns the location at the specified index of the locationDeck ArrayList
	 * @param index The index of the desired location
	 * @return The Location object stored at the index.
	 */
	public Location getLocation(int index){
		return locationDeck.get(index);
	}
	
	/**
	 * Returns the entire deck of locations.
	 * @return The location deck as an ArrayList<Location>
	 */
	public ArrayList<Location> getDeck(){
		return this.locationDeck;
	}
	
	/**
	 * Returns a lumber mill location
	 * @return A LumberMill Location object.
	 */
	public LumberMill getLumberMill(){
		return new LumberMill();
	}
	
	/**
	 * Returns a mine location
	 * @return A Mine Location object.
	 */
	public Mine getMine(){
		return new Mine();
	}
	
	/**
	 * Returns a quarry location
	 * @return A Quarry Location object.
	 */
	public Quarry getQuarry(){
		return new Quarry();
	}
	
	/**
	 * Returns a regular army location
	 * @return A RegularArym Location object.
	 */
	public RegularArmy getRegularArmy(){
		return new RegularArmy();
	}
	
	/**
	 * Returns the current number of cards remaining in the location deck.
	 * @return The number of cards remaining in the location deck.
	 */
	public int deckSize(){
		return locationDeck.size();
	}
	
	/**
	 * Reduces the number of lumber mills by 1
	 * @return True if there was a lumber mill to remove, false otherwise.
	 */
	public boolean subtractLumberMill(){
		if(this.lumberMillCount >= 1){
			this.lumberMillCount -= 1;
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * Reduces the number of quarries by 1.
	 * @return Returns true if a quarry was removed, false otherwise.
	 */
	public boolean subtractQuarry(){
		if(this.quarryCount >= 1){
			this.quarryCount -= 1;
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * Reduces the number of mines by one, if there is one to take.
	 * @return Returns true if a mine was removed, false otherwise.
	 */
	public boolean subtractMine(){
		if(this.mineCount >= 1){
			this.mineCount -= 1;
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * Reduces the number of regular armies by one, if there is one to take.
	 * @return Returns true if a regular army was taken, false otherwise.
	 */
	public boolean subtractRegularArmy(){
		if(this.armyCount >= 1){
			this.armyCount -= 1;
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * Removes the first instance of the specified location from the location deck.
	 * @param theLocation The location to be removed.
	 */
	public void removeLocation(Location theLocation){
		int objectToRemoveIndex = this.locationDeck.indexOf(theLocation);
		int replacementIndex = 8;
		
		if(locationDeck.size() >= 9){
			Collections.swap(locationDeck, objectToRemoveIndex, replacementIndex);
			locationDeck.remove(replacementIndex);
		}else{
			locationDeck.remove(objectToRemoveIndex);
		}

	}
}
