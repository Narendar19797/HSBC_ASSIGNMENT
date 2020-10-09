package com.assessment.hsbc.exchangeratesmicroservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.hsbc.exchangeratesmicroservice.clients.ExchangeRatesClient;
import com.assessment.hsbc.exchangeratesmicroservice.utils.CustApiResp;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/expos")
public class ExposController {

	private static final String AUTH_TOKEN = "Authorization";
	
	@Autowired
	private ExchangeRatesClient client;
	
	@GetMapping("/")
	public CustApiResp findAll(@RequestHeader(AUTH_TOKEN) String bearerToken) {
		return client.findAll(bearerToken);
	}

	@GetMapping("/{date}")
	public CustApiResp findByDate(@RequestHeader(AUTH_TOKEN) String bearerToken,@PathVariable String date) {
		return client.findByDate(bearerToken,date);
	}
	
	@GetMapping("/fromtodates")
	public CustApiResp findBetweenDate(@RequestHeader(AUTH_TOKEN) String bearerToken,@RequestParam String frmDate, @RequestParam String toDate) {
		return client.findBetweenDate(bearerToken,frmDate, toDate);
	}
}
