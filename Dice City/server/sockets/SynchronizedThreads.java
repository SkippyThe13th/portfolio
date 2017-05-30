/**
 * 
 */
package sockets;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

import gui.ServerConsole;
import location.Location;
import pieces.Board;

/**
 * @author Owner Bengtson
 * This class represents an object used as the memory side of a DiceCityServer.
 * It stores all information the server is responsible for knowing about the game
 * it controls.  This includes all threads created to handle client requests.
 */
public class SynchronizedThreads {

	final static int MAX_PLAYERS = 4;
	final static int MAX_ACTIVE_THREADS = 400;
	final static int FIRST_PLAYER = 1;
	final static int SECOND_PLAYER = 2;
	final static int THIRD_PLAYER = 3;
	final static int FOURTH_PLAYER = 4;
	final static int TYPES_OF_BANDIT_TRADESHIP_CARDS = 3;
	final static int STARTING_BASIC_LOCATION_COUNT = 15;
	
	//Server info
	ClientHandlerThread[] threadList;
	ServerConsole console;
	ReentrantLock listLock;
	
	//Game info
	private int playerCount;
	private int playerTurn;
	private Board[] playerBoards;
	private int[] banditsLeft;
	private int[] tradeshipsLeft;
	private ArrayList<Location> locationDeck;
	private int lumberMillsLeft;
	private int quarriesLeft;
	private int minesLeft;
	private int armiesLeft;
	private boolean gameOpen;
	
	/**
	 * Constructor.  Initializes variables.
	 * @param console The ServerConsole created by the DiceCityServer
	 */
	public SynchronizedThreads(ServerConsole console){
		this.threadList = new ClientHandlerThread[MAX_ACTIVE_THREADS];
		this.listLock = new ReentrantLock(true);
		this.console = console;
		
		this.playerCount = 0;
		this.playerTurn = FIRST_PLAYER;
		this.playerBoards = new Board[MAX_PLAYERS];
		this.banditsLeft = new int[TYPES_OF_BANDIT_TRADESHIP_CARDS];
		this.tradeshipsLeft = new int[TYPES_OF_BANDIT_TRADESHIP_CARDS];
		this.locationDeck = new ArrayList<Location>();
		this.lumberMillsLeft = STARTING_BASIC_LOCATION_COUNT;
		this.quarriesLeft = STARTING_BASIC_LOCATION_COUNT;
		this.minesLeft = STARTING_BASIC_LOCATION_COUNT;
		this.armiesLeft = STARTING_BASIC_LOCATION_COUNT;
		this.gameOpen = true;
	}
	
	/**
	 * Adds and starts a thread to the list of synchronized threads using the passed Runnable object.
	 * @param newThread The runnable object to be used to start a new thread.
	 */
	public void addThread(ClientHandlerThread newThread){
		listLock.lock();

			for(Thread thisThread: threadList){
				if(thisThread == null || thisThread.isAlive() == false){
					thisThread = newThread;
					thisThread.start();
					break;
				}
			}
			
		listLock.unlock();
	}
	
	/**
	 * Adds a new player to the game.  Does not do so if there are already 4 players
	 * associated with the game.
	 */
	public void addPlayer(){
		listLock.lock();
		if(gameOpen){
			this.playerCount++;
			System.out.println("New player added to game, making a total of "+playerCount+" players.");
		}
		listLock.unlock();
	}
	
	/**
	 * Sets the value of the integer variable representing which player's turn (out of 4 possible players)
	 * is currently active.
	 * @param player The number of the player who's turn is currently active.
	 */
	public void setPlayerTurn(int player){
		listLock.lock();
		this.playerTurn = player;
		listLock.unlock();
	}

	/**
	 * Move the playerTurn variable to the next player number in the game.
	 * If there are 3 players in a game, and the current value of playerTurn is 3, then
	 * it's value is changed to 1.
	 */
	public void moveToNextTurn(){
		listLock.lock();
		//If it was just the last player's turn, go to player 1.
		//Otherwise, move to the next player.
//		console.print("Progressing turn: The current value of playerTurn is: "+this.playerTurn+ 
//				" The current value of playerCount is: "+this.playerCount);
		if(this.playerTurn >= this.playerCount){
			this.playerTurn = FIRST_PLAYER;
		}else{
			this.playerTurn++;
		}
		//console.println("Server: Player turn moved forward by one, resulting in playerTurn="+this.playerTurn);
		listLock.unlock();
	}
	
	/**
	 * Stores the given Board object in the playerBoards array according the passed
	 * integer.  The integer should correspond to player number, not desired array
	 * index.
	 * @param player
	 * @param board
	 */
	public void setPlayerBoard(int player, Board board){
		listLock.lock();
		int playerIndex = player - 1;
		this.playerBoards[playerIndex] = board;
		listLock.unlock();
	}
	
	/**
	 * Sets the number of Bandit cards left based on the passed array.
	 * Only the first 3 indexes of the array are used.
	 * @param banditsRemaining The array of values indicating how many bandit cards are left in each deck.
	 */
	public void setBanditsLeft(int[] banditsRemaining){
		listLock.lock();
		this.banditsLeft[0] = banditsRemaining[0];
		this.banditsLeft[1] = banditsRemaining[1];
		this.banditsLeft[2] = banditsRemaining[2];
		listLock.unlock();
	}
	
	/**
	 * Sets the number of Trade Ship cards left based on the passed array.
	 * @param tradeshipsRemaining The array indicating amount of trade ship cards remaining.
	 */
	public void setTradeshipsLeft(int[] tradeshipsRemaining){
		listLock.lock();
		this.tradeshipsLeft[0] = tradeshipsRemaining[0];
		this.tradeshipsLeft[1] = tradeshipsRemaining[1];
		this.tradeshipsLeft[2] = tradeshipsRemaining[2];
		listLock.unlock();
	}
	
	/**
	 * Sets the ArrayList that represents the location deck.
	 * @param newDeck The deck to replace the stored deck.
	 */
	public void setLocationDeck(ArrayList<Location> newDeck){
		listLock.lock();
		this.locationDeck = newDeck;
		listLock.unlock();
	}
	
	/**
	 * Sets the remaining number of lumber mill cards.
	 * @param lumberMillsLeft The amount of remaining lumber mill cards.
	 */
	public void setLumberMillsLeft(int lumberMillsLeft){
		listLock.lock();
		this.lumberMillsLeft = lumberMillsLeft;
		listLock.unlock();
	}
	
	/**
	 * Sets the remaining number of quarry cards.
	 * @param quarriesLeft The number of remaining quarry cards.
	 */
	public void setQuarriesLeft(int quarriesLeft){
		listLock.lock();
		this.quarriesLeft = quarriesLeft;
		listLock.unlock();
	}
	
	/**
	 * Sets the remaining number of mine cards.
	 * @param minesLeft The remaining number of mine cards.
	 */
	public void setMinesLeft(int minesLeft){
		listLock.lock();
		this.minesLeft = minesLeft;
		listLock.unlock();
	}
	
	/**
	 * Sets the remaining number of regular army cards.
	 * @param armiesLeft The remaining number of regular army cards.
	 */
	public void setArmiesLeft(int armiesLeft){
		listLock.lock();
		this.armiesLeft = armiesLeft;
		listLock.unlock();
	}
	
	/**
	 * Returns the current number of players connected to the game.
	 * @return int playerCount the number of connected players.
	 */
	public int getPlayerCount(){
		listLock.lock();
		int currentPlayerCount = this.playerCount;
		listLock.unlock();
		return currentPlayerCount;
	}

	/**
	 * Returns the integer representing which player's turn is currently active.
	 * @return The number of the player who's turn is active.
	 */
	public int getPlayerTurn(){
		listLock.lock();
		int currentPlayerTurn = this.playerTurn;
		//console.print("Client request: player "+playerTurn+" is currently taking their turn.");
		listLock.unlock();
		return currentPlayerTurn;
	}
	
	/**
	 * Returns the array of player boards.
	 * @return The array of player board objects.
	 */
	public Board[] getPlayerBoards(){
		listLock.lock();
		Board[] currentPlayerBoards = this.playerBoards;
		listLock.unlock();
		return currentPlayerBoards;
	}
	
	/**
	 * Returns the number of bandit cards remaining in each deck.
	 * @return An int[] containing the number of bandit cards remaining in each deck.
	 */
	public int[] getBanditsLeft(){
		listLock.lock();
		int[] currentBanditsLeft = this.banditsLeft;
		listLock.unlock();
		return currentBanditsLeft;
	}
	
	/**
	 * Returns the number of trade ship cards remainin in each deck.
	 * @return int[] containing the number of trade ship cards remaining in each deck of trade ships.
	 */
	public int[] getTradeshipsLeft(){
		listLock.lock();
		int[] currentTradeshipsLeft = this.tradeshipsLeft;
		listLock.unlock();
		return currentTradeshipsLeft;
	}
	
	/**
	 * Returns the stored ArrayList containing the locations remaining in the location deck.
	 * @return An ArrayList containing in order the locations remaining in the location deck.
	 */
	public ArrayList<Location> getLocationDeck(){
		listLock.lock();
		ArrayList<Location> currentLocationDeck = this.locationDeck;
		listLock.unlock();
		return currentLocationDeck;
	}
	
	/**
	 * Returns the number of remaining lumber mill cards.
	 * @return the number of remaining lumber mill cards.
	 */
	public int getLumberMillsLeft(){
		listLock.lock();
		int currentLumberMillsLeft = this.lumberMillsLeft;
		listLock.unlock();
		return currentLumberMillsLeft;
	}
	
	/**
	 * Returns the number of remaining quarry cards.
	 * @return The number of remaining quarry cards.
	 */
	public int getQuarriesLeft(){
		listLock.lock();
		int currentQuarriesLeft = this.quarriesLeft;
		listLock.unlock();
		return currentQuarriesLeft;
	}
	
	/**
	 * Returns the number of remaining mine cards.
	 * @return the number of remaining mine cards.
	 */
	public int getMinesLeft(){
		listLock.lock();
		int currentMinesLeft = this.minesLeft;
		listLock.unlock();
		return currentMinesLeft;
	}
	
	/**
	 * Returns the number of regular army cards remaining.
	 * @return The number of regualr army cards remaining.
	 */
	public int getArmiesLeft(){
		listLock.lock();
		int currentArmiesLeft = this.armiesLeft;
		listLock.unlock();
		return currentArmiesLeft;
	}
	
	/**
	 * Returns the open or closed status of the game.
	 * @return True if the game is open, false otherwise.
	 */
	public boolean getGameStatus(){
		listLock.lock();
		boolean status = this.gameOpen;
		listLock.unlock();
		return status;
	}
	
	/**
	 * Closes the game so that no new players may join.
	 */
	public void closeGame(){
		listLock.lock();
		this.gameOpen = false;
		System.out.println("Server: Game closed.");
		listLock.unlock();
	}
	
	/**
	 * Opens the game, meaning new players may join.
	 */
	public void openGame(){
		listLock.lock();
		this.gameOpen = true;
		System.out.println("Server: Game opened.");
		listLock.unlock();
	}
	
}
