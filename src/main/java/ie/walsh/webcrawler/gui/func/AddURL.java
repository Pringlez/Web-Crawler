package ie.walsh.webcrawler.gui.func;

import ie.walsh.webcrawler.gui.MainPanel;

/**
 * This class handles adding new URLs to the queue
 * Allowing the GUI thread to remain unblocked
 * @author John
 *
 */
public class AddURL implements Runnable {
	
	private MainPanel mainPanel;
	
	public AddURL(MainPanel mainPanel) {
		this.mainPanel = mainPanel;
	}
	
	@Override
	public void run() {
		addToQueue(this.mainPanel);
	}

	/**
	 * Adds new URLs to the processing queue
	 * @param mainPanel
	 */
	private void addToQueue(MainPanel mainPanel) {
		mainPanel.getLblErrorStatus().setText("");
		if(!mainPanel.getTxtAddURL().getText().isEmpty()){
			if(new CheckURL().isURLGood(mainPanel.getTxtAddURL().getText())){
				mainPanel.getProgressBar().setValue(33);
				mainPanel.getwCrawl().addWebsite(mainPanel.getTxtAddURL().getText());
				mainPanel.getUrlListModel().addElement(mainPanel.getTxtAddURL().getText());
				mainPanel.getTxtAddURL().setText("");
				mainPanel.getLblErrorStatus().setText("URL Processed!");
			}
			else{
				mainPanel.getTxtAddURL().requestFocus();
				mainPanel.getLblErrorStatus().setText("URL Error!");
			}
		}
	}
}