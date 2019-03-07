package com.edu.eteration.demo.productservice;


public class OrderRequestDTO{

	private int productId;
	private int customerId;
	private int count;

	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "OrderDTO [productId=" + productId + ", customerId=" + customerId + ", count=" + count + "]";
	}


}
