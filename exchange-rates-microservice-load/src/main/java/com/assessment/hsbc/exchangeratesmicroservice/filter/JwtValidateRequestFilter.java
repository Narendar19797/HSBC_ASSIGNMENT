package com.assessment.hsbc.exchangeratesmicroservice.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.assessment.hsbc.exchangeratesmicroservice.clients.JWTClient;
import com.assessment.hsbc.exchangeratesmicroservice.utils.Constance;
import com.assessment.hsbc.exchangeratesmicroservice.utils.CustApiResp;
import com.assessment.hsbs.exchangeratesmicroservice.modle.AuthenticationRequest;
import com.assessment.hsbs.exchangeratesmicroservice.modle.AuthenticationResponse;

@Component
public class JwtValidateRequestFilter extends OncePerRequestFilter {

	@Autowired
	private com.assessment.hsbc.exchangeratesmicroservice.security.MyUserDetailsService usrDtlSrv;

	@Autowired
	private JWTClient client;

	/**
	 * UsernamePasswordAuthenticationToken is default token which is used by spring
	 * security to manage authentication this is after validate JwtToken stimulating
	 * normal flow of execution, which is by default spring security done.
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String username = null;
		UserDetails usrDetails = null;

		String token = request.getHeader(Constance.TOKEN_HEADER);

		AuthenticationResponse authRes = new AuthenticationResponse(token);
		AuthenticationRequest authReq = new AuthenticationRequest(token);

		CustApiResp resp = client.extractuser(authRes);

		if (resp.getStatus().equals(Constance.SUCCESS)) {
			username = (String) resp.getResults();
			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				usrDetails = this.usrDtlSrv.loadUserByUsername(username);
				authReq.setUser(usrDetails.getUsername());
				resp = client.tokenValidator(authReq);
				if (resp.getStatus().equals(Constance.SUCCESS)) {
					UsernamePasswordAuthenticationToken usrPassAuthToken = new UsernamePasswordAuthenticationToken(
							usrDetails, null, usrDetails.getAuthorities());
					usrPassAuthToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(usrPassAuthToken);
				}
			}
		}

		filterChain.doFilter(request, response);
	}

}
