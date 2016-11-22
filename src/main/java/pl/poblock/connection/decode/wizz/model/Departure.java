package pl.poblock.connection.decode.wizz.model;

import java.util.List;

public class Departure {
	private String ArrivalStationCode;
	private String CurrentDate;
	private String Date;
	private String DepartureStationCode;
	private List<Flight> Flights;
	private String HasSelection;
	private String InMonth;
	private String MinimumPrice;
	public Departure(String arrivalStationCode, String currentDate, String date, String departureStationCode,
			List<Flight> flights, String hasSelection, String inMonth, String minimumPrice) {
		ArrivalStationCode = arrivalStationCode;
		CurrentDate = currentDate;
		Date = date;
		DepartureStationCode = departureStationCode;
		Flights = flights;
		HasSelection = hasSelection;
		InMonth = inMonth;
		MinimumPrice = minimumPrice;
	}
	public String getArrivalStationCode() {
		return ArrivalStationCode;
	}
	public void setArrivalStationCode(String arrivalStationCode) {
		ArrivalStationCode = arrivalStationCode;
	}
	public String getCurrentDate() {
		return CurrentDate;
	}
	public void setCurrentDate(String currentDate) {
		CurrentDate = currentDate;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	public String getDepartureStationCode() {
		return DepartureStationCode;
	}
	public void setDepartureStationCode(String departureStationCode) {
		DepartureStationCode = departureStationCode;
	}
	public List<Flight> getFlights() {
		return Flights;
	}
	public void setFlights(List<Flight> flights) {
		Flights = flights;
	}
	public String getHasSelection() {
		return HasSelection;
	}
	public void setHasSelection(String hasSelection) {
		HasSelection = hasSelection;
	}
	public String getInMonth() {
		return InMonth;
	}
	public void setInMonth(String inMonth) {
		InMonth = inMonth;
	}
	public String getMinimumPrice() {
		return MinimumPrice;
	}
	public void setMinimumPrice(String minimumPrice) {
		MinimumPrice = minimumPrice;
	}
	@Override
	public String toString() {
		return "Departure [ArrivalStationCode=" + ArrivalStationCode + ", CurrentDate=" + CurrentDate + ", Date=" + Date
				+ ", DepartureStationCode=" + DepartureStationCode + ", Flights=" + Flights + ", HasSelection="
				+ HasSelection + ", InMonth=" + InMonth + ", MinimumPrice=" + MinimumPrice + "]";
	}
}
