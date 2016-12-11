package pl.poblock.connection.decode.ryan;

import org.joda.time.LocalDateTime;

import pl.poblock.connection.decode.LocaleConverter;
import pl.poblock.connection.decode.LotBuilder;
import pl.poblock.connection.decode.ryan.model.Fare;
import pl.poblock.connection.decode.ryan.model.Flight;
import pl.poblock.model.response.Lot;

public class RyanLotBuilder implements LotBuilder {

	private Lot lot;
	private String skad;
	private String dokad;
	private String czasWylotu;
	private String czasPrzylotu;
	private String czasLotu;
	private String pozostaloMiejsc;
	private String cena;
	private String waluta;
	private String dataWylotu;
	private String dataPrzylotu;
	private String godzinaWylotu;
	private String godzinaPrzylotu;
	private Flight flight;
	
	public RyanLotBuilder(String skad, String dokad, Flight flight, String waluta) {
		this.skad = skad;
		this.dokad = dokad;
		this.waluta = waluta;
		this.flight = flight;
		if(flight!=null) {
			if(flight.getFaresLeft()!=null) {
				this.pozostaloMiejsc = flight.getFaresLeft();
			}
		}
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
		return "RyanLotBuilder [skad=" + skad + ", dokad=" + dokad + ", czasWylotu=" + czasWylotu + ", czasPrzylotu="
				+ czasPrzylotu + ", czasLotu=" + czasLotu + ", pozostaloMiejsc=" + pozostaloMiejsc + ", cena=" + cena
				+ ", waluta=" + waluta + "]";
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
		if(flight!=null) {
			if(flight.getRegularFare()!=null && flight.getRegularFare().getFares()!=null && flight.getRegularFare().getFares().size()>0) {
				Fare first = flight.getRegularFare().getFares().get(0);
				this.cena = LocaleConverter.getInstance().getAmount(first.getAmount(), waluta);
			}
		}
		lot.setCena(cena);
	}

	public void buildLinia() {
		lot.setLinia("Ryanair");
	}

	public void buildDataWylotu() {
		if(flight!=null) {
			if(flight.getTime()!=null && flight.getTime().size()>0 && flight.getTime().size()==2) {
				this.czasWylotu = flight.getTime().get(0);
				String ldt = new LocalDateTime(czasWylotu).toString();
				String[] data = ldt.split("T");
				this.dataWylotu = data[0];
				this.godzinaWylotu = data[1].substring(0, 5);
			}
		}
		lot.setDataWylotu(dataWylotu);
	}

	public void buildDataPrzylotu() {
		if(flight!=null) {
			if(flight.getTime()!=null && flight.getTime().size()>0 && flight.getTime().size()==2) {
				this.czasPrzylotu = flight.getTime().get(1);
				String ldt = new LocalDateTime(czasPrzylotu).toString();
				String[] data = ldt.split("T");
				this.dataPrzylotu = data[0];
				this.godzinaPrzylotu = data[1].substring(0, 5);
			}
		}
		lot.setDataPrzylotu(dataPrzylotu);
	}

	public void buildGodzinaWylotu() {
		lot.setGodzinaWylotu(godzinaWylotu);
	}

	public void buildGodzinaPrzylotu() {
		lot.setGodzinaPrzylotu(godzinaPrzylotu);
	}

	public void buildCzasLotu() {
		if(flight!=null) {
			if(flight.getDuration()!=null) {
				this.czasLotu = flight.getDuration();
			}
		}
		lot.setCzasLotu(czasLotu);
	}
}
