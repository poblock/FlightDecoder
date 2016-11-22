package pl.poblock.connection.decode.ryan.model;

import java.util.List;

public class Date {
	private String dateOut;
	private List<Flight> flights;
	public Date(String dateOut, List<Flight> flights) {
		this.dateOut = dateOut;
		this.flights = flights;
	}
	public String getDateOut() {
		return dateOut;
	}
	public void setDateOut(String dateOut) {
		this.dateOut = dateOut;
	}
	public List<Flight> getFlights() {
		return flights;
	}
	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}
	@Override
	public String toString() {
		return "Date [dateOut=" + dateOut + ", flights=" + flights + "]";
	}
}
