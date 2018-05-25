package Main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import applet.ARC_APPLET;

/**
 * 
 * @author Fabulous Fellini
 *
 */
public class Main { 
	
	private static ARC_APPLET myGame;
	
	/**
	 * 
	 * @param array s
	 */
	public static void main(String s[]) {
		JFrame f = new JFrame("ARC");
		f.setLayout(new BorderLayout());
		myGame   = new ARC_APPLET();
		myGame.init();
		f.setPreferredSize(new Dimension(650, 750));
		f.setResizable(false);
		f.add(myGame, BorderLayout.CENTER);
  	  	myGame.start();
		f.pack();
		f.setVisible(true);
		f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e) {
				System.exit(0);
		}});	
	}
}