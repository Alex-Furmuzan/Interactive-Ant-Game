package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;

/**
 * This abstract class contains Fixed objects that are unable to move and extends GameObject.
 * @author Alex Furmuzan
 */

public abstract class Fixed extends GameObject implements ISelectable {
	private boolean isSelected;
	/**
	 * Constructor for Fixed game objects with size
	 * 
	 * @param size Size
	 */
	public Fixed(int size) {
		super(size);
		isSelected = false;
	}
	/**
	 * Constructor for Fixed game objects with size and color
	 * 
	 * @param size Size
	 * @param color Color
	 */
	public Fixed(int size, int color) {
		super(size, color);
	}
	
	/**
	 * Sets the selected value of a fixed object.
	 */
	public void setSelected(boolean b) {
		isSelected = b;
	}
	
	/**
	 * Returns whether an object is selected.
	 */
	public boolean isSelected() {
		return isSelected;
	}
	
	/**
	 * Abstract methods for fixed objects.
	 */
	public abstract void draw(Graphics g, Point pCmpRelPrnt);
	public abstract boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt);
	
}
