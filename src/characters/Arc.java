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
public class Arc extends GameObject {
	
	private int stopMovingValue = 0;

	/**
	 * Constructor.
	 * 
	 * @param double x
	 * @param double y
	 * @param double width
	 * @param double height
	 * @param int    startAngle
	 * @param int    arcAngle
	 */
	public Arc(double x, double y, double width, double height, int startAngle, int arcAngle){
		super(x, y, width, height);
		this.startAngle = startAngle;
		this.arcAngle   = arcAngle;
		dx              = 0;
		speed           = 5;
	}

	/**
	 * 
	 * @param Graphics g
	 */
	@Override
	public void draw(Graphics g){
		Color red = Color.RED;
		g.setColor(red);
		g.fillArc((int) x, (int) y, (int) width, (int) height, startAngle, arcAngle);

		int positionOffsetValue = 5;
		int sizeOffsetValue     = 10;
		g.setColor(Color.WHITE);
		g.fillArc(
				(int) x + positionOffsetValue, 
				(int) y + positionOffsetValue, 
				(int) width - sizeOffsetValue, 
				(int) height - sizeOffsetValue, 
				startAngle, 
				arcAngle
				);
		g.setColor(red);
		positionOffsetValue = 10;
		sizeOffsetValue     = 15;
		g.fillArc(
				(int) x + positionOffsetValue, 
				(int) y + positionOffsetValue, 
				(int) width - sizeOffsetValue, 
				(int) height - sizeOffsetValue, 
				startAngle, 
				arcAngle
				);
	}

	/**
	 * 
	 * @param ARC_APPLET arc_APPLET
	 */
	@Override
	public void update(ARC_APPLET arc_APPLET) {
		if (x + dx >= ARC_APPLET.SCREEN_RIGHT_BOUNDARY) {			
			dx = stopMovingValue;
		}	
		else if (x + dx <= ARC_APPLET.SCREEN_LEFT_BOUNDARY) {				
			dx = stopMovingValue;								
		}
		else {
			x += dx;	
		}
	}

	public void moveLeft() {
		dx -= speed;	
	}

	public void moveRight() {
		dx += speed;		
	}

	public void stopMoving() {
		dx = stopMovingValue;
	}
}
