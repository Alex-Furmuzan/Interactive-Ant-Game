package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

/**
 * This class contains the Ant Object. It extends the Movable class since it
 * is movable, along with implementing ISteerable to give the user the ability to steer the ant.
 * @author Alex Furmuzan
 */

public class Ant extends Movable implements ISteerable {

	private static Ant theAnt; // Singleton Design
	private GameWorld gw;
	private int maximumSpeed;
	private int foodLevel;
	private int foodConsumptionRate;
	private int healthLevel;
	private int lastFlagReached;
	private int maxHealthLevel;
	
	/**
	 * Constructor for the Ant.
	 */
	private Ant(GameWorld gw) {
		super(75);
		this.gw = gw;
		this.healthLevel = 10;
		this.maxHealthLevel = 10;
		//accelerate the ant beyond its maximumSpeed are to be ignored
		this.setSpeed(5);
		this.setHeading(0);
		this.maximumSpeed = this.healthLevel;
		this.foodLevel = 100;
		this.foodConsumptionRate = 5;
		this.lastFlagReached = 1;
		this.setColor(255, 0, 0); // COLOR OF ANT IS RED.
	}
	
	public static Ant getAnt(GameWorld gww) {
		if (theAnt == null) {
			theAnt = new Ant(gww);
		}
		return theAnt;
	}
	/**
	 * Move method for the Ant
	 */
	public void move(int elapsedTime) {
		// check the boundary to make sure it does not go out of bounds.
		this.boundaryCheck();
		// converts angle to radians and set new X and Y
		float angleInRadian = (float) Math.toRadians(90-this.getHeading()); 
		double deltaX = Math.cos(angleInRadian) * this.getSpeed() / elapsedTime;
		double deltaY = Math.sin(angleInRadian) * this.getSpeed() / elapsedTime;
		Point newPoint = new Point();
		newPoint.setX((float) (Math.round((this.getLocationX() + deltaX)*10.0)/10.0));
		newPoint.setY((float) (Math.round((this.getLocationY() + deltaY)*10.0)/10.0));
		
		// sets new point.
		this.setLocation(newPoint);
	}
	/**
	 * Gets maxHealthLevel
	 * 
	 * @return Returns the max health level of this ant
	 */
	public int getMaxHealthLevel() {
		return maxHealthLevel;
	}
	/**
	 * Gets current Health Level.
	 * 
	 * @return Returns health level
	 */
	public int getHealthLevel() {
		return healthLevel;
	}
	/**
	 * Gets the maximum speed
	 * 
	 * @return Returns the maximum speed the ant can go
	 */
	public int getMaxSpeed() {
		return maximumSpeed;
	}
	/**
	 * Get the food Consumption Rate.
	 * 
	 * @return Returns the food consumption rate
	 */
	public int getFoodConsumptionRate() {
		return this.foodConsumptionRate;
	}
	/**
	 * Gets food level
	 * 
	 * @return Returns food level
	 */
	public int getFoodLevel() {
		return foodLevel;
	}
	/**
	 * Gets the last flag reached
	 * 
	 * @return Returns last flag reached by the ant
	 */
	public int getLastFlagReached() {
		return lastFlagReached;
	}
	/**
	 * Sets the health level to v
	 * 
	 * @param v The value of what to set the health to
	 */
	public void setHealthLevel(int v) {
		healthLevel = v;
	}
	/**
	 * Sets the last flag reached to v.
	 * 
	 * @param v The value to set the last flag reached to.
	 */
	public void setLastFlagReached(int v) {

		this.lastFlagReached = v;
	}
	/**
	 * Sets food level to v.
	 * 
	 * @param v The value to set the food level to
	 */
	public void setFoodLevel(int v) {
		foodLevel = v;
	}
	/**
	 * Sets the maximum speed based on the currents ants health.
	 */
	public void setMaximumSpeedBasedOnHealth() {
		maximumSpeed = healthLevel;
	}
	/**
	 * Resets the ant to default values.
	 * Not resetting its location or last flag reached.
	 */
	public void resetAnt() {
		this.setColor(0, 0, 0);
		this.setSpeed(5);
		this.healthLevel = 10;
		this.maximumSpeed = this.getHealthLevel();
		this.foodLevel = 100;
	}
	/**
	 * Consumes the ants food by the consumption rate.
	 */
	public void consumeFood() {
		this.foodLevel -= this.foodConsumptionRate;
	}
	/**
	 * Decreases the ants health level.
	 * Sets the maximum speed based on its health.
	 */
	public void decreaseHealthLevel() {
		healthLevel--;
		this.setSpeed(this.getSpeed() - 1);
		setMaximumSpeedBasedOnHealth();
	}
	/**
	 * Turns left setting the heading to -5
	 */
	public void turnLeft() {
		this.setHeading(this.getHeading()-5);
		// Makes sure you can only go 0 through 360
		if (this.getHeading() < 0) {
			this.setHeading(360 + this.getHeading());
		}
	}
	/**
	 * Turns right setting the heading to +5
	 */
	public void turnRight() {
		this.setHeading(this.getHeading()+5);
		// Makes sure you can only go 0 through 360
		if (this.getHeading() > 360) {
			this.setHeading(this.getHeading()-360);
		}
	}
	public void draw(Graphics g, Point pCmpRelPrnt) {
		// TODO Auto-generated method stub
		g.setColor(this.getColor());
		int x = (int) (this.getLocationX() + pCmpRelPrnt.getX());
		int y = (int) (this.getLocationY() + pCmpRelPrnt.getY());
		int radius = this.getSize()/2;
		g.fillArc(x, y, 2*radius, 2*radius, 0, 360);
	}
	/**
	 * toString method for the Ant.
	 * 
	 * @return Statistics for Ant.
	 */
	public String toString() {
		String result = "Ant: ";
		result += "loc= " + this.getLocationX() + ", " + this.getLocationY() + " "; 
		result += "color= " + "[" + ColorUtil.red(this.getColor()) + ", " + ColorUtil.green(this.getColor()) + ", " + ColorUtil.blue(this.getColor()) + "] ";
		result += "heading= " + this.getHeading() + " ";
		result += "speed= " + this.getSpeed() + " ";
		result += "size= " + this.getSize() + " ";
		result += "maxSpeed= " + this.getMaxSpeed() + " ";
		result += "foodConsumptionRate= " + this.getFoodConsumptionRate()+ " ";
		
		return result;
	}

	/**
	 * This method returns whether or not the ant collided with an object.
	 */
	public boolean collidesWith(GameObject otherObject) {

		boolean result = false;
		double thisCenterX = this.getLocationX() + (this.getSize()/2);
		double thisCenterY = this.getLocationY() + (this.getSize()/2);
		
		double otherCenterX = otherObject.getLocationX() + (((GameObject) otherObject).getSize()/2);
		double otherCenterY = otherObject.getLocationY() + (((GameObject) otherObject).getSize()/2);
		
		double dx = thisCenterX - otherCenterX;
		double dy = thisCenterY - otherCenterY;
		

		double distBetweenCentersSqr = (dx * dx + dy * dy);
		int thisRadius= this.getSize() / 2;
		int otherRadius= otherObject.getSize() / 2;
		int radiiSqr= (thisRadius * thisRadius + 2 * thisRadius * otherRadius + otherRadius * otherRadius);
		
		if (distBetweenCentersSqr <= radiiSqr) { 
			result = true;
		}
		return result;
		
	}

	/**
	 * This method handles collision between an ant and other objects.
	 */
	public void handleCollision(GameObject otherObject) {

		if (otherObject instanceof FoodStation) {
			if (((FoodStation) otherObject).getCapacity() > 0) {
				gw.collisionFoodStation((FoodStation) otherObject);
			}
		} 
		if (otherObject instanceof Spider) {
			gw.collisionSpider();
		} 
		if (otherObject instanceof Flag) {
			gw.collisionFlags(((Flag) otherObject).getSequenceNumber());
		}
		
	}
}
