package com.mycompany.a3;

/**
 * This class contains the movable objects and extends Game Object. It includes boundchecking and movement logic.
 * @author Alex Furmuzan
 */

public abstract class Movable extends GameObject{
	private int heading;
	private int speed;
	private int mapWidth;
	private int mapHeight;
	
	/**
	 * Constructor for Movable objects with size
	 *  
	 * @param size Size
	 * @param color Color
	 */
	public Movable(int size) {
		super(size);
		mapWidth = 1669;
		mapHeight = 1215;
	}
	/**
	 * Constructor for Movable objects with size and color
	 *  
	 * @param size Size
	 * @param color Color
	 */
	public Movable(int size, int color) {
		super(size, color);
		mapWidth = 1669;
		mapHeight = 1215;
	}
	/**
	 * Abstract move method.
	 */
	public void move(int elapsedTime) {
		
	}
	/**
	 * Gets the current Heading of this game object.
	 * 
	 * @return Returns the heading
	 */
	public int getHeading() {
		return heading;
	}
	/**
	 * Gets the speed of this game object
	 * 
	 * @return Returns the speed
	 */
	public int getSpeed() {
		return speed;
	}
	/**
	 * Sets the objects speed
	 * 
	 * @param v Value to set the speed to
	 */
	public void setSpeed(int v) {
		speed = v;
	}
	/**
	 * Sets this objects heading. Has checks to make sure it stays in range.
	 * 
	 * @param v Value to set the heading to
	 */
	public void setHeading(int v) {
		heading = v;
		// makes sure the heading does not go below 0 and stays in 0-360 range.
		if (this.getHeading() < 0) {
			heading = 360 + v;
		}
		// makes sure the heading does not go above 360 and stays in 0-360 range.
		if (this.getHeading() > 359) {
			heading = v - 360;
		}
	}
	/**
	 * Makes sure the game object does not go out of bounds.
	 */
	public void boundaryCheck() {
		// if we go out of bounds on the X axis, we want to change the objects heading to do a 180 (turn around)
		if (this.getLocationX()+10 >= mapWidth || this.getLocationX()-10 <= 0.0) {
			this.setHeading(this.getHeading()+180);
		}
		// if we go out of bounds on the Y axis, we want to change the objects heading to do a 180 (turn around)
		if (this.getLocationY()+10 >= mapHeight || this.getLocationY()-10 <= 0.0) {
			this.setHeading(this.getHeading()+180);
		}
	}
	
}
