package com.assessment.hsbc.exchangeratesmicroservice.utils;

import org.springframework.http.HttpStatus.Series;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustApiResp {

	private Object results;
	private String status;
	private String message;
	private Integer code;
	private String value;
	private Series series;

}