package com.nineleaps.supplier.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nineleaps.supplier.entity.SupplierEntity;
import com.nineleaps.supplier.exception.NoContentException;
import com.nineleaps.supplier.model.Supplier;
import com.nineleaps.supplier.repository.SupplierRepository;
import com.nineleaps.supplier.service.SupplierService;


@RestController
@RequestMapping("/supplier")
public class SupplierController {

	@Autowired
	private SupplierService supplierService;
	
	@Autowired
	private SupplierRepository supplierRepository;

	@PostMapping("/save")
	public ResponseEntity<Supplier> saveIntoSupplierTable(@RequestBody Supplier supplier) {
		return new ResponseEntity<>(supplierService.saveIntoSupplierTable(supplier), HttpStatus.OK);
	}

	@GetMapping(path = "{id}")
	public ResponseEntity<Supplier> fetchRecordFromSupplierTable(@PathVariable("id") String supplierId){
		Supplier supplierData = null;
		try {
			supplierData = supplierService.fetchRecordFromSupplierTable(supplierId);
		} catch (NoContentException e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(supplierData, HttpStatus.OK);
		
	}
	
	@PutMapping("/updateSupplier/{id}")
	public ResponseEntity<Supplier> updateSupplier(@PathVariable("id") String supplierId, @RequestBody Supplier supplier) {
		
		
		 Optional<SupplierEntity> supplierData = supplierRepository.findById(supplierId);

	  if (supplierData.isPresent()) {
		
		return new ResponseEntity<>(supplierService.updateRecordIntoSupplierTable(supplier), HttpStatus.OK);
		
		 } else {
		    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		  }
	}
	@DeleteMapping("/deleteSupplier/{id}")
	public ResponseEntity<HttpStatus> deleteSupplier(@PathVariable("id") String supplierId) {
	  try {
		
			
	    supplierRepository.deleteById(supplierId);
	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	  } catch (Exception e) {
	    return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
	  }
	}
	
	@GetMapping("/getAllSuppliers")
	public ResponseEntity<List<SupplierEntity>> getAllSuppliers() {
	  try {
	    List<SupplierEntity> suppliers = new ArrayList<SupplierEntity>();

	   
	      supplierRepository.findAll().forEach(suppliers::add);
	    
	    if (suppliers.isEmpty()) {
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }

	    return new ResponseEntity<>(suppliers, HttpStatus.OK);
	  } catch (Exception e) {
	    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	}

}
