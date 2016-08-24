package ie.walsh.webcrawler;

import ie.walsh.webcrawler.gui.*;

public class Runner {

	public static void main(String[] args) {
		/**
		 * The variable passed determines how many threads 
		 * the application can use to process and crawl websites
		 * 4 = Four Core Processor
		 * 8 = Eight Core Processor
		 */
		new WebCrawler();
	}
}