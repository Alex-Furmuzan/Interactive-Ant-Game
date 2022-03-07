package com.mycompany.a3;
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

/**
 * This class contains the Flag object that extends the fixed objects since it is motionless.
 * @author Alex Furmuzan
 */

public class Flag extends Fixed{
	private int sequenceNumber;
	
	/**
	 * Constructor for Flags with a sequence number.
	 * 
	 * @param sNumber Sequence Number for this flag to be constructed with
	 */
	public Flag(int sNumber) {
		super(55, ColorUtil.BLUE);
		this.sequenceNumber = sNumber;	
	}
	/**
	 * Gets the sequence number for this Flag.
	 * 
	 * @return Returns sequence Number
	 */
	public int getSequenceNumber() {
		return sequenceNumber;
	}
	/**
	 * Override so we cannot set the color
	 */
	@Override
	public void setColor(int r, int g, int b) {
		
	}
	public void draw(Graphics g, Point pCmpRelPrnt) {
		// TODO Auto-generated method stub
		g.setColor(this.getColor());
		int x = (int) (this.getLocationX() + pCmpRelPrnt.getX());
		int y = (int) (this.getLocationY() + pCmpRelPrnt.getY());
		int[] xPoints = { x, (x - this.getSize()), (x + this.getSize()) };
		int[] yPoints = { (y + this.getSize()), (y - this.getSize()), (y - this.getSize()) };
		
		//int[] xPoints = { x, (x - this.getSize()), (x + this.getSize()) };
		//int[] yPoints = { (y - this.getSize()), (y + this.getSize()), (y + this.getSize()) };
		
		int nPoints = 3;
		
		if(isSelected()) {
			g.drawPolygon(xPoints, yPoints, nPoints);
		}
		else {
			g.fillPolygon(xPoints, yPoints, nPoints);
		}

		g.setColor(ColorUtil.BLACK);
		g.drawString(""+this.getSequenceNumber(), x-10, y-25);
		
	}
	/**
	 * Stats for Flags.
	 */
	public String toString() {
		String result = "Flag " + this.getSequenceNumber() + ": ";
		result += "loc= " + this.getLocationX() + ", " + this.getLocationY() + " "; 
		result += "color= " + "[" + ColorUtil.red(this.getColor()) + ", " + ColorUtil.green(this.getColor()) + ", " + ColorUtil.blue(this.getColor()) + "] ";
		result += "size= " + this.getSize() + " ";
		result += "seqNum= " + this.getSequenceNumber();
		
		return result;
	}
	@Override
	public boolean collidesWith(GameObject otherObject) {
		// TODO Auto-generated method stub
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
	@Override
	public void handleCollision(GameObject otherObject) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt) {
		// TODO Auto-generated method stub
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
