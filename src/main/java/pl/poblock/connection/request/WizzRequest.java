package pl.poblock.connection.request;

import java.util.ArrayList;
import java.util.List;

public class WizzRequest extends AbstractRequest {
	
	public WizzRequest(int id, String skad, String dokad, int month, int year) {
		super(id, skad, dokad, month, year);
		setCoreURL("https://cdn.static.wizzair.com/pl-PL/TimeTableAjax?");
		addURLParam("departureIATA",skad);
		addURLParam("&arrivalIATA",dokad);
		addURLParam("&month",String.valueOf(month));
		addURLParam("&year",String.valueOf(year));
	}
	
	private void makeReturnURL() {
		setCoreURL("https://cdn.static.wizzair.com/pl-PL/TimeTableAjax?");
		addURLParam("departureIATA",getDokad());
		addURLParam("&arrivalIATA",getSkad());
		addURLParam("&month",String.valueOf(getMonth()));
		addURLParam("&year",String.valueOf(getYear()));
	}

	@Override
	public List<HttpGetFlight> makeRequestList() {
		ArrayList<HttpGetFlight> list = new ArrayList<HttpGetFlight>();
		HttpGetFlight request = makeBasicRequest();
		if(request!=null) {
			list.add(request);
		}
		makeReturnURL();
		request = makeBasicRequest();
		if(request!=null) {
			list.add(request);
		}
		return list;
	}

	@Override
	protected HttpGetFlight makeBasicRequest() {
		String url = getCoreURL();
		if(url!=null) {
			return new HttpGetFlight(url,"Wizzair");
		}
		return null;
	}

}
