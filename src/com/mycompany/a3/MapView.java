package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.Border;

/**
 * This class includes the display of the map and styles the center container.
 * It updates the observable GameWorld by showing the map.
 * @author Alex Furmuzan
 *
 */
public class MapView extends Container implements Observer{
	
	private GameWorld gw;
	public static int height;
	public static int width;
	/**
	 * Constructor for the MapView
	 */
	public MapView() {
		this.setWidth(width);
		MapView.height = this.getHeight();
		MapView.width = this.getWidth();
		this.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.rgb(255,0,0)));
		this.setLayout(new BorderLayout());
		this.getAllStyles().setBgTransparency(255);
	}
	
	/**
	 * Gets the maps height
	 * 
	 * @return Returns map height
	 */
	public static double getMHeight() {
		return (double) height;
	}
	/**
	 * Gets the maps width
	 * 
	 * @return Returns map width
	 */
	public static double getMWidth() {
		return (double) width;
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Point pCmpRelPrnt = new Point(this.getX(), this.getY());
		IIterator itr = gw.getCollection().getIterator();
		while(itr.hasNext()) {
			GameObject temp = itr.getNext();
			if(temp instanceof IDrawable) {
				((IDrawable)temp).draw(g, pCmpRelPrnt);
			}
						
		}
	}
	
	/**
	 * Handles pointer pressed logic.
	 */
	public void pointerPressed(int x, int y) {
		x = x - getParent().getAbsoluteX();
		y = y - getParent().getAbsoluteY();
		Point pPtrRelPrnt = new Point(x,y);
		Point pCmpRelPrnt = new Point(getX(), getY());
		if(gw.getPaused()) {
			gw.positionObject(pPtrRelPrnt, pCmpRelPrnt);
		}
		repaint();
	}
	
	/**
	 * TO be called automatically when notify observers is called
	 */
	
	public void update(Observable observable, Object data) {
		gw = (GameWorld) data;
		MapView.width = getWidth();
		MapView.height = getHeight();
		System.out.println("__________________________________________________________________________________________________");
		System.out.println("__________________________________________________________________________________________________");
		((GameWorld) data).showMap();

		this.repaint();
	}

}
