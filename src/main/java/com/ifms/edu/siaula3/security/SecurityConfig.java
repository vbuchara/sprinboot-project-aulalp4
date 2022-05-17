package com.ifms.edu.siaula3.security;

import com.ifms.edu.siaula3.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserRepository userRep;

    @Autowired
    private LoginSuccess loginSuccess;

    @Override
	public UserDetailsService userDetailsServiceBean() throws Exception {
		return new SpringUserDetailService(userRep);
	}

    @Bean
    public static  BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

    @Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/login").permitAll()
        .antMatchers("/index").hasAnyAuthority("USER", "ADMIN")
        .antMatchers("/users").hasAnyAuthority("ADMIN")
		.antMatchers("/users/*").hasAnyAuthority("ADMIN")
		.antMatchers("/items").hasAnyAuthority("USER", "ADMIN")
        .antMatchers("/items/").hasAnyAuthority("USER", "ADMIN")
		.antMatchers("/items/add").hasAnyAuthority("ADMIN")
		.antMatchers("/items/*").hasAnyAuthority("ADMIN")
		.antMatchers("/admin/*").hasAnyAuthority("ADMIN")
		.and()
		.exceptionHandling().accessDeniedPage("/login")
		.and()
		.formLogin().successHandler(loginSuccess)
		.loginPage("/login").permitAll()
		.and()
		.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/").permitAll();
		http.csrf().disable();
	}

    @Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsServiceBean()).passwordEncoder(passwordEncoder());
	}
}
