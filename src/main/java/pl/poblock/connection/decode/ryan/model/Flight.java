package pl.poblock.connection.decode.ryan.model;

import java.util.List;

public class Flight {
	private String flightNumber;
	private List<String> time;
	private List<String> timeUTC;
	private String duration;
	private String faresLeft; // -1 czyli zostalo duzo
	private String flightKey;
	private String infantsLeft;
	private FareClass regularFare;
	private FareClass businessFare;
	private FareClass leisureFare;
	public Flight(String flightNumber, List<String> time, List<String> timeUTC, String duration, String faresLeft,
			String flightKey, String infantsLeft, FareClass regularFare, FareClass businessFare,
			FareClass leisureFare) {
		this.flightNumber = flightNumber;
		this.time = time;
		this.timeUTC = timeUTC;
		this.duration = duration;
		this.faresLeft = faresLeft;
		this.flightKey = flightKey;
		this.infantsLeft = infantsLeft;
		this.regularFare = regularFare;
		this.businessFare = businessFare;
		this.leisureFare = leisureFare;
	}
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	public List<String> getTime() {
		return time;
	}
	public void setTime(List<String> time) {
		this.time = time;
	}
	public List<String> getTimeUTC() {
		return timeUTC;
	}
	public void setTimeUTC(List<String> timeUTC) {
		this.timeUTC = timeUTC;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getFaresLeft() {
		return faresLeft;
	}
	public void setFaresLeft(String faresLeft) {
		this.faresLeft = faresLeft;
	}
	public String getFlightKey() {
		return flightKey;
	}
	public void setFlightKey(String flightKey) {
		this.flightKey = flightKey;
	}
	public String getInfantsLeft() {
		return infantsLeft;
	}
	public void setInfantsLeft(String infantsLeft) {
		this.infantsLeft = infantsLeft;
	}
	public FareClass getRegularFare() {
		return regularFare;
	}
	public void setRegularFare(FareClass regularFare) {
		this.regularFare = regularFare;
	}
	public FareClass getBusinessFare() {
		return businessFare;
	}
	public void setBusinessFare(FareClass businessFare) {
		this.businessFare = businessFare;
	}
	public FareClass getLeisureFare() {
		return leisureFare;
	}
	public void setLeisureFare(FareClass leisureFare) {
		this.leisureFare = leisureFare;
	}
//	@Override
//	public String toString() {
//		return "Flight [flightNumber=" + flightNumber + ", time=" + time + ", timeUTC=" + timeUTC + ", duration="
//				+ duration + ", faresLeft=" + faresLeft + ", flightKey=" + flightKey + ", infantsLeft=" + infantsLeft
//				+ ", regularFare=" + regularFare + ", businessFare=" + businessFare + ", leisureFare=" + leisureFare
//				+ "]";
//	}
	@Override
	public String toString() {
		return "Flight [time=" + time + ", duration="
				+ duration + ", faresLeft=" + faresLeft
				+ ", cena=" + regularFare + "]";
	}
}
