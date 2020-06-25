package com.nineleaps.supplier;

import org.cassandraunit.spring.CassandraDataSet;
import org.cassandraunit.spring.CassandraUnit;
import org.cassandraunit.spring.CassandraUnitDependencyInjectionTestExecutionListener;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import com.nineleaps.supplier.controller.SupplierController;
import org.springframework.beans.factory.annotation.Autowired;
import com.nineleaps.supplier.repository.SupplierRepository;


/*@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
@ContextConfiguration(classes = {TestContext.class, SupplierController.class})
@WebAppConfiguration
@RunWith(SpringRunner.class)
@ComponentScan(basePackages={"com.nineleaps.supplier"}) 
@EntityScan(basePackages={"com.nineleaps.supplier"}) 
@EnableCassandraRepositories(basePackages={"com.nineleaps.supplier"}) */

/*@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest({"spring.data.cassandra.port=9142","spring.data.cassandra.keyspace-name=cycling1"})
@EnableAutoConfiguration
@ComponentScan
@ContextConfiguration
@TestExecutionListeners({ CassandraUnitDependencyInjectionTestExecutionListener.class,
    DependencyInjectionTestExecutionListener.class })
@CassandraDataSet(value = { "setupTables.cql" }, keyspace = "cycling1")
@CassandraUnit*/

@SpringBootTest
public class SupplierApplicationTests {
	

    @Autowired
    SupplierRepository repo;


	@Test
	public void contextLoads() {
		
	}

}
