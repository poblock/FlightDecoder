package pl.poblock.connection;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.LocalDateTime;

import pl.poblock.model.response.Lot;
import pl.poblock.model.response.Loty;

public class LotManager {
	private static final Double MAX_SUMA_LOTOW_W_JEDNA_STRONE = 1000.00;
	private ArrayList<Loty> arrayLoty = new ArrayList<Loty>();
	private int etapIteration = 0;
	
	private Loty znajdzLoty(Lot lot) {
		for(Loty l : arrayLoty) {
			if(l.getLoty().size()!=etapIteration) {
				if(fulFillCriteria(lot, l)) {
					return l;
				}
			}
		}
		return null;
	}
	
	private boolean fulFillCriteria(Lot l, Loty loty) {
		String data = l.getDataWylotu();
		String godzina = l.getGodzinaWylotu();
		LocalDateTime ldt = new LocalDateTime(data+"T"+godzina);
		LocalDateTime last = new LocalDateTime(loty.getData()+"T"+loty.getGodzina());
		int days = Days.daysBetween(last, ldt).getDays();
		int hours = Hours.hoursBetween(last, ldt).getHours();
		return ldt.isAfter(last) && (days==0 || days==1) && (hours > 1);
	}
	
	public void dodajKolejnyEtap(List<Lot> listaLotow) {
		etapIteration++;
		for(Lot l : listaLotow) {
			if(etapIteration == 1) {
				Loty loty = new Loty();
				loty.dodajLot(l);
				arrayLoty.add(loty);
			} else {
				Loty loty = znajdzLoty(l);
				if(loty!=null) {
					loty.dodajLot(l);
					arrayLoty.add(loty);
				}
			}
		}
		sprawdzZgodnosc();
	}

	private void sprawdzZgodnosc() {
		ArrayList<Loty> toRemove = new ArrayList<Loty>();
		for(Loty l : arrayLoty) {
			if(l.getLoty().size()!=etapIteration) {
				toRemove.add(l);
			}
		}
		if(toRemove.size()>0) {
			for(Loty remove : toRemove) {
				arrayLoty.remove(remove);
			}
		}
	}
	
	public ArrayList<Loty> dajTanieLoty() {
		ArrayList<Loty> kopia = new ArrayList<Loty>();
		for(Loty loty : arrayLoty) {
			if(loty.getSuma() < MAX_SUMA_LOTOW_W_JEDNA_STRONE) {
				kopia.add(loty);
			}
		}
		return kopia;
	}
}
