package me.sreejithag;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controler {
	
	@GetMapping(value="/")
	public String api_response() {
		return "Rate_api Works";
	}
	
	@GetMapping("/convertionrate/{from}/{to}")
	public <optional> Double getConvertionRate(@PathVariable("from") String from, @PathVariable("to") String to){
		CurrencyCovert currencyCovert = new CurrencyCovert(from, to);
		return currencyCovert.getRate();
	}
	
	
	@GetMapping(value="/convert/{from}/{to}/{amount}")
	public double convert(@PathVariable("from") String from,@PathVariable("to") String to, @PathVariable("amount") double amount){
		CurrencyCovert currencyCovert = new CurrencyCovert(from, to);
		return currencyCovert.convert(amount);
	}
	

}
