package characters;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import applet.ARC_APPLET;
import gameobjects.GameObject;

/**
 * 
 * @author Fabulous Fellini
 *
 */
public class FireBall extends GameObject {
	
	public static float frame = 0;
	public Image FIRE_BALL;
	public URL url;
	
	/**
	 * Constructor.
	 * 
	 * @param double x
	 * @param double y
	 * @param double width
	 * @param double height
	 * @param Color  color
	 */
	public FireBall(double x, double y, double width, double height, Color color){
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
		int tester = (int)(frame + .2);
		if (tester < 3)
			frame += .2;
		else frame = 0;
		y -= 8;
	}
}
