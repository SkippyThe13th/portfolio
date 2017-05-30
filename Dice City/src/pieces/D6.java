package pieces;
import java.util.Random;

/**
 * This is a six sided die that is allocated according to it's value in the grid contained on a player's board.  The row the d6 is placed in is
 * determined by the row variable passed in the constructor, which is also stored in a private variable.
 * @author Chase Bengtson
 *
 */
public class D6 {
	//Create generator for random numbers [1,6].
	Random generator = new Random();
	//Create private variable equal to the d6's current value (i.e. last roll) "roll" once to determine starting value.
	private int value = generator.nextInt(6) + 1;
	//Create boolean value to represent if the d6 has been "used" yet this turn.  True if it has been used, false if otherwise.
	private boolean used = false;
	//The row the d6 is placed in.
	private int row;
	//Set images of sides.
	private static String[][] diceFaces = new String[5][6];
	

	
	
	
	/**
	 * Constructor for a d6 object. Assigns row value based on the passed value.
	 * @param row The number of the row the d6 is assigned.  This should be a number between [1,5]
	 */
	public D6(int row){
		this.row = row;
		
		diceFaces[0][0] = "images/WhiteOne.png";
		diceFaces[0][1] = "images/WhiteTwo.png";
		diceFaces[0][2] = "images/WhiteThree.png";
		diceFaces[0][3] = "images/WhiteFour.png";
		diceFaces[0][4] = "images/WhiteFive.png";
		diceFaces[0][5] = "images/WhiteSix.png";
		
		diceFaces[1][0] = "images/YellowOne.png";
		diceFaces[1][1] = "images/YellowTwo.png";
		diceFaces[1][2] = "images/YellowThree.png";
		diceFaces[1][3] = "images/YellowFour.png";
		diceFaces[1][4] = "images/YellowFive.png";
		diceFaces[1][5] = "images/YellowSix.png";
		
		diceFaces[2][0] = "images/RedOne.png";
		diceFaces[2][1] = "images/RedTwo.png";
		diceFaces[2][2] = "images/RedThree.png";
		diceFaces[2][3] = "images/RedFour.png";
		diceFaces[2][4] = "images/RedFive.png";
		diceFaces[2][5] = "images/RedSix.png";
		
		diceFaces[3][0] = "images/BlueOne.png";
		diceFaces[3][1] = "images/BlueTwo.png";
		diceFaces[3][2] = "images/BlueThree.png";
		diceFaces[3][3] = "images/BlueFour.png";
		diceFaces[3][4] = "images/BlueFive.png";
		diceFaces[3][5] = "images/BlueSix.png";
		
		diceFaces[4][0] = "images/BlackOne.png";
		diceFaces[4][1] = "images/BlackTwo.png";
		diceFaces[4][2] = "images/BlackThree.png";
		diceFaces[4][3] = "images/BlackFour.png";
		diceFaces[4][4] = "images/BlackFive.png";
		diceFaces[4][5] = "images/BlackSix.png";
	}
	
	/**
	 * Randomly give the d6's value variable a new value between [1,6]
	 */
	public void roll(){
		this.value = generator.nextInt(6) + 1;
		this.used = false;
	}
	
	/**
	 * Returns the value of value.
	 * @return the current value of value, or the last number the d6 rolled.
	 */
	public int getValue(){
		return this.value;
	}
	
	/**
	 * Increases the d6's value by 1, essentially moving it right 1 space on the board.
	 */
	public void increase(){
		this.value = this.value + 1;
	}
	
	/**
	 * Decreases the d6's value by 1, essentially moving it left 1 space on the board.
	 */
	public void decrease(){
		this.value = this.value - 1;
	}
	
	/**
	 * Returns true if the die has been used, false otherwise.
	 * @return the contents of the used variable.
	 */
	public boolean isUsed(){
		return this.used;
	}
	
	/**
	 * Sets the used value of the die called on to true, to indicate it has been used for the turn.
	 */
	public void flagAsUsed(){
		this.used = true;
	}
	
	/**
	 * Sets the used value of the die called on to false, to indicate it has not been used for the turn.
	 */
	public void flagAsNotUsed(){
		this.used = false;
	}
	
	/**
	 * Returns the address in the specified index of the 2D list of die face images.
	 * @param row The index of the row. (Must be 0-4)
	 * @param column The index of the column. (Must be 0-5)
	 * @return The address in the specified index.
	 */
	public static String getFaceImage(int row, int column){
		return diceFaces[row][column];
	}
	
	
	
}
