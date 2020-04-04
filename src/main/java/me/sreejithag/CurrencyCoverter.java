package me.sreejithag;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

public class CurrencyCoverter {
	String from;
	String to;
	double rate;
	
	public CurrencyCoverter(String from, String to) {
		from = from.toUpperCase();
		to = to.toUpperCase();
		final String url = "https://api.ratesapi.io/api/latest?symbols="+to+"&base="+from;
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		
		try {
			HttpEntity<String> response = restTemplate.exchange(url, HttpMethod.GET,entity,String.class);
			JSONParser parser = new JSONParser();
			JSONObject resultJSON = (JSONObject) parser.parse(response.getBody());
			JSONObject resultRates = (JSONObject) resultJSON.get("rates");  
			rate =  (double) resultRates.get(to);
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public double convert(double amount) {
		return Math.round(this.rate*amount*100)/100.00;
	}
	public double getRate() {
		return Math.round(this.rate*100)/100.00;
	}
}
