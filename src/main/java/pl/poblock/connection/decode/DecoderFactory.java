package pl.poblock.connection.decode;

import pl.poblock.connection.decode.ryan.RyanDecoder;
import pl.poblock.connection.decode.wizz.WizzDecoder;

public class DecoderFactory {

	public static Decoder getDecoder(String linia, String response) {
		if(linia.equals("Wizzair")) {
			return new WizzDecoder(response);
		} else if(linia.equals("Ryanair")) {
			return new RyanDecoder(response);
		}
		return null;
	}
}
