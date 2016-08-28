package ie.walsh.webcrawler.app;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JProgressBar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WorkerThread implements Runnable {
	
	private Website website;
	private Document doc;
    private Elements links;
    private ArrayList<Website> processedWebsites;
    
 // Processing bar
 	private JProgressBar progressBar;
	
	public WorkerThread(Website website, ArrayList<Website> processedWebsites, JProgressBar progressBar) {
		setWebsite(website);
		initCrawl(website.getUrl());
		setProcessedWebsites(processedWebsites);
		setProgressBar(progressBar);
	}
	
	private void initCrawl(String url) {
		try {
			url = checkURL(url);
			this.doc = Jsoup.connect(url).get();
			this.links = doc.select("a");
		} catch (IOException error) {
			System.out.println("Error - " + error);
		}
	}

	private String checkURL(String url) {
		try {
			if(!url.contains("http://")){
				if(url.contains("https://")){
					url = "http://" + url.substring(8, url.length());
				}
				else{
					url = "http://" + url;
				}
			}
		} catch (Exception error) {
			System.out.println("URL Check Error - " + error);
		}
		return url;
	}

	@Override
	public void run() {
		crawlSite(getWebsite().getUrl());
	}
	
	private void crawlSite(String url) {
        try {
			for (Element link : this.links) {
			    if(!url.contains(link.attr("abs:href"))) {
			    	getWebsite().setHyperLinkCount(getWebsite().getHyperLinkCount() + 1);
			    	System.out.println("Site: " + url + " has " + getWebsite().getHyperLinkCount() + " links");
			    	//crawlSite(link.attr("href"));
			    }
			}
			
			getProgressBar().setValue(100);
			
		    if(!getWebsite().isInitProcess()){
				getProcessedWebsites().add(getWebsite());
				getWebsite().setInitProcess(true);
		    }
		} catch (Exception error) {
			System.out.println("Error - " + error);
		}
    }

	private Website getWebsite() {
		return website;
	}

	private void setWebsite(Website website) {
		this.website = website;
	}

	private ArrayList<Website> getProcessedWebsites() {
		return processedWebsites;
	}

	private void setProcessedWebsites(ArrayList<Website> processedWebsites) {
		this.processedWebsites = processedWebsites;
	}
	
	private JProgressBar getProgressBar() {
		return progressBar;
	}

	private void setProgressBar(JProgressBar progressBar) {
		this.progressBar = progressBar;
	}
}