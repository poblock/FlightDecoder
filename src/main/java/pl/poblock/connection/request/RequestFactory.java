package pl.poblock.connection.request;

import java.util.List;

import pl.poblock.connection.Polaczenie;

public class RequestFactory {

	public static List<HttpGetFlight> getRequestList(int id, Polaczenie polaczenie, int month, int year) {
		if(polaczenie.getLinia().equals("Wizzair")) {
			WizzRequest req = new WizzRequest(id, polaczenie.getSkad(), polaczenie.getDokad(), month,year);
			return req.makeRequestList();
		} else if(polaczenie.getLinia().equals("Ryanair")) {
			RyanRequest req = new RyanRequest(id, polaczenie.getSkad(), polaczenie.getDokad(), month,year);
			return req.makeRequestList();
		}
		return null;
	}
}
