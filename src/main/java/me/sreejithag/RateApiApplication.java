package me.sreejithag;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class RateApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RateApiApplication.class, args);
	}
	@RequestMapping(value="/")
	public String api_response() {
		return "Rate_api Works";
	}
	@RequestMapping(value="/convertionrate")
	public String getConvertionRate(@RequestParam(value = "from") String from, @RequestParam(value = "to") String to) throws IllegalAccessException {
		
		if(null == from || null == to  ) {
			throw new IllegalAccessException();
		}
		else {
			Services service = new Services();
			double res = service.getConvertionRate(from, to);
			if(res !=0) {
				return String.valueOf(Math.round(res*100)/100.00);
			}
			else {
				return "Error Invalid currency code";
			}
		}
	}
	
	
	@RequestMapping(value="/convert")
	public String convert(@RequestParam(value = "amount") Double amount,@RequestParam(value = "from") String from, @RequestParam(value = "to") String to) throws IllegalAccessException {
		
		if(null == from || null == to ) {
			throw new IllegalAccessException();
		}
		
		else {
			Services service = new Services();
			double res = service.convert(amount,from, to);
			if(res !=0) {
				return String.valueOf(Math.round(res*100)/100.00);
			}
			else {
				return "Error Invalid currency code or amount is null";
			}
		}
	}
	
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public String handleMissingParams(MissingServletRequestParameterException ex) {
	    String name = ex.getParameterName();
	    return (name + " parameter is missing");
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public String handleIlligalArgument(IllegalArgumentException ex) {
		return "Parameter can't be empty";
	}
	
	
	
}
