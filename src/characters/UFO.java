package characters;

import java.awt.Color;
import java.awt.Graphics;

import applet.ARC_APPLET;

/**
 * 
 * @author Fabulous Fellini
 *
 */
public class UFO {
	private int x; 
	private int y; 
	private int width; 
	private int height; 
	private Color ufoColor;
	private double dx = 1;
	private double dy = 1;
	
	/**
	 * Constructor.
	 * 
	 * @param int   x
	 * @param int   y
	 * @param int   width
	 * @param int   height
	 * @param Color starColor
	 */
	public UFO(int x, int y, int width, int height, Color ufoColor){
		this.x        = x;
		this.y        = y;
		this.width    = width;
		this.height   = height;
		this.ufoColor = ufoColor;
	}
		
	/**
	 * 
	 * @param Graphics g
	 */
	public void draw(Graphics g){
		g.setColor(Color.LIGHT_GRAY);
		g.fillOval(x, y, width, height);
		
		g.setColor(Color.red);
		g.fillRect(x - 15, y + (height /2 ), width / 3, height / 3);
		g.fillRect(x + width - 15, y + (height / 2), width / 3, height / 3);
		
		g.setColor(Color.DARK_GRAY);
		g.fillOval(x + 20, y + 5, width - 40, height / 2);
		
		g.setColor(Color.BLACK);
		g.fillOval(x + 35, y + 5, 10, 15);
		g.fillOval(x + 55, y + 5, 10, 15);
		
		g.setColor(Color.red);
		g.fillRect(x - 16, y + (height / 2), height / 3, width / 3);
		g.fillRect(x + width + 12, y + (height / 2), height / 3, width / 3);
		
		g.fillRect(x + width / 2 - 2, y + height, height / 3, width / 3);
		
		g.fillOval(x - 29, y + (height / 2) + 20, 20, 20);
		g.fillOval(x + width + 12, y + (height / 2) + 20, 20, 20);
		
		g.setColor(Color.RED);
		g.fillOval(x + 37, y + 7, 3, 3);
		g.fillOval(x + 41, y + 7, 3, 3);
		
		g.fillOval(x + 57, y + 7, 3, 3);
		g.fillOval(x + 61, y + 7, 3, 3);
	}
	
	/**
	 * 
	 * @param ARC_APPLET arc_APPLET
	 */
	public void update(ARC_APPLET arc_APPLET) {
		if(x < 100)
			dx = 1;
		if(x >= arc_APPLET.APPLET_WIDTH - 200)
			dx = -1;
		if(y < 100)
			dy = 1;
		if(y >= arc_APPLET.APPLET_HEIGHT - 300)
			dy = -1;
		x += dx;
		y += dy;
	}

	/**
	 * 
	 * @return Color
	 */
	public Color getUfoColor() {		
		return ufoColor;
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
