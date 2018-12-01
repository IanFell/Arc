package characters;

import java.awt.Color;
import java.awt.Graphics;

import applet.ARC_APPLET;
import gameobjects.GameObject;

/**
 * 
 * @author Fabulous Fellini
 *
 */
public class Stars extends GameObject {
	
	/**
	 * Constructor.
	 * 
	 * @param double x
	 * @param double y
	 * @param double width
	 * @param double height
	 * @param Color  color
	 */
	public Stars(double x, double y, double width, double height, Color color){
		super(x, y, width, height);
		this.color = color;
	}
		
	/**
	 * 
	 * @param Graphics g
	 */
	@Override
	public void draw(Graphics g){
		g.setColor(color);
		g.fillRect((int) x, (int) y, (int) width, (int) height);
	}
	
	/**
	 * 
	 * @param ARC_APPLET arc_APPLET
	 */
	@Override
	public void update(ARC_APPLET arc_APPLET) {
		y += dy;
	}
}
