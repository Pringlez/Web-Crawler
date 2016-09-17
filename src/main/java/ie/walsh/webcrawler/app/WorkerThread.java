package ie.walsh.webcrawler.app;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JProgressBar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import ie.walsh.webcrawler.gui.func.CheckURL;

/**
 * Performs most of the work and crawls through multiple areas of a website
 * @author John
 *
 */
public class WorkerThread implements Runnable {
	
	private Website website;
	private Document webpageDoc;
    private Elements aTags;
    private Elements imageTags;
    private Elements paragraphTags;
    private Elements metaTags;
    private Elements linkTags;
    private Elements scriptTags;
    private ArrayList<Website> processedWebsites;
    
    // Processing bar
 	private JProgressBar progressBar;
 	
 	// Work thread control
 	private boolean isCrawlable;
	
 	/**
 	 * Constructing a new 'WorkerThread' instance with passed variables
 	 * @param website
 	 * @param processedWebsites
 	 * @param progressBar
 	 */
	public WorkerThread(Website website, ArrayList<Website> processedWebsites, JProgressBar progressBar) {
		setWebsite(website);
		if(new CheckURL().isURLGood(website.getUrl())){
			setTagsVars(website.getUrl());
			setProcessedWebsites(processedWebsites);
			setProgressBar(progressBar);
			setCrawlable(true);
		}
	}
	
	private void setTagsVars(String url) {
		try {
			this.webpageDoc = Jsoup.connect(url).get();
			this.aTags = webpageDoc.select("a");
			this.imageTags = webpageDoc.select("img");
			this.paragraphTags = webpageDoc.select("p");
			this.metaTags = webpageDoc.select("meta");
			this.linkTags = webpageDoc.select("link");
			this.scriptTags = webpageDoc.select("script");
		} catch (IOException error) {
			System.out.println("Set Tags Error - " + error);
		}
	}

	/**
	 * Run thread method that will initiate the crawling of a website
	 */
	@Override
	public void run() {
		if(isCrawlable())
			crawlSite(getWebsite().getUrl());
	}
	
	/**
	 * Crawls a specific URL for details about it
	 * @param url
	 */
	private void crawlSite(String url) {
        try {
        	// Start recording the processing time
        	long startTime = System.currentTimeMillis();
        	
			for (Element aTag : this.aTags) {
			    if(aTag.attr("abs:href").contains("http")){
			    	getWebsite().setHyperLinkCount(getWebsite().getHyperLinkCount() + 1);
			    }
			}
			
			for (Element imageTag : this.imageTags) {
				if(imageTag.attr("abs:src").contains("http")){
			    	getWebsite().setImagesCount(getWebsite().getImagesCount() + 1);
			    }
			}
			
			for (Element paragraphTag : this.paragraphTags) {
				if(paragraphTag.tagName().equals("p")){
			    	getWebsite().setParagraphCount(getWebsite().getParagraphCount() + 1);
				}
			}
			
			for (Element metaTag : this.metaTags) {
				if(!metaTag.attr("abs:content").isEmpty()){
			    	getWebsite().setMetaDataCount(getWebsite().getMetaDataCount() + 1);
			    }
			}
			
			for (Element linkTag : this.linkTags) {
				if(linkTag.attr("abs:href").contains("css")){
			    	getWebsite().setCssFileCount(getWebsite().getCssFileCount() + 1);
			    }
			}
			
			for (Element scriptTag : this.scriptTags) {
				if(scriptTag.attr("abs:type").contains("text/javascript")){
			    	getWebsite().setJsFileCount(getWebsite().getJsFileCount() + 1);
			    }
			}
			
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