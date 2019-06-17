package com.ripple.trustline.application;

import com.ripple.trustline.Service.CreditService;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.WebRequest;

@RestController
public class Controller {

	@Autowired
	public CreditService creditService;
	
	@Resource(name = "logger")
	private Logger log;
	
	@Value("${peer}")
	private String peer;
	
	@Value("${name}")
	private String name;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping(value = "/trustline/balance", method = RequestMethod.GET, produces = {"application/JSON"})
	Map<String, Object> getBalance(){
		Map<String, Object> map = new HashMap<String, Object>();;
		map.put("balance",trustline.getBalance());
	    return map;									
	}

	@RequestMapping(value = "/trustline/credit", method = RequestMethod.POST, consumes = {"application/JSON"},
			produces = {"application/JSON"})
	Map<String, Object> CreateDeposit(@RequestBody Map<String, String> payload) {
		Map<String, Object> map;



    	// if(validatorService.validCredit(payload))
		Integer creditAmt = 20;
		Integer balance = creditService.CreateDeposit(creditAmt);

		map = new HashMap<String, Object>();
		map.put("balance",balance);
		return map;

	}

    @RequestMapping(value = "/trustline/debit", method = RequestMethod.POST, consumes = {"application/JSON"}, produces = {"application/JSON"})
    public Map<String, Object>  CreateDebit(@RequestBody Map<String, String> payload) {
    	Map<String, Object> map;
    	HttpHeaders headers;
    	HttpEntity<Map<String, Object>> entity;
    	ResponseEntity<String> response;
    	 
		if (!payload.containsKey("debit")) {
			log.debug("Payload does not contain a debit field");
			throw new BadDebitValueException("Payload does not contain a debit field");
		}
		
		log.info("Paying " + payload.get("debit") + " to " + name);
		// create a request to peer's credit API.
		headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        map = new HashMap<String,Object>();
        map.put("credit", payload.get("debit"));
        entity = new HttpEntity<Map<String, Object>>(map,headers);
        
        response = restTemplate.exchange(
           peer+"/trustline/credit", HttpMethod.POST, entity, String.class);
        if (response.getStatusCode() != HttpStatus.OK) {
        	log.debug("peer returned http status: " + response.getStatusCode());
        	throw new RuntimeException("peer returned http status: " + response.getStatusCode());
        }
		
		trustline.debit(payload.get("debit"));
		log.info("Sent");
		log.info("Trustline balance is:" + trustline.getBalance());
		log.info("");
		map = new HashMap<String, Object>();
		map.put("balance",trustline.getBalance());
		return map;
	 }

	@ExceptionHandler(BadDebitValueException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Map<String, Object> handleBadDebitValueException(BadDebitValueException e, WebRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error",e.getMessage());
	    return map;									
	    
	  }
	
	@ExceptionHandler(BadCreditValueException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public final ResponseEntity<String> handleBadDepositValueException(BadCreditValueException e, WebRequest request) {
	    return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	  }
}
