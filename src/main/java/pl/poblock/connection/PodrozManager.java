package pl.poblock.connection;

import java.util.ArrayList;
import java.util.HashMap;

import pl.poblock.model.request.Polaczenia;
import pl.poblock.model.response.Loty;

public class PodrozManager {

	private HashMap<Polaczenia, ArrayList<Loty>> polaczeniaTam = new HashMap<Polaczenia, ArrayList<Loty>>();
	private HashMap<Polaczenia, ArrayList<Loty>> polaczeniaPowrot = new HashMap<Polaczenia, ArrayList<Loty>>();
	
	public void dodajPolaczeniaTam(Polaczenia p, ArrayList<Loty> lista) {
		polaczeniaTam.put(p, lista);
	}
	public void dodajPolaczeniaPowrot(Polaczenia p, ArrayList<Loty> lista) {
		polaczeniaPowrot.put(p, lista);
	}
	public void wypisz() {
		System.out.println("Podroze");
		System.out.println(polaczeniaTam.toString());
		System.out.println("--------");
		System.out.println(polaczeniaPowrot.toString());
	}
}
