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
public class UFO extends GameObject {
	
	/**
	 * Constructor.
	 * 
	 * @param double x
	 * @param double y
	 * @param double width
	 * @param double height
	 * @param Color  color
	 */
	public UFO(double x, double y, double width, double height, Color color){
		super(x, y, width, height);
		this.color = color;
		dx         = 1;
		speed      = 1;
	}
		
	/**
	 * 
	 * @param Graphics g
	 */
	@Override
	public void draw(Graphics g){
		g.setColor(Color.LIGHT_GRAY);
		g.fillOval((int) x, (int) y, (int) width, (int) height);
		
		Color red = Color.RED;
		g.setColor(red);
		g.fillRect((int) x - 15, (int) (y + (height /2 )), (int) width / 3, (int) height / 3);
		g.fillRect((int) (x + width - 15), (int) (y + (height / 2)), (int) width / 3, (int) height / 3);
		
		g.setColor(Color.DARK_GRAY);
		g.fillOval((int) x + 20, (int) y + 5, (int) width - 40,(int) height / 2);
		
		g.setColor(Color.BLACK);
		g.fillOval((int) x + 35, (int) y + 5, 10, 15);
		g.fillOval((int) x + 55, (int) y + 5, 10, 15);
		
		g.setColor(red);
		g.fillRect((int) x - 16, (int) (y + (height / 2)), (int) height / 3, (int) width / 3);
		g.fillRect((int) (x + width + 12), (int) (y + (height / 2)), (int) height / 3, (int) width / 3);
		
		g.fillRect((int) (x + width / 2 - 2), (int) (y + height), (int) height / 3, (int) width / 3);
		
		g.fillOval((int) x - 29, (int) (y + (height / 2)) + 20, 20, 20);
		g.fillOval((int) (x + width + 12), (int) (y + (height / 2)) + 20, 20, 20);
		
		g.fillOval((int) x + 37, (int) y + 7, 3, 3);
		g.fillOval((int) x + 41, (int) y + 7, 3, 3);
		
		g.fillOval((int) x + 57, (int) y + 7, 3, 3);
		g.fillOval((int) x + 61, (int) y + 7, 3, 3);
	}
	
	/**
	 * 
	 * @param ARC_APPLET arc_APPLET
	 */
	@Override
	public void update(ARC_APPLET arc_APPLET) {
		if(x < 100)
			dx = speed;
		if(x >= ARC_APPLET.APPLET_WIDTH - 200)
			dx = -speed;
		if(y < 100)
			dy = 1;
		if(y >= ARC_APPLET.APPLET_HEIGHT - 300)
			dy = -speed;
		x += dx;
		y += dy;
	}
}
