package pl.poblock.connection.decode;

import pl.poblock.model.response.Lot;

public interface LotBuilder {
	public Lot getLot();
	public void buildSkad();
	public void buildDokad();
	public void buildCena();
	public void buildLinia();
	public void buildDataWylotu();
	public void buildDataPrzylotu();
	public void buildGodzinaWylotu();
	public void buildGodzinaPrzylotu();
	public void buildCzasLotu();
}
