/**
 * 
 */
package pieces;

import java.util.ArrayList;
import location.Location;
import location.TradeShipLarge;
import location.TradeShipMed;
import location.TradeShipSmall;

/**
 * @author Owner Bengtson
 * The TradeShipMarket consists of 3 decks of trade ship cards that can be purchased by players in order to
 * gain victory points.  The number of trade ship cards in each deck is determined by the number of players
 * in the game.
 */
public class TradeShipMarket {
	
	//Lists representing the decks of trade ships.
	private ArrayList<Location> smallTradeShips;
	private ArrayList<Location> medTradeShips;
	private ArrayList<Location> largeTradeShips;
	
	/**
	 * Constructor for a trade ship market object.
	 * @param players The number of players in the game.  Used when calculating the starting
	 * 			number of trade ships in each deck.
	 */
	public TradeShipMarket(int players){
		this.smallTradeShips = new ArrayList<Location>();
		this.medTradeShips = new ArrayList<Location>();
		this.largeTradeShips = new ArrayList<Location>();
		
		for(int i=0; i<players+2; i++){
			smallTradeShips.add(new TradeShipSmall());
		}
		for(int i=0; i<players; i++){
			medTradeShips.add(new TradeShipMed());
		}
		largeTradeShips.add(new TradeShipLarge());
	}
	
	/**
	 * Returns the number of cards remaining in the deck of small trade ships.
	 * @return the number of cards remaining.
	 */
	public int checkSmallTradeShipDeckSize(){
		return smallTradeShips.size();
	}
	
	/**
	 * Returns the number of cards remaining in the deck of medium trade ships.
	 * @return the number of cards remaining.
	 */
	public int checkMedTradeShipDeckSize(){
		return medTradeShips.size();
	}
	
	/**
	 * Returns the number of cards remaining in the deck of large trade ships.
	 * @return the number of cards remaining.
	 */
	public int checkLargeTradeShipDeckSize(){
		return largeTradeShips.size();
	}
	
	/**
	 * Returns a small trade ship location
	 * @return A TradeShipSmall Location object.
	 */
	public Location getSmallTradeShipLocation(){
		if(this.checkSmallTradeShipDeckSize() >= 1){
			int firstLocation = 0;
			return smallTradeShips.get(firstLocation);
		}else{
			return null;
		}
	}
	
	/**
	 * Returns a medium trade ship location
	 * @return A TradeShipMed Location object.
	 */
	public Location getMedTradeShipLocation(){
		if(this.checkMedTradeShipDeckSize() >= 1){
			int firstLocation = 0;
			return medTradeShips.get(firstLocation);
		}else{
			return null;
		}
	}
	
	/**
	 * Returns a large trade ship location
	 * @return A TradeShipLarge Location object.
	 */
	public Location getLargeTradeShipLcoation(){
		if(this.checkLargeTradeShipDeckSize() >= 1){
			int firstLocation = 0;
			return largeTradeShips.get(firstLocation);
		}else{
			return null;
		}
	}
}
