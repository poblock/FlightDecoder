package pl.poblock.model.response;

import java.util.ArrayList;
import java.util.List;

public class Loty {
	private List<Lot> loty;
	private int id;
	private String data;
	private String godzina;
	public Loty() {
		
	}
	
	public void dodajLot(Lot lot) {
		if(loty==null) {
			loty = new ArrayList<Lot>();
		}
		loty.add(lot);
		setData(lot.getDataPrzylotu());
		setGodzina(lot.getGodzinaPrzylotu());
	}

	public List<Lot> getLoty() {
		return loty;
	}

	public void setLoty(List<Lot> loty) {
		this.loty = loty;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getGodzina() {
		return godzina;
	}

	public void setGodzina(String godzina) {
		this.godzina = godzina;
	}
	
	public Double getSuma() {
		Double sum = 0.0;
		for(Lot lot : loty) {
			sum += Double.parseDouble(lot.getCena());
		}
		return sum;
	}

	@Override
	public String toString() {
		return "Loty [data=" + data + ", godzina=" + godzina +" loty=" + loty + "]";
	}
}
