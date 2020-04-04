package me.sreejithag;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
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
		try {
			String result = restTemplate.getForObject(url,String.class);
			JSONParser parser = new JSONParser();
			JSONObject resultJSON = (JSONObject) parser.parse(result);
			JSONObject resultRates = (JSONObject) resultJSON.get("rates");  
			rate =  (double) resultRates.get(to);
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public double convert(double amount) {
		return this.rate*amount;
	}
	public double getRate() {
		return this.rate;
	}
}
