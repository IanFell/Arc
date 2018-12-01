package ammo;

import java.awt.Color;
import java.awt.Graphics;

import applet.ARC_APPLET;
import gameobjects.GameObject;

/**
 * 
 * @author Fabulous Fellini
 *
 */
public class Lazer extends GameObject {

	/**
	 * Constructor.
	 * 
	 * @param double x
	 * @param double y
	 * @param double width
	 * @param double height
	 * @param Color  color
	 * @param int    startAngle
	 * @param int    arcAngle
	 */
	public Lazer(double x, double y, double width, double height, Color color, int startAngle, int arcAngle) {
		super(x, y, width, height);
		this.color      = color;
		this.startAngle = startAngle;
		this.arcAngle   = arcAngle;
	}

	/**
	 * 
	 * @param g
	 */
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.LIGHT_GRAY);
		g.fillRoundRect((int) x, (int) y, (int) width, (int) height, startAngle, arcAngle);
		g.setColor(Color.GREEN);
		g.fillRoundRect((int) x, (int) y + 25, (int) width, (int) height + 10, startAngle, arcAngle);
	}

	/**
	 * 
	 * @param ARC_APPLET arc_APPLET
	 */
	@Override
	public void update(ARC_APPLET arc_APPLET){
		y += 2;
		if(height < 100) {
			height++;
		}
	}
} 
