package pl.poblock;

import java.io.IOException;
import java.util.ArrayList;

import pl.poblock.connection.Connection;
import pl.poblock.model.request.Polaczenia;
import pl.poblock.model.request.Polaczenie;

public class FlightDecoder {
	
	public static void main(String[] args) {
		new FlightDecoder();
	}

	public FlightDecoder() {
		Connection c = null;
		try {
			ArrayList<Polaczenia> mockList = prepareMockList();
			c = new Connection();
			c.connect(mockList, 1, 2017);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			c.close();
		}
	}
	
	private ArrayList<Polaczenia> prepareMockList() {
		ArrayList<Polaczenia> opcje = new ArrayList<Polaczenia>();
		long id = 0;
		opcje.add(add(id++, new Polaczenie("GDN","BGY","Wizzair"),new Polaczenie("BGY","ATH","Ryanair"),new Polaczenie("ATH","JTR","Ryanair")));
//		opcje.add(add(id++, new Polaczenie("GDN","BGY","Ryanair"),new Polaczenie("BGY","ATH","Ryanair"),new Polaczenie("ATH","JTR","Ryanair")));
		opcje.add(add(id++, new Polaczenie("GDN","CRL","Wizzair"),new Polaczenie("CRL","ATH","Ryanair"),new Polaczenie("ATH","JTR","Ryanair")));
//		opcje.add(add(id++, new Polaczenie("WMI","ATH","Ryanair"),new Polaczenie("ATH","JTR","Ryanair")));
//		opcje.add(add(id++, new Polaczenie("SXF","ATH","Ryanair"),new Polaczenie("ATH","JTR","Ryanair")));
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
