package com.mycompany.a3;
import java.util.Random;
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

/**
 * This is the abstract parent class that contains the Game Objects.
 * @author Alex Furmuzan
 */

public abstract class GameObject implements IDrawable,  ICollider {
	private int size;
	private Point location;
	private int color;
	private int mapWidth;
	private int mapHeight;
	
	
	/**
	 * Constructor for GameObject
	 */
	public GameObject() {
		Random random = new Random();
		mapWidth = 1669-200;
		mapHeight = 1215-200;
		
		float x = random.nextFloat() * mapWidth;
		float y = random.nextFloat() * mapHeight;
		x = (float) (Math.round(x*10.0)/10.0);
		y = (float) (Math.round(y*10.0)/10.0);
		location = new Point(x,y);
	}
	/**
	 * Constructor for GameObject that takes in a size and color
	 * 
	 * @param size Size of GameObject
	 * @param myColor Color of GameObject
	 */
	public GameObject(int size, int myColor) {
		mapWidth = 1669-200;
		mapHeight = 1215-200;
		color = myColor;
		this.size = size;
		Random random = new Random();
		float x = random.nextFloat() * mapWidth;
		float y = random.nextFloat() * mapHeight;
		x = (float) (Math.round(x*10.0)/10.0);
		y = (float) (Math.round(y*10.0)/10.0);
		
		location = new Point(x,y);
		
	}
	/**
	 * Constructor for GameObject that only takes in size
	 * 
	 * @param size Size for GameObject
	 */
	public GameObject(int size) {
		mapWidth = 1669-200;
		mapHeight = 1215-200;
		this.size = size;
		Random random = new Random();
		float x = random.nextFloat() * mapWidth;
		float y = random.nextFloat() * mapHeight;
		x = (float) (Math.round(x*10.0)/10.0);
		y = (float) (Math.round(y*10.0)/10.0);
		System.out.println(MapView.getMHeight());
		location = new Point(x,y);
		
	}
	/**
	 * Gets the locations x value.
	 * 
	 * @return Returns the x value of the location.
	 */
	public float getLocationX() {
		return this.getLocation().getX();
	}
	/**
	 * Gets the locations y value.
	 * 
	 * @return Returns the y value of the location.
	 */
	public float getLocationY() {
		return this.getLocation().getY();
	}
	/**
	 * Gets the size of this game object.
	 * 
	 * @return Returns the size of this game object.
	 */
	public int getSize() {
		return size;
	}
	/**
	 * Get the color of this game object.
	 * 
	 * @return Returns the color of this game object.
	 */
	public int getColor() {
		return color;
	}
	/**
	 * Get the location of this game object.
	 * 
	 * @return Returns the location of this game object. (in Point form)
	 */
	public Point getLocation() {
		return location;
	}
	/**
	 * Sets the location of this game object.
	 * 
	 * @param xy location for this game object to be set to.
	 */
	public void setLocation(Point xy) {
		location = xy;
	}
	/**
	 * Sets the color of this game object.
	 * 
	 * @param r Red value
	 * @param g Green value
	 * @param b Blue value
	 */
	public void setColor(int r, int g, int b) {
		color = ColorUtil.rgb(r, g, b);
	}
	public boolean collidesWith(ICollider obj) {
		return false;
	}
	public void draw(Graphics g, Point pCmpRelPrnt) {
		
	}
	/**
	 * abstract toString method.
	 */
	public String toString() {
		return "";
	}
}
