package com.assessment.hsbc.jwt.Utils;

import org.springframework.stereotype.Component;

@Component
/**
 * Contance declaration in project
 * 
 * @author 91994
 *
 */
public interface Constance {

	String TOKEN_ERROR = "Incorrect token..,Please regenerate token..,";
	String TOKEN_HEADER = "Authorization";
	String TOKEN_BEARER = "Bearer ";
	String TOKEN_BEARER_ERROR = "Bearer should exist in token";
	String SUCCESS = "SUCCESS";
	String LOAD_SUCCESSFULLY = "Data load sucefully..,";
	String LOAD_FAILED = "Data load failed..,";
	String LOAD_ERROR = "Error while load..,";
	String FAILURE = "FAILURE";
	String TOKEN_MUST_PRESENT = "Incorrect token..,In Request header authorization missing:";
	String SECURITY_KEY = "secret";

}
