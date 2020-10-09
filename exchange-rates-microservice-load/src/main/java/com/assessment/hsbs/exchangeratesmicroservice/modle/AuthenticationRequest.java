package com.assessment.hsbs.exchangeratesmicroservice.modle;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class AuthenticationRequest {

	private final String jwt;
	private String user;

}
