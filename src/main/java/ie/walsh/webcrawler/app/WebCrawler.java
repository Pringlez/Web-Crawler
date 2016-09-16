package ie.walsh.webcrawler.app;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JProgressBar;

/**
 * The web crawler application that processes the queue of tasks
 * Employs multiple threads to perform the work on its behalf
 * Stores the results of the work in an ArrayList to be reviewed
 * @author John
 *
 */
public class WebCrawler implements Runnable {

	private BlockingQueue<Website> websites;
	private ExecutorService executor;
	private ArrayList<Website> processedWebsites;
	
	// Processing bar
	private JProgressBar progressBar;
	
	/**
	 * Constructing a new 'WebCrawler' instance with passed variables
	 * Then creates a new thread specific for this newly created class instance
	 * @param threadPoolSize
	 * @param processedWebsites
	 * @param progressBar
	 */
	public WebCrawler(int threadPoolSize, ArrayList<Website> processedWebsites, JProgressBar progressBar) {
		setWebsites(new ArrayBlockingQueue<Website>(5000));
		setExecutor(Executors.newFixedThreadPool(threadPoolSize));
		setProcessedWebsites(processedWebsites);
		setProgressBar(progressBar);
		(new Thread(this)).start();
	}
	
	/**
	 * Run thread method that will initiate worker threads
	 * Pass a integer to set the delay in milliseconds
	 */
	@Override
	public void run() {
		initThreads(250);
	}
	
	/**
	 * Initiate the thread pool that will delegate 
	 * work out to separate threads
	 * You can pass a integer to delay the time between
	 * new threads starting work
	 * @param delayMS
	 */
	private void initThreads(int delayMS) {
		// Keep looping & check the queue for any websites to process
		while(true){
			if(!getWebsites().isEmpty()){
				Runnable worker;
				try {
					// Take a website from the queue and attempt to start a new thread
					worker = new WorkerThread(getWebsites().take(), processedWebsites, progressBar);
					executor.execute(worker);
				} catch (Exception error) {
					System.out.println("Init Thread Error - " + error);
					error.printStackTrace();
				}
			}
			// Delay the thread by certain time
	        try {
				Thread.sleep(delayMS);
			} catch (Exception error) {
				System.out.println("Sleep Thread Error - " + error);
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