package com.nineleaps.supplier.email;

import java.util.*; 
import javax.mail.*; 
import javax.mail.internet.*;

import org.springframework.beans.factory.annotation.Autowired;

import com.nineleaps.supplier.config.PropertyConfiguration;
import com.nineleaps.supplier.model.OrderDetails;

import javax.activation.*; 
  
  
public class SendEmail  
{ 
  public void sendEmailToSupplier(PropertyConfiguration propertyConfiguration,String supplierId,OrderDetails orderDetails) {

      final String username = System.getenv("USERNAME");
      final String password = System.getenv("PASSWORD");
	  
      
    

      Properties prop = new Properties();
      prop.put("mail.smtp.host", "smtp.gmail.com");
      prop.put("mail.smtp.port", "587");
      prop.put("mail.smtp.auth", "true");
      prop.put("mail.smtp.starttls.enable", "true"); //TLS

      Session session = Session.getInstance(prop,
              new javax.mail.Authenticator() {
                  protected PasswordAuthentication getPasswordAuthentication() {
                      return new PasswordAuthentication(username, password);
                  }
              });

      try {

          Message message = new MimeMessage(session);
          message.setFrom(new InternetAddress(username));
          message.setRecipients(
                  Message.RecipientType.TO,
                  InternetAddress.parse(orderDetails.getSupplierEmail())
          );
          
          message.setSubject("Orders Received"); 
          
          // set body of the email. 
          message.setText("OrderId :"+" "+orderDetails.getOrderId() +"\n "+
         		 		 "ProductId :"+" "+orderDetails.getProductId() +"\n "+
         		 		"Product Name :"+" "+orderDetails.getProductName() +"\n "+
         		 		"Price :"+" "+orderDetails.getPrice()+"\n"+
         		 		"Quantity :"+" "+orderDetails.getQuantity() +"\n"+
         		 		"Description :"+" "+orderDetails.getDescription()+"\n"); 
   
       /*   message.setSubject("Testing Gmail TLS");
          message.setText("Dear Mail Crawler,"
                  + "\n\n Please do not spam my email!");*/

          Transport.send(message);

          System.out.println("Done");

      } catch (MessagingException e) {
          e.printStackTrace();
      }
  }

  }


      
      
      


   