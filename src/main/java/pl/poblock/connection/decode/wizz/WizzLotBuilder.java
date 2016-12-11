package pl.poblock.connection.decode.wizz;

import org.joda.time.LocalDateTime;

import pl.poblock.connection.decode.LocaleConverter;
import pl.poblock.connection.decode.LotBuilder;
import pl.poblock.connection.decode.wizz.model.Flight;
import pl.poblock.model.response.Lot;

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
	private Flight flight;
	
	public WizzLotBuilder(String skad, String dokad, String data, String cena, Flight flight) {
		this.skad = skad;
		this.dokad = dokad;
		this.dataWylotu = data;
		this.dataPrzylotu = data;
		this.cena = cena;
		this.flight = flight;
		build();
	}

	public void build() {
		this.lot = new Lot();
		buildSkad();
		buildDokad();
		buildCena();
		buildLinia();
		buildDataWylotu();
		buildDataPrzylotu();
		buildGodzinaWylotu();
		buildGodzinaPrzylotu();
		buildCzasLotu();
	}
	
	@Override
	public String toString() {
		return "WizzLotBuilder [skad=" + skad + ", dokad=" + dokad + ", dataWylotu=" + dataWylotu
				+ ", godzinaWylotu=" + godzinaWylotu + ", dataPrzylotu=" + dataPrzylotu + ", godzinaPrzylotu="
				+ godzinaPrzylotu + ", czasLotu=" + czasLotu + ", cena=" + cena + "]";
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
		this.cena = LocaleConverter.getInstance().convertWizzCurrency(cena);
		lot.setCena(cena);
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
		if(flight!=null) {
			if(flight.getSTD()!=null) {
				this.godzinaWylotu = flight.getSTD();
			}
		}
		lot.setGodzinaWylotu(godzinaWylotu);
	}

	public void buildGodzinaPrzylotu() {
		if(flight!=null) {
			if(flight.getSTA()!=null) {
				this.godzinaPrzylotu = flight.getSTA();
			}
		}
		lot.setGodzinaPrzylotu(godzinaPrzylotu);
	}

	public void buildCzasLotu() {
		LocalDateTime przylot = LocalDateTime.parse(dataPrzylotu+"T"+godzinaPrzylotu);
		LocalDateTime wylot = LocalDateTime.parse(dataWylotu+"T"+godzinaWylotu);
		String flightTime = LocaleConverter.getInstance().convertFlightTime(skad, przylot, dokad, wylot);
		if(flightTime.equals("")) {
			przylot = przylot.plusDays(1);
			flightTime = LocaleConverter.getInstance().convertFlightTime(skad, przylot, dokad, wylot);
		}
		lot.setCzasLotu(flightTime);
	}
}
