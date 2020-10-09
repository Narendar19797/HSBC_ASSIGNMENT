package com.assessment.hsbc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.hsbc.jwt.Utils.Constance;
import com.assessment.hsbc.jwt.Utils.CustApiResp;
import com.assessment.hsbc.jwt.Utils.JwtUtil;
import com.assessment.hsbc.jwt.Utils.Utils;
import com.assessment.hsbc.jwt.mode.AuthenticationResponse;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/generatetoken")
@Slf4j
public class AutenticateController {

	@Autowired
	private JwtUtil jwtUtl;

	/**
	 * authenticate API endpoint return JWT in the payload - Accepts userID and
	 * Password - UsernamePasswordAuthenticationToken is a standard spring mvc uses
	 * - If Authentication fails their is an exception or else Generate JWT Token
	 * and - Returns JWT as response
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{user}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CustApiResp authenticate(@PathVariable("user") String principal) throws Exception {
		HttpStatus htpSts = null;
		try {
			final String generateJwtToken = jwtUtl.generateToken(principal);
			htpSts = HttpStatus.OK;
			return Utils.CustApiObject(new AuthenticationResponse(generateJwtToken), Constance.SUCCESS, Constance.LOAD_SUCCESSFULLY, htpSts);
		} catch (Exception e) {
			log.error(Constance.LOAD_ERROR,e);
			htpSts = HttpStatus.BAD_REQUEST;
			return Utils.CustApiObject(null, Constance.FAILURE, Constance.LOAD_FAILED, htpSts);
		}
	}
}
