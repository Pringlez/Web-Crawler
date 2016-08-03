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
		initThreads(100);
	}
	
	private void initThreads(int delayMS){
		
		while(isRunning()){
			if(!getWebsites().isEmpty()){
				Runnable worker;
				try {
					worker = new WorkerThread(getWebsites().take());
					executor.execute(worker);
			        executor.shutdown();
				} catch (InterruptedException error) {
					System.out.println("Error - " + error);
				}
				
		        //while (!executor.isTerminated()) {
		        //}
		        
		        System.out.println("All Threads Done!");
			}
				
	        try {
				Thread.sleep(delayMS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}	
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