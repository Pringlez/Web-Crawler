package ie.webcrawler;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WebCrawler {

	private BlockingQueue<Website> websites;
	private ExecutorService executor;
	private boolean isRunning;
	
	public WebCrawler(boolean isRunning, int threadPoolSize) {
		setWebsites(new ArrayBlockingQueue<Website>(1000));
		setExecutor(Executors.newFixedThreadPool(threadPoolSize));
		setRunning(isRunning);
		addTestWebsites();
		initThreads(100);
	}
	
	private void initThreads(int delayMS){
		if(!getWebsites().isEmpty()){
			Runnable worker;
			try {
				worker = new WorkerThread(getWebsites().take());
				executor.execute(worker);
		        executor.shutdown();
			} catch (InterruptedException error) {
				System.out.println("Error - " + error);
			}
		}
			
        try {
			Thread.sleep(delayMS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void addTestWebsites(){
		this.websites.offer(new Website("http://google.com"));
		//this.websites.offer(new Website("http://www.rte.ie"));
		//this.websites.offer(new Website("http://www.bbc.com"));
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

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}
}