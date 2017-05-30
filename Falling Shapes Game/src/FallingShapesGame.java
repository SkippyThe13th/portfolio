import edu.truman.cs260.bengtson.falling_shapes_game.*;

import java.util.*;

import javax.swing.*;
import javax.swing.Timer;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Last Edited: 4/16/2015
 * @author cpb2622 (Chase Bengtson)
 * A program to make a window of falling squares and circles that randomly spawn.
 */
public class FallingShapesGame 
{
	final static int CONTAINER_WIDTH = 1000;//The width of the container Icon.
	final static int CONTAINER_HEIGHT = 700;//The height of the container Icon.
	
	final static int ICON_X = 0;//The x position of the Icon.
	final static int ICON_Y = 0;//The y position of the Icon.
	
	final static int FPS = 41;//The number of times the Icon is updated per second.
	final static int PER_SECOND = 1000;//The rate at which shapes attempt to spawn per second.
	
	static int shapesMade = 0;
	
	
	/**
	 * The main method.  Contains the Timers that control the program.
	 * @param args
	 */
	public static void main(String[] args) 
	{	
		//Create frame to hold the GameArea.
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//frame.setUndecorated(true);
		
		//Create GameArea container to put in the frame.
		final GameArea container = new GameArea(CONTAINER_WIDTH, CONTAINER_HEIGHT);
		
		//Create Button to put in the frame.
		final JButton startButton = new JButton();
		startButton.setText("Start Game");
		
		//Create Text Field to put in the frame.
		final JTextField textField = new JTextField();
		textField.setText("Press the button to start.");
		
		//Create Panel to place the button and textfield in.
		JPanel userInterface = new JPanel();
		userInterface.add(startButton);
		userInterface.add(textField);
		
		//Add the Components to the frame.
		frame.add(container, BorderLayout.CENTER);
		frame.add(userInterface, BorderLayout.SOUTH);
		
		//Create ActionListener for shape-moving Timer.
		final ActionListener moveShapes = new
				ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				container.moveShapes();
				
				if(shapesMade > 0)
				{
				double performance = container.checkPerformance(shapesMade);
				
				if(performance >= .9)
				{textField.setText("Excellent");}
				else if(performance >= .8 && performance < .9)
				{textField.setText("Good");}
				else if(performance >= .7 && performance < .8)
				{textField.setText("Fair");}
				else if(performance >= .6 && performance < .7)
				{textField.setText("Poor");}
				else if(performance < .6)
				{textField.setText("Fail");}
				}
				
				container.repaint();
			}
		};

		//Create ActionListener to attach to shape-making Timer.
		final ActionListener makeShape = new
				ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				Random spawnChance = new Random();
				if(spawnChance.nextInt(4) == 0)
				{
					container.addOneShape();
					shapesMade += 1;
					container.repaint();
				}
			}
		};
		
		//Create and start Timer to create new shapes.
		final Timer shapeMaker = new Timer(PER_SECOND, makeShape);
		
		//Create and start Timer to move shapes.
		final Timer shapeMover = new Timer(FPS, moveShapes);
		
		//Create listener for when button is clicked.
		ActionListener startGame = new
				ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				shapeMaker.start();
				shapeMover.start();
			}
		};
		
		//Add start listener to button.
		startButton.addActionListener(startGame);
		
		//Make frame visible.
		frame.setVisible(true);
	}

}
