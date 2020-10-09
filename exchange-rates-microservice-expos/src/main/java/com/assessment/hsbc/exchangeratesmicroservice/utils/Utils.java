package com.assessment.hsbc.exchangeratesmicroservice.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatus.Series;

public final class Utils {

	public static CustApiResp CustApiObject(Object response, String status, String errorMessage, HttpStatus htpSts) {
		return new CustApiResp(response, status, errorMessage, htpSts.value(), htpSts.name(), Series.valueOf(htpSts));
	}
}
