package gameobjects;

import java.awt.Color;
import java.awt.Graphics;

import applet.ARC_APPLET;

public class GameObject {
	
	protected double x;
	protected double y;
	protected double width;
	protected double height;
	
	protected double dx;
	protected double dy = 1;
	
	protected int speed;
	
	protected int startAngle;
	protected int arcAngle;
	
	protected Color color;
	
	/**
	 * Constructor.
	 * 
	 * @param double x
	 * @param double y
	 * @param double width
	 * @param double height
	 */
	public GameObject (double x, double y, double width, double height) {
		this.x      = x;
		this.y      = y;
		this.width  = width;
		this.height = height;
	}
	
	/**
	 * 
	 * @param g
	 */
	public void draw(Graphics g) {}
	
	/**
	 * 
	 * @param ARC_APPLET arc_APPLET
	 */
	public void update(ARC_APPLET arc_APPLET) {}
	
	/**
	 * 
	 * @return int
	 */
	public double getX(){
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
	public double getY() {
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
	public double getWidth() {
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
	public double getHeight() {
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
	 * @return Color 
	 */
	public Color getBallColor() {		
		return color;
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
