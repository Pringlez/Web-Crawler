package ie.walsh.webcrawler.gui;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import ie.walsh.webcrawler.app.WebCrawler;
import ie.walsh.webcrawler.app.Website;
import ie.walsh.webcrawler.gui.func.AddURL;
import ie.walsh.webcrawler.gui.func.ReprocessURL;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.JList;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class MainPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	// The main panel
	private MainPanel mainPanel;
	
	// Global font type & size
	private Font myFont = new Font("Serif", Font.BOLD, 14);
	private Border bGreyLine = BorderFactory.createLineBorder(Color.GRAY, 1, true);
	private JList<String> urlList;
	private DefaultListModel<String> urlListModel;
	
	// Errors status label
	private JLabel lblErrorStatus;
	
	// Website details panel
	private JLabel lblURLNameTxt;
	private JLabel lblURLHyperLinksTxt;
	private JLabel lblURLProcessTimeTxt;
	private JLabel lblURLImageTxt;
	private JLabel lblURLMetaDataTxt;
	private JLabel lblURLJavaScriptFilesTxt;
	private JLabel lblURLCSSFilesTxt;
	
	// Add URL
	private JTextField txtAddURL;
	
	// Processing bar
	private JProgressBar progressBar;
	
	// Web crawler app & variables
	private WebCrawler wCrawl;
	private ArrayList<Website> processedWebsites;
	
	public MainPanel() {
		setLayout(null);
		setMainPanel(this);
		setupURLPanel();
		setupURLListPanel();
		setupURLDetailsPanel();
		setProcessedWebsites(new ArrayList<Website>());
		wCrawl = new WebCrawler(4, processedWebsites, progressBar);
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
		
		txtAddURL = new JTextField();
		txtAddURL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Thread(new AddURL(getMainPanel())).start();
			}
		});
		txtAddURL.setBounds(80, 6, 504, 25);
		insertURLPanel.add(txtAddURL);
		
		JButton btnAddURL = new JButton("Add to Queue");
		btnAddURL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Thread(new AddURL(getMainPanel())).start();
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
		
		lblErrorStatus = new JLabel();
		lblErrorStatus.setBounds(8, 8, 150, 19);
		lblErrorStatus.setFont(myFont);
		optionsPanel.add(lblErrorStatus);
		
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
		
		urlList = new JList<String>(urlListModel);
		urlList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if(!urlListModel.isEmpty())
					setWebsiteDetails(processedWebsites.get(urlList.getSelectedIndex()));
			}
		});
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
				new Thread(new ReprocessURL(getMainPanel())).start();
			}
		});
		btnReProcess.setBounds(10, 339, 107, 26);
		btnReProcess.setFont(myFont);
		urlListPanel.add(btnReProcess);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index;
				try{
					index = urlList.getSelectedIndex();
					urlList.clearSelection();
					urlListModel.removeElementAt(index);
					wCrawl.getProcessedWebsites().remove(index);
				}catch(Exception error){
					System.out.println("Error Removing - " + error);
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
				wCrawl.getProcessedWebsites().clear();
				urlList.clearSelection();
			}
		});
		btnClearList.setBounds(243, 339, 107, 26);
		btnClearList.setFont(myFont);
		urlListPanel.add(btnClearList);
		
		progressBar = new JProgressBar();
		progressBar.setBounds(10, 372, 340, 23);
		progressBar.setStringPainted(true);
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
		
		lblURLNameTxt = new JLabel();
		lblURLNameTxt.setBounds(108, 35, 236, 20);
		lblURLNameTxt.setFont(myFont);
		urlDetailsPanel.add(lblURLNameTxt);
		
		JLabel lblURLHyperLinks = new JLabel("Links:");
		lblURLHyperLinks.setBounds(20, 75, 55, 20);
		lblURLHyperLinks.setFont(myFont);
		urlDetailsPanel.add(lblURLHyperLinks);
		
		lblURLHyperLinksTxt = new JLabel();
		lblURLHyperLinksTxt.setBounds(75, 75, 268, 20);
		lblURLHyperLinksTxt.setFont(myFont);
		urlDetailsPanel.add(lblURLHyperLinksTxt);
		
		JLabel lblURLProcessTime = new JLabel("Process Time:");
		lblURLProcessTime.setBounds(20, 115, 100, 20);
		lblURLProcessTime.setFont(myFont);
		urlDetailsPanel.add(lblURLProcessTime);
		
		lblURLProcessTimeTxt = new JLabel();
		lblURLProcessTimeTxt.setBounds(121, 115, 221, 20);
		lblURLProcessTimeTxt.setFont(myFont);
		urlDetailsPanel.add(lblURLProcessTimeTxt);
		
		JLabel lblURLImages = new JLabel("URL Images:");
		lblURLImages.setBounds(20, 155, 88, 20);
		lblURLImages.setFont(myFont);
		urlDetailsPanel.add(lblURLImages);
		
		lblURLImageTxt = new JLabel();
		lblURLImageTxt.setBounds(108, 155, 234, 20);
		lblURLImageTxt.setFont(myFont);
		urlDetailsPanel.add(lblURLImageTxt);
		
		JLabel lblURLMetaDataLinks = new JLabel("MetaData Links:");
		lblURLMetaDataLinks.setBounds(20, 195, 110, 20);
		lblURLMetaDataLinks.setFont(myFont);
		urlDetailsPanel.add(lblURLMetaDataLinks);
		
		lblURLMetaDataTxt = new JLabel();
		lblURLMetaDataTxt.setBounds(130, 195, 212, 20);
		lblURLMetaDataTxt.setFont(myFont);
		urlDetailsPanel.add(lblURLMetaDataTxt);
		
		JLabel lblURLJavaScriptFiles = new JLabel("JavaScript Files:");
		lblURLJavaScriptFiles.setBounds(20, 235, 115, 20);
		lblURLJavaScriptFiles.setFont(myFont);
		urlDetailsPanel.add(lblURLJavaScriptFiles);
		
		lblURLJavaScriptFilesTxt = new JLabel();
		lblURLJavaScriptFilesTxt.setBounds(135, 235, 207, 20);
		lblURLJavaScriptFilesTxt.setFont(myFont);
		urlDetailsPanel.add(lblURLJavaScriptFilesTxt);
		
		JLabel lblURLCSSFiles = new JLabel("CSS Files:");
		lblURLCSSFiles.setBounds(20, 275, 78, 20);
		lblURLCSSFiles.setFont(myFont);
		urlDetailsPanel.add(lblURLCSSFiles);
		
		lblURLCSSFilesTxt = new JLabel();
		lblURLCSSFilesTxt.setBounds(98, 275, 244, 20);
		lblURLCSSFilesTxt.setFont(myFont);
		urlDetailsPanel.add(lblURLCSSFilesTxt);
	}

	private void setMainPanel(MainPanel mainPanel) {
		this.mainPanel = mainPanel;
	}

	private MainPanel getMainPanel() {
		return mainPanel;
	}

	public Font getMyFont() {
		return myFont;
	}

	public Border getbGreyLine() {
		return bGreyLine;
	}

	public DefaultListModel<String> getUrlListModel() {
		return urlListModel;
	}

	public JLabel getLblErrorStatus() {
		return lblErrorStatus;
	}

	public JLabel getLblURLNameTxt() {
		return lblURLNameTxt;
	}

	public JLabel getLblURLHyperLinksTxt() {
		return lblURLHyperLinksTxt;
	}

	public JLabel getLblURLProcessTimeTxt() {
		return lblURLProcessTimeTxt;
	}

	public JLabel getLblURLDepthTxt() {
		return lblURLImageTxt;
	}

	public JLabel getLblURLMetaDataLinksTxt() {
		return lblURLMetaDataTxt;
	}

	public JLabel getLblURLJavaScriptFilesTxt() {
		return lblURLJavaScriptFilesTxt;
	}

	public JLabel getLblURLCSSFilesTxt() {
		return lblURLCSSFilesTxt;
	}

	public JTextField getTxtAddURL() {
		return txtAddURL;
	}

	public JProgressBar getProgressBar() {
		return progressBar;
	}

	public WebCrawler getwCrawl() {
		return wCrawl;
	}

	public ArrayList<Website> getProcessedWebsites() {
		return processedWebsites;
	}

	private void setProcessedWebsites(ArrayList<Website> processedWebsites) {
		this.processedWebsites = processedWebsites;
	}
	
	private void setWebsiteDetails(Website website){
		this.lblURLNameTxt.setText(website.getUrl());
		this.lblURLHyperLinksTxt.setText(Integer.toString(website.getHyperLinkCount()));
		this.lblURLProcessTimeTxt.setText(Integer.toString(website.getProcessTime()) + "ms");
		this.lblURLImageTxt.setText(Integer.toString(website.getImagesCount()));
		this.lblURLMetaDataTxt.setText(Integer.toString(website.getMetaDataCount()));
		this.lblURLJavaScriptFilesTxt.setText(Integer.toString(website.getJsFileCount()));
		this.lblURLCSSFilesTxt.setText(Integer.toString(website.getCssFileCount()));
	}
}