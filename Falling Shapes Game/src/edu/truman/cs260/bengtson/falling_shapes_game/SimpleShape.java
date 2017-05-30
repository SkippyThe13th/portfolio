package edu.truman.cs260.bengtson.falling_shapes_game;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 * Last Edited: 4/16/2015
 * @author cpb2622 (Chase Bengtson)
 *
 */
public class SimpleShape implements MoveableShape
{
	private int yLocation; //The y position of the shape.
	private int theShape; //Whether the shape is a rectangle or ellipse.
	private int start; //The x position where the shape spawns.
	private int shapeWidth; //The width of the shape.
	private int shapeHeight; //The height of the shape.
	private boolean dragging; //Whether the shape is being dragged or not.
	
	/**
	 * Constructor for a new SimpleShape.
	 * @param whichShape determines whether an ellipse or rectangle is made.
	 * @param xStart the starting x value of the shape.
	 * @param width the width of the shape.
	 * @param height the height of the shape.
	 */
    public SimpleShape(int whichShape, int xStart, int width, int height)
    {
    	this.yLocation = 0;
    	this.theShape = whichShape;
    	this.start = xStart;
    	this.shapeWidth = width;
    	this.shapeHeight = height;
    	this.dragging = false;
    }
    
    /**
     * Moves the shape down one pixel.
     */
    public void move()
    {
    	if(this.dragging == false)
    	{
    	this.yLocation = this.yLocation + 1;
    	}
    }
    
    /**
     * Draws the shape.
     */
    public void draw(Graphics2D g2)
    {
    	g2.setColor(Color.BLACK);
		Ellipse2D.Double ellipse = 
				new Ellipse2D.Double(start, yLocation, shapeWidth, shapeHeight);
    	
		Rectangle2D.Double rectangle =
				new Rectangle2D.Double(start, yLocation, shapeWidth, shapeHeight);
    	
    	if(theShape == 0)
    	{
        	g2.fill(ellipse);
    	}
    	else
    	{    	
    		g2.fill(rectangle);
    	}

    }
    
    /**
     * Moves the shape to the specified location.
     * @param x the specified x-coordinate.
     * @param y the specified y-coordinate.
     */
    public void moveTo(int x, int y)
    {
    	this.yLocation = y - (shapeHeight/2);
    	this.start = x - (shapeHeight/2);
    }
    
    /**
     * Determines if a shape contains a certain point within it's bounding box.
     * @param p the specified point.
     */
    public boolean contains(Point p)
    {
    	boolean doesContain = false;
    	
    	Point shapeLocation = new Point(start, yLocation);
    	Point shapeOppositeCorner = new Point(start + shapeWidth, yLocation + shapeHeight);
    	
    	if(p.getX() >= shapeLocation.getX() && p.getX() <= shapeOppositeCorner.getX()
    			&& p.getY() >= shapeLocation.getY() && p.getY() <= shapeOppositeCorner.getY())
    	{
    		doesContain = true;
    	}
    	
    	return doesContain;
    }
    
    /**
     * Indicates a shape is currently being dragged.
     */
    public void setDragging()
    {
    	dragging = true;
    }
    
    /**
     * Indicates a shape is no longer being dragged.
     */
    public void clearDragging()
    {
    	dragging = false;
    }
    
    /**
     * Returns whether or not a shape is draggable.
     */
    public boolean seeDragging()
    {
    	return dragging;
    }

    /**
     * Tells whether a shape is on the screen or not.
     */
    public boolean onScreen()
    {
    	if(this.yLocation <= 700)
    	{
    	    return true;
    	}
    	
    	return false;
    }
    
}
