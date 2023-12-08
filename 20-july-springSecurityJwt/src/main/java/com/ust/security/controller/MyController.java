package com.ust.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ust.security.model.AuthRequset;
import com.ust.security.model.MyUser;
import com.ust.security.service.MyUserDetailsService;
import com.ust.security.util.JwtUtil;
import com.ust.security.util.Response;

@RestController
//@RequestMapping("/ust")
public class MyController {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUtil jutil;
	@Autowired
	private MyUserDetailsService ms;

	@GetMapping
	public String home() {
		return "welcome to home";
	}

	@GetMapping("/contact")
	public String contact() {
		return "Contact us";
	}

	@PostMapping("/login")
	public Response login(@RequestBody AuthRequset user) {
//		UserDetails temp = ms.loadUserByUsername(user.getUsername());
		Response response = new Response(null);

		try
		{
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		}catch(Exception ex)
		{
			return response;
		}
		
				String token = jutil.generateToken(user.getUsername());
				response.setJwt(token);
			
		
		return response;
	}

	@PostMapping("/signup")
	public MyUser signup(@RequestBody MyUser user) {
		return ms.create(user);
	}

}