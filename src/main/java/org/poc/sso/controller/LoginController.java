package org.poc.sso.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

	@RequestMapping(value = "/entryPoint", method = RequestMethod.GET)
	public String redirectToSSO(@RequestParam String clientURL, HttpSession session) {
		session.setAttribute("CLIENT_URL", clientURL);
		return "redirect:/login.html";
	}

}
