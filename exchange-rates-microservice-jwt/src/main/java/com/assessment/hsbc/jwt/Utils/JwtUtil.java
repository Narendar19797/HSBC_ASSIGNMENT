package com.assessment.hsbc.jwt.Utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
	

	public String extractUserName(String token) {
		return exrtractClaim(token,Claims::getSubject);
	}

	public Date extractExpiration(String token) {
		return exrtractClaim(token,Claims::getExpiration);
	}
	
	private <T> T exrtractClaim(String token, Function<Claims,T> claimResolver) {
		final Claims claims = extractAllClaims(token);
		return claimResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(Constance.SECURITY_KEY).parseClaimsJws(token).getBody();
	}

	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	/**
	 * Generate Token by taking as UserDetails as input
	 * @param userDetails
	 * @return
	 */
	public String generateToken(String principal) {
		Map<String,Object> claims = new HashMap<>();
		return createToken(claims,principal);
	}

	/**
	 * create Jwt builder by using claims and subject/username 
	 * where are may algorithm we can use as part of Jwt specification, SignatureAlgorithm.HS256 is one of them
	 * Set Starting and Exiration time currectly it is 2-hours.
	 * @param claims that you want to pass as payload 
	 * @param username
	 * @return
	 */
	private String createToken(Map<String, Object> claims, String username) {
		return Jwts.builder().setClaims(claims).setSubject(username)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+1000*60*60*10))
				.signWith(SignatureAlgorithm.HS256, Constance.SECURITY_KEY).compact();
	}
	
	/**
	 * validate incoming token by subject and token expired time 
	 * @param token
	 * @param userDetails
	 * @return
	 */
	public boolean validateToken(String token,String principal) {
	  final String user = extractUserName(token);
	  return (user.equals(principal) && !isTokenExpired(token));
	}

}
