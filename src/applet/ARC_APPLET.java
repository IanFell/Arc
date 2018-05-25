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

	// Game screen width and height.
	public final int APPLET_WIDTH  = 600;
	public final int APPLET_HEIGHT = 700;

	public int PLAYER_SCORE = 0;
	public int LEVEL = 0;

	public Color colorOfBall;

	// Color of flashing square.
	public Color flashColor; 

	// Keeps track of fireBallArray
	public int fire = 0;

	// Keeps track of impacts with black holes, used to update life icons.
	public int impacts        = 0;
	public int ballImpacts    = 0;
	public boolean impact     = false;
	public boolean ballImpact = false;
	public boolean EASY, MEDIUM, HARD, GAME_OVER, WINNER;
	public int STATE;

	// This is for double buffering.
	private Image i;       
	private Graphics doubleG;
	URL url;

	// Player.
	Arc arc           = new Arc(APPLET_WIDTH / 2 - 60, APPLET_HEIGHT - 35, 100, 25, 135, 270);

	// Ball player has to catch.
	Ball[] ballArray  = new Ball[6];

	// Other game objects.
	Stars[] starArray = new Stars[100];
	FireBall[] fireBallArray = new FireBall[100];
	BlackHole[] blackHoleArray = new BlackHole[2];
	UFO ufo;
	Lazer [] lazerArray = new Lazer[2];

	// Use this to load images.
	Toolkit tk = this.getToolkit();

	// All audio.
	AudioClip music;
	AudioClip ballCollide;
	AudioClip blackHoleCollide;		
	AudioClip gameover, lazerhit, lazershot, winner;

	// All images.
	Image ARC_LOGO;
	Image ARC_RULES;
	Image explosion;

	/**
	 * Load audio.
	 */
	public void loadAudio(){
		URL musicsong = this.getClass().getResource("/Music/menu.au");
		music = ARC_APPLET.newAudioClip(musicsong);

		URL bc = this.getClass().getResource("/Music/ballcollision.au");
		ballCollide = ARC_APPLET.newAudioClip(bc);

		URL bhc = this.getClass().getResource("/Music/blackholecollision.au");
		ballCollide = ARC_APPLET.newAudioClip(bhc);

		URL go = this.getClass().getResource("/Music/gameover.au");
		ballCollide = ARC_APPLET.newAudioClip(go);

		URL lh = this.getClass().getResource("/Music/lazerhit.au");
		ballCollide = ARC_APPLET.newAudioClip(lh);

		URL ls = this.getClass().getResource("/Music/lazershot.au");
		ballCollide = ARC_APPLET.newAudioClip(ls);

		URL win = this.getClass().getResource("/Music/lazershot.au");
		ballCollide = ARC_APPLET.newAudioClip(win);
	}

	/**
	 * Load images.
	 */
	public void loadImages(){
		URL ex = this.getClass().getResource("/Images/explosion.png");
		explosion = tk.getImage(ex);

		URL al = this.getClass().getResource("/Images/ARC_LOGO.png");
		ARC_LOGO = tk.getImage(al);

		URL ar = this.getClass().getResource("/Images/ARC_RULES.png");
		ARC_RULES = tk.getImage(ar);
	}

	/**
	 * Initialize game.
	 */
	public void init(){			
		addKeyListener(this);
		loadAudio();
		loadImages();
		setSize(APPLET_WIDTH, APPLET_HEIGHT);
		setBackground(Color.BLACK);
		ufo = new UFO(200, 200, 100, 25, Color.LIGHT_GRAY);
		music.loop();
		EASY  = true;

		// Set level state to intro screen.
		STATE = -1;

		// Creates new balls, with random x position and color.
		for(int i = 0; i < ballArray.length; i++){
			Random rand  = new Random(); // For x start position.
			Random rand2 = new Random(); // For ball color.
			int x = rand2.nextInt(6);    // For ball color.
			getBallColor(x);
			ballArray[i] = new Ball(rand.nextInt(APPLET_WIDTH - 50), 0, 50, 50, colorOfBall);	

		}

		// Creates new Stars, with random x,y position. 
		for(int i = 0; i < starArray.length; i++){
			Random randx = new Random(); // For x start position.
			Random randy = new Random(); // For y start position.
			starArray[i] = new Stars(
					randx.nextInt(APPLET_WIDTH), 
					randy.nextInt(APPLET_HEIGHT), 
					1, 
					1, 
					Color.WHITE
					);
		}

		// Creates the fire balls.
		for(int i = 0; i < fireBallArray.length; i++){
			fireBallArray[i] = new FireBall(0, APPLET_HEIGHT, 50, 50, Color.WHITE);
		}

		// Creates the black holes.
		for(int i = 0; i  <blackHoleArray.length; i++){
			Random randx      = new Random(); // For x start position.
			blackHoleArray[i] = new BlackHole(randx.nextInt(APPLET_WIDTH), 0, 10, 10, Color.DARK_GRAY);
		}

		// Creates the lazers.
		for(int i = 0; i < lazerArray.length; i++){			
			lazerArray[i] = new Lazer(
					(ufo.getX() + ufo.getWidth())  / 2, 
					ufo.getY() + ufo.getHeight() + 50, 
					15, 
					10, 
					Color.GREEN, 
					70, 
					180
					);
		}
	}

	/**
	 * Start game.
	 */
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

			if(fire != 0)
				fireBallArray[fire].update(this);

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
		switch(STATE)
		{
		case -1:
			getIntro(g);
			break;
		case 0:
			getRules(g);	
			break;
		case 1:
			drawStars(g);
			gameLogic(g);
			break;
		case 2:
			drawStars(g);
			gameOver(g);						    		
			break;
		case 3:
			drawStars(g);						    		
			drawWinningScreen(g);
			break;
		}
	}

	/**
	 * Reset to original state for new game.
	 */
	public void reset(){
		EASY        = true;
		MEDIUM      = false;
		HARD        = false;
		impacts     = 0;
		ballImpacts = 0;
		STATE       = -1;
	}

	/**
	 * Start new game.
	 */
	public void newGame(){
		EASY        = true;
		MEDIUM      = false;
		HARD        = false;
		impacts     = 0;
		ballImpacts = 0;
		STATE       = 1;
	}

	/**
	 * Game logic.
	 * 
	 * @param Graphics g
	 */
	public void gameLogic(Graphics g){
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
		checkBlackHoleCollision(g);//check for collision with black hole

		if(impact) {
			impacts++;
		}

		drawLives(g);
		ufo.draw(g);
		lazerArray[0].draw(g);
		drawLazers(g);
		checkLazerCollision(g);
		drawScore(g);

		// Used to know which color ball to catch.
		drawFlashSquare(g);

		impact     = false;
		ballImpact = false;

		// Sets how fast balls are falling, and if player has won.
		setGameStates();
	}

	/**
	 * Sets how fast balls are falling, and if player has won.
	 */
	public void setGameStates() {
		if (ballImpacts == 5){
			EASY   = false;
			MEDIUM = true;
			HARD   = false;			
		}

		if(ballImpacts == 10) {
			EASY   = false;
			MEDIUM = false;
			HARD   = true;
		}

		if(ballImpacts == 15) {
			try{
				if(winner != null) {
					winner.play();
				}
			} catch(NullPointerException e){
				e.printStackTrace();
			}
			STATE = 3;
		}			
	}

	/**
	 * Sets ball color
	 * 
	 * @param int i
	 * @return Color
	 */
	private Color getBallColor(int i) {
		if(i == 0)
			colorOfBall = Color.BLUE;
		else if(i == 1)
			colorOfBall = Color.GREEN;
		else if(i == 2)
			colorOfBall = Color.YELLOW;
		else if(i == 3)
			colorOfBall = Color.CYAN;
		else if(i == 4)
			colorOfBall = Color.MAGENTA;
		else if(i == 5)
			colorOfBall = Color.PINK;
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
				ballImpacts = 0; // Reset player score.
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
	 * Draws lazers from UFOs.
	 * 
	 * @param Graphics g
	 */
	private void drawLazers(Graphics g) {
		if(lazerArray[0].getY() >= APPLET_HEIGHT){
			for(int i = 0; i < lazerArray.length; i++) {				
				lazerArray[i] = new Lazer(ufo.getX() + ufo.getWidth() / 2,  ufo.getY() + ufo.getHeight() + 30, 15, 10, Color.WHITE, 70, 180);				
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
	 * Draws screen when player wins.
	 * 
	 * @param Graphics g
	 */
	public void drawWinningScreen(Graphics g){

		for(int i=0; i<starArray.length; i++) {	
			starArray[i].draw(g);
			g.setColor(Color.GRAY);
			g.fillRect(APPLET_WIDTH-130,  10,  200,  120);

			Font font = new Font("Bauhaus 93", Font.BOLD, 50);
			g.setFont(font);
			g.setColor(Color.WHITE);	
			g.drawString("CONGRADULATIONS", (APPLET_WIDTH / 2) - 240, 250);

			Font font3 = new Font("Bauhaus 93", Font.BOLD, 50);
			g.setFont(font3);
			g.setColor(Color.WHITE);	
			g.drawString("YOU HAVE SAVED THE", (APPLET_WIDTH / 2) - 250, 300);

			Font font4 = new Font("Bauhaus 93", Font.BOLD, 50);
			g.setFont(font4);
			g.setColor(Color.WHITE);	
			g.drawString("WORLD FROM NUCLEAR", (APPLET_WIDTH / 2) - 280, 350);

			Font font5 = new Font("Bauhaus 93", Font.BOLD, 50);
			g.setFont(font5);
			g.setColor(Color.WHITE);	
			g.drawString("DESTRUCTION", (APPLET_WIDTH / 2) - 160, 400);

			Font font2 = new Font("Bauhaus 93", Font.BOLD, 25);
			g.setFont(font2);
			g.setColor(Color.WHITE);	
			g.drawString("NEW GAME?", (APPLET_WIDTH / 2) - 60, 450);

			Font font6 = new Font("Bauhaus 93", Font.BOLD, 25);
			g.setFont(font6);
			g.setColor(Color.WHITE);	
			g.drawString("Y/N", (APPLET_WIDTH / 2) - 10, 500);

			// Set color rectangle back to gray.
			g.setColor(Color.GRAY);
			g.fillRect(0,  10,  130,  120);

			// Reset score to 0.
			ballImpacts = 0;
		}		
	}

	/**
	 * Draws game over screen.
	 * 
	 * @param Graphics g
	 */
	public void gameOver(Graphics g){
		for(int i = 0; i < starArray.length; i++) {		
			starArray[i].draw(g);
			g.setColor(Color.GRAY);
			g.fillRect(APPLET_WIDTH - 130,  10,  200,  120);

			Font font = new Font("Bauhaus 93", Font.BOLD, 50);
			g.setFont(font);
			g.setColor(Color.WHITE);	
			g.drawString("GAME OVER_", (APPLET_WIDTH/2)-140, 250);

			Font font2 = new Font("Bauhaus 93", Font.BOLD, 25);
			g.setFont(font2);
			g.setColor(Color.WHITE);	
			g.drawString("NEW GAME?", (APPLET_WIDTH / 2) - 60, 300);

			Font font3 = new Font("Bauhaus 93", Font.BOLD, 25);
			g.setFont(font3);
			g.setColor(Color.WHITE);	
			g.drawString("Y/N", (APPLET_WIDTH / 2) - 10, 350);

			// Set color rectangle back to gray.
			g.setColor(Color.GRAY);
			g.fillRect(0,  10,  130,  120);

			// Reset score to 0.
			ballImpacts = 0;
		}
	}

	/**
	 * Draws intro screen / image.
	 * 
	 * @param Graphics g
	 */
	public void getIntro(Graphics g){
		g.drawImage(ARC_LOGO, 0, 0, Color.WHITE, this);
	}

	/**
	 * Draws rules screen / image.
	 * 
	 * @param Graphics g
	 */
	public void getRules(Graphics g){
		g.drawImage(ARC_RULES, 0, 0, Color.WHITE, this);
	}

	/**
	 * Draws little arc objects to represent lives.
	 * 
	 * @param Graphics g
	 */
	public void drawLives(Graphics g){
		switch(impacts){
		case 0:
			g.setColor(Color.RED);
			g.fillArc(APPLET_WIDTH - 130, 140, 40, 25, 135, 270);
			g.fillArc(APPLET_WIDTH - 85, 140, 40, 25, 135, 270);
			g.fillArc(APPLET_WIDTH - 40, 140, 40, 25, 135, 270);   		
			break;

		case 1:
			g.setColor(Color.RED);
			g.fillArc(APPLET_WIDTH - 130, 140, 40, 25, 135, 270);
			g.fillArc(APPLET_WIDTH - 85, 140, 40, 25, 135, 270);		
			break;

		case 2:
			g.setColor(Color.RED);
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
			STATE = 2;			    		
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
	 * Draw player score.
	 * 
	 * @param Graphics g
	 */
	public void drawScore(Graphics g){	
		g.setColor(Color.GRAY);
		g.fillRect(APPLET_WIDTH - 130,  10,  200,  120);
		Font font = new Font("Bauhaus 93", Font.BOLD, 90);
		g.setFont(font);
		g.setColor(Color.WHITE);	
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
	public void checkBlackHoleCollision(Graphics g){
		for(int i = 0; i < blackHoleArray.length; i++) {
			if(
					((arc.getX()+arc.getWidth()>=blackHoleArray[0].getX()+50) && 
							((arc.getX()<=blackHoleArray[0].getX()+blackHoleArray[0].getWidth()-50))) && 
					(arc.getY()<blackHoleArray[0].getY()+blackHoleArray[0].getHeight())
					){

				if(arc.getX() > APPLET_WIDTH / 2) {
					arc.setX(0);
				}
				else if(arc.getX() < APPLET_WIDTH / 2) {
					arc.setX(APPLET_WIDTH-arc.getWidth());
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
				g.fillArc(arc.getX(), arc.getY(), arc.getWidth(), arc.getHeight(), arc.getStartAngle(), arc.getArcAngle());
			}
		}
	}

	/**
	 * Draws black holes.
	 * 
	 * @param Graphics g
	 */
	public void drawBlackHoles(Graphics g){
		if(blackHoleArray[0].getY() >= APPLET_HEIGHT) {
			for(int i = 0; i < blackHoleArray.length; i++) {
				Random randx = new Random();
				blackHoleArray[i] = new BlackHole(randx.nextInt(APPLET_WIDTH), 0, 100, 100, Color.DARK_GRAY);
				for(int a = 0; a < blackHoleArray.length; a++) {	
					blackHoleArray[a].draw(g);												
				}						
			}
		}
	}

	/**
	 * Draws fireball image when player collects correct ball.
	 * 
	 * @param Graphics g
	 */
	public void drawFireBalls(Graphics g) {		
		if(fire != 0) {
			fireBallArray[fire].setX(arc.getX() + arc.getWidth() / 2);
			g.drawImage(
					explosion, 
					fireBallArray[fire].getX() + fireBallArray[fire].getWidth() / 2, 
					fireBallArray[fire].getY(), 
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
		if(
				((ballArray[0].getY() > arc.getY()) && 
						ballArray[0].getY() < arc.getY() + 7) && 
				((ballArray[0].getX() > arc.getX() && 
						ballArray[0].getX() < arc.getX()+arc.getWidth()))
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
	 * Generates all balls after the first initial balls.
	 * 
	 * @param Graphics g
	 */
	public void generateAllSubsequentBalls(Graphics g){
		if(ballArray[5].getY() >= APPLET_HEIGHT) {			
			for(int i=0; i<ballArray.length; i++){
				Random rand  = new Random();
				Random rand2 = new Random();
				int x        = rand2.nextInt(6);
				getBallColor(x);
				ballArray[i] = new Ball(rand.nextInt(APPLET_WIDTH - 50), 0, 50, 50, colorOfBall);	
				flashColor = ballArray[0].getBallColor();								
				g.setColor(flashColor);
				g.fillRect(20,  20,  100,  100);
				for(int a = 0; a < ballArray.length; a++){			
					ballArray[a].draw(g);						
				}								
			}
		}		
	}

	/**
	 * Not sure what this method actually does and I'm too lazy to trace it.
	 * 
	 * @param Graphics g
	 */
	public void drawScoreCircles(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillOval(ballArray[0].getX(), ballArray[0].getY() - 300, 200, 200);
		g.setColor(Color.BLUE);
		g.fillOval(ballArray[0].getX(), ballArray[0].getY() - 400, 150, 150);
		g.setColor(Color.RED);
		g.fillOval(ballArray[0].getX() + 200, ballArray[0].getY() - 100, 200, 200);
		g.setColor(Color.GREEN);
		g.fillOval(ballArray[0].getX() - 400, ballArray[0].getY() - 400, 300, 300);
	}

	/**
	 * Use double buffering.
	 * 
	 * @param Graphics g
	 */
	public void update(Graphics g) {
		if(i == null) {
			i = createImage(this.getSize().width, this.getSize().height);
			doubleG = i.getGraphics();
		}
		doubleG.setColor(Color.black);
		doubleG.fillRect(0,  0, this.getSize().width, this.getSize().height);
		doubleG.setColor(Color.black);
		paint(doubleG);
		g.drawImage(i, 0, 0, this);			
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
			STATE=0;
			break;

		case KeyEvent.VK_Y:
			newGame();
			break;

		case KeyEvent.VK_N:
			reset();
			break;

		case KeyEvent.VK_SPACE:
			STATE=1;
			break;

		case KeyEvent.VK_ESCAPE:
			System.exit(0);
			break;
		}
	}
}
