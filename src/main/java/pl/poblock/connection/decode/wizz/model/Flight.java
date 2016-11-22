package pl.poblock.connection.decode.wizz.model;

public class Flight {
	private String CarrierCode;
	private String FlightNumber;
	private String STD;
	private String STA;
	private String ArrivalStationName;
	private String DepartureStationName;
	private String IsMACStation;
	private String IsAirportChange;
	public Flight(String carrierCode, String flightNumber, String sTD, String sTA, String arrivalStationName,
			String departureStationName, String isMACStation, String isAirportChange) {
		CarrierCode = carrierCode;
		FlightNumber = flightNumber;
		STD = sTD;
		STA = sTA;
		ArrivalStationName = arrivalStationName;
		DepartureStationName = departureStationName;
		IsMACStation = isMACStation;
		IsAirportChange = isAirportChange;
	}
	public String getCarrierCode() {
		return CarrierCode;
	}
	public void setCarrierCode(String carrierCode) {
		CarrierCode = carrierCode;
	}
	public String getFlightNumber() {
		return FlightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		FlightNumber = flightNumber;
	}
	public String getSTD() {
		return STD;
	}
	public void setSTD(String sTD) {
		STD = sTD;
	}
	public String getSTA() {
		return STA;
	}
	public void setSTA(String sTA) {
		STA = sTA;
	}
	public String getArrivalStationName() {
		return ArrivalStationName;
	}
	public void setArrivalStationName(String arrivalStationName) {
		ArrivalStationName = arrivalStationName;
	}
	public String getDepartureStationName() {
		return DepartureStationName;
	}
	public void setDepartureStationName(String departureStationName) {
		DepartureStationName = departureStationName;
	}
	public String getIsMACStation() {
		return IsMACStation;
	}
	public void setIsMACStation(String isMACStation) {
		IsMACStation = isMACStation;
	}
	public String getIsAirportChange() {
		return IsAirportChange;
	}
	public void setIsAirportChange(String isAirportChange) {
		IsAirportChange = isAirportChange;
	}
	@Override
	public String toString() {
		return "Flight [CarrierCode=" + CarrierCode + ", FlightNumber=" + FlightNumber + ", STD=" + STD + ", STA=" + STA
				+ ", ArrivalStationName=" + ArrivalStationName + ", DepartureStationName=" + DepartureStationName
				+ ", IsMACStation=" + IsMACStation + ", IsAirportChange=" + IsAirportChange + "]";
	}
}
