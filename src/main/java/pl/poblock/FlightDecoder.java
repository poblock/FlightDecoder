package pl.poblock;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pl.poblock.connection.Connection;
import pl.poblock.model.request.Polaczenia;
import pl.poblock.model.request.Polaczenie;
import pl.poblock.model.response.Podroz;

public class FlightDecoder {
	
	public static final Double MAX_CENA_W_JEDNA_STRONE = 800.00;
	public static final Integer MAX_LICZBA_DNI = 5;
	public static final Integer TOP_OFERT = 5;
	public static final Boolean CZY_WDC = true;
	
	public static void main(String[] args) {
		new FlightDecoder();
	}

	public FlightDecoder() {
		Connection c = null;
		try {
			ArrayList<Polaczenia> mockList = prepareMockList();
			c = new Connection();
			List<Podroz> wynik = c.connect(mockList, 5, 2017);
			System.out.println(wynik);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			c.close();
		}
	}
	
	private ArrayList<Polaczenia> prepareMockList() {
		ArrayList<Polaczenia> opcje = new ArrayList<Polaczenia>();
		long id = 0;
		opcje.add(add(id++, new Polaczenie("GDN","BCN","Wizzair"),new Polaczenie("BGY","AGP","Ryanair")));
		opcje.add(add(id++, new Polaczenie("GDN","BGY","Ryanair"),new Polaczenie("BGY","AGP","Ryanair")));
		opcje.add(add(id++, new Polaczenie("GDN","CRL","Wizzair"),new Polaczenie("CRL","AGP","Ryanair")));
		opcje.add(add(id++, new Polaczenie("GDN","BGY","Wizzair"),new Polaczenie("BGY","AGP","Ryanair")));
		opcje.add(add(id++, new Polaczenie("GDN","KRK","Ryanair"),new Polaczenie("KRK","AGP","Ryanair")));
		opcje.add(add(id++, new Polaczenie("GDN","DTM","Wizzair"),new Polaczenie("DTM","AGP","Ryanair")));
		opcje.add(add(id++, new Polaczenie("GDN","CGN","Wizzair"),new Polaczenie("CGN","AGP","Ryanair")));
		opcje.add(add(id++, new Polaczenie("GDN","EIN","Wizzair"),new Polaczenie("EIN","AGP","Ryanair")));
//		opcje.add(add(id++, new Polaczenie("GDN","BLL","Wizzair"),new Polaczenie("BLL","AGP","Ryanair")));
		opcje.add(add(id++, new Polaczenie("GDN","HHN","Wizzair"),new Polaczenie("HHN","AGP","Ryanair")));
		return opcje;
	}
	
	private Polaczenia add(long id, Polaczenie... lista) {
		ArrayList<Polaczenie> polacz = new ArrayList<Polaczenie>();
		for(Polaczenie polaczenie : lista) {
			polacz.add(polaczenie);
		}
		return new Polaczenia(id, polacz);
	}
}
