package pl.poblock.connection.request;

import org.apache.http.client.methods.HttpGet;

public class HttpGetFlight extends HttpGet {
	private String linia;

	public HttpGetFlight(String url, String linia) {
		super(url);
		this.setLinia(linia);
	}

	public String getLinia() {
		return linia;
	}

	public void setLinia(String linia) {
		this.linia = linia;
	}
	
}
