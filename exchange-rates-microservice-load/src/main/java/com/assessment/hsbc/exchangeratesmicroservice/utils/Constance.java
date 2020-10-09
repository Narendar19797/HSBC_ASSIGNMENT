package com.assessment.hsbc.exchangeratesmicroservice.utils;

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
	String SUCCESS = "SUCCESS";
	String FAILURE = "FAILURE";
	String TOKEN_MUST_PRESENT = "Incorrect token..,In Request header authorization missing:";
	String SECURITY_KEY = "secret";
	
	String LOAD_SUCCESSFULLY = "Data load sucefully..,";
	String LOAD_FAILED = "Data load failed..,";
	String LOAD_ERROR = "Error while load..,";

	String INVALIED_DATE_ERROR = "Invalied Date Formate..,";
	String DATA_SAVED_SUCCESS = "Data Saved Successfully..,";
	String DATA_SAVED_FAILED = "Data Saved Failed..,";
	String NO_DATA_FOUND = "No Data fetched from exchange rates api..,";
	String VALIDATION = "Allowed rate as of the 1'st Day of Month only.";
	String INVALIED_PAYLOAD = "Invalied Payload.., Error: ";
	String ERROR_FETCHING_DATA = "Error while fetching data..,";

	String FALL_BACK_ERROR = "fallback; reason was:";
}
