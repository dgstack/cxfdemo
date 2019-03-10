package com.dgstack.cxfdemo.config.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.Collections;

@Slf4j
public class CustomAfterBasicAuthFilter extends GenericFilterBean {


    public CustomAfterBasicAuthFilter() {
    }

    private boolean isAllow = true;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        log.info("filter is {} ", isAllow);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("authentication : {}", authentication);

        boolean isAuthentication = authentication != null && authentication.isAuthenticated();
        if(!isAuthentication && isAllow){
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken("nonce", "nonce",
                    Collections.singleton(new SimpleGrantedAuthority("USER")));
            SecurityContextHolder.getContext().setAuthentication(token);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
