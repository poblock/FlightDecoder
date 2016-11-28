package pl.poblock;

import java.io.IOException;

import pl.poblock.connection.Connection;

public class FlightDecoder {

	public static void main(String[] args) {
		try {
			new Connection();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
