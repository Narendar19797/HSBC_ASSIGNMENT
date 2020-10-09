package com.assessment.hsbc.exchangeratesmicroservice.expos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.hsbc.exchangeratesmicroservice.utils.CustApiResp;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/fetch")
public class ExposController {

	private static final String AUTH_TOKEN = "Authorization";
	
	@Autowired
	private ExposService service;

	@RequestMapping(method = RequestMethod.GET, value = "/")
	public CustApiResp findAll(@RequestHeader(AUTH_TOKEN) String bearerToken) {
		return service.listAll();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{date}")
	public CustApiResp findByDate(@RequestHeader(AUTH_TOKEN) String bearerToken,@PathVariable String date) {
		
		return service.getByDate(date);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/frmToDates")
	public CustApiResp findBetweenDate(@RequestHeader(AUTH_TOKEN) String bearerToken,@RequestParam String frmDate, @RequestParam String toDate) {
		return service.findBetweenDate(frmDate, toDate);
	}

}
