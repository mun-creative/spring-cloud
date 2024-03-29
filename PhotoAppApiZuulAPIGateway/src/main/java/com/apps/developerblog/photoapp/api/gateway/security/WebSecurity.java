/*
 * package com.apps.developerblog.photoapp.api.gateway.security;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.core.env.Environment; import
 * org.springframework.http.HttpMethod; import
 * org.springframework.security.config.annotation.web.builders.HttpSecurity;
 * import org.springframework.security.config.annotation.web.configuration.
 * EnableWebSecurity; import
 * org.springframework.security.config.annotation.web.configuration.
 * WebSecurityConfigurerAdapter; import
 * org.springframework.security.config.http.SessionCreationPolicy;
 * 
 * @Configuration
 * 
 * @EnableWebSecurity public class WebSecurity extends
 * WebSecurityConfigurerAdapter { private Environment env;
 * 
 * @Autowired public WebSecurity(Environment env) { super(); this.env = env; }
 * 
 * protected void configure(HttpSecurity http) throws Exception {
 * 
 * http.csrf().disable(); http.headers().frameOptions().disable();
 * http.authorizeRequests().antMatchers(HttpMethod.GET,
 * env.getProperty("api.path")).permitAll().anyRequest()
 * .authenticated().and().addFilter(new
 * AuthorizationFilter(authenticationManager(), env));
 * http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.
 * STATELESS); }
 * 
 * }
 */