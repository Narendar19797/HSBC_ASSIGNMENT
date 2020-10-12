package com.assessment.hsbc.exchangeratesmicroservice.load;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoadEntity	implements Serializable {

	private static final long serialVersionUID = 5952745511727988253L;

	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long rateId;

	private String base;
	private Double GBP;
	private Double USD;
	private Double HKD;
	
	@Temporal(TemporalType.DATE)
	//@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;

	

}
