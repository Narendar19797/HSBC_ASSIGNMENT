package com.assessment.hsbc.exchangeratesserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ExchangeRatesServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExchangeRatesServerApplication.class, args);
	}

}
