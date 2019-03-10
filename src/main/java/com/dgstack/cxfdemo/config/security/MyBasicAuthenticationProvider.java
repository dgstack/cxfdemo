package com.dgstack.cxfdemo.config.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Slf4j
@Component
public class MyBasicAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        Object principal = authentication.getPrincipal();
        Object credentials = authentication.getCredentials();
        log.info("principal : {}, crdentials: {}", principal, credentials);
        if("test".equals(principal) && "test".equals(credentials)){
            return new UsernamePasswordAuthenticationToken(principal, credentials, Collections.singleton(new SimpleGrantedAuthority("USER")));
        }
        throw new UsernameNotFoundException("Invalid Username or Password");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        log.info("support class: {}", aClass);
        return UsernamePasswordAuthenticationToken.class.equals(aClass);
    }
}
