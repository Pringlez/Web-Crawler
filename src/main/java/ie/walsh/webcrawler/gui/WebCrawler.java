package ie.walsh.webcrawler.gui;

import javax.swing.JFrame;

/**
 * The web crawler GUI frame, which contains the main GUI controls
 * @author John
 *
 */
public class WebCrawler extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private MainPanel currentPanel;
	
	public WebCrawler(){
		currentPanel = new MainPanel();
		setupFrame();
	}
	
	private void setupFrame(){
		setContentPane(currentPanel);
		setTitle("Web Crawler");
		setSize(753, 545);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new WebCrawler().setVisible(true);
	}
}