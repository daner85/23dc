package com.tweentythree.people.apirest.courses.jwtutil;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final String HEADER = "Authorization";
	private final String PREFIX = "Bearer ";
	private final String SECRET = "mySecretKey";

//	public Claims validateToken(HttpServletRequest request) {
//		String jwtToken = request.getHeader(HEADER).replace(PREFIX, "");
//		return Jwts.parser().setSigningKey(SECRET.getBytes()).parseClaimsJws(jwtToken).getBody();
//	}
//
//	/**
//	 * Authentication method in Spring flow
//	 * 
//	 * @param claims
//	 */
//	public void setUpSpringAuthentication(Claims claims) {
//		@SuppressWarnings("unchecked")
//		List<String> authorities = (List<String>) claims.get("authorities");
//
//		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(claims.getSubject(), null,
//				authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
//
//		SecurityContextHolder.getContext().setAuthentication(auth);
//
//	}
//
//	public boolean checkJWTToken(HttpServletRequest request, HttpServletResponse res) {
//		String authenticationHeader = request.getHeader(HEADER);
//		if (authenticationHeader == null || !authenticationHeader.startsWith(PREFIX))
//			return false;
//		return true;
//	}
	
	public String getJWTToken(String username) {
		String secretKey = "mySecretKey";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_USER");
		
		String token = Jwts
				.builder()
				.setId("23peopleJWT")
				.setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();

		return "Bearer " + token;
	}
}