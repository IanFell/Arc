package characters;

import java.awt.Color;
import java.awt.Graphics;

import applet.ARC_APPLET;

/**
 * 
 * @author Fabulous Fellini
 *
 */
public class BlackHole {
	private int x; 
	private int y; 
	private double width;
	private double height;
	private Color ballColor;
	
	/**
	 * 
	 * @param int    x
	 * @param int    y
	 * @param double width
	 * @param double height
	 * @param ballColor
	 */
	public BlackHole(int x, int y, double width, double height, Color ballColor){
		this.x        = x;
		this.y         = y;
		this.width     = width;
		this.height    = height;
		this.ballColor = ballColor;
	}
		
	/**
	 * 
	 * @param Graphics g
	 */
	public void draw(Graphics g){
		g.setColor(ballColor);
		g.fillOval(x, y, (int) width, (int) height);
	}
	
	/**
	 * 
	 * @param ARC_APPLET arc_APPLET
	 */
	public void update(ARC_APPLET arc_APPLET) {
		width  += .5;
		height += .5;
		y      += 3;				
	}

	/**
	 * 
	 * @return Color
	 */
	public Color getBallColor() {		
		return ballColor;
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
	 * @return double
	 */
	public double getWidth() {
		return width;
	}

	/**
	 * 
	 * @param double width
	 */
	public void setWidth(double width) {
		this.width = width;
	}

	/**
	 * 
	 * @return double
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * 
	 * @param double height
	 */
	public void setHeight(double height) {
		this.height = height;
	}
}
