package pl.poblock.connection.decode.ryan.model;

import java.util.List;

public class Trip {
	private String origin;
	private String destination;
	private List<Date> dates;
	public Trip(String origin, String destination, List<Date> dates) {
		this.origin = origin;
		this.destination = destination;
		this.dates = dates;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public List<Date> getDates() {
		return dates;
	}
	public void setDates(List<Date> dates) {
		this.dates = dates;
	}
	@Override
	public String toString() {
		return "Trip [origin=" + origin + ", destination=" + destination + ", dates=" + dates + "]";
	}
}
