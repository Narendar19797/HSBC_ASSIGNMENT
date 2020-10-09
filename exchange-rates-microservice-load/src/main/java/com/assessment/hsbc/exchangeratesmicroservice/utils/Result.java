package com.assessment.hsbc.exchangeratesmicroservice.utils;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Result {

	private Map<String, Double> rates = new HashMap<>();
	private String base;
	private String date;

	public Result() {
	}

	public void clear() {
		rates.clear();
	}

	public void setRate(String currencyCode, double rate) {
		rates.put(currencyCode.toUpperCase(), rate);
	}

	public double getRate(String currencyCode) {
		return rates.get(currencyCode.toUpperCase());
	}

	public Map<String, Double> getRates() {
		return rates;
	}

	public void setRates(Map<String, Double> rates) {
		this.rates = rates;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Result [rates=" + rates + ", base=" + base + ", date=" + date + "]";
	}

}
