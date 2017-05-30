package edu.truman.cs260.bengtson.falling_shapes_game;
import java.awt.*;

/**
 * Last Edited: 4/16/2015
 * @author cpb2622 (Chase Bengtson)
 *
 */
public interface MoveableShape
{
	/**
	 * Moves the shape.
	 */
    void move();
    
    /**
     * Draws the shape.
     * @param g2 the graphics to use to draw the shape.
     */
    void draw(Graphics2D g2);
    
    /**
     * Move the upper left corner of the bounding box of this shape to (x,y).
     * @param x the x-coordinate.
     * @param y the y-coordinate.
     */
    void moveTo(int x, int y);
    
    /**
     * Determine if this shape contains the point p.
     * @param p the point to be checked for.
     * @return true if this shape contains the point and false otherwise.
     */
    boolean contains(Point p);
    
    /**
     * Mark this shape as currently being dragged.
     */
    void setDragging();
    
    /**
     * Mark this shape as not currently being dragged.
     */
    void clearDragging();
    
    /**
     * Returns whether or not the shape is draggable.
     * @return true if draggable, false otherwise.
     */
    boolean seeDragging();
    
    /**
     * Is this shape currently visible in a component of the specified sized?
     * @param componentWidth the width of the specified component.
     * @param componentHeight the height of the specified component.
     * @return true if the shape is visible, and false otherwise.
     */
    boolean onScreen();
}
