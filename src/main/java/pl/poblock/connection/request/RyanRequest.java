package pl.poblock.connection.request;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDateTime;

public class RyanRequest extends FlightRequest {

	public RyanRequest(int id, String skad, String dokad, int month, int year) {
		super(id, skad, dokad, month, year);
		setCoreURL("https://desktopapps.ryanair.com/pl-pl/availability?ADT=1&CHD=0"
			+ "&FlexDaysIn=6&FlexDaysOut=6&INF=0&RoundTrip=true&TEEN=0");
		addURLParam("&Origin",skad);
		addURLParam("&Destination",dokad);
	}

	@Override
	public List<HttpGetFlight> makeRequestList() {
		ArrayList<HttpGetFlight> list = new ArrayList<HttpGetFlight>();
		if(getMonth()!=-1 && getYear()!=-1) {
			List<Daty> dates = generateListOfDates();
			for(Daty d : dates) {
				String url = getCoreURL();
				url += "&DateOut="+d.dataOd;
				url += "&DateIn="+d.dataDo;
				list.add(new HttpGetFlight(getId(), url,"Ryanair"));
			}
		} else {
			HttpGetFlight request = makeBasicRequest();
			if(request!=null) {
				list.add(request);
			}
		}
		return list;
	}
	
	private List<Daty> generateListOfDates() {
		String datePattern = "YYYY-MM-dd";
		ArrayList<Daty>lista = new ArrayList<RyanRequest.Daty>();
		LocalDateTime dt = new LocalDateTime(getYear(),getMonth(),1,0,0);
		LocalDateTime nextDt = new LocalDateTime(dt).plusMonths(1);
		dt = dt.withDayOfWeek(1);
		nextDt = nextDt.withDayOfMonth(1);
		nextDt = nextDt.withDayOfWeek(7);
		while(dt.compareTo(nextDt) < 1) {
			Daty daty = new Daty();
			daty.dataOd = dt.toString(datePattern);
			dt = dt.plusDays(7);
			daty.dataDo = dt.toString(datePattern);
			lista.add(daty);
		}
		return lista;
	}
	
	class Daty {
		String dataOd;
		String dataDo;
	}

	@Override
	protected HttpGetFlight makeBasicRequest() {
		String url = getCoreURL();
		if(url!=null) {
			return new HttpGetFlight(getId(), url,"Ryanair");
		}
		return null;
	}
}
