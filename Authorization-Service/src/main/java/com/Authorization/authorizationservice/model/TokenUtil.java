package com.Authorization.authorizationservice.model;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.Authorization.authorizationservice.exception.JwtTokenMalformedException;
import com.Authorization.authorizationservice.exception.JwtTokenMissingException;

//import com.user.user.exceptions.JwtTokenMalformedException;
//import com.user.user.exceptions.JwtTokenMissingException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
@Component
public class TokenUtil {

	private String jwtSecret = "@arch454jdjh$2@09()mwegstamxnj=+%$";
	
	private long tokenValidity = 86400000;

	public String getJwtSecret() {
		return jwtSecret;
	}

	public void setJwtSecret(String jwtSecret) {
		this.jwtSecret = jwtSecret;
	}

	public long getTokenValidity() {
		return tokenValidity;
	}

	public void setTokenValidity(long tokenValidity) {
		this.tokenValidity = tokenValidity;
	}

	public TokenUtil(String jwtSecret, long tokenValidity) {
		super();
		this.jwtSecret = jwtSecret;
		this.tokenValidity = tokenValidity;
	}

	public TokenUtil() {
		super();
	}

	@Override
	public String toString() {
		return "TokenDetails [jwtSecret=" + jwtSecret + ", tokenValidity=" + tokenValidity + "]";
	}
	public Claims getClaims(final String token) {
		try {
			Claims body = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
			return body;
		} catch (Exception e) {
			System.out.println(e.getMessage() + " => " + e);
		}
		return null;
	}

	public String generateToken(String id) {
		Claims claims = Jwts.claims().setSubject(id);
		long nowMillis = System.currentTimeMillis();
		long expMillis = nowMillis + tokenValidity;
		Date exp = new Date(expMillis);
		return Jwts.builder().setClaims(claims).setIssuedAt(new Date(nowMillis)).setExpiration(exp)
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	}

	public void validateToken(final String token) throws JwtTokenMalformedException, JwtTokenMissingException {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
		} catch (SignatureException ex) {
			throw new JwtTokenMalformedException("Invalid JWT signature");
		} catch (MalformedJwtException ex) {
			throw new JwtTokenMalformedException("Invalid JWT token");
		} catch (ExpiredJwtException ex) {
			throw new JwtTokenMalformedException("Expired JWT token");
		} catch (UnsupportedJwtException ex) {
			throw new JwtTokenMalformedException("Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
			throw new JwtTokenMissingException("Token not found.");
		}
	}
}
