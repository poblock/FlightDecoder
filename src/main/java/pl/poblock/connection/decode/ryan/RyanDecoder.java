package pl.poblock.connection.decode.ryan;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import pl.poblock.connection.Lot;
import pl.poblock.connection.decode.Decoder;
import pl.poblock.connection.decode.ryan.model.Date;
import pl.poblock.connection.decode.ryan.model.Departure;
import pl.poblock.connection.decode.ryan.model.Flight;
import pl.poblock.connection.decode.ryan.model.Trip;


public class RyanDecoder extends Decoder {
	public RyanDecoder(String res) {
		super(res);
	}

	@Override
	public List<Lot> decode() {
		List<Lot> lista = new ArrayList<Lot>();
		if(response!=null) {
			Gson gson = new Gson();  
			Departure departure = gson.fromJson(response, Departure.class);
			if(departure.getTrips()!=null && departure.getTrips().size()>0) {
				for(Trip t : departure.getTrips()) {
					if(t.getDates()!=null && t.getDates().size()>0) {
						for(Date d : t.getDates()) {
							if(d.getFlights()!=null && d.getFlights().size()>0) {
								for(Flight f : d.getFlights()) {
									RyanLotBuilder b = new RyanLotBuilder(t.getOrigin(), t.getDestination(), f, departure.getCurrency());
									System.out.println(b.getLot());
								}
							}
						}
					}
				}
			}
		}
		return lista;
	}
}
