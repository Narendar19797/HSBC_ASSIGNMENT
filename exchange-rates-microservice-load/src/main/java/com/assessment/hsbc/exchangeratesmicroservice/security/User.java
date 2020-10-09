package com.assessment.hsbc.exchangeratesmicroservice.security;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties
@Getter
@Setter
@Entity
@Table(name = "USER")
public class User {

	@Column(name = "id", insertable = false, updatable = false)
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Integer id;
	@Column(name = "username", unique = true, nullable = true, insertable = false, updatable = false)
	private String username;
	@Column(name = "password", nullable = true)
	private String password;
	@Column(name = "role")
	private String role;

	// standard getters and setters
}
