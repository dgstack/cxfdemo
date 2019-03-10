package com.dgstack.cxfdemo.config;

import com.dgstack.cxfdemo.config.security.CustomAfterBasicAuthFilter;
import com.dgstack.cxfdemo.config.security.CustomBeforeBasicAuthFilter;
import com.dgstack.cxfdemo.config.security.MyBasicAuthenticationEntryPoint;
import com.dgstack.cxfdemo.config.security.MyBasicAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final MyBasicAuthenticationEntryPoint basicAuthenticationEntryPoint;
    private final MyBasicAuthenticationProvider basicAuthenticationProvider;

    public SecurityConfig(MyBasicAuthenticationEntryPoint basicAuthenticationEntryPoint,
                          MyBasicAuthenticationProvider basicAuthenticationProvider) {
        this.basicAuthenticationEntryPoint = basicAuthenticationEntryPoint;
        this.basicAuthenticationProvider = basicAuthenticationProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http
                .formLogin().disable()
                .authorizeRequests()
                .anyRequest().authenticated().and()
                .httpBasic()
                .authenticationEntryPoint(this.basicAuthenticationEntryPoint);

        http.addFilterAfter(new CustomAfterBasicAuthFilter(), BasicAuthenticationFilter.class);
//        http.addFilterBefore(new CustomBeforeBasicAuthFilter(authenticationManager()),
//                BasicAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
        auth.authenticationProvider(this.basicAuthenticationProvider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
