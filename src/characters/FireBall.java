package characters;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import applet.ARC_APPLET;

/**
 * 
 * @author Fabulous Fellini
 *
 */
public class FireBall {
	private int x; 
	private int y; 
	private int width; 
	private int height; 
	private Color ballColor;
	public static float frame = 0;
	public Image FIRE_BALL;
	public URL url;
	
	/**
	 * Constructor.
	 * 
	 * @param int   x
	 * @param int   y
	 * @param int   width
	 * @param int   height
	 * @param Color ballColor
	 */
	public FireBall(int x, int y, int width, int height, Color ballColor){
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
		int tester = (int)(frame + .2);
		if (tester < 3)
			frame += .2;
		else frame = 0;
		y -= 8;
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
