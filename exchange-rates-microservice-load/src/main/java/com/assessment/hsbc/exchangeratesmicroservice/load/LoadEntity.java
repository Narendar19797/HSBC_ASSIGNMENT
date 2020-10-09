package com.assessment.hsbc.exchangeratesmicroservice.load;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class LoadEntity	implements Serializable {

	private static final long serialVersionUID = 5952745511727988253L;

	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long rateId;

	private String base;
	private Double GBP;
	private Double USD;
	private Double HKD;
	@Temporal(TemporalType.DATE)
	private java.util.Date date;

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public Double getGBP() {
		return GBP;
	}

	public void setGBP(Double gBP) {
		GBP = gBP;
	}

	public Double getUSD() {
		return USD;
	}

	public void setUSD(Double uSD) {
		USD = uSD;
	}

	public Double getHKD() {
		return HKD;
	}

	public void setHKD(Double hKD) {
		HKD = hKD;
	}

	public java.util.Date getDate() {
		return date;
	}

	public void setDate(java.util.Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "LoadEntity [base=" + base + ", GBP=" + GBP + ", USD=" + USD + ", HKD=" + HKD + ", date=" + date + "]";
	}

}
