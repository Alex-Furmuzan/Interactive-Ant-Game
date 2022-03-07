package com.mycompany.a3;

/**
 * This interface is used by the Ant class to control its steering left and right.
 * @author Alex Furmuzan
 */

public interface ISteerable {

	/**
	 * Turn Left for this game object.
	 */
	public void turnLeft();
	/**
	 * Turn right for this game object.
	 */
	public void turnRight();
}
