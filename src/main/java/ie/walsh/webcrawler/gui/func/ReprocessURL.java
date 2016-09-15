package ie.walsh.webcrawler.gui.func;

import ie.walsh.webcrawler.gui.MainPanel;

/**
 * This class handles the re-processing of URLs in the queue
 * Allowing the GUI thread to remain unblocked
 * @author John
 *
 */
public class ReprocessURL implements Runnable {

	private MainPanel mainPanel;
	
	public ReprocessURL(MainPanel mainPanel) {
		this.mainPanel = mainPanel;
	}

	@Override
	public void run() {
		processURLs(this.mainPanel);
	}
	
	/**
	 * Process URLs contained in the queue
	 * @param mainPanel
	 */
	private void processURLs(MainPanel mainPanel) {
		mainPanel.getwCrawl().getProcessedWebsites().clear();
		mainPanel.getProgressBar().setValue(10);
		for(int i = 0; i < mainPanel.getUrlListModel().size(); i++){
			mainPanel.getwCrawl().addWebsite(mainPanel.getUrlListModel().getElementAt(i));
		}
		mainPanel.getLblErrorStatus().setText("URL's Re-processed!");
		mainPanel.getProgressBar().setValue(100);
	}
}