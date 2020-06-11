package com.nineleaps.supplier.listener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.nineleaps.supplier.config.PropertyConfiguration;
import com.nineleaps.supplier.constants.GlobalConstants;
import com.nineleaps.supplier.email.SendEmail;
import com.nineleaps.supplier.exception.NoContentException;
import com.nineleaps.supplier.exception.UserException;
import com.nineleaps.supplier.model.ItemConsumer;
import com.nineleaps.supplier.model.OrderConsumer;
import com.nineleaps.supplier.model.OrderDetails;
import com.nineleaps.supplier.model.Product;
import com.nineleaps.supplier.model.Supplier;
import com.nineleaps.supplier.service.SupplierService;
import com.nineleaps.supplier.springclient.SpringRestClient;

@Service
public class KafkaConsumer {
	@Autowired
	private SpringRestClient springRestClient;
	
	@Autowired
	private PropertyConfiguration propertyConfiguration;
	
	@Autowired
	SupplierService supplierService;
	
	final Logger logger = Logger.getLogger(KafkaConsumer.class);

	
	public String orderJson = null;
	 Gson gson = new Gson(); 

	/* @KafkaListener(topics = "Kafka_Order_test", groupId = "group_id")
	    public void consume(String order) {
	        System.out.println("Consumed message: " + order);
	        OrderConsumer ronaldo =null;
	        
	       order = order.substring(1, order.length()- 1);
	       System.out.println("Encoded message: " + order);
	       
	        
	           JSONObject jsonObj = new JSONObject(order);
		
		try {
				ObjectMapper mapper = new ObjectMapper();
				mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

				ronaldo = new ObjectMapper().readValue(order, OrderConsumer.class);
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	       
	      OrderConsumer orderConsumer = gson.fromJson(orderJson, 
	          		  OrderConsumer.class); 
	     	  
	       System.out.println("orderConsumer>>>"+jsonObj);
	    }*/
	 
	  
	


    @KafkaListener(topics = "Kafka_Order_test3", groupId = "group_json",
            containerFactory = "kafkaListenerContainerFactory")
    public void consumeJson(@Payload String order) {
    	 JSONObject jsonObj = new JSONObject(order);
    	 OrderConsumer orderConsumer =null;
    	 ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

			try {
				orderConsumer = mapper.readValue(jsonObj.toString(), OrderConsumer.class);
				Map<String,OrderDetails> suppliermap =getSupplierDetails(orderConsumer);
				sendEmail(suppliermap);
			} catch (JsonParseException e) {
				logger.error("JsonParseException", e);
			} catch (JsonMappingException e) {
				logger.error("JsonMappingException", e);

			} catch (IOException e) {
				logger.error("IOException", e);
			} catch (NoContentException e) {
				logger.error("IOException", e);
			}
	
 		
          }
    
    public void sendEmail(Map<String, OrderDetails> supplierMap) throws NoContentException {
    	  for (Map.Entry<String,OrderDetails> entry : supplierMap.entrySet()) { 
    		Supplier supplier =  supplierService.fetchRecordFromSupplierTable(entry.getKey()) ;
    		entry.getValue().setSupplierEmail(supplier.getEmail());
    		sendEmailToSuppliers(entry.getKey(),entry.getValue());
    		
		}
	}

	public void sendEmailToSuppliers(String supplierId,OrderDetails orderDetails) {
		SendEmail sendEmail = new SendEmail();
		
		sendEmail.sendEmailToSupplier(supplierId,orderDetails);
			
	}

	public Map<String,OrderDetails> getSupplierDetails(OrderConsumer orderConsumer) throws NoContentException {

		List<ItemConsumer> itemsList = new ArrayList<>();
		int n = orderConsumer.getItem().size();
		List<String> supplierIds = new ArrayList<>();
		String productId = null;
		String productUrl = null;
		Map<String,OrderDetails> map = new HashMap<>();
		for (int i = 0; i < n; i++) {
			productId = orderConsumer.getItem().get(i).getProductId();
			OrderDetails orderDetails = new OrderDetails();

			productUrl = propertyConfiguration.getProductUrl().replace("{id}", productId);
			Map<String, String> headerMap = new HashMap<>();
			headerMap.put(GlobalConstants.CONTENT_TYPE, GlobalConstants.APPLICATION_JSON);

			ResponseEntity<String> product;
			try {
				product = (ResponseEntity<String>) springRestClient.call(productUrl,
						headerMap, productId, HttpMethod.GET);
				ObjectMapper mapper = new ObjectMapper();
				Product productData = mapper.readValue(product.getBody(), Product.class);
				if(productData!=null) {
					orderDetails.setDescription(productData.getDescription());
					orderDetails.setPrice(productData.getPrice());
					orderDetails.setProductId(productData.getProductId());
					orderDetails.setQuantity(orderConsumer.getItem().get(i).getQuantity());
					orderDetails.setProductName(productData.getName());
					orderDetails.setCustomerEmail(orderConsumer.getCustomerEmail());
					orderDetails.setOrderId(orderConsumer.getId());
					map.put(productData.getSupplierId(),orderDetails);
				}
					

			} catch (JsonParseException e) {
				logger.error("JsonParseException", e);

			} catch (JsonMappingException e) {
				logger.error("JsonMappingException", e);

			} catch (IOException e) {
				logger.error("IOException", e);

			} catch (UserException e) {
			
				logger.error("error occured while getting supplier Details", e);

			}

		}
		
		return map;
	}   

}
