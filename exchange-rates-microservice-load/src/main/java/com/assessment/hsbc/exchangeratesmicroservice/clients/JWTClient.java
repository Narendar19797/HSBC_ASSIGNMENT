package com.assessment.hsbc.exchangeratesmicroservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.assessment.hsbc.exchangeratesmicroservice.clients.JWTClient.HystrixClientFallbackFactory;
import com.assessment.hsbc.exchangeratesmicroservice.utils.Constance;
import com.assessment.hsbc.exchangeratesmicroservice.utils.CustApiResp;
import com.assessment.hsbc.exchangeratesmicroservice.utils.Utils;
import com.assessment.hsbs.exchangeratesmicroservice.modle.AuthenticationRequest;
import com.assessment.hsbs.exchangeratesmicroservice.modle.AuthenticationResponse;

import feign.hystrix.FallbackFactory;

@FeignClient(url = "${jwt.url}", name = "token-validator", fallbackFactory = HystrixClientFallbackFactory.class)
public interface JWTClient {

	@RequestMapping(method = RequestMethod.GET, value = "/generatetoken/{user}", consumes = "application/json")
	CustApiResp generatetoken(@PathVariable(value = "user") String user);

	@RequestMapping(method = RequestMethod.POST, value = "/token/validate", consumes = "application/json")
	CustApiResp tokenValidator(@RequestBody AuthenticationRequest authReq);

	@RequestMapping(method = RequestMethod.POST, value = "/token/extractuser", consumes = "application/json")
	CustApiResp extractuser(@RequestBody AuthenticationResponse authReq);

	@Component
	static class HystrixClientFallbackFactory implements FallbackFactory<JWTClient> {

		@Override
		public JWTClient create(Throwable cause) {
			return new JWTClient() {
				@Override
				public CustApiResp generatetoken(@PathVariable(value = "user") String user) {
					return Utils.CustApiObject(null, Constance.FALL_BACK_ERROR, Constance.LOAD_FAILED,
							HttpStatus.INTERNAL_SERVER_ERROR);
				}

				@Override
				public CustApiResp tokenValidator(@RequestBody AuthenticationRequest authReq) {
					return Utils.CustApiObject(null, Constance.FALL_BACK_ERROR, Constance.LOAD_FAILED,
							HttpStatus.INTERNAL_SERVER_ERROR);
				}

				@Override
				public CustApiResp extractuser(@RequestBody AuthenticationResponse authReq) {
					return Utils.CustApiObject(null, Constance.FALL_BACK_ERROR, Constance.LOAD_FAILED,
							HttpStatus.INTERNAL_SERVER_ERROR);
				}
			};
		}
	}

}
