package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;

/**
 * IDrawable interface to handle drawing of objects.
 * @author Alex Furmuzan
 *
 */
public interface IDrawable {
	 public void draw(Graphics g, Point pCmpRelPrnt);
}
