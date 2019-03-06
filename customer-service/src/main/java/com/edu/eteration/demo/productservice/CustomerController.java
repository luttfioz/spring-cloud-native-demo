package com.edu.eteration.demo.productservice;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	
	@GetMapping("/ping")
	public String ping(){
		return "customer pong";
	}
	

}
