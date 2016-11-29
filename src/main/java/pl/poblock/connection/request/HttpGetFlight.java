package pl.poblock.connection.request;

import org.apache.http.client.methods.HttpGet;

public class HttpGetFlight extends HttpGet {
	private int id;
	private String linia;
	
	public HttpGetFlight(int id, String url, String linia) {
		super(url);
		this.setLinia(linia);
		this.setId(id);
	}

	public String getLinia() {
		return linia;
	}

	public void setLinia(String linia) {
		this.linia = linia;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "HttpGetFlight [id=" + id + ", linia=" + linia + ", "+ this.getURI() +"]";
	}
	
}
