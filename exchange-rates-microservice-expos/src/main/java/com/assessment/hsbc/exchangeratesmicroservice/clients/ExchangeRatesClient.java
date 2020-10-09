package com.assessment.hsbc.exchangeratesmicroservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.assessment.hsbc.exchangeratesmicroservice.clients.ExchangeRatesClient.HystrixClientFallbackFactory;
import com.assessment.hsbc.exchangeratesmicroservice.utils.Constance;
import com.assessment.hsbc.exchangeratesmicroservice.utils.CustApiResp;
import com.assessment.hsbc.exchangeratesmicroservice.utils.Utils;

import feign.hystrix.FallbackFactory;

@FeignClient(url = "${jwt.url}", name = "EXCHANGE-RATES-Load", fallbackFactory = HystrixClientFallbackFactory.class)
public interface ExchangeRatesClient  {

	String AUTH_TOKEN = "Authorization";
	
	@RequestMapping(method = RequestMethod.GET, value = "/fetch/")
	CustApiResp findAll(@RequestHeader(AUTH_TOKEN) String bearerToken);

	@RequestMapping(method = RequestMethod.GET, value = "/fetch/{date}")
	CustApiResp findByDate(@RequestHeader(AUTH_TOKEN) String bearerToken,@PathVariable String date);

	@RequestMapping(method = RequestMethod.GET, value = "/fetch/frmToDates")
	CustApiResp findBetweenDate(@RequestHeader(AUTH_TOKEN) String bearerToken,@RequestParam String frmDate, @RequestParam String toDate);

	@Component
	static class HystrixClientFallbackFactory implements FallbackFactory<ExchangeRatesClient> {
		@Override
		public ExchangeRatesClient create(Throwable cause) {
			return new ExchangeRatesClient() {
				@Override
				public CustApiResp findAll(@RequestHeader(AUTH_TOKEN) String bearerToken) {
					return Utils.CustApiObject(null, Constance.FALL_BACK_ERROR, Constance.LOAD_FAILED,
							HttpStatus.INTERNAL_SERVER_ERROR);
				}
				@Override
				public CustApiResp findByDate(@RequestHeader(AUTH_TOKEN) String bearerToken,@PathVariable String date) {
					return Utils.CustApiObject(null, Constance.FALL_BACK_ERROR, Constance.LOAD_FAILED,
							HttpStatus.INTERNAL_SERVER_ERROR);
				}
				@Override
				public CustApiResp findBetweenDate(@RequestHeader(AUTH_TOKEN) String bearerToken,@RequestParam String frmDate, @RequestParam String toDate) {
					return Utils.CustApiObject(null, Constance.FALL_BACK_ERROR, Constance.LOAD_FAILED,
							HttpStatus.INTERNAL_SERVER_ERROR);
				}
			};
		}
	}
}
