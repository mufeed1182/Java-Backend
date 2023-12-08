package com.ust.spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
	@GetMapping
	public String home() {
		return "Hello malapuram acharangal and anushtanangal samrakshikkapedanam";
	}
	
	@GetMapping("/abin")
	public String home1() {
		return "Ok da nadapadi sweekarikkunnathaaan";
	}

}
