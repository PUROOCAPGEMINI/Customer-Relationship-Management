package com.crm.api_gateway.filter;
 
import java.util.List;
 
import com.crm.api_gateway.exception.UnauthorizedException;
import com.crm.api_gateway.util.JWTUtil;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
 
import org.springframework.stereotype.Component;
 
@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {
 
	@Autowired
	private RoleRouteValidator validator;
 
	@Autowired
	private JWTUtil jwtUtil;
 
	public AuthFilter() {
		super(Config.class);
	}
 
	@Override
	public GatewayFilter apply(Config config) {
		return (exchange, chain) -> {
			String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
			String method = exchange.getRequest().getMethod().name();
			String path = exchange.getRequest().getPath().value();
 
			
			if (authHeader == null || !authHeader.startsWith("Bearer ")) {
				throw new UnauthorizedException("Missing Authorization header");
			}
 
			String token = authHeader.substring(7);
			String role = jwtUtil.validateToken(token);
 
			
 
			List<String> allowedRoles = validator.getAllowedRoles(method, path);
 
			if (!allowedRoles.contains(role)) {
				throw new UnauthorizedException("Access denied for role: " + role);
			}
 
			return chain.filter(exchange);
		};
	}
 
	public static class Config {
	}
}