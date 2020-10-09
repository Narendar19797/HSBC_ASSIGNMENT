package com.assessment.hsbc.gateway.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class FallBackController {

	private String messageFallBack="Load Service is taking too long to respond (or) is down. Please try again after some time.";
	
	@RequestMapping("/loadFallBack")
	public Mono<String> loadServiceFallBack(){
		return Mono.just(messageFallBack);
	}
	
	@RequestMapping("/exposFallBack")
	public Mono<String> exposFallBack(){
		return Mono.just(messageFallBack);
	}
	
	@RequestMapping("/jwtFallBack")
	public Mono<String> jwtFallBack(){
		return Mono.just(messageFallBack);
	}
	
	
}
