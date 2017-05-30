/**
 * 
 */
package gameControl;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import edu.truman.bengtsonc.DiceCityClient;
import gui.*;
import location.*;
import pieces.*;

/**
 * @author Chase Bengtson
 * This is a class that allows the creation and start of a Dice City game. This class contains the structure
 * for individual turns, as well as disabling and enabling listeners when appropriate for each phase during
 * a player's turn.
 */
public class DiceCityGame {
	
	public final static int MAX_PLAYERS = 4;
	public final static int PLAYER_1_INDEX = 0;
	public final static int PLAYER_2_INDEX = 1;
	public final static int PLAYER_3_INDEX = 2;
	public final static int PLAYER_4_INDEX= 3;
	
	private String serverIP;
	private int port;
	
	private int players;
	private int playerNumber;
	private int currentTurn;
	private Board[] boardList;
	private LocationMarket locationMarket;
	private BanditMarket banditMarket;
	private TradeShipMarket tradeShipMarket;
	private GameWindow gameWindow;
	private ArrayList<Location> locationPlacementBuffer;
	
	/**
	 * Basic Constructor.
	 */
	public DiceCityGame(GameWindow gameWindow) {
		this.players = 1;
		Board player_1 = new Board();
		Board player_2 = new Board();
		Board player_3 = new Board();
		Board player_4 = new Board();
		this.boardList = new Board[MAX_PLAYERS];
		this.boardList[PLAYER_1_INDEX] = player_1;
		this.boardList[PLAYER_2_INDEX] = player_2;
		this.boardList[PLAYER_3_INDEX] = player_3;
		this.boardList[PLAYER_4_INDEX] = player_4;
		this.locationMarket = new LocationMarket();
		this.banditMarket = new BanditMarket(players);
		this.tradeShipMarket = new TradeShipMarket(players);
		this.gameWindow = gameWindow;
		this.locationPlacementBuffer = new ArrayList<Location>();
	}
	
	/**
	 * Function for preparing the launch of a game of Dice City.  This function creates a ConnectingWindow, which is the main
	 * way that players indicate they are ready to start the game.
	 */
	public void setUpNewGame(){
		//Pre-roll all dice for the start of the game.
		for(Board board: boardList){
			board.rollDice(); 
		}
		//Shuffle location market
		this.locationMarket.shuffleLocationDeck();
		//System.out.println("SU: Location market deck shuffled.");

		//Display connecting window.
		ConnectingWindow setUpWindow = new ConnectingWindow(this);
		setUpWindow.displayWindow();
		
		while(setUpWindow.isConnected() == false){
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//System.out.println("Waiting for game to connect...");
		}
		setUpWindow.updateText();
		
		DiceCityClient gameStartChecker = new DiceCityClient(serverIP, port);
		
		//Until the game is started, update connecting window text 
		while(gameStartChecker.requestCheckIfGameOpen()){
			try {
				setUpWindow.updateText();
				this.players = setUpWindow.getPlayerCount();
				TimeUnit.SECONDS.sleep(5);
				System.out.println("Checking if game is closed.");
			} catch (InterruptedException e) {
				System.out.println("Error while waiting to check if game closed during setup: "+e);
			}
		}//End of while
		
		this.gameWindow.loadGameScreen(this);
		//System.out.println("SU: Game view loaded.");
		this.gameWindow.updateDiceDisplay(this);
		//System.out.println("SU: Starting value of diceUsed() generated as " + getPlayerBoard(playerNumber).checkIfDiceAreUsed());
		
		setUpWindow.getFrame().dispose();
	}
	
	/**
	 * The main loop of the game.  Controls the order and progression of the turns.
	 */
	public void playGame(){
		System.out.println("Begining play...");
		useDicePhase();
	}
	
	/**
	 * In this phase, players select and use their dice in any order they wish.
	 */
	public void useDicePhase(){
		DiceCityClient turnRequester = new DiceCityClient(serverIP, port);
		turnRequester.requestTurn();
		
		while(turnRequester.getTurn() != this.playerNumber){
			try {
				System.out.println("The current turn from player "+this.playerNumber+"'s perspective is "+turnRequester.getTurn());
				TimeUnit.SECONDS.sleep(7);
				turnRequester.requestTurn();
			} catch (InterruptedException e) {
				System.out.println("Interrupted while waiting for next turn: "+e);
			}
		}
		
		this.currentTurn = turnRequester.getTurn();
		
		gameWindow.enableUseDicePhaseListeners();

	}
	
	/**
	 * In his phase, players use any swords they have collected in the previous phase to attack bandits or
	 * other players.
	 */
	public void attackPhase(){
		System.out.println("Attack Phase Begun...");
		//Remove dice from UI
		gameWindow.disableUseDicePhaseListeners();
		gameWindow.changeDiceVisibilityTo(false);
		gameWindow.enableAttackPhaseListeners(this);

		if(this.getPlayerBoard(playerNumber).getSwords() <= 2){
			this.buildPhase();
		}
	}
	
	/**
	 * In this phase players use the resources gather in the useDicePhace to build locations or buy trade
	 * ships.
	 */
	public void buildPhase(){
		System.out.println("Build Phase Begun...");
		gameWindow.disableAttackPhaseListeners(this);
		this.getPlayerBoard(playerNumber).loseExcessSwords();
		gameWindow.updateResourceCounters(this);
		gameWindow.enableBuildPhaseListeners(this);

	}
	
	/**
	 * In this phase players reroll their dice for the next turn as well as forfeit any extra resources they
	 * did not spend except 1 of each type.
	 */
	public void endPhase(){
		//Reset to the "default" use dice step
		gameWindow.disableBuildPhaseListeners(this);
		this.getPlayerBoard(playerNumber).loseExcessResources();
		this.getPlayerBoard(playerNumber).rollDice();
		gameWindow.updateDiceDisplay(this);
		gameWindow.updateResourceCounters(this);
		gameWindow.changeDiceVisibilityTo(true);
		
		DiceCityClient turnEnder = new DiceCityClient(serverIP, port);
		turnEnder.tellTurnEnded(this.playerNumber);
		
		while(this.currentTurn == this.playerNumber){
			turnEnder.requestTurn();
			currentTurn = turnEnder.getTurn();
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		useDicePhase();
		
	}
	
	/**
	 * Returns the location market object that is contained within this game.
	 * @return The instance of this game's LocationMarket object
	 */
	public LocationMarket getLocationMarket(){
		return this.locationMarket;
	}
	
	/**
	 * Returns the bandit market contained in this board.
	 * @return The bandit market associated with the board object.
	 */
	public BanditMarket getBanditMarket(){
		return this.banditMarket;
	}
	
	/**
	 * Returns the TradeShipMarket object that is contained within this game.
	 * @return The instance of the TradeShipMarket class associated with this game.
	 */
	public TradeShipMarket getTradeShipMarket(){
		return this.tradeShipMarket;
	}
	
	/**
	 * A method to return the list of player boards present in the game.
	 * @return the list of player boards.
	 */
 	public Board[] getPlayerBoards(){
		return boardList;
	}
	
	/**
	 * Returns the board of the indicated player. The passed parameter is the number of the player between
	 * 1 and 4.
	 * @param playerNumber The number to indicate which player board is desired. Must be between 1 and 4.
	 * @return The board of the corresponding player.
	 */
	public Board getPlayerBoard(int playerNumber){
		int playerIndex = playerNumber - 1;
		
		if(playerIndex >= 0 && playerIndex <= MAX_PLAYERS){
			return boardList[playerIndex];
		}else{
			return null;
		}
	}
	
	/**
	 * Returns the locationPlacementBuffer list of the DiceCityGame object it is called on.
	 * @return the locaitonPlacementBuffer list.
	 */
	public ArrayList<Location> getLocationPlacementBuffer(){
		return this.locationPlacementBuffer;
	}
	
	/**
	 * Adds the passed location to the location placement buffer.
	 * @param location the location to be added to the buffer.
	 */
	public void addLocationToPlacementBuffer(Location location){
		this.locationPlacementBuffer.add(location);
	}

	
	/**
	 * Sets the value of the playerNumber variable, telling which player out of four this game is.
	 * @param playerNumber The number between [1, 4] that this game is.
	 */
	public void setPlayerNumber(int playerNumber){
		this.playerNumber = playerNumber;
	}
	
	public int getPlayerNumber(){
		return this.playerNumber;
	}
	
	/**
	 * Sets the server IP
	 * @param ip the ip as a string.
	 */
	public void setServerIP(String ip){
		this.serverIP = ip;
	}
	
	/**
	 * Sets the server port value
	 * @param port the port of the server.
	 */
	public void setServerPort(int port){
		this.port = port;
	}
}
