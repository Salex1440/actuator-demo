package com.salex.actuatordemo.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Order(1)
public class ApiKeyFilter extends OncePerRequestFilter {
    private final String AUTHORITIES_KEY = "ApiKey ";

    @Value("http.api-key.value")
    private final String allowedKey = "db989573c1d24a6b62c0713006d54791";

    @Override
    protected void doFilterInternal(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            FilterChain filterChain) throws ServletException, IOException {
        String auth = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);

        if (auth != null && auth.startsWith(AUTHORITIES_KEY)) {
            String apikey = auth.substring(AUTHORITIES_KEY.length());

            ApiKeyAuthenticationToken apiKeyAuthenticationToken = new ApiKeyAuthenticationToken(apikey, AuthorityUtils.NO_AUTHORITIES);
            if (apikey.equals(allowedKey)) {
                SecurityContextHolder.getContext().setAuthentication(apiKeyAuthenticationToken);
            } else {
                SecurityContextHolder.clearContext();
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
