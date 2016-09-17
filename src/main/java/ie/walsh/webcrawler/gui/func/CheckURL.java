package ie.walsh.webcrawler.gui.func;

import org.jsoup.Jsoup;

public class CheckURL {

	/**
	 * Checks the URL passed is OK to be processed
	 * Return a boolean on result
	 * @param url
	 * @return
	 */
	public boolean isURLGood(String url) {
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
			System.out.println("Is URL Good Error - " + error);
			return false;
		}
	}
}
