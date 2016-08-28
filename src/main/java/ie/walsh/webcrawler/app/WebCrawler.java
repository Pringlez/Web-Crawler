package ie.walsh.webcrawler.app;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JProgressBar;

public class WebCrawler implements Runnable {

	private BlockingQueue<Website> websites;
	private ExecutorService executor;
	private ArrayList<Website> processedWebsites;
	
	// Processing bar
	private JProgressBar progressBar;
	
	public WebCrawler(int threadPoolSize, ArrayList<Website> processedWebsites, JProgressBar progressBar) {
		setWebsites(new ArrayBlockingQueue<Website>(5000));
		setExecutor(Executors.newFixedThreadPool(threadPoolSize));
		setProcessedWebsites(processedWebsites);
		setProgressBar(progressBar);
		(new Thread(this)).start();
	}
	
	@Override
	public void run() {
		initThreads(250);
	}
	
	private void initThreads(int delayMS) {
		
		//int count = 0;
		
		// Keep looping & check the queue for any websites to process
		while(true){
			if(!getWebsites().isEmpty()){
				Runnable worker;
				try {
					// Take a website from the queue and attempt to start a new thread
					worker = new WorkerThread(getWebsites().take(), processedWebsites, progressBar);
					executor.execute(worker);
				} catch (Exception error) {
					System.out.println("Error - " + error);
					error.printStackTrace();
				}
			}
			
			// Delay the thread by certain time
	        try {
				Thread.sleep(delayMS);
				//System.out.println("Main Work Spawner - Running! " + ++count);
			} catch (Exception error) {
				System.out.println("Error - " + error);
				error.printStackTrace();
			}
		}
	}
	
	public void addWebsite(String url){
		this.websites.offer(new Website(url));
	}
	
	public void removeWebsite(String url){
		this.websites.remove(new Website(url));
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

	private void setProcessedWebsites(ArrayList<Website> processedWebsites) {
		this.processedWebsites = processedWebsites;
	}

	public JProgressBar getProgressBar() {
		return progressBar;
	}

	private void setProgressBar(JProgressBar progressBar) {
		this.progressBar = progressBar;
	}
}