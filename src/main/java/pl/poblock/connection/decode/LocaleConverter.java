package pl.poblock.connection.decode;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;
import org.joda.time.Period;

import pl.poblock.FlightDecoder;

public class LocaleConverter {
	private String[] currencies = {"AED","BAM","BGN","CHF","CZK","EUR","GBP","GEL","HRK","HUF","ILS","MKD","NOKSEK","PLN","RON","RSD","UAH","USD"};
	private String[][] alternatives = {
			{}, //AED
			{}, //BAM
			{"lv"}, //BGN
			{"SFr"}, //CHF
			{"Kč"}, //CZK
			{"�","€"}, //EUR TODO
			{"£"}, //GBP
			{"GEL"}, //GEL
			{}, //HRK
			{"Ft"}, //HUF
			{"₪"}, //ILS
			{"MKD"}, //MKD
			{"kr"}, //NOKSEK
			{"z�","zł"}, //PLN TODO
			{"lei"}, //RON
			{"din"}, //RSD
			{"UAH"}, //UAH
			{}  //USD
	};
	private Double[] values = {
		1.13, //AED
		2.26, //BAM
		2.26, //BGN
		4.2, //CHF
		0.16, //CZK
		4.5, //EUR
		5.2, //GBP
		1.68, //GEL
		0.59, //HRK
		0.01, //HUF
		1.08, //ILS
		0.07, //MKD
		0.5, //NOKSEK
		1.00, //PLN
		0.99, //RON
		0.04, //RSD
		0.16, //UAH
		4.16  //USD
	};
	private HashMap<String, ArrayList<String>> codes = null;
	private HashMap<String, Double> currencyValues = null;
	private HashMap<String, String> timezoneMap = null;
	private static LocaleConverter INSTANCE = null;
	
	private LocaleConverter() {
		codes = new HashMap<String, ArrayList<String>>();
		currencyValues = new HashMap<String, Double>();
		for(int i=0; i<currencies.length; i++) {
			String code = currencies[i];
			String[] alter = alternatives[i];
			ArrayList<String> lista = null;
			if(alter!=null && alter.length>0) {
				lista = new ArrayList<String>();
				for(String s : alter) {
					lista.add(s);
				}
			}
			codes.put(code, lista);	
			currencyValues.put(code, values[i]);
		}
		
		timezoneMap = new HashMap<String, String>();
		timezoneMap.put("BOJ","Europe/Sofia");
		timezoneMap.put("SOF","Europe/Sofia");
		timezoneMap.put("PDV","Europe/Sofia");
		timezoneMap.put("LCA","Asia/Nicosia");
		timezoneMap.put("PFO","Asia/Nicosia");
		timezoneMap.put("TLL","Europe/Tallinn");
		timezoneMap.put("LPP","Europe/Helsinki");
		timezoneMap.put("TMP","Europe/Helsinki");
		timezoneMap.put("TKU","Europe/Helsinki");
		timezoneMap.put("RVN","Europe/Helsinki");
		timezoneMap.put("GLA","Europe/London");
		timezoneMap.put("LDY","Europe/London");
		timezoneMap.put("INV","Europe/London");
		timezoneMap.put("LTN","Europe/London");
		timezoneMap.put("NWI","Europe/London");
		timezoneMap.put("BOH","Europe/London");
		timezoneMap.put("DSA","Europe/London");
		timezoneMap.put("EDI","Europe/London");
		timezoneMap.put("LBA","Europe/London");
		timezoneMap.put("PIK","Europe/London");
		timezoneMap.put("LPL","Europe/London");
		timezoneMap.put("BLK","Europe/London");
		timezoneMap.put("MAN","Europe/London");
		timezoneMap.put("NCL","Europe/London");
		timezoneMap.put("NQY","Europe/London");
		timezoneMap.put("HUY","Europe/London");
		timezoneMap.put("BHX","Europe/London");
		timezoneMap.put("BHD","Europe/London");
		timezoneMap.put("MME","Europe/London");
		timezoneMap.put("EMA","Europe/London");
		timezoneMap.put("BFS","Europe/London");
		timezoneMap.put("CWL","Europe/London");
		timezoneMap.put("ABZ","Europe/London");
		timezoneMap.put("LGW","Europe/London");
		timezoneMap.put("STN","Europe/London");
		timezoneMap.put("BRS","Europe/London");
		timezoneMap.put("KUT","Asia/Tbilisi");
		timezoneMap.put("TBS","Asia/Tbilisi");
		timezoneMap.put("EFL","Europe/Athens");
		timezoneMap.put("JMK","Europe/Athens");
		timezoneMap.put("KLX","Europe/Athens");
		timezoneMap.put("HER","Europe/Athens");
		timezoneMap.put("SKG","Europe/Athens");
		timezoneMap.put("AXD","Europe/Athens");
		timezoneMap.put("RHO","Europe/Athens");
		timezoneMap.put("VOL","Europe/Athens");
		timezoneMap.put("CHQ","Europe/Athens");
		timezoneMap.put("GPA","Europe/Athens");
		timezoneMap.put("KGS","Europe/Athens");
		timezoneMap.put("ATH","Europe/Athens");
		timezoneMap.put("CFU","Europe/Athens");
		timezoneMap.put("ZTH","Europe/Athens");
		timezoneMap.put("JTR","Europe/Athens");
		timezoneMap.put("DUB","Europe/Dublin");
		timezoneMap.put("SNN","Europe/Dublin");
		timezoneMap.put("ORK","Europe/Dublin");
		timezoneMap.put("KIR","Europe/Dublin");
		timezoneMap.put("NOC","Europe/Dublin");
		timezoneMap.put("TLV","Asia/Tel_Aviv");
		timezoneMap.put("VDA","Asia/Tel_Aviv");
		timezoneMap.put("PLQ","Europe/Vilnius");
		timezoneMap.put("VNO","Europe/Vilnius");
		timezoneMap.put("KUN","Europe/Vilnius");
		timezoneMap.put("RIX","Europe/Riga");
		timezoneMap.put("RAK","Africa/Casablanca");
		timezoneMap.put("FEZ","Africa/Casablanca");
		timezoneMap.put("ESU","Africa/Casablanca");
		timezoneMap.put("NDR","Africa/Casablanca");
		timezoneMap.put("OUD","Africa/Casablanca");
		timezoneMap.put("TNG","Africa/Casablanca");
		timezoneMap.put("AGA","Africa/Casablanca");
		timezoneMap.put("RBA","Africa/Casablanca");
		timezoneMap.put("FAO","Europe/Lisbon");
		timezoneMap.put("PDL","Europe/Lisbon");
		timezoneMap.put("OPO","Europe/Lisbon");
		timezoneMap.put("LIS","Europe/Lisbon");
		timezoneMap.put("TER","Europe/Lisbon");
		timezoneMap.put("CRA","Europe/Bucharest");
		timezoneMap.put("TSR","Europe/Bucharest");
		timezoneMap.put("CND","Europe/Bucharest");
		timezoneMap.put("OTP","Europe/Bucharest");
		timezoneMap.put("ARW","Europe/Bucharest");
		timezoneMap.put("TGM","Europe/Bucharest");
		timezoneMap.put("OMR","Europe/Bucharest");
		timezoneMap.put("KEF","Iceland");
	}
	
	public static LocaleConverter getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new LocaleConverter();
		}
		return INSTANCE;
	}
	
	public String convertWizzCurrency(String cena) {
		Iterator<Entry<String, ArrayList<String>>> it = codes.entrySet().iterator();
		while(it.hasNext()) {
			Entry<String, ArrayList<String>> entry = it.next();
			ArrayList<String> lista = entry.getValue();
			String waluta = entry.getKey();
			if(lista!=null && lista.size()>0) {
				for(String s : lista) {
					if(cena.contains(s)) {
						String value = cena.substring(0, cena.indexOf(s)).trim();
						value = value.replaceAll(",", ".");
						value = value.replaceAll(" ","");
						value = value.replaceAll(" ", "");
						if(FlightDecoder.CZY_WDC) {
							return getDiscountAmount(value, waluta);
						} else {
							return getAmount(value, waluta);
						}
					}
				}
			}
		}
		return null;
	}
	
	public String getDiscountAmount(String value, String waluta) {
		double dValue = Double.parseDouble(value);
		double kurs = currencyValues.get(waluta);
		double cena = roundValue(dValue*kurs).doubleValue();
		if(cena > 75) {
			cena -= 45.0;
		} else {
			cena = 39.0;
		}
		return roundValue(cena).toString();
	}
	
	private BigDecimal roundValue(double value) {
		return new BigDecimal(value).setScale(2, RoundingMode.HALF_UP);
	}
	
	public String getAmount(String value, String waluta) {
		double dValue = Double.parseDouble(value);
		double kurs = currencyValues.get(waluta);
		return roundValue(dValue*kurs).toString();
	}
	
	private DateTimeZone getDTZ(String code) {
		String id = timezoneMap.get(code);
		if(id!=null) {
			return DateTimeZone.forID(id);
		} else {
			return DateTimeZone.forID("Europe/Warsaw");
		}
	}
	
	public String convertFlightTime(String skad, LocalDateTime przylot, String dokad, LocalDateTime wylot) {
		DateTime wylotTZ = wylot.toDateTime(getDTZ(skad));
		DateTime przylotTZ = przylot.toDateTime(getDTZ(dokad));
		Period p = new Period(wylotTZ,przylotTZ);
		if(p.getHours()>=0 && p.getMinutes()>=0) {
			String strH;
			if(p.getHours()<10) {
				strH = "0"+String.valueOf(p.getHours());
			} else {
				strH = String.valueOf(p.getHours());
			}
			String strM;
			if(p.getMinutes()<10) {
				strM = "0"+String.valueOf(p.getMinutes());
			} else {
				strM = String.valueOf(p.getMinutes());
			}
			return strH+":"+strM;
		} else {
			return "";
		}
	}
}
