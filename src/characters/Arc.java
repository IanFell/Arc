package characters;

import java.awt.Color;
import java.awt.Graphics;

import applet.ARC_APPLET;

/**
 * 
 * @author Fabulous Fellini
 *
 */
public class Arc {
	private int x; 
	private int y; 
	private int width; 
	private int height; 
	private int startAngle; 
	private int arcAngle;
	private double dx = 0;

	/**
	 * Constructor.
	 * 
	 * @param int x
	 * @param int y
	 * @param int width
	 * @param int height
	 * @param int startAngle
	 * @param int arcAngle
	 */
	public Arc(int x, int y, int width, int height, int startAngle, int arcAngle){
		this.x          = x;
		this.y          = y;
		this.width      = width;
		this.height     = height;
		this.startAngle = startAngle;
		this.arcAngle   = arcAngle;
	}

	/**
	 * 
	 * @param Graphics g
	 */
	public void draw(Graphics g){
		g.setColor(Color.RED);
		g.fillArc(x, y, width, height, startAngle, arcAngle);

		g.setColor(Color.WHITE);
		g.fillArc(x+5, y+5, width-10, height-10, startAngle, arcAngle);

		g.setColor(Color.RED);
		g.fillArc(x+10, y+10, width-15, height-15, startAngle, arcAngle);
	}

	/**
	 * 
	 * @param ARC_APPLET arc_APPLET
	 */
	public void update(ARC_APPLET arc_APPLET) {
		if (x + dx >= arc_APPLET.APPLET_WIDTH - 100) {			
			dx = 0;
		}	
		else if (x + dx <= 0) {				
			dx = 0;								
		}
		else {
			x += dx;	
		}
	}

	/**
	 * Move player left.
	 */
	public void moveLeft() {
		dx -= 5;	
	}

	/**
	 * Move player right.
	 */
	public void moveRight() {
		dx += 5;		
	}

	/**
	 * Stop player from moving.
	 */
	public void stopMoving() {
		dx = 0;
	}

	/**
	 * 
	 * @return int
	 */
	public int getX(){
		return x;
	}
	
	/**
	 * 
	 * @param int x
	 */
	public void setX(int x){
		this.x = x;
	}
	
	/**
	 * 
	 * @return int
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * 
	 * @param int y
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * 
	 * @return int
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * 
	 * @param int width
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * 
	 * @return int
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * 
	 * @param int height
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * 
	 * @return int
	 */
	public int getStartAngle() {
		return startAngle;
	}

	/**
	 * 
	 * @param int startAngle
	 */
	public void setStartAngle(int startAngle) {
		this.startAngle = startAngle;
	}

	/**
	 * 
	 * @return int
	 */
	public int getArcAngle() {
		return arcAngle;
	}

	/**
	 * 
	 * @param int arcAngle
	 */
	public void setArcAngle(int arcAngle) {
		this.arcAngle = arcAngle;
	}
}
