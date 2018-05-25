package characters;

import java.awt.Color;
import java.awt.Graphics;

import applet.ARC_APPLET;

/**
 * 
 * @author Fabulous Fellini
 *
 */
public class Ball {
	private int x; 
	private int y; 
	private int width; 
	private int height; 
	private Color ballColor;
	
	/**
	 * Constructor.
	 * 
	 * @param int   x
	 * @param int   y
	 * @param int   width
	 * @param int   height
	 * @param Color ballColor
	 */
	public Ball(int x, int y, int width, int height, Color ballColor){
		this.x         = x;
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
		g.fillOval(x, y, width, height);
	}
	
	/**
	 * 
	 * @param ARC_APPLET arc_APPLET
	 */
	public void update(ARC_APPLET arc_APPLET) {
		if(arc_APPLET.EASY == true){
			y += 4;
		}
		
		if(arc_APPLET.MEDIUM == true){
			y += 5;
		}
		
		if(arc_APPLET.HARD == true){
			y += 6;
		}
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
	 * @param Color ballColor
	 */
	public void setBallColor(Color ballColor) {
		this.ballColor = ballColor;
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
