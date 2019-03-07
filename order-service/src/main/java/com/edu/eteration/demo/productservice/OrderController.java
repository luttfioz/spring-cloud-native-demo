package com.edu.eteration.demo.productservice;

import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/orders")
public class OrderController {


	@GetMapping("/ping")
	public String ping(){
		return "order pong";
	}



	@RequestMapping(method = RequestMethod.POST)
	public OrderResultDTO createOrder(@RequestBody OrderRequestDTO order) throws InterruptedException, ExecutionException {

		OrderResultDTO or = new OrderResultDTO();
		or.setMessage(order.getCustomerId() + "-" + order.getProductId());

		return or;
	}
	

}
