package pl.poblock.connection.decode.wizz;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import pl.poblock.connection.Lot;
import pl.poblock.connection.decode.Decoder;
import pl.poblock.connection.decode.wizz.model.Departure;
import pl.poblock.connection.decode.wizz.model.Flight;

public class WizzDecoder extends Decoder {
	public WizzDecoder(String res) {
		super(res);
	}

	@Override
	public List<Lot> decode() {
		List<Lot> lista = new ArrayList<Lot>();
		if(response!=null) {
			Gson gson = new Gson();  
			Type responseListType = new TypeToken<ArrayList<Departure>>(){}.getType();
			ArrayList<Departure> list = gson.fromJson(response, responseListType);
			for(Departure d : list) {
				if(d.getFlights()!=null && d.getFlights().size()>0) {
					for(Flight f : d.getFlights()) {
						WizzLotBuilder b = new WizzLotBuilder(d.getDepartureStationCode(), d.getArrivalStationCode(), 
								d.getCurrentDate(), d.getMinimumPrice(), f);
						System.out.println(b.getLot());
					}
				}
			}
		}
		return lista;
	}
}
