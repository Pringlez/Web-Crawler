package ie.walsh.webcrawler.gui;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.JList;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private Font myFont = new Font("Serif", Font.BOLD, 14);
	private Border bGreyLine = BorderFactory.createLineBorder(Color.GRAY, 1, true);
	private DefaultListModel<String> urlListModel;
	
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
		
		final JTextField txtAddURL = new JTextField();
		txtAddURL.setBounds(80, 6, 504, 25);
		insertURLPanel.add(txtAddURL);
		
		JButton btnAddURL = new JButton("Add to Queue");
		btnAddURL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				urlListModel.addElement(txtAddURL.getText());
			}
		});
		btnAddURL.setBounds(589, 6, 129, 24);
		btnAddURL.setFont(myFont);
		insertURLPanel.add(btnAddURL);
		
		JPanel optionsPanel = new JPanel();
		optionsPanel.setLayout(null);
		optionsPanel.setBounds(10, 467, 726, 38);
		optionsPanel.setBorder(bGreyLine);
		add(optionsPanel);
		
		JButton btnExitApp = new JButton("Exit");
		btnExitApp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExitApp.setBounds(596, 7, 122, 25);
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
		
		urlListModel = new DefaultListModel<String>();
		
		final JList<String> urlList = new JList<String>(urlListModel);
		urlList.setBounds(10, 26, 340, 306);
		urlList.setBorder(bGreyLine);
		urlList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		urlList.setLayoutOrientation(JList.VERTICAL);
		urlList.setVisibleRowCount(-1);
		urlListPanel.add(urlList);
		
		//JScrollPane listScroller = new JScrollPane(urlList);
		//listScroller.setPreferredSize(new Dimension(250, 80));
		
		JButton btnReProcess = new JButton("Re-Process");
		btnReProcess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < urlListModel.size(); i++){
					
				}
			}
		});
		btnReProcess.setBounds(10, 339, 107, 26);
		btnReProcess.setFont(myFont);
		urlListPanel.add(btnReProcess);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					urlListModel.removeElementAt(urlList.getSelectedIndex());
				}catch(Exception error){
					System.out.println("Error - " + error);
				}
			}
		});
		btnRemove.setBounds(127, 339, 107, 26);
		btnRemove.setFont(myFont);
		urlListPanel.add(btnRemove);
		
		JButton btnClearList = new JButton("Clear List");
		btnClearList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				urlListModel.clear();
			}
		});
		btnClearList.setBounds(243, 339, 107, 26);
		btnClearList.setFont(myFont);
		urlListPanel.add(btnClearList);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(10, 372, 340, 23);
		urlListPanel.add(progressBar);
	}
	
	private void setupURLDetailsPanel() {
		JPanel urlDetailsPanel = new JPanel();
		urlDetailsPanel.setLayout(null);
		urlDetailsPanel.setBounds(377, 54, 361, 405);
		
		TitledBorder title = BorderFactory.createTitledBorder(bGreyLine, "URL Details");
		title.setTitleJustification(TitledBorder.CENTER);
		urlDetailsPanel.setBorder(title);
		
		add(urlDetailsPanel);
		
		JLabel lblURLName = new JLabel("URL Name:");
		lblURLName.setBounds(20, 35, 87, 20);
		lblURLName.setFont(myFont);
		urlDetailsPanel.add(lblURLName);
		
		JLabel lblURLNameTxt = new JLabel();
		lblURLNameTxt.setBounds(108, 35, 236, 20);
		lblURLNameTxt.setFont(myFont);
		urlDetailsPanel.add(lblURLNameTxt);
		
		JLabel lblURLHyperLinks = new JLabel("Links:");
		lblURLHyperLinks.setBounds(20, 75, 55, 20);
		lblURLHyperLinks.setFont(myFont);
		urlDetailsPanel.add(lblURLHyperLinks);
		
		JLabel lblURLHyperLinksTxt = new JLabel();
		lblURLHyperLinksTxt.setBounds(75, 75, 268, 20);
		lblURLHyperLinksTxt.setFont(myFont);
		urlDetailsPanel.add(lblURLHyperLinksTxt);
		
		JLabel lblURLProcessTime = new JLabel("Process Time:");
		lblURLProcessTime.setBounds(20, 115, 100, 20);
		lblURLProcessTime.setFont(myFont);
		urlDetailsPanel.add(lblURLProcessTime);
		
		JLabel lblURLProcessTimeTxt = new JLabel();
		lblURLProcessTimeTxt.setBounds(121, 115, 221, 20);
		lblURLProcessTimeTxt.setFont(myFont);
		urlDetailsPanel.add(lblURLProcessTimeTxt);
		
		JLabel lblURLDepth = new JLabel("URL Depth:");
		lblURLDepth.setBounds(20, 155, 88, 20);
		lblURLDepth.setFont(myFont);
		urlDetailsPanel.add(lblURLDepth);
		
		JLabel lblURLDepthTxt = new JLabel();
		lblURLDepthTxt.setBounds(108, 155, 234, 20);
		lblURLDepthTxt.setFont(myFont);
		urlDetailsPanel.add(lblURLDepthTxt);
		
		JLabel lblURLExternalLinks = new JLabel("External Links:");
		lblURLExternalLinks.setBounds(20, 205, 110, 20);
		lblURLExternalLinks.setFont(myFont);
		urlDetailsPanel.add(lblURLExternalLinks);
		
		JLabel lblURLExternalLinksTxt = new JLabel();
		lblURLExternalLinksTxt.setBounds(130, 205, 212, 20);
		lblURLExternalLinksTxt.setFont(myFont);
		urlDetailsPanel.add(lblURLExternalLinksTxt);
		
		JLabel lblURLJavaScriptFiles = new JLabel("JavaScript Files:");
		lblURLJavaScriptFiles.setBounds(20, 245, 115, 20);
		lblURLJavaScriptFiles.setFont(myFont);
		urlDetailsPanel.add(lblURLJavaScriptFiles);
		
		JLabel lblURLJavaScriptFilesTxt = new JLabel();
		lblURLJavaScriptFilesTxt.setBounds(135, 245, 207, 20);
		lblURLJavaScriptFilesTxt.setFont(myFont);
		urlDetailsPanel.add(lblURLJavaScriptFilesTxt);
		
		JLabel lblURLCSSFiles = new JLabel("CSS Files:");
		lblURLCSSFiles.setBounds(20, 285, 78, 20);
		lblURLCSSFiles.setFont(myFont);
		urlDetailsPanel.add(lblURLCSSFiles);
		
		JLabel lblURLCSSFilesTxt = new JLabel();
		lblURLCSSFilesTxt.setBounds(98, 285, 244, 20);
		lblURLCSSFilesTxt.setFont(myFont);
		urlDetailsPanel.add(lblURLCSSFilesTxt);
	}
}