package br.com.architecture.authservice.infrastructure.configuration.security;

import br.com.architecture.authservice.domain.entities.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
public class TokenService {
	
	@Value("${auth.jwt.expiration}")
	private String expiration;
	
	@Value("${auth.jwt.secret}")
	private String secret;

	public static final String BEAREN = "Bearer";

	public String generateToken(Authentication authentication) {
		UserEntity logged = (UserEntity) authentication.getPrincipal();
		Date today = new Date();
		Date dateExpiration = new Date(today.getTime() + Long.parseLong(expiration));
		
		return Jwts.builder()
				.setIssuer("API of authentication")
				.setSubject(logged.getId())
				.setIssuedAt(today)
				.setExpiration(dateExpiration)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}

	public boolean isTokenValid(String token) {
		if(Objects.nonNull(token)){
			return false;
		}

		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public String getUserId(String token) {
		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		return claims.getSubject();
	}
}
