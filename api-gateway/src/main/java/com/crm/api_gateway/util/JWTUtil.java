package com.crm.api_gateway.util;
 
import java.util.Date;
 
import org.springframework.stereotype.Component;
 
import com.crm.api_gateway.exception.InvalidTokenException;
 
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
 
@Component
public class JWTUtil {
	public static final String SECRET = "7863725367566B59703373367639792F423F4528482B4D625165";
 
	private javax.crypto.SecretKey getSignKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET);
		return Keys.hmacShaKeyFor(keyBytes);
	}
 
	public String validateToken(final String token) {
		try {
			Jws<Claims> claims = Jwts.parser().verifyWith(getSignKey()).build().parseSignedClaims(token);
			Claims payload = claims.getPayload();
 
			if (payload.getExpiration().before(new Date())) {
				throw new InvalidTokenException("Token is expired");
			}
			Object authorities = payload.get("authorities");
			if(authorities == null || !(authorities instanceof String)) {
				throw new InvalidTokenException("Invalid token: role is missing");
			}
 
			return authorities.toString();
		} catch (Exception e) {
			throw new InvalidTokenException("Token validation failed" + e.getMessage());
		}
	}
}