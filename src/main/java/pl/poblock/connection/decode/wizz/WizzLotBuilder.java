package pl.poblock.connection.decode.wizz;

import pl.poblock.connection.Lot;
import pl.poblock.connection.decode.LotBuilder;
import pl.poblock.connection.decode.wizz.model.Flight;

public class WizzLotBuilder implements LotBuilder {
	private Lot lot;
	private String skad;
	private String dokad;
	private String dataWylotu;
	private String godzinaWylotu;
	private String dataPrzylotu;
	private String godzinaPrzylotu;
	private String czasLotu;
	private String cena;
	private String waluta;
	
	public WizzLotBuilder(String skad, String dokad, String data, String cena, Flight flight) {
		this.lot = new Lot();
		this.skad = skad;
		this.dokad = dokad;
		this.dataWylotu = data;
		this.dataPrzylotu = data;
		convert(cena, flight);
		build();
	}

	private void convert(String cena, Flight flight) {
		if(flight!=null) {
			if(flight.getSTD()!=null) {
				this.godzinaWylotu = flight.getSTD();
			}
			if(flight.getSTA()!=null) {
				this.godzinaPrzylotu = flight.getSTA();
			}
		}
		
//		cena,waluta TODO
		this.cena = cena;
		this.waluta = cena;
	}

	public void build() {
		buildSkad();
		buildDokad();
		buildCena();
		buildLinia();
		buildDataWylotu();
		buildDataPrzylotu();
		buildGodzinaWylotu();
		buildGodzinaPrzylotu();
	}
	
	@Override
	public String toString() {
		return "WizzLotBuilder [skad=" + skad + ", dokad=" + dokad + ", dataWylotu=" + dataWylotu
				+ ", godzinaWylotu=" + godzinaWylotu + ", dataPrzylotu=" + dataPrzylotu + ", godzinaPrzylotu="
				+ godzinaPrzylotu + ", czasLotu=" + czasLotu + ", cena=" + cena + ", waluta=" + waluta + "]";
	}

	public Lot getLot() {
		return lot;
	}

	public void buildSkad() {
		lot.setSkad(skad);
	}

	public void buildDokad() {
		lot.setDokad(dokad);
	}

	public void buildCena() {
		// TODO Auto-generated method stub
		
	}

	public void buildLinia() {
		lot.setLinia("Wizzair");
	}

	public void buildDataWylotu() {
		lot.setDataWylotu(dataWylotu);
	}

	public void buildDataPrzylotu() {
		lot.setDataPrzylotu(dataPrzylotu);
	}

	public void buildGodzinaWylotu() {
		lot.setGodzinaWylotu(godzinaWylotu);
	}

	public void buildGodzinaPrzylotu() {
		lot.setGodzinaPrzylotu(godzinaPrzylotu);
	}

	public void buildCzasLotu() {
		// TODO Auto-generated method stub
		
	}
}
