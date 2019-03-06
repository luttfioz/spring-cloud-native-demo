package com.edu.eteration.demo.productservice;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@GetMapping("/ping")
	public String ping(){
		return "product pong";
	}
	

}
