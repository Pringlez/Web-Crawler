package ie.walsh.webcrawler.app;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WebCrawler {

	private BlockingQueue<Website> websites;
	private ExecutorService executor;
	private ArrayList<Website> processedWebsites;
	
	public WebCrawler(int threadPoolSize) {
		setWebsites(new ArrayBlockingQueue<Website>(1000));
		setExecutor(Executors.newFixedThreadPool(threadPoolSize));
		initThreads(250);
	}
	
	private void initThreads(int delayMS){
		// Keep looping & check the queue for any websites to process
		while(true){
			if(!getWebsites().isEmpty()){
				Runnable worker;
				try {
					// Take a website from the queue and attempt to start a new thread
					worker = new WorkerThread(getWebsites().take(), processedWebsites);
					executor.execute(worker);
			        executor.shutdown();
				} catch (InterruptedException error) {
					System.out.println("Error - " + error);
				}
			}
			
			// Delay the thread by certain time
	        try {
				Thread.sleep(delayMS);
			} catch (InterruptedException error) {
				System.out.println("Error - " + error);
			}
		}
	}
	
	public void addWebsite(String url){
		this.websites.offer(new Website(url));
	}
	
	public void removeWebsite(String url){
		this.websites.offer(new Website(url));
	}
	
	public BlockingQueue<Website> getWebsites() {
		return websites;
	}

	private void setWebsites(ArrayBlockingQueue<Website> websites) {
		this.websites = websites;
	}

	public ExecutorService getExecutor() {
		return executor;
	}

	private void setExecutor(ExecutorService executor) {
		this.executor = executor;
	}

	public ArrayList<Website> getProcessedWebsites() {
		return processedWebsites;
	}

	public void setProcessedWebsites(ArrayList<Website> processedWebsites) {
		this.processedWebsites = processedWebsites;
	}
}