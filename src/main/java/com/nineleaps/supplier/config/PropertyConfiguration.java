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
	
	

	public String getProductUrl() {
		return productUrl;
	}

	public void setProductUrl(String productUrl) {
		this.productUrl = productUrl;
	}

	

}
