package ie.walsh.webcrawler.gui.func;

import ie.walsh.webcrawler.gui.MainPanel;

public class ReprocessURL implements Runnable {

	private MainPanel mainPanel;
	
	public ReprocessURL(MainPanel mainPanel) {
		this.mainPanel = mainPanel;
	}

	@Override
	public void run() {
		processURLs(this.mainPanel);
	}
	
	private void processURLs(MainPanel mainPanel) {
		mainPanel.getwCrawl().getProcessedWebsites().clear();
		mainPanel.getProgressBar().setValue(10);
		for(int i = 0; i < mainPanel.getUrlListModel().size(); i++){
			mainPanel.getwCrawl().addWebsite(mainPanel.getUrlListModel().getElementAt(i));
		}
		mainPanel.getProgressBar().setValue(100);
	}
}