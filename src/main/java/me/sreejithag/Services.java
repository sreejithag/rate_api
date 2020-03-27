package me.sreejithag;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestTemplate;


public class Services {
	public double getConvertionRate(String from, String to ) {
		if((from.length()==3)&&(to.length()==3)) {
			from = from.toUpperCase();
			to = to.toUpperCase();
			final String url = "http://api.ratesapi.io/api/latest?symbols="+to+"&base="+from;
			RestTemplate restTemplate = new RestTemplate();
			try {
				String result = restTemplate.getForObject(url,String.class);
				JSONParser parser = new JSONParser();
				JSONObject resultJSON = (JSONObject) parser.parse(result);
				JSONObject resultRates = (JSONObject) resultJSON.get("rates");  
				double res =  (double) resultRates.get(to);
				return  res;
			}
			catch(Exception e) {
				System.out.println(e);
				return 0;
			}
		}
		return 0;
		
		
	}

	public double convert(Double amount, String from, String to) {
		if(amount != null) {
			double rate = getConvertionRate(from, to);
			System.out.println(rate);
			return rate*amount;
		}
		else {
				return 0;
		}
	}

}
