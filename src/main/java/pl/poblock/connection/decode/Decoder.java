package pl.poblock.connection.decode;

import java.util.List;

import pl.poblock.model.response.Lot;

public abstract class Decoder {
	protected String response;
	public Decoder(String response) {
		this.response = response;
	}
	public abstract List<Lot> decode();
}
