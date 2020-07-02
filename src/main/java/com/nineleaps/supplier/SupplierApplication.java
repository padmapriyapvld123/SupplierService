package com.nineleaps.supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ProtocolVersion;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.policies.DefaultRetryPolicy;
import com.datastax.driver.core.policies.LoggingRetryPolicy;

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