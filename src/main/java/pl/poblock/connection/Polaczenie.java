package pl.poblock.connection;

public class Polaczenie {
	private int id;
	private String skad;
	private String dokad;
	private String linia;
	public Polaczenie(String skad, String dokad, String linia) {
		this.skad = skad;
		this.dokad = dokad;
		this.linia = linia;
	}
	public Polaczenie(int id, String skad, String dokad, String linia) {
		this.id = id;
		this.skad = skad;
		this.dokad = dokad;
		this.linia = linia;
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
	public String getLinia() {
		return linia;
	}
	public void setLinia(String linia) {
		this.linia = linia;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Polaczenie [id=" + id + ", skad=" + skad + ", dokad=" + dokad + ", linia=" + linia + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dokad == null) ? 0 : dokad.hashCode());
		result = prime * result + ((linia == null) ? 0 : linia.hashCode());
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
		Polaczenie other = (Polaczenie) obj;
		if (dokad == null) {
			if (other.dokad != null)
				return false;
		} else if (!dokad.equals(other.dokad))
			return false;
		if (linia == null) {
			if (other.linia != null)
				return false;
		} else if (!linia.equals(other.linia))
			return false;
		if (skad == null) {
			if (other.skad != null)
				return false;
		} else if (!skad.equals(other.skad))
			return false;
		return true;
	}
	
}
