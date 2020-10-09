package com.assessment.hsbc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.assessment.hsbc.jwt.Utils.Constance;
import com.assessment.hsbc.jwt.Utils.CustApiResp;
import com.assessment.hsbc.jwt.Utils.JwtUtil;
import com.assessment.hsbc.jwt.Utils.Utils;
import com.assessment.hsbc.jwt.mode.AuthenticationRequest;
import com.assessment.hsbc.jwt.mode.AuthenticationResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TokenValidatorService {

	@Autowired
	private JwtUtil jwtUtl;

	/**
	 * authenticate API endpoint return JWT in the payload - Accepts userID and
	 * Password - UsernamePasswordAuthenticationToken is a standard spring mvc uses
	 * - If Authentication fails their is an exception or else Generate JWT Token
	 * and - Returns JWT as response
	 */
	public CustApiResp validateToken(AuthenticationRequest authReq) {
		CustApiResp responce = null;
		HttpStatus htpSts = HttpStatus.BAD_REQUEST;
		
		try {

			String jwtToken = null;
			String username = null;
			String authHeader = (authReq != null) ? authReq.getJwt() : null;

			if (authHeader != null && authHeader.startsWith(Constance.TOKEN_BEARER)) {
				jwtToken = authHeader.substring(7);
				username = jwtUtl.extractUserName(jwtToken);
			} else {
				responce = Utils.CustApiObject(null, Constance.FAILURE, Constance.TOKEN_BEARER_ERROR, htpSts);
			}
			if (username != null && jwtToken != null) {
				if (jwtUtl.validateToken(jwtToken, authReq.getUser())) {
					htpSts = HttpStatus.OK;
					responce = Utils.CustApiObject(true, Constance.SUCCESS, Constance.LOAD_SUCCESSFULLY, htpSts);
				} else {
					responce = Utils.CustApiObject(null, Constance.FAILURE, Constance.TOKEN_ERROR, htpSts);
				}
			} else {
				responce = Utils.CustApiObject(null, Constance.FAILURE, Constance.TOKEN_ERROR, htpSts);
			}
		} catch (Exception e) {
			log.error(Constance.TOKEN_ERROR, e);
			responce = Utils.CustApiObject(null, Constance.TOKEN_ERROR, Constance.LOAD_FAILED, htpSts);
		}
		return responce;
	}

	/**
	 * authenticate API endpoint return JWT in the payload - Accepts userID and
	 * Password - UsernamePasswordAuthenticationToken is a standard spring mvc uses
	 * - If Authentication fails their is an exception or else Generate JWT Token
	 * and - Returns JWT as response
	 */
	public CustApiResp extractUserName(AuthenticationResponse request) {
		CustApiResp responce = null;
		HttpStatus htpSts = HttpStatus.BAD_REQUEST;
		try {
			final String authHeader = request.getJwt();
			String jwtToken = null;
			String username = null;

			if (authHeader != null && authHeader.startsWith(Constance.TOKEN_BEARER)) {
				jwtToken = authHeader.substring(7);
				username = jwtUtl.extractUserName(jwtToken);
				htpSts = HttpStatus.OK;
				responce = Utils.CustApiObject(username, Constance.SUCCESS, Constance.LOAD_SUCCESSFULLY, htpSts);
			} else {
				responce = Utils.CustApiObject(null, Constance.FAILURE, Constance.TOKEN_BEARER_ERROR, htpSts);
			}

		} catch (Exception e) {
			log.error(Constance.TOKEN_ERROR, e);
			responce = Utils.CustApiObject(null, Constance.TOKEN_ERROR, Constance.LOAD_FAILED, htpSts);
		}
		return responce;
	}

}
