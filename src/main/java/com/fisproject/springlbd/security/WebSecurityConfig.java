package com.fisproject.springlbd.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/whoami").hasAnyRole("USER", "ADMIN")
                .anyRequest().permitAll()
                .and()
                .httpBasic();
    }

    @Autowired public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("usern")
//                .password("{noop}usern")
                .password(encoder().encode("usern"))
                .roles("USER")
                .and()
                .withUser("usera")
                .password(encoder().encode("usera"))
                .roles("ADMIN");
    }

    @Bean public static PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

}
