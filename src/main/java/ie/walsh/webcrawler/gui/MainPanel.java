package ie.walsh.webcrawler.gui;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import ie.walsh.webcrawler.app.Website;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.JList;

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
		lblAddURL.setBounds(8, 8, 72, 19);
		lblAddURL.setFont(myFont);
		insertURLPanel.add(lblAddURL);
		
		JTextField txtAddURL = new JTextField("");
		txtAddURL.setBounds(80, 6, 504, 25);
		insertURLPanel.add(txtAddURL);
		
		JButton btnAddURL = new JButton("Add to Queue");
		btnAddURL.setBounds(589, 6, 129, 24);
		btnAddURL.setFont(myFont);
		insertURLPanel.add(btnAddURL);
		
		JPanel optionsPanel = new JPanel();
		optionsPanel.setLayout(null);
		optionsPanel.setBounds(10, 467, 726, 38);
		optionsPanel.setBorder(bGreyLine);
		add(optionsPanel);
		
		JButton btnExitApp = new JButton("Exit");
		btnExitApp.setBounds(598, 7, 122, 25);
		btnExitApp.setFont(myFont);
		optionsPanel.add(btnExitApp);
	}
	
	private void setupURLListPanel() {
		JPanel urlListPanel = new JPanel();
		urlListPanel.setLayout(null);
		urlListPanel.setBounds(8, 54, 361, 405);
		add(urlListPanel);
		
		TitledBorder title = BorderFactory.createTitledBorder(bGreyLine, "List of URL's");
		title.setTitleJustification(TitledBorder.CENTER);
		urlListPanel.setBorder(title);
		
		JList<Website> list = new JList<Website>();
		list.setBounds(10, 26, 335, 306);
		list.setBorder(bGreyLine);
		urlListPanel.add(list);
		
		JButton btnReProcess = new JButton("Re-Process");
		btnReProcess.setBounds(10, 339, 107, 26);
		btnReProcess.setFont(myFont);
		urlListPanel.add(btnReProcess);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.setBounds(123, 339, 107, 26);
		btnRemove.setFont(myFont);
		urlListPanel.add(btnRemove);
		
		JButton btnClearList = new JButton("Clear List");
		btnClearList.setBounds(238, 339, 107, 26);
		btnClearList.setFont(myFont);
		urlListPanel.add(btnClearList);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(10, 372, 335, 23);
		urlListPanel.add(progressBar);
	}
	
	private void setupURLDetailsPanel() {
		JPanel urlDetailsPanel = new JPanel();
		urlDetailsPanel.setBounds(377, 54, 361, 405);
		
		TitledBorder title = BorderFactory.createTitledBorder(bGreyLine, "URL Details");
		title.setTitleJustification(TitledBorder.CENTER);
		urlDetailsPanel.setBorder(title);
		
		add(urlDetailsPanel);
	}
}