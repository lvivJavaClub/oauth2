package com.lohika.jclub.oauth2;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Tutorial on Spring Security OAuth2:
 * https://spring.io/guides/tutorials/spring-boot-oauth2/#_social_login_simple
 */
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/", "/index.html")
                    .permitAll()
                .anyRequest()
                    .authenticated();
    }
}
