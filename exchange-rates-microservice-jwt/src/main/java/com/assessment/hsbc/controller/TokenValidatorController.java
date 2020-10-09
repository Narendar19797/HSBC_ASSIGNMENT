package com.assessment.hsbc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.hsbc.jwt.Utils.CustApiResp;
import com.assessment.hsbc.jwt.mode.AuthenticationRequest;
import com.assessment.hsbc.jwt.mode.AuthenticationResponse;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/token")
public class TokenValidatorController {

	private static final Logger log = LoggerFactory.getLogger(TokenValidatorController.class);

	@Autowired
	private TokenValidatorService service;

	/**
	 * authenticate API endpoint return JWT in the payload 
	 * - If Authentication fails their is an exception or else Generate JWT Token and - Returns JWT as response
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/validate", produces = MediaType.APPLICATION_JSON_VALUE)
	public CustApiResp tokenValidator(@RequestBody AuthenticationRequest request) throws Exception {
		log.info("Input Request:"+request);
		return service.validateToken(request);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/extractuser", produces = MediaType.APPLICATION_JSON_VALUE)
	public CustApiResp extractUserName(@RequestBody AuthenticationResponse request) throws Exception {
		return service.extractUserName(request);
	}
	
}
