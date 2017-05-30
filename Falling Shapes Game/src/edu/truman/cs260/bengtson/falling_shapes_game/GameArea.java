package edu.truman.cs260.bengtson.falling_shapes_game;

import edu.truman.cs260.bengtson.falling_shapes_game.SimpleShape;
import javax.swing.Icon;
import javax.swing.JComponent;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.*;

/**
 * Last Edited: 4/16/2015
 * @author cpb2622 (Chase Bengtson)
 * An Icon that contains many shapes intended to fall down the icon.
 */
public class GameArea extends JComponent
{
    static final int SHAPE_WIDTH = 50; //The width of any created shapes.
    static final int SHAPE_HEIGHT = 50; //The height of any created shapes.
    private int width; //The width of the ShapeContainer object.
    private int height; //The height of the ShapeContainer object.
    private Point mousePoint; //The location of the mouse when it is pressed.
    
    //List of shapes to be added to the container.
    private ArrayList<SimpleShape> shapeList;
    
    //Random number generator used to choose type of shape created.
    private Random shapeChooser = new Random();
    
    /**
     * Constructs a new ShapeContainer object.
     * @param containerWidth the width of the container.
     * @param containerHeight the height of the container.
     */
    public GameArea(int containerWidth, int containerHeight)
    {
    	this.width = containerWidth;
    	this.height = containerHeight;
    	shapeList = new ArrayList<SimpleShape>();
    	
    	//Add listener for mouse being clicked.
    	addMouseListener(new
    			MouseAdapter()
    	{
    		public void mousePressed(MouseEvent event)
    		{
	    		mousePoint = event.getPoint();
    			
    		    for(SimpleShape s: shapeList)
    		    {
    		    	if(s.contains(mousePoint)) 
    		    	{
    		    		s.setDragging();
    		    		break;
    		    	}
    		    }
    		}
    	});
    	
    	addMouseListener(new
    			MouseAdapter()
    	{
    		public void mouseReleased(MouseEvent event)
    		{
    			for(SimpleShape s: shapeList)
    			{
    				s.clearDragging();
    			}
    		}
    	});
    	
    	//Add listener for mouse being moved.
    	addMouseMotionListener(new
    			MouseMotionAdapter()
    	{
    		public void mouseDragged(MouseEvent event)
    		{
    			if(mousePoint == null) return;
    			
    			for(SimpleShape s: shapeList)
    			{
        			double x = event.getX();
        			double y = event.getY();
    				
    				if(s.seeDragging() == true)
    				{
    					s.moveTo((int)x, (int)y);
    					repaint();
    				}
    			}
    		    
    		}
    	});
    	
    }
    
    /**
     * Adds a new shape object to the list of shapes to draw on the Icon.
     */
    public void addOneShape()
    {
    	int shape = shapeChooser.nextInt(2);
    	int xStart = shapeChooser.nextInt(width - SHAPE_WIDTH);
    	SimpleShape newShape = new SimpleShape(shape, xStart, SHAPE_WIDTH, SHAPE_HEIGHT);
    	
    	shapeList.add(newShape);
    }
    
    /**
     * Moves all shapes to be drawn on the icon down by one pixel.
     */
    public void moveShapes()
    {
    	for(SimpleShape s: shapeList)
    	{
    		s.move();
    	}
    }
    
    /**
     * Returns the height of the Icon.
     * @return the height of the Icon.
     */
    public int getIconHeight()
    {
    	return this.height;
    }
    
    /**
     * Returns the width of the Icon.
     * @return the width of the Icon.
     */
    public int getIconWidth()
    {
    	return this.width;
    }
    
    /**
     * Paints the ShapeContainer Icon.
     */
    public void paintComponent(Graphics g)
    {
    	Graphics2D g2 = (Graphics2D) g;
    	 
        for(SimpleShape s: shapeList)
    	{
    		 s.draw(g2);
        }
    }
    
    /**
     * Checks the current score of the player
     * @param shapesMade the amount of shapes that have been made since the game started.
     * @return the score of the player as a percentile.
     */
    public double checkPerformance(int shapesMade)
    {
    	double shapesMissed = 0;
    	double performanceScore;
    	
    	for(SimpleShape s: shapeList)
    	{
    		if(s.onScreen() == false)
    		{
    			shapesMissed = shapesMissed + 1;
    		}
    	}
    	//Calculate the performance percentage.
    	performanceScore = 1.0 - (shapesMissed/shapesMade);
    	
    	return performanceScore;
    }
}
