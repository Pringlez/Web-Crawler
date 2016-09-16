package ie.walsh.webcrawler.app;

/**
 * The object that stores multiple details about a website
 * @author John
 *
 */
public class Website {

	private String url;
	private int hyperLinkCount;
	private int processTime;
	private int imagesCount;
	private int paragraphCount;
	private int metaDataCount;
	private int jsFileCount;
	private int cssFileCount;
	private boolean initProcess;
	
	public Website() {	
	}
	
	/**
	 * Constructing a new 'Website' instance with passed variables
	 * @param url
	 */
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

	public int getImagesCount() {
		return imagesCount;
	}

	public void setImagesCount(int imagesCount) {
		this.imagesCount = imagesCount;
	}

	public int getParagraphCount() {
		return paragraphCount;
	}

	public void setParagraphCount(int paragraphCount) {
		this.paragraphCount = paragraphCount;
	}

	public int getMetaDataCount() {
		return metaDataCount;
	}

	public void setMetaDataCount(int metaDataCount) {
		this.metaDataCount = metaDataCount;
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