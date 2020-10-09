package com.assessment.hsbc.exchangeratesmicroservice.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateValidatorUsingDateFormat implements DateValidator {
	private String dateFormat;

	public DateValidatorUsingDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	@Override
	public Date isValid(String dateStr) {
		Date date = null;
		try {
			SimpleDateFormat format = new SimpleDateFormat(this.dateFormat);
			date = format.parse(dateStr);
		} catch (ParseException e) {
			return date;
		}
		return date;
	}
	
	public Date getLastYearDate() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); // Give Your Date
		c.add(Calendar.MONTH, -12);
		Date fromDate = c.getTime();
		return fromDate;
	}
	
	@Override
	public String stringDate(Date date) {
		String dateStr = null;
		DateFormat format = new SimpleDateFormat(this.dateFormat);
		dateStr = format.format(date);
		return dateStr;
	}
	
}
