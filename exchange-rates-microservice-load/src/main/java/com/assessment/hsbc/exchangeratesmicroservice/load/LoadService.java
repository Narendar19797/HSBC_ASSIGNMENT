package com.assessment.hsbc.exchangeratesmicroservice.load;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.assessment.hsbc.exchangeratesmicroservice.utils.Constance;
import com.assessment.hsbc.exchangeratesmicroservice.utils.DateValidator;
import com.assessment.hsbc.exchangeratesmicroservice.utils.DateValidatorUsingDateFormat;
import com.assessment.hsbc.exchangeratesmicroservice.utils.Result;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LoadService {

	@Value("${ratesapi.url}")
	private String baseUrl;

	private DateValidator validator = new DateValidatorUsingDateFormat("yyyy-MM-dd");

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private LoadRepository repo;

	@Async
	public CompletableFuture<ResponseEntity<?>> loadData(String inputDate, String base, List<String> symbols) {
		String url = baseUrl;
		ResponseEntity<?> resp = null;

		try {
			Date date = validator.isValid(inputDate);
			if (date == null) {
				resp = new ResponseEntity<String>(Constance.INVALIED_DATE_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
			} else if (date != null) {
				LocalDate currentDate = LocalDate.parse(inputDate);
				if (currentDate.getDayOfMonth() != 1) {
					resp = new ResponseEntity<String>(Constance.VALIDATION, HttpStatus.INTERNAL_SERVER_ERROR);
				} else {
					url += inputDate;
				}
			}

			if (base != null)
				url += base;
			if (symbols != null) {
				url += String.join(",", symbols);
			}

			if (resp == null) {
				Result response = restTemplate.getForObject(url, Result.class);
				resp = this.saveData(response);
			} else {
				resp = new ResponseEntity<String>(Constance.NO_DATA_FOUND, HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			log.error(Constance.INVALIED_PAYLOAD, e);
			resp = new ResponseEntity<String>(Constance.INVALIED_PAYLOAD + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return CompletableFuture.completedFuture(resp);
	}

	private ResponseEntity<?> saveData(Result response) {
		ResponseEntity<?> results = null;
		try {
			LoadEntity le = new LoadEntity();
			Map<String, Double> rates = response.getRates();
			le.setBase(response.getBase());

			if (rates != null) {
				if (rates.containsKey("GBP"))
					le.setGBP(rates.get("GBP"));

				if (rates.containsKey("HKD"))
					le.setHKD(rates.get("HKD"));

				if (rates.containsKey("USD"))
					le.setUSD(rates.get("USD"));
			}
			le.setDate(validator.isValid(response.getDate()));

			repo.save(le);
			results = new ResponseEntity<String>(Constance.DATA_SAVED_SUCCESS, HttpStatus.OK);
		} catch (Exception e) {
			log.error(Constance.DATA_SAVED_FAILED, e);
			results = new ResponseEntity<String>(Constance.DATA_SAVED_FAILED + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return results;
	}

	public CompletableFuture<ResponseEntity<?>> loadData(String base, List<String> symbols) {
		CompletableFuture<ResponseEntity<?>> resp = null;
		Date fromDate = validator.getLastYearDate();
		Date eachMonths = null;
		String currDate = null;
		for (int i = 0; i < 12; i++) {
			Calendar c = Calendar.getInstance();
			c.setTime(fromDate);
			c.add(Calendar.MONTH, i + 1);
			c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
			eachMonths = c.getTime();
			currDate = validator.stringDate(eachMonths);
			resp = this.loadData(currDate, base, symbols);
		}
		return resp;
	}

}
