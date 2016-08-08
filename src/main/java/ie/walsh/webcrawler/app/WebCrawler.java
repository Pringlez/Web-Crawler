package ie.walsh.webcrawler.app;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WebCrawler {

	private BlockingQueue<Website> websites;
	private ExecutorService executor;
	
	public WebCrawler(int threadPoolSize) {
		setWebsites(new ArrayBlockingQueue<Website>(1000));
		setExecutor(Executors.newFixedThreadPool(threadPoolSize));
		addWebsite("http://www.gmit.ie");
		initThreads(250);
	}
	
	private void initThreads(int delayMS){
		// Keep looping & check the queue for any websites to process
		while(true){
			if(!getWebsites().isEmpty()){
				Runnable worker;
				try {
					// Take a website from the queue and attempt to start a new thread
					worker = new WorkerThread(getWebsites().take());
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
	
	private void addWebsite(String url){
		this.websites.offer(new Website(url));
	}
	
	public BlockingQueue<Website> getWebsites() {
		return websites;
	}

	public void setWebsites(ArrayBlockingQueue<Website> websites) {
		this.websites = websites;
	}

	public ExecutorService getExecutor() {
		return executor;
	}

	public void setExecutor(ExecutorService executor) {
		this.executor = executor;
	}
}