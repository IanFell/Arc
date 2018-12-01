package applet;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.Random;

import ammo.Lazer;
import characters.Arc;
import characters.Ball;
import characters.BlackHole;
import characters.FireBall;
import characters.Stars;
import characters.UFO;

/**
 * 
 * @author Fabulous Fellini
 *
 */
public class ARC_APPLET extends Applet implements KeyListener, Runnable {

	// Keep the compiler happy.
	private final static long serialVersionUID = 654321; 
	
	public final static int APPLET_WIDTH  = 600;
	public final static int APPLET_HEIGHT = 700;
	
	public final static int SCREEN_LEFT_BOUNDARY  = 0;
	public final static int SCREEN_RIGHT_BOUNDARY = APPLET_WIDTH - 100;
	
	private final static int SCREEN_CENTER = APPLET_WIDTH / 2;

	// Color of ball "planets".
	private Color colorOfBall;

	// Color of flashing square.
	private Color flashColor; 

	// Keeps track of fireBallArray
	private int fire = 0;

	// Keeps track of impacts with black holes, used to update life icons.
	private int impacts        = 0;
	private int ballImpacts    = 0;
	private boolean impact     = false;
	private boolean ballImpact = false;
	
	public boolean easy;
	public boolean medium;
	public boolean hard;
	
	private int gameState;
	private final int INTRO_SCREEN     = -1;
	private final int RULES_SCREEN     = 0;
	private final int GAME_PLAY_SCREEN = 1;
	private final int GAME_OVER_SCREEN = 2;
	private final int WINNING_SCREEN   = 3;
	
	private final int DIFFICULTY_LEVEL_ONE   = 5;
	private final int DIFFICULTY_LEVEL_TWO   = 10;
	private final int DIFFICULTY_LEVEL_THREE = 15;
	
	// This is for double buffering.
	private Image i;       
	private Graphics doubleG;
	URL url;
	
	// Images start at 0, 0.
	private int imageOriginValue = 0;
	
	private Color imageBackgroundColor = Color.WHITE;
	
	private String fontName = "Bauhaus 93";
	private int bold        = Font.BOLD;
	private int fontSize    = 50;
	private Color fontColor = Color.WHITE;

	// Player.
	private Arc arc = new Arc(APPLET_WIDTH / 2 - 60, APPLET_HEIGHT - 35, 100, 25, 135, 270);

	// Ball "planet" player has to catch.
	private Ball[] ballArray = new Ball[6];

	// Other game objects.
	private Stars[] starArray          = new Stars[100];
	private FireBall[] fireBallArray   = new FireBall[100];
	private BlackHole[] blackHoleArray = new BlackHole[2];
	private Lazer [] lazerArray        = new Lazer[2];
	private UFO ufo;

	// Use this to load images.
	private Toolkit tk = this.getToolkit();

	private AudioClip music;
	private AudioClip ballCollide;
	private AudioClip blackHoleCollide;		
	private AudioClip gameover;
	private AudioClip lazerhit;
	private AudioClip lazershot;
	private AudioClip winner;

	private Image ARC_LOGO;
	private Image ARC_RULES;
	private Image explosion;

	private void loadAudio(){
		URL musicsong = this.getClass().getResource("/Music/menu.au");
		music         = ARC_APPLET.newAudioClip(musicsong);

		URL bc      = this.getClass().getResource("/Music/ballcollision.au");
		ballCollide = ARC_APPLET.newAudioClip(bc);

		URL bhc     = this.getClass().getResource("/Music/blackholecollision.au");
		ballCollide = ARC_APPLET.newAudioClip(bhc);

		URL go      = this.getClass().getResource("/Music/gameover.au");
		ballCollide = ARC_APPLET.newAudioClip(go);

		URL lh      = this.getClass().getResource("/Music/lazerhit.au");
		ballCollide = ARC_APPLET.newAudioClip(lh);

		URL ls      = this.getClass().getResource("/Music/lazershot.au");
		ballCollide = ARC_APPLET.newAudioClip(ls);

		URL win     = this.getClass().getResource("/Music/lazershot.au");
		ballCollide = ARC_APPLET.newAudioClip(win);
	}

	private void loadImages(){
		URL ex    = this.getClass().getResource("/Images/explosion.png");
		explosion = tk.getImage(ex);

		URL al   = this.getClass().getResource("/Images/ARC_LOGO.png");
		ARC_LOGO = tk.getImage(al);

		URL ar    = this.getClass().getResource("/Images/ARC_RULES.png");
		ARC_RULES = tk.getImage(ar);
	}

	public void init(){			
		addKeyListener(this);
		loadAudio();
		loadImages();
		setSize(APPLET_WIDTH, APPLET_HEIGHT);
		setBackground(Color.BLACK);
		ufo   = new UFO(200, 200, 100, 25, Color.LIGHT_GRAY);
		easy  = true;
		music.loop();

		gameState = INTRO_SCREEN;

		// Creates new "planet" balls, with random x position and color.
		for(int i = 0; i < ballArray.length; i++){
			Random rand2         = new Random();   
			int randomColorValue = rand2.nextInt(6); 
			getBallColor(randomColorValue);
			Random xPosition  = new Random(); 
			int offset        = 50;
			ballArray[i]      = new Ball(
					xPosition.nextInt(APPLET_WIDTH - offset), 
					0, 
					offset, 
					offset, 
					colorOfBall
					);	

		}

		// Creates new Stars, with random x,y position. 
		for(int i = 0; i < starArray.length; i++){
			Random xPosition = new Random();
			Random yPosition = new Random(); 
			int size         = 1;
			starArray[i]     = new Stars(
					xPosition.nextInt(APPLET_WIDTH), 
					yPosition.nextInt(APPLET_HEIGHT), 
					size, 
					size, 
					Color.WHITE
					);
		}

		// Creates the fire balls.
		int size = 50;
		for(int i = 0; i < fireBallArray.length; i++){
			fireBallArray[i] = new FireBall(0, APPLET_HEIGHT, size, size, Color.WHITE);
		}

		// Creates the black holes.
		size = 10;
		for(int i = 0; i  <blackHoleArray.length; i++){
			Random xPosition  = new Random(); 
			blackHoleArray[i] = new BlackHole(xPosition.nextInt(APPLET_WIDTH), 0, size, size, Color.DARK_GRAY);
		}

		// Creates the lazers.
		for(int i = 0; i < lazerArray.length; i++){			
			lazerArray[i] = new Lazer(
					(ufo.getX() + ufo.getWidth())  / 2, 
					ufo.getY() + ufo.getHeight() + 50, 
					15, 
					size, 
					Color.GREEN, 
					70, 
					180
					);
		}
	}

	public void start(){
		Thread thread = new Thread(this);
		thread.start();
	}

	/**
	 * Game loop.
	 */
	@Override
	public void run() {
		while(true){
			arc.update(this);		
			for(int i = 0; i < starArray.length; i++){
				starArray[i].update(this);
			}
			for(int i = 0; i < starArray.length; i++){
				if(starArray[i].getY() > APPLET_WIDTH)
					starArray[i].setY(0);
			}
			for(int i = 0; i < ballArray.length; i++){
				ballArray[i].update(this);
			}
			for(int i = 0; i < blackHoleArray.length; i++){
				blackHoleArray[i].update(this);						
			}
			for(int i = 0; i < lazerArray.length; i++){
				lazerArray[i].update(this);
			}
			if(fire != 0) {
				fireBallArray[fire].update(this);
			}
			ufo.update(this);
			repaint(); 
			try{
				Thread.sleep(10);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param Graphcis g
	 */
	public void paint(Graphics g){													
		switch(gameState)
		{
		case INTRO_SCREEN:
			drawIntro(g);
			break;
		case RULES_SCREEN:
			drawRules(g);	
			break;
		case GAME_PLAY_SCREEN:
			drawStars(g);
			performGameLogic(g);
			break;
		case GAME_OVER_SCREEN:
			drawStars(g);
			drawGameOverScreen(g);						    		
			break;
		case WINNING_SCREEN:
			drawStars(g);						    		
			drawWinningScreen(g);
			break;
		}
	}

	/**
	 * Reset to original state for new game.
	 */
	public void resetGame(){
		easy        = true;
		medium      = false;
		hard        = false;
		impacts     = 0;
		ballImpacts = 0;
		gameState   = INTRO_SCREEN;
	}

	/**
	 * Start new game.
	 */
	public void newGame(){
		easy        = true;
		medium      = false;
		hard        = false;
		impacts     = 0;
		ballImpacts = 0;
		gameState   = GAME_PLAY_SCREEN;
	}

	/**
	 * 
	 * @param Graphics g
	 */
	public void performGameLogic(Graphics g){
		arc.draw(g);
		drawFirstBalls(g);			
		generateAllSubsequentBalls(g); 
		checkBallArcCollision(g);
		drawFireBalls(g);
		if (ballImpact) {
			ballImpacts++;
		}
		blackHoleArray[0].draw(g);				
		drawBlackHoles(g);					
		checkBlackHoleCollisionWithPlayer(g);
		if(impact) {
			impacts++;
		}
		drawLives(g);
		ufo.draw(g);
		lazerArray[0].draw(g);
		drawLazers(g);
		checkLazerCollision(g);
		drawScore(g);

		// Square flashes to show player which color ball to catch.
		drawFlashSquare(g);

		impact     = false;
		ballImpact = false;

		// Sets how fast balls are falling, and if player has won.
		setGameState();
	}

	/**
	 * Sets how fast balls are falling, and if player has won.
	 */
	public void setGameState() {
		if (ballImpacts == DIFFICULTY_LEVEL_ONE){
			easy   = false;
			medium = true;
			hard   = false;			
		}
		if (ballImpacts == DIFFICULTY_LEVEL_TWO) {
			easy   = false;
			medium = false;
			hard   = true;
		}
		if (ballImpacts == DIFFICULTY_LEVEL_THREE) {
			try{
				if(winner != null) {
					winner.play();
				}
			} catch(NullPointerException e){
				e.printStackTrace();
			}
			gameState = WINNING_SCREEN;
		}			
	}

	/**
	 * 
	 * @param int color
	 * @return Color
	 */
	private Color getBallColor(int color) {
		switch (color) {
		case 0:
			colorOfBall = Color.BLUE;
			break;
		case 1:
			colorOfBall = Color.GREEN;
			break;
		case 2:
			colorOfBall = Color.YELLOW;
			break;
		case 3:
			colorOfBall = Color.CYAN;
			break;
		case 4:
			colorOfBall = Color.MAGENTA;
			break;
		case 5:
			colorOfBall = Color.PINK;
			break;
		}
		return colorOfBall;
	}

	/**
	 * Checks collisions with lazer and player.
	 * 
	 * @param Graphics g
	 */
	private void checkLazerCollision(Graphics g) {
		for(int i = 0; i < lazerArray.length; i++){
			if(
					(lazerArray[i].getY()+lazerArray[0].getHeight() > arc.getY()) &&
					((lazerArray[i].getX() >= arc.getX()) && 
					(lazerArray[0].getX() <= (arc.getX()+arc.getWidth())))
					){		
				// Reset player score.
				ballImpacts = 0; 
				try{
					if(lazershot != null) {
						lazershot.play();
					}
				} catch(NullPointerException e){
					e.printStackTrace();
				}
			}
		}	
	}

	/**
	 * Draws lazers shooting from UFOs.
	 * 
	 * @param Graphics g
	 */
	private void drawLazers(Graphics g) {
		if(lazerArray[0].getY() >= APPLET_HEIGHT) {
			for(int i = 0; i < lazerArray.length; i++) {				
				lazerArray[i] = new Lazer(
						ufo.getX() + ufo.getWidth() / 2,  
						ufo.getY() + ufo.getHeight() + 30, 
						15, 
						10, 
						Color.WHITE, 
						70, 
						180
						);				
				for(int a = 0; a < lazerArray.length; a++){	
					lazerArray[a].draw(g);	
					try{
						if(lazerhit != null) {
							lazerhit.play();
						}
					} catch(NullPointerException e){
						e.printStackTrace();
					}
				} 					
			}		
		}
	}
	
	/**
	 * 
	 * @param Graphics g
	 */
	private void resetScoreSquare(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(APPLET_WIDTH - 130,  10,  200,  120);
	}
	
	/**
	 * 
	 * @param Graphics g
	 */
	private void resetColorSqure(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(0,  10,  130,  120);
	}

	/**
	 * 
	 * @param Graphics g
	 */
	public void drawWinningScreen(Graphics g) {
		drawStars(g);
		resetScoreSquare(g);
		resetColorSqure(g);
		fontSize  = 50;
		Font font = new Font(fontName, bold, fontSize);
		g.setFont(font);	
		g.setColor(fontColor);
		
		g.drawString("CONGRADULATIONS", SCREEN_CENTER - 240, 250);
		g.drawString("YOU HAVE SAVED THE", SCREEN_CENTER - 250, 300);
		g.drawString("WORLD FROM NUCLEAR", SCREEN_CENTER - 280, 350);
		g.drawString("DESTRUCTION", SCREEN_CENTER - 160, 400);

		fontSize = 25;
		font     = new Font(fontName, bold, fontSize);
		g.setFont(font);	
		g.drawString("NEW GAME?", SCREEN_CENTER - 60, 450);
		g.drawString("Y/N", SCREEN_CENTER - 10, 500);

		// Reset score to 0.
		ballImpacts = 0;
	}

	/**
	 * 
	 * @param Graphics g
	 */
	public void drawGameOverScreen(Graphics g){
		drawStars(g);
		resetScoreSquare(g);
		resetColorSqure(g);
		fontSize  = 50;
		Font font = new Font(fontName, bold, fontSize);
		g.setFont(font);	
		g.setColor(fontColor);
		
		g.drawString("GAME OVER", SCREEN_CENTER - 140, 250);

		fontSize   = 25;
		Font font2 = new Font(fontName, bold, fontSize);	
		g.setFont(font2);
		g.drawString("NEW GAME?", SCREEN_CENTER - 60, 300);
		g.drawString("Y/N", SCREEN_CENTER - 10, 350);

		// Reset score to 0.
		ballImpacts = 0;
	}

	/**
	 * 
	 * @param Graphics g
	 */
	public void drawIntro(Graphics g){
		g.drawImage(ARC_LOGO, imageOriginValue, imageOriginValue, imageBackgroundColor, this);
	}

	/**
	 * 
	 * @param Graphics g
	 */
	public void drawRules(Graphics g){
		g.drawImage(ARC_RULES, imageOriginValue, imageOriginValue, imageBackgroundColor, this);
	}

	/**
	 * Draws little arc objects to represent lives.
	 * 
	 * @param Graphics g
	 */
	public void drawLives(Graphics g){
		g.setColor(Color.RED);
		switch(impacts){
		case 0:
			g.fillArc(APPLET_WIDTH - 130, 140, 40, 25, 135, 270);
			g.fillArc(APPLET_WIDTH - 85, 140, 40, 25, 135, 270);
			g.fillArc(APPLET_WIDTH - 40, 140, 40, 25, 135, 270);   		
			break;
		case 1:
			g.fillArc(APPLET_WIDTH - 130, 140, 40, 25, 135, 270);
			g.fillArc(APPLET_WIDTH - 85, 140, 40, 25, 135, 270);		
			break;
		case 2:
			g.fillArc(APPLET_WIDTH - 130, 140, 40, 25, 135, 270);		
			break;
		case 3:
			try{
				if(gameover != null) {
					gameover.play();
				}
			} catch(NullPointerException e){
				e.printStackTrace();
			}				    		
			gameState = GAME_OVER_SCREEN;			    		
			break;
		}
	}

	/**
	 * Draws color square so player knows which color ball to collect.
	 * 
	 * @param Graphics g
	 */
	public void drawFlashSquare(Graphics g){
		g.setColor(Color.GRAY);
		g.fillRect(0,  10,  130,  120);

		// Get flash color for first set of balls
		flashColor = ballArray[0].getBallColor();
		g.setColor(flashColor);
		g.fillRect(20,  20,  100,  100);
	}

	/**
	 * 
	 * @param Graphics g
	 */
	public void drawScore(Graphics g){	
		g.setColor(Color.GRAY);
		g.fillRect(APPLET_WIDTH - 130,  10,  200,  120);
		fontSize  = 90;
		Font font = new Font(fontName, bold, fontSize);
		g.setFont(font);	
		g.setColor(fontColor);
		g.drawString(" " + ballImpacts, APPLET_WIDTH-150, 100);
	}

	/**
	 * Draws first set of falling balls.
	 * 
	 * @param Graphics g
	 */
	public void drawFirstBalls(Graphics g){	
		for(int i=0; i<ballArray.length; i++){		
			ballArray[i].draw(g);			
		}
	}

	/**
	 * Checks collision with black holes and player.
	 * 
	 * @param Graphics g
	 */
	public void checkBlackHoleCollisionWithPlayer(Graphics g){
		for(int i = 0; i < blackHoleArray.length; i++) {
			if(
					((arc.getX()+arc.getWidth()>=blackHoleArray[0].getX()+50) && 
					((arc.getX()<=blackHoleArray[0].getX()+blackHoleArray[0].getWidth()-50))) && 
					(arc.getY()<blackHoleArray[0].getY()+blackHoleArray[0].getHeight())
					){
				if(arc.getX() > SCREEN_CENTER) {
					arc.setX(0);
				}
				else if(arc.getX() < SCREEN_CENTER) {
					arc.setX((int) (APPLET_WIDTH - arc.getWidth()));
				}
				try{
					if(blackHoleCollide != null) {
						blackHoleCollide.play();
					}
				} catch(NullPointerException e){
					e.printStackTrace();
				}
				impact = true;
				g.setColor(Color.BLACK);
				g.fillArc(
						(int) arc.getX(), 
						(int) arc.getY(), 
						(int) arc.getWidth(), 
						(int) arc.getHeight(), 
						arc.getStartAngle(), 
						arc.getArcAngle()
						);
			}
		}
	}

	/**
	 * 
	 * @param Graphics g
	 */
	public void drawBlackHoles(Graphics g){
		int size = 100;
		if(blackHoleArray[0].getY() >= APPLET_HEIGHT) {
			for(int i = 0; i < blackHoleArray.length; i++) {
				Random xPosition = new Random();
				blackHoleArray[i] = new BlackHole(xPosition.nextInt(APPLET_WIDTH), 0, size, size, Color.DARK_GRAY);
				for(int a = 0; a < blackHoleArray.length; a++) {	
					blackHoleArray[a].draw(g);												
				}						
			}
		}
	}

	/**
	 * Draws fireball image when player collects correct "planet" ball.
	 * 
	 * @param Graphics g
	 */
	public void drawFireBalls(Graphics g) {		
		if (fire != 0) {
			fireBallArray[fire].setX((int) (arc.getX() + arc.getWidth() / 2));
			g.drawImage(
					explosion, 
					(int) (fireBallArray[fire].getX() + fireBallArray[fire].getWidth() / 2), 
					(int) fireBallArray[fire].getY(), 
					1, 
					1, 
					0, 
					50 * (int)FireBall.frame, 
					50, 
					50 * (int)FireBall.frame + 50, 
					this
					);					 
		}
	}

	/**
	 * Draws stars in background.
	 * 
	 * @param Graphics g
	 */
	public void drawStars(Graphics g) {
		for(int i = 0; i < starArray.length; i++){		
			starArray[i].draw(g);	
		}		
	}

	/**
	 * Checks collisions between player and balls.
	 * 
	 * @param Graphics g
	 */
	public void checkBallArcCollision(Graphics g){
		double ballXPosition = ballArray[0].getX();
		double ballYPosition = ballArray[0].getY();
		double arcXPosition  = arc.getX();
		double arcYPosition  = arc.getY();
		if(
				((ballYPosition > arcYPosition) && 
				ballYPosition < arcYPosition + 7) && 
				((ballXPosition > arcXPosition && 
				ballXPosition < arcXPosition + arc.getWidth()))
				){
			fire++;
			drawScoreCircles(g);
			ballImpact = true;	
			try{
				if(ballCollide != null) {
					ballCollide.play();
				}
			} catch(NullPointerException e){
				e.printStackTrace();
			}
			ballArray[0].setBallColor(Color.BLACK);
		}
	}

	/**
	 * Generates all "planet" balls after the first initial "planet" balls.
	 * 
	 * @param Graphics g
	 */
	public void generateAllSubsequentBalls(Graphics g){
		if(ballArray[5].getY() >= APPLET_HEIGHT) {			
			for(int i = 0; i < ballArray.length; i++){
				Random rand2        = new Random();
				int randomBallColor = rand2.nextInt(6);
				getBallColor(randomBallColor);
				
				Random ballXPosition  = new Random();
				int size     = 50;
				ballArray[i] = new Ball(ballXPosition.nextInt(APPLET_WIDTH - 50), 0, size, size, colorOfBall);	
				flashColor   = ballArray[0].getBallColor();								
				g.setColor(flashColor);
				int flashColorPosition = 20;
				int flashColorSize     = 100;
				g.fillRect(flashColorPosition,  flashColorPosition,  flashColorSize,  flashColorSize);
				for(int a = 0; a < ballArray.length; a++){			
					ballArray[a].draw(g);						
				}								
			}
		}		
	}

	/**
	 * 
	 * @param Graphics g
	 */
	public void drawScoreCircles(Graphics g) {
		int xPosition = (int) ballArray[0].getX();
		int yPosition = (int) ballArray[0].getY();
		g.setColor(Color.YELLOW);
		g.fillOval(xPosition, yPosition - 300, 200, 200);
		g.setColor(Color.BLUE);
		g.fillOval(xPosition, yPosition - 400, 150, 150);
		g.setColor(Color.RED);
		g.fillOval(xPosition + 200, yPosition - 100, 200, 200);
		g.setColor(Color.GREEN);
		g.fillOval(xPosition - 400, yPosition - 400, 300, 300);
	}

	/**
	 * Use double buffering.
	 * 
	 * @param Graphics g
	 */
	public void update(Graphics g) {
		Color doubleBufferingColor = Color.BLACK;
		if(i == null) {
			i = createImage(this.getSize().width, this.getSize().height);
			doubleG = i.getGraphics();
		}
		doubleG.setColor(doubleBufferingColor);
		doubleG.fillRect(imageOriginValue,  imageOriginValue, this.getSize().width, this.getSize().height);
		paint(doubleG);
		g.drawImage(i, imageOriginValue, imageOriginValue, this);			
	}

	@Override
	public void keyReleased(KeyEvent e) {
		arc.stopMoving();
	}				

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent event) {
		switch(event.getKeyCode())
		{
		case KeyEvent.VK_LEFT:
			arc.moveLeft();	    		
			break;
		case KeyEvent.VK_RIGHT:
			arc.moveRight();
			break;
		case KeyEvent.VK_ENTER:
			gameState = 0;
			break;
		case KeyEvent.VK_Y:
			newGame();
			break;
		case KeyEvent.VK_N:
			resetGame();
			break;
		case KeyEvent.VK_SPACE:
			gameState = 1;
			break;
		case KeyEvent.VK_ESCAPE:
			System.exit(0);
			break;
		}
	}
}
