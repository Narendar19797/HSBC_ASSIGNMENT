package com.assessment.hsbc.exchangeratesmicroservice.utils;

import java.util.Date;

public interface DateValidator {
	Date isValid(String dateStr);
	String stringDate(Date date);
	Date getLastYearDate();
}
