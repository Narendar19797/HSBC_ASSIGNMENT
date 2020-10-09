package com.assessment.hsbc;

import java.time.Duration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class ExcRateApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExcRateApplication.class, args);
	}

	/**
	 * Client side load balancing
	 * @param builder
	 * @return
	 */
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
	    return builder
	            .setConnectTimeout(Duration.ofMillis(3000))
	            .setReadTimeout(Duration.ofMillis(3000)).build();
	}
}
