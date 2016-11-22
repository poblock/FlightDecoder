package pl.poblock.connection.decode.ryan;

import pl.poblock.connection.Lot;
import pl.poblock.connection.decode.LotBuilder;
import pl.poblock.connection.decode.ryan.model.Fare;
import pl.poblock.connection.decode.ryan.model.Flight;

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
	
	public RyanLotBuilder(String skad, String dokad, Flight flight, String waluta) {
		this.lot = new Lot();
		this.skad = skad;
		this.dokad = dokad;
		this.waluta = waluta;
		convert(flight);
		build();
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
	
	private void convert(Flight flight) {
		if(flight!=null) {
			if(flight.getTime()!=null && flight.getTime().size()>0 && flight.getTime().size()==2) {
				this.czasWylotu = flight.getTime().get(0);
				this.czasPrzylotu = flight.getTime().get(1);
			}
			if(flight.getDuration()!=null) {
				this.czasLotu = flight.getDuration();
			}
			if(flight.getFaresLeft()!=null) {
				this.pozostaloMiejsc = flight.getFaresLeft();
			}
			if(flight.getRegularFare()!=null && flight.getRegularFare().getFares()!=null && flight.getRegularFare().getFares().size()>0) {
				Fare first = flight.getRegularFare().getFares().get(0);
				this.cena = first.getAmount();
			}
		}
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
		// TODO Auto-generated method stub
		
	}

	public void buildLinia() {
		lot.setLinia("Ryanair");
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
		lot.setCzasLotu(czasLotu);
	}
}
