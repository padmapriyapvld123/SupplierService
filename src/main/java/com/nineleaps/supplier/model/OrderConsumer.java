package com.nineleaps.supplier.model;

import java.io.Serializable;


import java.util.ArrayList;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)

public class OrderConsumer{
		
		public String id;
		public String date;
		public String customerName;
		public String customerEmail;
		public String customerAddress;
		@JsonProperty(value="item")
		public List<ItemConsumer> item;
		public double total;
		
		public OrderConsumer() {
			
		}
		
		

		public OrderConsumer(String id, String date, String customerName, String customerEmail, String customerAddress,
				List<ItemConsumer> item, double total) {
			super();
			this.id = id;
			this.date = date;
			this.customerName = customerName;
			this.customerEmail = customerEmail;
			this.customerAddress = customerAddress;
			this.item = item;
			this.total = total;
		}



		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

		public String getCustomerName() {
			return customerName;
		}

		public void setCustomerName(String customerName) {
			this.customerName = customerName;
		}

		public String getCustomerEmail() {
			return customerEmail;
		}

		public void setCustomerEmail(String customerEmail) {
			this.customerEmail = customerEmail;
		}

		public String getCustomerAddress() {
			return customerAddress;
		}

		public void setCustomerAddress(String customerAddress) {
			this.customerAddress = customerAddress;
		}

		public List<ItemConsumer> getItem() {
			return item;
		}

		public void setItem(List<ItemConsumer> item) {
			this.item = item;
		}

		public double getTotal() {
			return total;
		}

		public void setTotal(double total) {
			this.total = total;
		}

							    
}
