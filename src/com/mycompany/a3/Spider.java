package com.mycompany.a3;

import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

/**
 * This class contains the Spider Object. It extends the Movable class since it
 * is movable.
 * @author Alex Furmuzan
 */
public class Spider extends Movable {

	Random random = new Random();
	
	/**
	 * Constructor for spider.
	 */
	public Spider() {
		
		super(new Random().nextInt(61)+20, ColorUtil.GRAY);
		this.setSpeed(random.nextInt(6)+5);
		this.setHeading(random.nextInt(360));
	}
	/**
	 * Move method for spider
	 */
	public void move(int elapsedTime) {
		//set random heading
		randomHeading();
		// convert angle to radians
		float angleInRadian = (float) Math.toRadians(90-this.getHeading()); 
		// create new X and Y
		double deltaX = Math.cos(angleInRadian) * this.getSpeed() / elapsedTime;//* (elapsedTime/1000);
		double deltaY = Math.sin(angleInRadian) * this.getSpeed() / elapsedTime;//* (elapsedTime/1000);
		Point newPoint = new Point();
		newPoint.setX((float) (Math.round((this.getLocationX() + deltaX)*10.0)/10.0));
		newPoint.setY((float) (Math.round((this.getLocationY() + deltaY)*10.0)/10.0));
		
		//set new location based of random heading.
		this.setLocation(newPoint);
	}
	/**
	 * Override so we cannot set the color of a spider after it has been created
	 */
	@Override
	public void setColor(int r, int g, int b) {
		
	}
	/**
	 * Randomizes the heading for the spider to change its heading by +5 or -5
	 */
	private void randomHeading() {
		
		//checks its boundary to make sure its in bounds.
		this.boundaryCheck();
		int rand = random.nextInt(2);
		
		//random between 0 and 1 to change its heading.
		if(rand == 1) {
			this.setHeading(this.getHeading()+5);
			// makes sure the heading does not go above 360 and stays in its range
			if (this.getHeading() > 360) {
				this.setHeading(this.getHeading()-360);
			}
		}
		else {
			this.setHeading(this.getHeading()-5);
			// makes sure the heading does not go below 0 and stays in its range
			if (this.getHeading() < 0) {
				this.setHeading(360 + this.getHeading());
			}
		}
	}
	/**
	 * Statistics for spider
	 */
	public String toString() {
		String result = "Spider: ";
		result += "loc= " + this.getLocationX() + ", " + this.getLocationY() + " "; 
		result += "color= " + "[" + ColorUtil.red(this.getColor()) + ", " + ColorUtil.green(this.getColor()) + ", " + ColorUtil.blue(this.getColor()) + "] ";
		result += "heading= " + this.getHeading() + " ";
		result += "speed= " + this.getSpeed() + " ";
		result += "size= " + this.getSize();
		return result;
	}
	
	/**
	 * Draws the spiders on the screen.
	 */
	public void draw(Graphics g, Point pCmpRelPrnt) {
		g.setColor(this.getColor());
		int x = (int) (this.getLocationX() + pCmpRelPrnt.getX());
		int y = (int) (this.getLocationY() + pCmpRelPrnt.getY());
		
		int[] xPoints = { x, (x - this.getSize()), (x + this.getSize()) };
		int[] yPoints = { (y + this.getSize()), (y - this.getSize()), (y - this.getSize()) };
		int nPoints = 3;
		g.drawPolygon(xPoints, yPoints, nPoints);
	}
	
	/**
	 * Tells whether or not a spider collided with another object.
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
	
	public void handleCollision(GameObject otherObject) {
		
		
	}
}
