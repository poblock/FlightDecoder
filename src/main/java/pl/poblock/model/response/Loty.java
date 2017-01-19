package pl.poblock.model.response;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDateTime;

public class Loty {
	private List<Lot> loty;
	private int id;
	private String data;
	private String godzina;
	private Double suma = -1.0;
	private long polaczeniaID;
	private LocalDateTime localDateTime;
	
	public Loty() {
	}
	
	public void dodajLot(Lot lot, boolean isTam) {
		boolean isFirst = false;
		if(loty==null) {
			loty = new ArrayList<Lot>();
			isFirst = true;
		}
		loty.add(lot);
		if(isTam) { // lot tam liczony od czasu ostatniego przylotu
			setData(lot.getDataPrzylotu());
			setGodzina(lot.getGodzinaPrzylotu());
		} else if(!isTam && isFirst) { // lot powrotny liczony od czasu pierwszego wylotu
			setData(lot.getDataWylotu());
			setGodzina(lot.getGodzinaWylotu());
		}
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
		if(suma == -1.0) {
			Double sum = 0.0;
			for(Lot lot : loty) {
				sum += Double.parseDouble(lot.getCena());
			}
			setSuma(sum);
		}
		return suma;
	}

	public void setSuma(Double suma) {
		this.suma = suma;
	}

	public long getPolaczeniaID() {
		return polaczeniaID;
	}

	public void setPolaczeniaID(long polaczeniaID) {
		this.polaczeniaID = polaczeniaID;
	}

	public LocalDateTime getLocalDateTime() {
		if(localDateTime == null) {
			setLocalDateTime(LocalDateTime.parse(getData()+"T"+getGodzina()));
		}
		return localDateTime;
	}

	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}

	@Override
	public String toString() {
		return "Loty [id=" + id + ", polaczeniaID=" + polaczeniaID + ", data=" + data + ", godzina=" + godzina + ", suma=" + suma + ", loty=" + loty + "]";
	}
}
