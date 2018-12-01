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
public class BlackHole extends GameObject {
	
	/**
	 * Constructor.
	 * 
	 * @param double x
	 * @param double y
	 * @param double width
	 * @param double height
	 * @param Color  color
	 */
	public BlackHole(double x, double y, double width, double height, Color color) {
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
		g.fillOval((int) x, (int) y, (int) width, (int) height);
	}
	
	/**
	 * 
	 * @param ARC_APPLET arc_APPLET
	 */
	@Override
	public void update(ARC_APPLET arc_APPLET) {
		double size = 0.5d;
		width       += size;
		height      += size;
		y           += 3;				
	}
}
