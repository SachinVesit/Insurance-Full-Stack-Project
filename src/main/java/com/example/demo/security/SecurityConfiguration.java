package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder setupPasswordEncoder() {
		return passwordEncoder;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/**").hasAnyAuthority("USER","UNDERWRITERLEVEL1","UNDERWRITERLEVEL2","UNDERWRITERLEVEL3","UNDERWRITERLEVEL1","SCRUTINIZERLEVEL1","SCRUTINIZERLEVEL2","SCRUTINIZERLEVEL3")
		.antMatchers("/insurance_request/start_new_process").hasAnyAuthority("ADMIN")
		.antMatchers("/api/user/delete/**").hasAnyAuthority("ADMIN")
		.antMatchers("/api/users/**","/api/user/update").permitAll()
		.anyRequest().authenticated().and()
//		.formLogin()
		.httpBasic()
		.and().logout().logoutUrl("/logout").logoutSuccessUrl("/Index.html").and().csrf().disable();
		
	}
}
