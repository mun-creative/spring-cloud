/*
 * package com.apps.developerblog.photoapp.api.users.security;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.core.env.Environment; import
 * org.springframework.security.config.annotation.authentication.builders.
 * AuthenticationManagerBuilder; import
 * org.springframework.security.config.annotation.web.builders.HttpSecurity;
 * import org.springframework.security.config.annotation.web.configuration.
 * EnableWebSecurity; import
 * org.springframework.security.config.annotation.web.configuration.
 * WebSecurityConfigurerAdapter; import
 * org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; import
 * org.springframework.security.crypto.password.PasswordEncoder;
 * 
 * import com.apps.developerblog.photoapp.api.users.service.UsersService;
 * 
 * @Configuration
 * 
 * @EnableWebSecurity public class WebSecurity extends
 * WebSecurityConfigurerAdapter { private Environment environment;
 * 
 * @Autowired private UsersService usersService; // private
 * BCryptPasswordEncoder bCryptPasswordEncoder;
 * 
 * public WebSecurity(Environment environment) { super(); this.environment =
 * environment;
 * 
 * }
 * 
 * protected void configure(HttpSecurity http) throws Exception {
 * http.csrf().disable(); //
 * http.authorizeRequests().antMatchers("/users/**").permitAll();
 * http.authorizeRequests().antMatchers("/users/**").hasAnyAuthority(environment
 * .getProperty("gateway.ip")).and() .addFilter(getAuthenticationFilter());
 * http.headers().frameOptions().disable(); }
 * 
 * private AuthenticationFilter getAuthenticationFilter() throws Exception {
 * AuthenticationFilter authFilter = new AuthenticationFilter(environment,
 * authenticationManager()); //
 * authFilter.setAuthenticationManager(authenticationManager()); return
 * authFilter; }
 * 
 * protected void configure(AuthenticationManagerBuilder auth) throws Exception
 * { auth.userDetailsService(usersService).passwordEncoder(passwordEncoder()); }
 * 
 * @Bean public PasswordEncoder passwordEncoder() { BCryptPasswordEncoder
 * bCryptPasswordEncoder = new BCryptPasswordEncoder(); return
 * bCryptPasswordEncoder; } }
 */