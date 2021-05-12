/*
 * package com.apps.developerblog.photoapp.api.users.security;
 * 
 * import java.io.IOException; import java.util.ArrayList; import
 * java.util.Date;
 * 
 * import javax.servlet.FilterChain; import javax.servlet.ServletException;
 * import javax.servlet.http.HttpServletRequest; import
 * javax.servlet.http.HttpServletResponse;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.core.env.Environment; import
 * org.springframework.security.authentication.AuthenticationManager; import
 * org.springframework.security.authentication.
 * UsernamePasswordAuthenticationToken; import
 * org.springframework.security.core.Authentication; import
 * org.springframework.security.core.userdetails.User; import
 * org.springframework.security.web.authentication.
 * UsernamePasswordAuthenticationFilter;
 * 
 * import com.apps.developerblog.photoapp.api.users.service.UsersService; import
 * com.apps.developerblog.photoapp.api.users.shared.UserDto; import
 * com.apps.developerblog.photoapp.api.users.ui.model.LoginRequestModel; import
 * com.fasterxml.jackson.databind.ObjectMapper;
 * 
 * import io.jsonwebtoken.Jwts; import io.jsonwebtoken.SignatureAlgorithm;
 * 
 * public class AuthenticationFilter extends
 * UsernamePasswordAuthenticationFilter {
 * 
 * @Autowired private UsersService usersService; private Environment
 * environment;
 * 
 * public AuthenticationFilter(Environment environment, AuthenticationManager
 * authenticationManager) { super();
 * 
 * this.environment = environment;
 * super.setAuthenticationManager(authenticationManager); }
 * 
 * public Authentication attemptAuthentication(HttpServletRequest req,
 * HttpServletResponse res) { try { LoginRequestModel creds = new
 * ObjectMapper().readValue(req.getInputStream(), LoginRequestModel.class);
 * 
 * return getAuthenticationManager().authenticate( new
 * UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword(),
 * new ArrayList<>())); } catch (IOException e) { throw new RuntimeException(e);
 * } }
 * 
 * protected void successfulAuthentication(HttpServletRequest req,
 * HttpServletResponse res, FilterChain chain, Authentication auth) throws
 * IOException, ServletException {
 * 
 * String userName = ((User) auth.getPrincipal()).getUsername(); UserDto
 * userDetailsbyEmail = usersService.getUserDetailsbyEmail(userName); String
 * token = Jwts.builder().setSubject(userDetailsbyEmail.getUserId())
 * .setExpiration(new Date( System.currentTimeMillis() +
 * Long.parseLong(environment.getProperty("token.expiration_time"))))
 * .signWith(SignatureAlgorithm.HS512,
 * environment.getProperty("token.secret")).compact(); res.addHeader("token",
 * token); res.addHeader("userId", userDetailsbyEmail.getUserId()); } }
 */