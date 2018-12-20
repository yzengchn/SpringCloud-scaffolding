package xyz.yzblog.cloudgateway.filter;

import java.util.Locale;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class AuthFilter implements GlobalFilter{

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		ServerHttpRequest request = exchange.getRequest();
		System.out.println("request.getPath():::" + request.getPath()+ "----" +request.getURI());
		String token = request.getQueryParams().getFirst("token");
		String userId = request.getHeaders().getFirst("userId");
		
		System.out.println("userId"+userId);
		System.out.println(token);
		if (token == null || token.isEmpty()) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
		return chain.filter(exchange);
	}

}
