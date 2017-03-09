package org.poc.sso.controller;

import java.io.IOException;

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
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SSOAppController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Value("${jwt.header}")
	private String tokenHeader;

	public final static String CLIENT_URL = "CLIENT_URL";
	
	/**
	 *  When Client redirects to SSO, this method will be hit and the call will be re directed to login.html to enter user name and password
	 */
	@RequestMapping(value = "/entryPoint", method = RequestMethod.GET)
	public String redirectToSSO(@RequestParam String clientURL, HttpSession session) {
		session.setAttribute("CLIENT_URL", clientURL);
		return "redirect:/login.html";
	}
	
	
	/**
	 *  After the user enters the credentials in the User Login Form, the call will come here and will validate that he is authenticated or not
	 */
	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	public String authenticateUser(@ModelAttribute("userLoginForm") LoginUser user, HttpSession session,
			HttpServletResponse response) throws IOException {

		final Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
		final String clientURL = (String) session.getAttribute(CLIENT_URL);

		final String token = jwtTokenUtil.generateToken(userDetails, null);

		return "redirect:" + clientURL + "?jwtToken=" + token;

	}
	
	

}
