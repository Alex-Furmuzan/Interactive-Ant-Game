package com.mycompany.a3;

/**
 * ICollider interface to handle collisions.
 * @author Alex Furmuzan
 *
 */
public interface ICollider {
	public boolean collidesWith(GameObject otherObject);
	void handleCollision(GameObject otherObject);
}
