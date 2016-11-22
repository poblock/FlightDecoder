package pl.poblock.connection.decode.ryan.model;

import java.util.List;

public class Departure {
	private String currency;
	private String currPrecision;
	private List<Trip> trips;
	private String serverTimeUTC;
	public Departure(String currency, String currPrecision, List<Trip> trips, String serverTimeUTC) {
		this.currency = currency;
		this.currPrecision = currPrecision;
		this.trips = trips;
		this.setServerTimeUTC(serverTimeUTC);
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getCurrPrecision() {
		return currPrecision;
	}
	public void setCurrPrecision(String currPrecision) {
		this.currPrecision = currPrecision;
	}
	public List<Trip> getTrips() {
		return trips;
	}
	public void setTrips(List<Trip> trips) {
		this.trips = trips;
	}
	public String getServerTimeUTC() {
		return serverTimeUTC;
	}
	public void setServerTimeUTC(String serverTimeUTC) {
		this.serverTimeUTC = serverTimeUTC;
	}
	@Override
	public String toString() {
		return "Departure [currency=" + currency + ", currPrecision=" + currPrecision + ", trips=" + trips
				+ ", serverTimeUTC=" + serverTimeUTC + "]";
	}
}
