package ie.walsh.webcrawler.gui.func;

import org.jsoup.Jsoup;

import ie.walsh.webcrawler.gui.MainPanel;

public class AddURL implements Runnable {
	
	private MainPanel mainPanel;
	
	public AddURL(MainPanel mainPanel) {
		this.mainPanel = mainPanel;
	}
	
	@Override
	public void run() {
		addToQueue(this.mainPanel);
	}

	private void addToQueue(MainPanel mainPanel) {
		mainPanel.getLblErrorStatus().setText("");
		if(!mainPanel.getTxtAddURL().getText().isEmpty()){
			if(isURLGood(mainPanel.getTxtAddURL().getText())){
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
	
	private boolean isURLGood(String url) {
		try {
			if(!url.contains("http://")){
				if(url.contains("https://")){
					url = "http://" + url.substring(8, url.length());
				}
				else{
					url = "http://" + url;
				}
			}
			Jsoup.connect(url).get();
		    return true;
		} catch (Exception error) {
			System.out.println("Error - " + error);
			return false;
		}
	}
}