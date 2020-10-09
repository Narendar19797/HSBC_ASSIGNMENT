package com.assessment.hsbc.exchangeratesmicroservice.expos;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.assessment.hsbc.exchangeratesmicroservice.load.LoadEntity;
import com.assessment.hsbc.exchangeratesmicroservice.utils.Constance;
import com.assessment.hsbc.exchangeratesmicroservice.utils.CustApiResp;
import com.assessment.hsbc.exchangeratesmicroservice.utils.DateValidator;
import com.assessment.hsbc.exchangeratesmicroservice.utils.DateValidatorUsingDateFormat;
import com.assessment.hsbc.exchangeratesmicroservice.utils.Utils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ExposService {

	@Autowired
	ExposRepository repo;
	
	private DateValidator validator = new DateValidatorUsingDateFormat("yyyy-MM-dd");

	public CustApiResp listAll() {
		CustApiResp responce = null;
		HttpStatus htpSts = HttpStatus.BAD_REQUEST;
		try {
			Iterable<LoadEntity> listAll = repo.findAll();
			htpSts = HttpStatus.OK;
			responce = Utils.CustApiObject(listAll, Constance.SUCCESS, Constance.LOAD_SUCCESSFULLY, htpSts);
		} catch (Exception e) {
			log.error(Constance.TOKEN_ERROR, e);
			responce = Utils.CustApiObject(null, Constance.FAILURE, Constance.ERROR_FETCHING_DATA, htpSts);
		}
		return responce;
	}

	public CustApiResp getByDate(String dateIn) {
		HttpStatus htpSts = HttpStatus.BAD_REQUEST;
		LoadEntity le = null;
		Date date = validator.isValid(dateIn);
		if (date == null) {
			return Utils.CustApiObject(null, Constance.FAILURE, Constance.INVALIED_DATE_ERROR, htpSts);
		}
		le = repo.findByDate(date);
		htpSts = HttpStatus.OK;
		return Utils.CustApiObject(le, Constance.SUCCESS, Constance.LOAD_SUCCESSFULLY, htpSts);
	}

	public CustApiResp findBetweenDate(String frmStrDate, String toStrDate) {
		HttpStatus htpSts = HttpStatus.BAD_REQUEST;
		Iterable<LoadEntity> le = null;
		Date fromDate = validator.isValid(frmStrDate);
		if (fromDate == null) {
			return Utils.CustApiObject(null, Constance.FAILURE, Constance.INVALIED_DATE_ERROR, htpSts);
		}
		Date toDate = validator.isValid(toStrDate);
		if (toDate == null) {
			return Utils.CustApiObject(null, Constance.FAILURE, Constance.INVALIED_DATE_ERROR, htpSts);
		}
		le = repo.findByDate(fromDate, toDate);
		htpSts = HttpStatus.OK;
		return Utils.CustApiObject(le, Constance.SUCCESS, Constance.LOAD_SUCCESSFULLY, htpSts);
	}
}
