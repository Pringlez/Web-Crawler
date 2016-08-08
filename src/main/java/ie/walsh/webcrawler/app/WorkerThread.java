package ie.walsh.webcrawler.app;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WorkerThread implements Runnable {
	
	private Website website;
	Document doc;
    Elements links;
	
	public WorkerThread(Website website) {
		setWebsite(website);
		initCrawl(website.getUrl());
	}
	
	private void initCrawl(String url) {
		try {
			this.doc = Jsoup.connect(url).get();
			this.links = doc.select("a");
		} catch (IOException error) {
			System.out.println("Error - " + error);
		}
	}

	@Override
	public void run() {
		try {
			crawlSite(getWebsite().getUrl());
		} catch (IOException error) {
			System.out.println("Error - " + error);
		}
	}
	
	public void crawlSite(String url) throws IOException {
        for (Element link : this.links) {
            if(!url.contains(link.attr("abs:href"))) {
            	getWebsite().setHyperLinkCount(getWebsite().getHyperLinkCount() + 1);
            	System.out.println("Site: " + url + " has " + getWebsite().getHyperLinkCount() + " links");
            	//crawlSite(link.attr("href"));
            }
        }
    }

	public Website getWebsite() {
		return website;
	}

	public void setWebsite(Website website) {
		this.website = website;
	}
}