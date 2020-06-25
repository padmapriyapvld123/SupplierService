package com.nineleaps.supplier.config;

import java.util.Arrays;
import java.util.List;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.DropKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.KeyspaceOption;
import org.springframework.data.cassandra.core.mapping.CassandraMappingContext;
import org.springframework.data.cassandra.core.mapping.SimpleUserTypeResolver;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import com.datastax.driver.core.AuthProvider;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PlainTextAuthProvider;
import com.datastax.driver.core.Session;

@Configuration
@EnableConfigurationProperties(CassandraProperties.class)
@EnableCassandraRepositories("com.nineleaps.supplier.repository")
public class CassandraConfig extends AbstractCassandraConfiguration {

	@Value("${spring.data.cassandra.keyspace-name}")
	private String keySpace;

	@Value("${spring.data.cassandra.contact-points}")
	private String contactPoints;

	@Value("${spring.data.cassandra.port}")
	private int port;

	@Value("${spring.data.cassandra.username}")
	private String userName;

	@Value("${spring.data.cassandra.password}")
	private String password;
	
	 @Value("${cassandra.basePackages}")
	  private String basePackages;
	 
	 @Value("${cassandra.hosts}")
		private String cassandraHosts;

	@Override
	  protected String getKeyspaceName() {
	    return keySpace;
	  }

	  @Override
	  protected String getContactPoints() {
	    return contactPoints;
	  }

	

	  @Override
	  public SchemaAction getSchemaAction() {
	    return SchemaAction.CREATE_IF_NOT_EXISTS;
	  }

	  @Override
	  public String[] getEntityBasePackages() {
	    return new String[] {"com.nineleaps.supplier.repository"};
	  }
	  
	 /* @Override
	  protected boolean getMetricsEnabled() { return false; }*/
	  
	/*  @Override
	    public CassandraClusterFactoryBean cluster() {
	        CassandraClusterFactoryBean bean = super.cluster();
	        bean.setContactPoints(contactPoints);
	        return bean;
	    }*/
	/*  @Bean
	    public CassandraClusterFactoryBean cluster() {
	        CassandraClusterFactoryBean cluster =
	                new CassandraClusterFactoryBean();
	        cluster.setContactPoints("localhost");
	        cluster.setPort(Integer.parseInt("9142"));
	        

	        return cluster;
	    }*/
	  
	  @Override
	  protected boolean getMetricsEnabled() { return false; }
	 
	 
	  
	  
	  
	  
	/*  @Override
	    protected AuthProvider getAuthProvider() {
	        return new PlainTextAuthProvider(userName, password);
	    }
	  
	  @Override
	  protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {

	      CreateKeyspaceSpecification specification = CreateKeyspaceSpecification.createKeyspace(keySpace)
	              .ifNotExists()
	              .with(KeyspaceOption.DURABLE_WRITES, true);

	      return Arrays.asList(specification);
	  }



	  @Override
	  protected List<DropKeyspaceSpecification> getKeyspaceDrops() {
	      return Arrays.asList(DropKeyspaceSpecification.dropKeyspace(keySpace));
	  }
	  
	  
	  Cluster cluster =
			    Cluster.builder()
			      .addContactPoint("192.168.99.100").withPort(9042)
			      .build();

			 	  Session session = cluster.connect(keySpace);*/



	  
}
