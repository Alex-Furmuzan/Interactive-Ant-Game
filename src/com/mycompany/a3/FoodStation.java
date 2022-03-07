package com.mycompany.a3;

import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

/**
 * This class contains the FoodStation object that extends the fixed objects since it is motionless.
 * @author Alex Furmuzan
 */

public class FoodStation extends Fixed{
	private int capacity;
	Random random = new Random();
	/**
	 * Constructor for FoodStation
	 */
	public FoodStation() {
		super(new Random().nextInt(60)+20);
		capacity = this.getSize();
		this.setColor(0, 255, 0); // color of FoodStation is GREEN
	}
	/**
	 * Gets the capacity of this Food Station.
	 * 
	 * @return Returns the capacity 
	 */
	public int getCapacity() {
		return capacity;
	}
	/**
	 * Sets the capacity to v
	 * 
	 * @param v Value to set the capacity to
	 */
	public void setCapacity(int v) {
		capacity = v;
	}
	/**
	 * Statistics for FoodStation
	 */
	public String toString() {
		String result = "FoodStation: ";
		result += "loc= " + this.getLocationX() + ", " + this.getLocationY() + " "; 
		result += "color= " + "[" + ColorUtil.red(this.getColor()) + ", " + ColorUtil.green(this.getColor()) + ", " + ColorUtil.blue(this.getColor()) + "] ";
		result += "size= " + this.getSize() + " ";
		result += "capacity= " + this.getCapacity();
		
		return result;
	}

	/**
	 * Draws the foodstations.
	 */
	public void draw(Graphics g, Point pCmpRelPrnt) {
		
		g.setColor(this.getColor());
		int x = (int) (this.getLocationX() + pCmpRelPrnt.getX());
		int y = (int) (this.getLocationY() + pCmpRelPrnt.getY());
		
		if(isSelected()) {
			g.drawRect(x, y, this.getSize(), this.getSize());
		}
		else {
			g.fillRect(x, y, this.getSize(), this.getSize());
		}
		
		g.setColor(ColorUtil.BLACK);
		g.drawString(""+this.getCapacity(), x, y);
	}
	@Override
	public void handleCollision(GameObject otherObject) {
	}
	
	/**
	 * Tells whether a collision happened between food stations and other objects.
	 */
	public boolean collidesWith(GameObject otherObject) {
		boolean result = false;
		double thisCenterX = this.getLocationX() + (this.getSize()/2);
		double thisCenterY = this.getLocationY() + (this.getSize()/2);
		
		double otherCenterX = ((GameObject) otherObject).getLocationX() + (((GameObject) otherObject).getSize()/2);
		double otherCenterY = ((GameObject) otherObject).getLocationY() + (((GameObject) otherObject).getSize()/2);
		
		double dx = thisCenterX - otherCenterX;
		double dy = thisCenterY - otherCenterY;
		

		double distBetweenCentersSqr = (dx * dx + dy * dy);
		int thisRadius= this.getSize() / 2;
		int otherRadius= ((GameObject) otherObject).getSize() / 2;
		int radiiSqr= (thisRadius * thisRadius + 2 * thisRadius * otherRadius + otherRadius * otherRadius);
		
		if (distBetweenCentersSqr <= radiiSqr) { 
			result = true;
		}
		return result;
	}
	
	/**
	 * Checks whether object is already in a collision.
	 */
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt) {
		
		int width = this.getSize();
		int height = this.getSize();
		int px = (int) pPtrRelPrnt.getX();
		int py = (int) pPtrRelPrnt.getY();
		
		int xLoc = (int) (pCmpRelPrnt.getX() + this.getLocationX());
		int yLoc = (int) (pCmpRelPrnt.getY() + this.getLocationY());
		
		if ( (px >= xLoc) && (px <= xLoc+width) && (py >= yLoc) && (py <= yLoc+height) ) {
			return true;
		} else {
			return false;
		}
		
		
	}
}

