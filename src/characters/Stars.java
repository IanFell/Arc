package characters;

import java.awt.Color;
import java.awt.Graphics;

import applet.ARC_APPLET;

/**
 * 
 * @author Fabulous Fellini
 *
 */
public class Stars {
	private int x; 
	private int y; 
	private int width; 
	private int height; 
	public double dy;
	private Color starColor;
	
	/**
	 * Constructor.
	 * 
	 * @param int   x
	 * @param int   y
	 * @param int   width
	 * @param int   height
	 * @param Color starColor
	 */
	public Stars(int x, int y, int width, int height, Color starColor){
		this.x         = x;
		this.y         = y;
		this.width     = width;
		this.height    = height;
		this.starColor = starColor;
	}
		
	/**
	 * 
	 * @param Graphics g
	 */
	public void draw(Graphics g){
		g.setColor(starColor);
		g.fillRect(x, y, width, height);
	}
	
	/**
	 * 
	 * @param ARC_APPLET arc_APPLET
	 */
	public void update(ARC_APPLET arc_APPLET) {
		y += 1;
	}

	/**
	 * 
	 * @return int
	 */
	public int getX() {
		return x;
	}

	/**
	 * 
	 * @param int x
	 */
	public void setX(int x) {
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
}
