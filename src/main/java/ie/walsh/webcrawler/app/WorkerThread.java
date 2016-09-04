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
 	
 	// Work thread control
 	private boolean isCrawlable;
	
	public WorkerThread(Website website, ArrayList<Website> processedWebsites, JProgressBar progressBar) {
		setWebsite(website);
		if(initCrawl(website.getUrl())){
			setProcessedWebsites(processedWebsites);
			setProgressBar(progressBar);
			setCrawlable(true);
		}
	}
	
	private boolean initCrawl(String url) {
		try {
			url = checkURL(url);
			this.doc = Jsoup.connect(url).get();
			this.links = doc.select("a");
			return true;
		} catch (IOException error) {
			System.out.println("Init Crawl Error - " + error);
			return false;
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
		if(isCrawlable())
			crawlSite(getWebsite().getUrl());
	}
	
	private void crawlSite(String url) {
        try {
        	long startTime = System.currentTimeMillis();
			for (Element link : this.links) {
			    if(link.attr("abs:href").contains("http")){
			    	getWebsite().setHyperLinkCount(getWebsite().getHyperLinkCount() + 1);
			    	//crawlSite(link.attr("href"));
			    }
			    if(!link.attr("abs:href").contains(url)){
			    	getWebsite().setExternalLinkCount(getWebsite().getExternalLinkCount() + 1);
			    }
			    if(link.attr("abs:script").contains("")){
			    	getWebsite().setJsFileCount(getWebsite().getJsFileCount() + 1);
			    	System.out.println("Found JS: " + link.text());
			    }
			    if(link.attr("abs:link").contains("class")){
			    	getWebsite().setCssFileCount(getWebsite().getCssFileCount() + 1);
			    	System.out.println("Found CSS: " + link.text());
			    }
			    
			    System.out.println("Link: " + link.text());
			}
			
			// Console output
			System.out.println("\nSite: " + url + " has " + getWebsite().getHyperLinkCount() + " Links");
			System.out.println("\nSite: " + url + " was processed in " + getWebsite().getProcessTime() + " MS");
			System.out.println("\nSite: " + url + " has depth of " + getWebsite().getUrlDepth() + " Levels");
			System.out.println("\nSite: " + url + " has " + getWebsite().getExternalLinkCount() + " Exteral Links");
			System.out.println("\nSite: " + url + " has " + getWebsite().getJsFileCount() + " JavaScript Files");
			System.out.println("\nSite: " + url + " has " + getWebsite().getCssFileCount() + " CSS Files");
			
			getWebsite().setProcessTime((int)((System.currentTimeMillis() - startTime)));
			getProgressBar().setValue(100);
			
		    if(!getWebsite().isInitProcess()){
				getProcessedWebsites().add(getWebsite());
				getWebsite().setInitProcess(true);
		    }
		} catch (Exception error) {
			System.out.println("Site Crawl Error - " + error);
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

	private boolean isCrawlable() {
		return isCrawlable;
	}

	private void setCrawlable(boolean isCrawlable) {
		this.isCrawlable = isCrawlable;
	}
}