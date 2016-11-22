package pl.poblock.connection.request;

import java.util.List;

public abstract class AbstractRequest {
	private String coreURL = null;
	private String skad = null;
	private String dokad = null;
	private int month = -1;
	private int year = -1;
	
	public AbstractRequest(int id, String skad, String dokad) {
		setSkad(skad);
		setDokad(dokad);
	}
	
	public AbstractRequest(int id, String skad, String dokad, int month, int year) {
		setSkad(skad);
		setDokad(dokad);
		setMonth(month);
		setYear(year);
	}
	
	protected abstract HttpGetFlight makeBasicRequest();
	public abstract List<HttpGetFlight> makeRequestList();
	
	public String getSkad() {
		return skad;
	}
	public void setSkad(String skad) {
		this.skad = skad;
	}
	public String getDokad() {
		return dokad;
	}
	public void setDokad(String dokad) {
		this.dokad = dokad;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	protected int getYear() {
		return year;
	}
	protected void setYear(int year) {
		this.year = year;
	}
	protected String getCoreURL() {
		return coreURL;
	}
	protected void setCoreURL(String coreURL) {
		this.coreURL = coreURL;
	}
	protected void addURLParam(String param, String value) {
		this.coreURL += (param+"="+value);
	}
}
