package pl.poblock.connection;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map.Entry;
import java.util.Random;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import pl.poblock.connection.decode.Decoder;
import pl.poblock.connection.decode.DecoderFactory;
import pl.poblock.connection.request.HttpGetFlight;
import pl.poblock.connection.request.RequestFactory;
import pl.poblock.connection.request.RyanRequest;
import pl.poblock.connection.request.WizzRequest;
import pl.poblock.model.request.Polaczenia;
import pl.poblock.model.request.Polaczenie;
import pl.poblock.model.response.Lot;
import pl.poblock.model.response.Loty;
import pl.poblock.model.response.Podroz;

public class Connection {
	
	private PoolingHttpClientConnectionManager cm;
	private CloseableHttpClient httpclient;
	public Connection() throws IOException {
		try {
			cm = new PoolingHttpClientConnectionManager();
	        cm.setMaxTotal(100);
	        httpclient = HttpClients.custom().setConnectionManager(cm).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Podroz> connect(List<Polaczenia> polaczenia, int month, int year) {
        try {
        	HashMap<Polaczenie, Integer> uniqueID = new HashMap<Polaczenie, Integer>();
    		HashMap<Polaczenie, LinkedList<Long>> uniqueMap = new HashMap<Polaczenie, LinkedList<Long>>();
    		Random r = new Random();
    		for(Polaczenia lista : polaczenia) {
    			for(Polaczenie p : lista.getPolaczenia()) {
    				long listaID = lista.getId();
    				if(uniqueMap.containsKey(p)) {
    					uniqueMap.get(p).add(listaID);
    					Integer id = uniqueID.get(p);
    					p.setId(id);  					
    				} else {
    					LinkedList<Long> l = new LinkedList<Long>();
    					l.add(listaID);
    					boolean isUnique = false;
    					Integer i = -1;
    					while(!isUnique) {
    						i = r.nextInt(1000);
    						isUnique = !uniqueID.values().contains(i);
    					}
    					if(i!=-1) {
    						p.setId(i);
        					uniqueID.put(p, i);
        					uniqueMap.put(p, l);
    					}
    				}
    			}
    		}
    		ArrayList<HttpGetFlight> listReq = new ArrayList<HttpGetFlight>();
            Iterator<Polaczenie> it = uniqueMap.keySet().iterator();
            while(it.hasNext()) {
            	Polaczenie polaczenie = it.next();
            	List<HttpGetFlight> list = RequestFactory.getRequestList(polaczenie, month, year);
    			if(list!=null) {
    				listReq.addAll(list);
    			}
            }
        	HashMap<Integer, List<Lot>> flightsByRequestsIdMap = makeConnection(listReq);
        	if(flightsByRequestsIdMap!=null && flightsByRequestsIdMap.size()>0) {
                HashMap<Integer, List<Lot>> flightsFrom = new HashMap<Integer, List<Lot>>();
                HashMap<Integer, List<Lot>> flightsReturn = new HashMap<Integer, List<Lot>>();
                Iterator<Entry<Integer, List<Lot>>> fIterator = flightsByRequestsIdMap.entrySet().iterator();
                while(fIterator.hasNext()) {
                	Entry<Integer, List<Lot>> entry = fIterator.next();
                	Integer id = entry.getKey();
                	List<Lot> lista = entry.getValue();
                	Iterator<Lot> lotIterator = lista.iterator();
                	Lot first = lotIterator.next();
                	addLotToMap(id, first, flightsFrom);
                	
                	while(lotIterator.hasNext()) {
                		Lot next = lotIterator.next();
                		if(next.getSkad().equals(first.getSkad())) {
                			addLotToMap(id, next, flightsFrom);
                		} else if(next.getSkad().equals(first.getDokad())) {
                			addLotToMap(id, next, flightsReturn);
                		}
                	}
                }
                
                boolean czyPolaczeniaTam = true;
                PodrozManager pManager = new PodrozManager();
                for(Polaczenia pol : polaczenia) {
                	ArrayList<Loty> wyniki = prepareJourney(pol, flightsFrom, czyPolaczeniaTam);
                	if(wyniki!=null) {
                		pManager.dodajPolaczenia(pol, wyniki, czyPolaczeniaTam);
                	}
                }
                czyPolaczeniaTam = false;
                for(Polaczenia pol : polaczenia) {
                	ArrayList<Loty> wyniki = prepareJourney(pol, flightsReturn, czyPolaczeniaTam);
                	if(wyniki!=null) {
                		pManager.dodajPolaczenia(pol, wyniki, czyPolaczeniaTam);
                	}
                }
                return pManager.szukaj();
        	}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        return null;
	}
	
	private ArrayList<Loty> prepareJourney(Polaczenia polaczenia, HashMap<Integer, List<Lot>> map, boolean isTam) {		
		LotManager manager = new LotManager();
		if(isTam) {
			Iterator<Polaczenie> it = polaczenia.getPolaczenia().iterator();
			while(it.hasNext()) {
				Polaczenie nextStage = it.next();
				List<Lot> listaLotow = map.get(nextStage.getId());
				if(listaLotow!=null) {
					manager.dodajKolejnyEtap(listaLotow, isTam);
				}
			}
		} else {
			ListIterator<Polaczenie> li = polaczenia.getPolaczenia().listIterator(polaczenia.getPolaczenia().size());
			while(li.hasPrevious()) {
				Polaczenie previousStage = li.previous();
				List<Lot> listaLotow = map.get(previousStage.getId());
				if(listaLotow!=null) {
					manager.dodajKolejnyEtap(listaLotow, isTam);
				}
			}
		}
		
		return manager.dajTanieLoty();
	}
	
	private HashMap<Integer, List<Lot>> makeConnection(List<HttpGetFlight> listReq) throws InterruptedException {
		GetThread[] threads = new GetThread[listReq.size()];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new GetThread(httpclient, listReq.get(i), i + 1);
        }
        for (int j = 0; j < threads.length; j++) {
            threads[j].start();
        }
        for (int j = 0; j < threads.length; j++) {
            threads[j].join();
        }
        HashMap<Integer, List<Lot>> flightsByRequestsIdMap = new HashMap<Integer, List<Lot>>();
        LinkedList<Integer> failedThreadsList = new LinkedList<Integer>();
        for (int j = 0; j < threads.length; j++) {
        	HttpGetFlight request = threads[j].httpget;
        	String linia = request.getLinia();
        	String response = threads[j].getResponseString();
        	if(response!=null) {
        		Decoder decoder = DecoderFactory.getDecoder(linia, response);
    			if(decoder!=null) {
    				int requestID = request.getId();
    				List<Lot> lotLista = decoder.decode();
    				if(lotLista!=null && lotLista.size()>0) {
    					if(flightsByRequestsIdMap.containsKey(requestID)) {
    						flightsByRequestsIdMap.get(requestID).addAll(lotLista);
    					} else {
    						flightsByRequestsIdMap.put(requestID, lotLista);
    					}
    				}
    			}
        	} else {
        		failedThreadsList.add(j);
        	}
        }
        
        for(Integer i : failedThreadsList) {
        	System.out.println("Try to reconnect to "+i+" thread");
        }
		return flightsByRequestsIdMap;
	}

	private void addLotToMap(int id, Lot lot, HashMap<Integer, List<Lot>> map) {
		if(map.containsKey(id)) {
    		map.get(id).add(lot);
    	} else {
    		LinkedList<Lot> lis = new LinkedList<Lot>();
    		lis.add(lot);
    		map.put(id, lis);
    	}
	}
	
	public void connect(String skad, String dokad, int month, int year) throws IOException, InterruptedException {
            int id = 0;
            ArrayList<HttpGetFlight> listReq = new ArrayList<HttpGetFlight>();
            
            WizzRequest wizz = new WizzRequest(id++,skad,dokad,month,year);
            listReq.addAll(wizz.makeRequestList());
            RyanRequest ryan = new RyanRequest(id++,skad,dokad,month,year);
            listReq.addAll(ryan.makeRequestList());

            GetThread[] threads = new GetThread[listReq.size()];
            for (int i = 0; i < threads.length; i++) {
                threads[i] = new GetThread(httpclient, listReq.get(i), i + 1);
            }
            for (int j = 0; j < threads.length; j++) {
                threads[j].start();
            }
            for (int j = 0; j < threads.length; j++) {
                threads[j].join();
            } 
            for (int j = 0; j < threads.length; j++) {
            	Decoder decoder = DecoderFactory.getDecoder(threads[j].httpget.getLinia(), threads[j].getResponseString());
    			if(decoder!=null) {
    				decoder.decode();
    			}
            }
	}
	
	public void close() {
		try {
			httpclient.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	static class GetThread extends Thread {

        private final CloseableHttpClient httpClient;
        private final HttpContext context;
        private final HttpGetFlight httpget;
        private final int id;
        private String responseString = null;
        
        public GetThread(CloseableHttpClient httpClient, HttpGetFlight httpget, int id) {
            this.httpClient = httpClient;
            this.context = new BasicHttpContext();
            this.httpget = httpget;
            this.id = id;
        }
        
        @Override
        public void run() {
            try {
            	Thread.sleep(500);
                CloseableHttpResponse response = httpClient.execute(httpget, context);
                try {
                    HttpEntity entity = response.getEntity();
                    if (entity != null && response.getStatusLine().getStatusCode()==200) {
                    	byte[] bytes = EntityUtils.toByteArray(entity);
                    	String s = new String(bytes);
                    	if(s!=null) {
                    		setResponseString(s);
                    	}
                    }
                } finally {
                    response.close();
                }
            } catch (Exception e) {
                System.out.println(id + " - error: " + e);
            }
        }

		public String getResponseString() {
			return responseString;
		}

		public void setResponseString(String responseString) {
			this.responseString = responseString;
		}

		public int getConnectionThreadId() {
			return id;
		}

    }
}
