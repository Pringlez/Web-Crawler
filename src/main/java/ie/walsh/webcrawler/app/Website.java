package ie.walsh.webcrawler.app;

public class Website {

	private String url;
	private int hyperLinkCount;
	private int processTime;
	private int urlDepth;
	private int externalLinkCount;
	private int jsFileCount;
	private int cssFileCount;
	private boolean initProcess;
	
	public Website() {	
	}
	
	public Website(String url) {
		setUrl(url);
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getHyperLinkCount() {
		return hyperLinkCount;
	}

	public void setHyperLinkCount(int hyperLinkCount) {
		this.hyperLinkCount = hyperLinkCount;
	}

	public int getProcessTime() {
		return processTime;
	}

	public void setProcessTime(int processTime) {
		this.processTime = processTime;
	}

	public int getUrlDepth() {
		return urlDepth;
	}

	public void setUrlDepth(int urlDepth) {
		this.urlDepth = urlDepth;
	}

	public int getExternalLinkCount() {
		return externalLinkCount;
	}

	public void setExternalLinkCount(int externalLinkCount) {
		this.externalLinkCount = externalLinkCount;
	}

	public int getJsFileCount() {
		return jsFileCount;
	}

	public void setJsFileCount(int jsFileCount) {
		this.jsFileCount = jsFileCount;
	}

	public int getCssFileCount() {
		return cssFileCount;
	}

	public void setCssFileCount(int cssFileCount) {
		this.cssFileCount = cssFileCount;
	}

	public boolean isInitProcess() {
		return initProcess;
	}

	public void setInitProcess(boolean initProcess) {
		this.initProcess = initProcess;
	}
}