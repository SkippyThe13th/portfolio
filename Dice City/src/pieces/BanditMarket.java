/**
 * 
 */
package pieces;

import java.util.ArrayList;
import location.Location;
import location.BanditsLarge;
import location.BanditsMed;
import location.BanditsSmall;

/**
 * @author Owner Bengtson
 *
 */
public class BanditMarket {

	private final static int SMALL_BANDIT_HEALTH = 3;
	private final static int MED_BANDIT_HEALTH = 4;
	private final static int LARGE_BANDIT_HEALTH = 5;
	private final static int FIRST_LOCATION = 0;
	
	private ArrayList<Location> smallBandits;
	private ArrayList<Location> medBandits;
	private ArrayList<Location> largeBandits;
	
	/**
	 * Constructor
	 * @param players The number of players in the game.  Used to calculate starting number of bandit cards
	 * 			in each deck.
	 */
	public BanditMarket(int players){
		
		this.smallBandits = new ArrayList<Location>();
		this.medBandits = new ArrayList<Location>();
		this.largeBandits = new ArrayList<Location>();
		
		for(int banditsNeeded=0; banditsNeeded<players+2; banditsNeeded++){
			smallBandits.add(new BanditsSmall());
			medBandits.add(new BanditsMed());
			largeBandits.add(new BanditsLarge());
		}
	}
	
	/**
	 * Returns the current size of the small bandit deck as  an integer.
	 * @return the size of the deck.
	 */
	public int checkSmallBanditDeckSize(){
		return smallBandits.size();
	}
	
	/**
	 * Returns the current size of the medium bandit deck as an integer.
	 * @return the current size of the deck
	 */
	public int checkMediumBanditDeckSize(){
		return smallBandits.size();
	}
	
	/**
	 * Returns the current size of the large bandit deck as an integer.
	 * @return the current size of the deck.
	 */
	public int checkLargeBanditDeckSize(){
		return smallBandits.size();
	}
	
	/**
	 * Returns the top location of the small bandit deck.  Returns null if the deck is empty.
	 * @return the small bandit location
	 */
	public Location getSmallBanditLocation(){
		if(checkSmallBanditDeckSize() >= 1){
			int firstLocation = 0;
			return smallBandits.get(firstLocation);
		}else{
			return null;
		}
	}
	
	/**
	 * Returns the top location of the medium bandit deck.  Returns null if the deck is empty
	 * @return the medium bandit location
	 */
	public Location getMediumBanditLocation(){
		if(checkMediumBanditDeckSize() >= 1){
			int firstLocation = 0;
			return medBandits.get(firstLocation);
		}else{
			return null;
		}
	}

	/**
	 * Returns the top location of the medium bandit deck.  Returns null if the deck is empty.
	 * @return the large bandit location.
	 */
	public Location getLargeBanditLocation(){
		if(checkLargeBanditDeckSize() >= 1){
			int firstLocation = 0;
			return largeBandits.get(firstLocation);
		}else{
			return null;
		}
	}
	
	public boolean removeBandit(Location banditType){
		boolean banditRemoved = true;
		int health = banditType.getHealth();
		
		
		if(health == SMALL_BANDIT_HEALTH){
			if(this.checkSmallBanditDeckSize() >= 1){
				this.smallBandits.remove(FIRST_LOCATION);
				return banditRemoved;
			}else{
				return !banditRemoved;
			}
		}else if(health == MED_BANDIT_HEALTH){
			if(this.checkMediumBanditDeckSize() >= 1){
				this.medBandits.remove(FIRST_LOCATION);
				return banditRemoved;
			}else{
				return !banditRemoved;
			}
		}else if(health == LARGE_BANDIT_HEALTH){
			if(this.checkLargeBanditDeckSize() >= 1){
				this.largeBandits.remove(FIRST_LOCATION);
				return banditRemoved;
			}else{
				return !banditRemoved;
			}
		}else{
			return !banditRemoved;
		}

	}
}
