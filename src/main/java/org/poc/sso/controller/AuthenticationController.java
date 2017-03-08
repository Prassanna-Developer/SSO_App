package org.poc.sso.controller;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.poc.sso.controller.data.LoginUser;
import org.poc.sso.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	 @Value("${jwt.header}")
    private String tokenHeader;
	 
	 public final static String CLIENT_URL="CLIENT_URL";

	
	

	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	public String authenticateUser(@ModelAttribute("userLoginForm") LoginUser user, HttpSession session,HttpServletResponse response ) throws IOException {

		final Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
		String token = getTokenFromSession(session);
		final String clientURL = (String) session.getAttribute(CLIENT_URL);
		
		
		if(token==null){
			token = jwtTokenUtil.generateToken(userDetails, null);
			storeTokenInSession(session, token);
		}
		Cookie myCookie =
				  new Cookie(tokenHeader, token);
		response.addCookie(myCookie);

		return "redirect:" + clientURL + "?jwtToken=" + token;

	}




	private void storeTokenInSession(HttpSession session, final String token) {
		session.setAttribute(tokenHeader, token);
	}
	
	private String getTokenFromSession(HttpSession session){
		return (String) session.getAttribute(tokenHeader);
	}
}
