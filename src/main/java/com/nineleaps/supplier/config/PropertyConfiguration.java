package com.nineleaps.supplier.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(ignoreInvalidFields = true)
@EnableConfigurationProperties

public class PropertyConfiguration {
	
	@Value("${PRODUCT_URL}")
	private String productUrl;
	
	@Value("${testpoc.username}")
	private String userName;
	
	@Value("${testpoc.password}")
	private String password;

	public String getProductUrl() {
		return productUrl;
	}

	public void setProductUrl(String productUrl) {
		this.productUrl = productUrl;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
	

}
