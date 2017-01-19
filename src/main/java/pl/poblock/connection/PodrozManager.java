package pl.poblock.connection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.joda.time.Days;
import org.joda.time.LocalDateTime;

import pl.poblock.FlightDecoder;
import pl.poblock.model.request.Polaczenia;
import pl.poblock.model.response.Loty;
import pl.poblock.model.response.Podroz;

public class PodrozManager {

	private HashMap<Polaczenia, ArrayList<Loty>> polaczeniaTam = new HashMap<Polaczenia, ArrayList<Loty>>();
	private HashMap<Polaczenia, ArrayList<Loty>> polaczeniaPowrot = new HashMap<Polaczenia, ArrayList<Loty>>();
	private TreeMap<LocalDateTime, Loty> mapaDatTam = new TreeMap<LocalDateTime, Loty>();
	private TreeMap<LocalDateTime, Loty> mapaDatPowrot = new TreeMap<LocalDateTime, Loty>();
	private TreeMap<Double, Podroz> mapaCen = new TreeMap<Double, Podroz>();
	
	public void dodajPolaczenia(Polaczenia p, ArrayList<Loty> lista, boolean isTam) {
		if(isTam) {
			polaczeniaTam.put(p, lista);
			for(Loty loty : lista) {
				loty.setPolaczeniaID(p.getId());
				LocalDateTime ldt = loty.getLocalDateTime();
				if(mapaDatTam.get(ldt)!=null) {
					Loty istniejace = mapaDatTam.get(ldt);
					if(loty.getSuma() < istniejace.getSuma()) {
						mapaDatTam.put(ldt, loty);
					}
				} else {
					mapaDatTam.put(ldt, loty);
				}
			}
		} else {
			polaczeniaPowrot.put(p, lista);
			for(Loty loty : lista) {
				loty.setPolaczeniaID(p.getId());
				LocalDateTime ldt = loty.getLocalDateTime();
				if(mapaDatPowrot.get(ldt)!=null) {
					Loty istniejace = mapaDatPowrot.get(ldt);
					if(loty.getSuma() < istniejace.getSuma()) {
						mapaDatPowrot.put(ldt, loty);
					}
				} else {
					mapaDatPowrot.put(ldt, loty);
				}
			}
		}
	}
	public void wypisz() {
		System.out.println("Podroze");
		System.out.println(polaczeniaTam.toString());
		System.out.println("---------------\nPodroze Powrotne");
		System.out.println(polaczeniaPowrot.toString());
		System.out.println("=======================================");
		System.out.println(mapaDatTam.toString());
		System.out.println("---------------\nPodroze Powrotne");
		System.out.println(mapaDatPowrot.toString());
	}
	public List<Podroz> szukaj(int top) {
		Iterator<Entry<LocalDateTime, Loty>> it = mapaDatTam.entrySet().iterator();
		while(it.hasNext()) {
			Entry<LocalDateTime, Loty> tam = it.next();
			List<Loty> powroty = znajdzPowrot(tam.getKey(), tam.getValue());
			if(powroty!=null && powroty.size()>0) {
				for(Loty powrot : powroty) {
					Podroz podroz = new Podroz(tam.getValue(), powrot);
					mapaCen.put(podroz.getCenaRazem(), podroz);
				}
			}
		}
		if(!mapaCen.isEmpty()) {
			List<Podroz> lista = new ArrayList<Podroz>(top);
			Iterator<Podroz> ite = mapaCen.values().iterator();
			int count = 0;
			while(ite.hasNext() && count<top) {
				lista.add(ite.next());
				count++;
			}
			return lista;
		}
		return null;
	}
	private List<Loty> znajdzPowrot(LocalDateTime data, Loty tam) {
		List<Loty> result = new ArrayList<Loty>();
		Iterator<Entry<LocalDateTime, Loty>> it = mapaDatPowrot.entrySet().iterator();
		boolean szukajDalej = true;
		while(it.hasNext() && szukajDalej) {
			Entry<LocalDateTime, Loty> powrot = it.next();
			LocalDateTime dataPowrotu = powrot.getKey();
			int dni = Days.daysBetween(data, dataPowrotu).getDays();
			if(dni>1 && dni<=FlightDecoder.MAX_LICZBA_DNI) {
				result.add(powrot.getValue());
			}
			szukajDalej = (dni<=FlightDecoder.MAX_LICZBA_DNI);
		}
		return result;
	}
}
