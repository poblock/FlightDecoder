package pl.poblock.connection;

public class Lot {
	private String skad;
	private String dokad;
	private String linia;
	private String dataWylotu;
	private String godzinaWylotu;
	private String dataPrzylotu;
	private String godzinaPrzylotu;
	private String czasLotu;
	private String cena;
	
	public Lot() {
		
	}

	public String getSkad() {
		return skad;
	}

	public void setSkad(String skad) {
		this.skad = skad;
	}

	public String getDokad() {
		return dokad;
	}

	public void setDokad(String dokad) {
		this.dokad = dokad;
	}

	public String getDataWylotu() {
		return dataWylotu;
	}

	public void setDataWylotu(String dataWylotu) {
		this.dataWylotu = dataWylotu;
	}

	public String getGodzinaWylotu() {
		return godzinaWylotu;
	}

	public void setGodzinaWylotu(String godzinaWylotu) {
		this.godzinaWylotu = godzinaWylotu;
	}

	public String getDataPrzylotu() {
		return dataPrzylotu;
	}

	public void setDataPrzylotu(String dataPrzylotu) {
		this.dataPrzylotu = dataPrzylotu;
	}

	public String getGodzinaPrzylotu() {
		return godzinaPrzylotu;
	}

	public void setGodzinaPrzylotu(String godzinaPrzylotu) {
		this.godzinaPrzylotu = godzinaPrzylotu;
	}

	public String getCzasLotu() {
		return czasLotu;
	}

	public void setCzasLotu(String czasLotu) {
		this.czasLotu = czasLotu;
	}

	public String getCena() {
		return cena;
	}

	public void setCena(String cena) {
		this.cena = cena;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cena == null) ? 0 : cena.hashCode());
		result = prime * result + ((czasLotu == null) ? 0 : czasLotu.hashCode());
		result = prime * result + ((dataPrzylotu == null) ? 0 : dataPrzylotu.hashCode());
		result = prime * result + ((dataWylotu == null) ? 0 : dataWylotu.hashCode());
		result = prime * result + ((dokad == null) ? 0 : dokad.hashCode());
		result = prime * result + ((godzinaPrzylotu == null) ? 0 : godzinaPrzylotu.hashCode());
		result = prime * result + ((godzinaWylotu == null) ? 0 : godzinaWylotu.hashCode());
		result = prime * result + ((skad == null) ? 0 : skad.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lot other = (Lot) obj;
		if (cena == null) {
			if (other.cena != null)
				return false;
		} else if (!cena.equals(other.cena))
			return false;
		if (czasLotu == null) {
			if (other.czasLotu != null)
				return false;
		} else if (!czasLotu.equals(other.czasLotu))
			return false;
		if (dataPrzylotu == null) {
			if (other.dataPrzylotu != null)
				return false;
		} else if (!dataPrzylotu.equals(other.dataPrzylotu))
			return false;
		if (dataWylotu == null) {
			if (other.dataWylotu != null)
				return false;
		} else if (!dataWylotu.equals(other.dataWylotu))
			return false;
		if (dokad == null) {
			if (other.dokad != null)
				return false;
		} else if (!dokad.equals(other.dokad))
			return false;
		if (godzinaPrzylotu == null) {
			if (other.godzinaPrzylotu != null)
				return false;
		} else if (!godzinaPrzylotu.equals(other.godzinaPrzylotu))
			return false;
		if (godzinaWylotu == null) {
			if (other.godzinaWylotu != null)
				return false;
		} else if (!godzinaWylotu.equals(other.godzinaWylotu))
			return false;
		if (skad == null) {
			if (other.skad != null)
				return false;
		} else if (!skad.equals(other.skad))
			return false;
		return true;
	}

	public String getLinia() {
		return linia;
	}

	public void setLinia(String linia) {
		this.linia = linia;
	}

	@Override
	public String toString() {
		return "Lot [skad=" + skad + ", dokad=" + dokad + ", linia=" + linia + ", dataWylotu=" + dataWylotu
				+ ", godzinaWylotu=" + godzinaWylotu + ", dataPrzylotu=" + dataPrzylotu + ", godzinaPrzylotu="
				+ godzinaPrzylotu + ", czasLotu=" + czasLotu + ", cena=" + cena + "]";
	}
}
