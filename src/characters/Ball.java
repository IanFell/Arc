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
public class Ball extends GameObject {
	
	/**
	 * Constructor.
	 * 
	 * @param double x
	 * @param double y
	 * @param double width
	 * @param double height
	 * @param Color  color
	 */
	public Ball(double x, double y, double width, double height, Color color){
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
		if(arc_APPLET.easy == true){
			y += 4;
		}
		if(arc_APPLET.medium == true){
			y += 5;
		}
		if(arc_APPLET.hard == true){
			y += 6;
		}
	}
	
	/**
	 * 
	 * @param Color ballColor
	 */
	public void setBallColor(Color color) {
		this.color = color;
	}
}
