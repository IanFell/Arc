package ammo;

import java.awt.Color;
import java.awt.Graphics;

import applet.ARC_APPLET;

/**
 * 
 * @author Fabulous Fellini
 *
 */
public class Lazer{
	private int x;
	private int y;
	private int width;
	private int height;
	private int startAngle;
	private int arcAngle;
	private Color lazerColor;

	/**
	 * Constructor.
	 * 
	 * @param int   x
	 * @param int   y
	 * @param int   width
	 * @param int   height
	 * @param Color lazerColor
	 * @param int   startAngle
	 * @param int   arcAngle
	 */
	public Lazer(int x, int y, int width, int height, Color lazerColor, int startAngle, int arcAngle) {
		this.x          = x;
		this.y          = y;
		this.width      = width;
		this.height     = height;
		this.lazerColor = lazerColor;
		this.startAngle = startAngle;
		this.arcAngle   = arcAngle;
	}

	/**
	 * 
	 * @param g
	 */
	public void draw(Graphics g) {
		g.setColor(Color.LIGHT_GRAY);
		g.fillRoundRect(x, y, width, height, startAngle, arcAngle);
		g.setColor(Color.GREEN);
		g.fillRoundRect(x, y+25, width, height+10, startAngle, arcAngle);
	}

	/**
	 * 
	 * @param ARC_APPLET arc_APPLET
	 */
	public void update(ARC_APPLET arc_APPLET){
		y+=2;
		if(height<100) {
			height++;
		}
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
	/**
	 * 
	 * @return int
	 */
	public Color getLazerColor() {
		return lazerColor;
	}

	/**
	 * 
	 * @param Color lazerColor
	 */
	public void setLazerColor(Color lazerColor) {
		this.lazerColor = lazerColor;
	}
} 
