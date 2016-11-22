package pl.poblock.connection.decode;

import pl.poblock.connection.decode.ryan.RyanDecoder;
import pl.poblock.connection.decode.wizz.WizzDecoder;

public class DecoderFactory {

	public static Decoder getDecoder(String url, String response) {
		if(url.contains("wizz")) {
			return new WizzDecoder(response);
		} else {
			return new RyanDecoder(response);
		}
	}
}
