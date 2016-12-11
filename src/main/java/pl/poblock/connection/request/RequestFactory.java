package pl.poblock.connection.request;

import java.util.List;

import pl.poblock.model.request.Polaczenie;

public class RequestFactory {

	public static List<HttpGetFlight> getRequestList(Polaczenie polaczenie, int month, int year) {
		if(polaczenie.getLinia().equals("Wizzair")) {
			WizzRequest req = new WizzRequest(polaczenie.getId(), polaczenie.getSkad(), polaczenie.getDokad(), month,year);
			return req.makeRequestList();
		} else if(polaczenie.getLinia().equals("Ryanair")) {
			RyanRequest req = new RyanRequest(polaczenie.getId(), polaczenie.getSkad(), polaczenie.getDokad(), month,year);
			return req.makeRequestList();
		}
		return null;
	}
}
