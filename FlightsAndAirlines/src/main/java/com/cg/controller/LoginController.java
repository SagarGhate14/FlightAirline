package com.cg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cg.entity.UserDetails;
import com.cg.service.LoginService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

	@Autowired
	LoginService service;

	@Autowired
	PasswordEncoder passwordEncoder;

	private final SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();

	// Login page
	@GetMapping("/login")
	public String loginPage() {

		return "Flight/login";
	}

	// Validating the credentials
	@PostMapping("/login")
	public String checkLogin(@RequestParam("username") String username, @RequestParam("password") String pass,
			HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {

		UserDetails user = service.checkUser(username);

		if (user != null && passwordEncoder.matches(pass, user.getPassword())) {
			// 1. Create Authentication
			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null,
					AuthorityUtils.NO_AUTHORITIES);

			// 2. Set Context
			SecurityContext context = SecurityContextHolder.createEmptyContext();
			context.setAuthentication(auth);
			SecurityContextHolder.setContext(context);

			// 3. PERSIST THE SESSION (Crucial for 2026)
			// Use the HttpSessionSecurityContextRepository to ensure it survives the
			// redirect

			securityContextRepository.saveContext(context, request, response);

			redirectAttributes.addFlashAttribute("loggedUserName", username);
			return "redirect:/api/list";
		}

		// If it fails, this triggers your 'invalid credentials' message
		return "redirect:/login?error=true";
	}

	// Register the New User
	@GetMapping("/register")
	public String registerUser(Model model) {
		model.addAttribute("UserDetails", new UserDetails());
		return "Flight/register";
	}

	@PostMapping("/register")
	public String saveUser(@ModelAttribute("UserDetails") UserDetails user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		service.saveUser(user);
		return "redirect:/login";
	}
}
