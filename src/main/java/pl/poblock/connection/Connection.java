package pl.poblock.connection;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import pl.poblock.connection.decode.Decoder;
import pl.poblock.connection.decode.DecoderFactory;

public class Connection {
	
	private String skad = "GDN";
	private String dokad = "BGY";
	private String wizz = "https://cdn.static.wizzair.com/pl-PL/TimeTableAjax?departureIATA="+skad+"&arrivalIATA="+dokad+"&year=2017&month=3";
	private String ryan = "https://desktopapps.ryanair.com/pl-pl/availability?ADT=1&CHD=0"
			+ "&DateIn=2017-03-29&DateOut=2017-03-15&Destination="+dokad+"&FlexDaysIn=6&FlexDaysOut=6&INF=0&Origin="+skad+"&RoundTrip=true&TEEN=0";
	
	public Connection() {
		try {
			sendGet(wizz);
			sendGet(ryan);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void sendGet(String url) throws Exception {
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		String strResponse = response.toString();
		if(strResponse!=null) {
			Decoder decoder = DecoderFactory.getDecoder(url, strResponse);
			decoder.decode();
		}
	}
}
