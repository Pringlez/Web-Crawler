package ie.webcrawler;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WorkerThread implements Runnable {
	
	private Website website;
	
	public WorkerThread(Website website) {
		setWebsite(website);
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

		Document doc = Jsoup.connect("").get();
	    Elements links = doc.select("a");

        for (Element link : links) {
            if(!url.contains(link.attr("abs:href"))) {
            	getWebsite().setHyperLinkCount(getWebsite().getHyperLinkCount() + 1);
            	crawlSite(link.attr("href"));
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