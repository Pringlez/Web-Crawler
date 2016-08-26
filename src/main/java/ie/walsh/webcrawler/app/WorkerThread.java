package ie.walsh.webcrawler.app;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WorkerThread implements Runnable {
	
	private Website website;
	private Document doc;
    private Elements links;
    private ArrayList<Website> processedWebsites;
	
	public WorkerThread(Website website, ArrayList<Website> processedWebsites) {
		setWebsite(website);
		initCrawl(website.getUrl());
		setProcessedWebsites(processedWebsites);
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
		getProcessedWebsites().add(getWebsite());
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
		} catch (Exception error) {
			System.out.println("Error - " + error);
		}
    }

	public Website getWebsite() {
		return website;
	}

	public void setWebsite(Website website) {
		this.website = website;
	}

	public ArrayList<Website> getProcessedWebsites() {
		return processedWebsites;
	}

	public void setProcessedWebsites(ArrayList<Website> processedWebsites) {
		this.processedWebsites = processedWebsites;
	}
}