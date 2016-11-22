package pl.poblock.connection.decode.ryan.model;

import java.util.List;

public class FareClass {
	private String fareKey;
	private String fareClass;
	private List<Fare> fares;
	public FareClass(String fareKey, String fareClass, List<Fare> fares) {
		this.fareKey = fareKey;
		this.fareClass = fareClass;
		this.fares = fares;
	}
	public String getFareKey() {
		return fareKey;
	}
	public void setFareKey(String fareKey) {
		this.fareKey = fareKey;
	}
	public String getFareClass() {
		return fareClass;
	}
	public void setFareClass(String fareClass) {
		this.fareClass = fareClass;
	}
	public List<Fare> getFares() {
		return fares;
	}
	public void setFares(List<Fare> fares) {
		this.fares = fares;
	}
//	@Override
//	public String toString() {
//		return "FareClass [fareKey=" + fareKey + ", fareClass=" + fareClass + ", fares=" + fares + "]";
//	}
	@Override
	public String toString() {
		return fares.toString();
	}
}
