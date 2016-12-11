package pl.poblock.model.request;

import java.util.List;

public class Polaczenia {
	private long id;
	private List<Polaczenie> polaczenia;
	public Polaczenia(long id, List<Polaczenie> polaczenia) {
		this.id = id;
		this.polaczenia = polaczenia;
	}

	public List<Polaczenie> getPolaczenia() {
		return polaczenia;
	}

	public void setPolaczenia(List<Polaczenie> polaczenia) {
		this.polaczenia = polaczenia;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Polaczenia [id=" + id + ", polaczenia=" + polaczenia + "]";
	}

}
