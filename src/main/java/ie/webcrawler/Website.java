package ie.webcrawler;

public class Website {

	private String url;
	private int hyperLinkCount;
	
	public Website() {
		
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
}