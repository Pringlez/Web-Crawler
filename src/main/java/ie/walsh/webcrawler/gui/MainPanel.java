package ie.walsh.webcrawler.gui;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JButton;

public class MainPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private Font myFont = new Font("Serif", Font.BOLD, 14);
	private Border bGreyLine = BorderFactory.createLineBorder(Color.GRAY, 1, true);
	
	public MainPanel() {
		setLayout(null);
		setupURLPanel();
		setupURLListPanel();
		setupURLDetailsPanel();
	}

	private void setupURLPanel() {
		JPanel insertURLPanel = new JPanel();
		insertURLPanel.setLayout(null);
		insertURLPanel.setBounds(10, 11, 726, 36);
		insertURLPanel.setBorder(bGreyLine);
		add(insertURLPanel);
		
		JLabel lblAddURL = new JLabel("Add URL:");
		lblAddURL.setBounds(6, 8, 72, 19);
		lblAddURL.setFont(myFont);
		insertURLPanel.add(lblAddURL);
		
		JTextField txtAddURL = new JTextField("");
		txtAddURL.setBounds(80, 6, 504, 25);
		insertURLPanel.add(txtAddURL);
		
		JButton btnAddURL = new JButton("Add to Queue");
		btnAddURL.setBounds(591, 5, 125, 25);
		btnAddURL.setFont(myFont);
		insertURLPanel.add(btnAddURL);
		
		JPanel optionsPanel = new JPanel();
		optionsPanel.setLayout(null);
		optionsPanel.setBounds(10, 470, 726, 36);
		optionsPanel.setBorder(bGreyLine);
		add(optionsPanel);
		
		JButton btnClearList = new JButton("Clear List");
		btnClearList.setBounds(458, 5, 125, 25);
		btnClearList.setFont(myFont);
		optionsPanel.add(btnClearList);
		
		JButton btnExitApp = new JButton("Exit");
		btnExitApp.setBounds(592, 5, 125, 25);
		btnExitApp.setFont(myFont);
		optionsPanel.add(btnExitApp);
	}
	
	private void setupURLListPanel() {
		JPanel urlListPanel = new JPanel();
		urlListPanel.setBounds(8, 56, 356, 405);
		
		TitledBorder title = BorderFactory.createTitledBorder(bGreyLine, "List of URL's");
		title.setTitleJustification(TitledBorder.CENTER);
		urlListPanel.setBorder(title);
		
		add(urlListPanel);
	}
	
	private void setupURLDetailsPanel() {
		JPanel urlDetailsPanel = new JPanel();
		urlDetailsPanel.setBounds(382, 56, 356, 405);
		
		TitledBorder title = BorderFactory.createTitledBorder(bGreyLine, "URL Details");
		title.setTitleJustification(TitledBorder.CENTER);
		urlDetailsPanel.setBorder(title);
		
		add(urlDetailsPanel);
	}
}