package com.nineleaps.supplier.model;

public class OrderDetails {
	public String orderId;
	public String customerEmail;
	public String productId;
	public String description;
	public String productName;
	public double price;
	public int quantity;
	public String supplierEmail;
	
	
	public OrderDetails() {
		
	}
	
	

	public OrderDetails(String orderId, String customerEmail, String productId, String description, String productName,
			double price, int quantity, String supplierEmail) {
		super();
		this.orderId = orderId;
		this.customerEmail = customerEmail;
		this.productId = productId;
		this.description = description;
		this.productName = productName;
		this.price = price;
		this.quantity = quantity;
		this.supplierEmail = supplierEmail;
	}



	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getSupplierEmail() {
		return supplierEmail;
	}

	public void setSupplierEmail(String supplierEmail) {
		this.supplierEmail = supplierEmail;
	}

			

}
