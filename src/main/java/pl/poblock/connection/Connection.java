package pl.poblock.connection;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
		} finally {
			httpclient.close();
		}
	}
	
	public void connect(List<Polaczenia> polaczenia, int month, int year) {
		int id = 0;
		
//		HashMap<>
		
		for(Polaczenia lista : polaczenia) {
			
		}
		
        ArrayList<HttpGetFlight> listReq = new ArrayList<HttpGetFlight>();
		for(Polaczenia lista : polaczenia) {
			for(Polaczenie p : lista.getPolaczenia()) {
				List<HttpGetFlight> list = RequestFactory.getRequestList(id, p, month, year);
				if(list!=null) {
					listReq.addAll(list);
				}
			}
			id++;
		}
	}
	
	private void connect(String skad, String dokad, int month, int year) throws IOException, InterruptedException {
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
	
	static class GetThread extends Thread {

        private final CloseableHttpClient httpClient;
        private final HttpContext context;
        private final HttpGetFlight httpget;
        private final int id;
        private String responseString;
        
        public GetThread(CloseableHttpClient httpClient, HttpGetFlight httpget, int id) {
            this.httpClient = httpClient;
            this.context = new BasicHttpContext();
            this.httpget = httpget;
            this.id = id;
        }
        
        @Override
        public void run() {
            try {
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

    }
}
