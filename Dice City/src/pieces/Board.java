package pieces;

import location.Location;
import location.Forest;
import location.Mountain;
import location.Cave;
import location.SmallHouse;
import location.Traveller;
import location.Militia;

/**
 * The board is what contains everything belonging to a player other than their victory point cards, resources in their stockpile, and their physical
 * victory points.
 * @author Chase Bengtson
 *
 */
public class Board {
	//Numbers of rows in the grid on the board.
	final static int WHITE_ROW = 0;
	final static int YELLOW_ROW = 1;
	final static int RED_ROW = 2;
	final static int BLUE_ROW = 3;
	final static int BLACK_ROW =4;
	//The grid used to represent the contents of the city on the board.  It has 5 rows (colors) 
	//and 6 columns (numbers)
	private Location[][] city = new Location[5][6];
	//Create d6 objects that will be rolled.
	private D6 white = new D6(WHITE_ROW);
	private D6 yellow = new D6(YELLOW_ROW);
	private D6 red = new D6(RED_ROW);
	private D6 blue = new D6(BLUE_ROW);
	private D6 black = new D6(BLACK_ROW);
	//The resource values currently held by the player(board)
	private int wood = 0;
	private int stone = 0;
	private int metal = 0;
	private int swords = 0;
	//The Victory Points currently held by the player(board)
	private int vp = 0;
	//The pass tokens currently held by the player
	private int passTokens = 0;
	
	/**
	 * Empty constructor
	 */
	public Board(){
		//Assign default locations
		city[0][0] = new Forest();
		city[0][1] = new Mountain();
		city[0][2] = new Cave();
		city[0][3] = new Forest();
		city[0][4] = new Mountain();
		city[0][5] = new Cave();
		city[1][0] = new Mountain();
		city[1][1] = new Militia();
		city[1][2] = new SmallHouse();
		city[1][3] = new Traveller();
		city[1][4] = new Militia();
		city[1][5] = new Forest();
		city[2][0] = new Cave();
		city[2][1] = new Militia();
		city[2][2] = new Traveller();
		city[2][3] = new Militia();
		city[2][4] = new SmallHouse();
		city[2][5] = new Mountain();
		city[3][0] = new Forest();
		city[3][1] = new SmallHouse();
		city[3][2] = new Militia();
		city[3][3] = new Traveller();
		city[3][4] = new Militia();
		city[3][5] = new Cave();
		city[4][0] = new Mountain();
		city[4][1] = new Cave();
		city[4][2] = new Forest();
		city[4][3] = new Mountain();
		city[4][4] = new Cave();
		city[4][5] = new Forest();
		
	}
	
	/**
	 * Rolls all 5 dice the board contains.  Should be performed at the end of every turn.
	 */
	public void rollDice(){
		this.white.roll();
		this.yellow.roll();
		this.red.roll();
		this.blue.roll();
		this.black.roll();
	}
	
	/**
	 * Returns how much wood the board possesses
	 * @return wood The amount of wood.
	 */
	public int getWood(){
		return this.wood;
	}
	
	/**
	 * Returns the amount of stone the board possesses
	 * @return stone The amount of stone.
	 */
	public int getStone(){
		return this.stone;
	}
	
	/**
	 * Returns the amount of metal the board possesses
	 * @return metal The amount of metal.
	 */
	public int getMetal(){
		return this.metal;
	}
	
	/**
	 * Returns the amount of victory points the board possesses.
	 * @return vp The amount of victory points.
	 */
	public int getVP(){
		return this.vp;
	}
	
	/**
	 * Returns the amount of swords the board possesses.
	 * @return swords The amount of swords.
	 */
	public int getSwords(){
		return this.swords;
	}
	
	/**
	 * Returns the amount of pass tokens the board possesses.
	 * @return passTokens The amount of pass tokens.
	 */
	public int getPassTokens(){
		return this.passTokens;
	}
	
	/**
	 * Returns the 2D array that contains the locaitons comprising the city.
	 * @return the 2D array of locations.
	 */
	public Location[][] getCity(){
		return this.city;
	}
	
	/**
	 * Returns the location that is currently occupying the indicated index of the city.
	 * @param x The index of the row the location is in.
	 * @param y The index of the column the location is in.
	 * @return The corresponding location.
	 */
	public Location getLocation(int x, int y){
		return this.city[x][y];
	}
	
	/**
	 * Returns the value of the white die assigned to the board.
	 * @return the value of the white die
	 */
	public int getWhiteDieValue(){
		return white.getValue();
	}
	
	/**
	 * Returns the value of the yellow die assigned to the board.
	 * @return the value of the yellow die.
	 */
	public int getYellowDieValue(){
		return yellow.getValue();
	}
	
	/**
	 * Returns the value of the red die assigned to the board.
	 * @return the value of the red die.
	 */
	public int getRedDieValue(){
		return red.getValue();
	}
	
	/**
	 * Returns the value of the blue die assigned to the board.
	 * @return the value of the blue die.
	 */
	public int getBlueDieValue(){
		return blue.getValue();
	}
	
	/**
	 * Returns the value of the black die assigned to the board.
	 * @return 
	 */
	public int getBlackDieValue(){
		return black.getValue();
	}
	
	/**
	 * Retrieves the D6 object that corresponds to the indicated row.
	 * @param row The row associated with the desired D6 object.
	 * @return the associated D6 object
	 */
	public D6 getDieFromRow(int row){
		if(row == 0){
			return this.white;
		}else if(row == 1){
			return this.yellow;
		}else if(row == 2){
			return this.red;
		}else if(row == 3){
			return this.blue;
		}else if(row == 4){
			return this.black;
		}else{
			return null;
		}
	}
	
	/**
	 * Checks if all the dice for this board have been used.
	 * @return true if all 5 dice have been used, false otherwise.
	 */
	public boolean checkIfDiceAreUsed(){
		boolean diceUsed = false;
		
		if(this.white.isUsed() && this.yellow.isUsed() && this.red.isUsed() &&
				this.blue.isUsed() && this.black.isUsed()){
			diceUsed = true;
		}
		
		return diceUsed;
	}
	
	/**
	 * Adds the specified amount of wood to the stored value of the board.
	 * @param amountToAdd The amount of wood to add.
	 */
	public void addAmountOfWood(int amountToAdd){
		this.wood += amountToAdd;
		//System.out.println("Player has " + Integer.toString(this.wood) + " wood.");
	}
	
	/**
	 * Adds the specified amount of metal to the stored value of the board.
	 * @param amountToAdd The amount of metal to add.
	 */
	public void addAmountOfMetal(int amountToAdd){
		this.metal += amountToAdd;
		//System.out.println("Player has " + Integer.toString(this.metal) + " metal.");
	}
	
	/**
	 * Adds the specified amount of stone to the stored value of the board.
	 * @param amountToAdd The amount of stone to add.
	 */
	public void addAmountOfStone(int amountToAdd){
		this.stone += amountToAdd;
		//System.out.println("Player has " + Integer.toString(this.stone) + " stone.");
	}
	
	/**
	 * Adds the specified amount of swords to the stored value of the board.
	 * @param amountToAdd The amount of swords to add.
	 */
	public void addAmountOfSwords(int amountToAdd){
		this.swords += amountToAdd;
		//System.out.println("Player has " + Integer.toString(this.swords) + " swords.");
	}
	
	public void addAmountOfVP(int amountToAdd){
		this.vp += amountToAdd;
		//System.out.println("Player has " + this.vp + " victory points");
	}

	/**
	 * All but 1 wood, stone, or metal are discarded, as only 1 of each resource may be kept between turns.
	 */
	public void loseExcessResources(){
		if(this.wood > 1){
			this.wood = 1;
		}
		if(this.stone > 1){
			this.stone = 1;
		}
		if(this.metal > 1){
			this.metal = 1;
		}
	}

	/**
	 * Resets the count of the board's swords to 0.  Swords not used by the end of the attack step are lost.
	 */
	public void loseExcessSwords(){
		this.swords = 0;
	}
	
}