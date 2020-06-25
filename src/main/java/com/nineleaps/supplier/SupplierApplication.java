package com.nineleaps.supplier;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("com.nineleaps.supplier")
@ComponentScan(basePackages={"com.nineleaps.supplier"}) 
@EntityScan(basePackages={"com.nineleaps.supplier"}) 
@EnableCassandraRepositories(basePackages={"com.nineleaps.supplier"}) 
public class SupplierApplication {

	public static void main(String[] args) {
		SpringApplication.run(SupplierApplication.class, args);
	}
}