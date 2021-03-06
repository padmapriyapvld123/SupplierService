package com.nineleaps.supplier;

import org.cassandraunit.spring.CassandraDataSet;



import org.cassandraunit.spring.CassandraUnit;
import org.cassandraunit.spring.CassandraUnitDependencyInjectionTestExecutionListener;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nineleaps.supplier.constants.JsonConstants;
import com.nineleaps.supplier.controller.SupplierController;
import com.nineleaps.supplier.entity.SupplierEntity;
import com.nineleaps.supplier.model.Supplier;
import com.nineleaps.supplier.repository.SupplierRepository;
import com.nineleaps.supplier.service.SupplierService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Mockito.when;

import static org.mockito.BDDMockito.*;

@RunWith(JUnitPlatform.class)
@SpringBootTest({ "spring.data.cassandra.port=9042", "spring.data.cassandra.keyspace-name=cycling1" })

@EnableAutoConfiguration
@ComponentScan
@ContextConfiguration
//@TestExecutionListeners({ CassandraUnitDependencyInjectionTestExecutionListener.class,
//    DependencyInjectionTestExecutionListener.class })
@CassandraDataSet(value = { "cassandra-init.sh" }, keyspace = "cycling1")
@CassandraUnit
//@TestMethodOrder(OrderAnnotation.class)
public class SupplierServiceTest {

	private MockMvc mockMvc;

	@Autowired
	private SupplierService supplierService;

	@Autowired
	private SupplierController supplierController;
	
	



	@BeforeEach
	public void init() {
		supplierController.setSupplierService(supplierService);

		this.mockMvc = MockMvcBuilders.standaloneSetup(supplierController).build();
		// mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

	}


	@Test
	//@Order(1)  
	public void saveSupplier() throws Exception {

		String mockSupplierJson = JsonConstants.mockSupplierJson;
		ObjectMapper mapper = new ObjectMapper();
		Supplier supplier = mapper.readValue(mockSupplierJson, Supplier.class);
	
		mockMvc.perform(MockMvcRequestBuilders.post("/supplier/save").content(asJsonString(supplier))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.supplierId").exists());

	}
	
	@Test
	//@Order(2)  
	public void getSupplierById() throws Exception {

		mockMvc.perform(get("/supplier/{id}", "S003").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}

	@Test
	//@Order(3)  
	public void getAllSuppliers() throws Exception {

		mockMvc.perform(get("/supplier/getAllSuppliers").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}


	@Test
	//@Order(4)  
	public void updateSupplierAPI() throws Exception {
		String mockSupplierJson = JsonConstants.mockSupplierJson;
		ObjectMapper mapper = new ObjectMapper();
		SupplierEntity supplier = mapper.readValue(mockSupplierJson, SupplierEntity.class);
	

		mockMvc.perform(MockMvcRequestBuilders.put("/supplier/updateSupplier/{id}", "S003")
				.content(asJsonString(supplier))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.email").value(supplier.getEmail()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value(supplier.getName()));
	}
	
	@Test
	//@Order(5)  
	public void deleteSupplierAPI() throws Exception 
	{
		mockMvc.perform( MockMvcRequestBuilders.delete("/supplier/deleteSupplier/{id}", "S003") )
	        .andExpect(status().isNoContent());
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}