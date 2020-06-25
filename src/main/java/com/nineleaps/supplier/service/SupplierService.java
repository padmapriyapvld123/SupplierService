package com.nineleaps.supplier.service;


import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.nineleaps.supplier.entity.SupplierEntity;

import com.nineleaps.supplier.exception.NoContentException;
import com.nineleaps.supplier.model.Product;
import com.nineleaps.supplier.model.Supplier;
import com.nineleaps.supplier.repository.SupplierRepository;

@Service
public class SupplierService {

	@Autowired
	private SupplierRepository supplierRepository;
	

	

	public Supplier saveIntoSupplierTable(Supplier supplier) {
		SupplierEntity entity = supplierRepository.save(mapObjectToEntity(supplier));
		return mapEntityToObject(entity);
	}

	public SupplierEntity mapObjectToEntity(Supplier supplier) {
		
		SupplierEntity entity = new SupplierEntity();
		entity.setSupplierId(supplier.getSupplierId());
		entity.setName(supplier.getName());
		entity.setEmail(supplier.getEmail());
		entity.setSupplierId(supplier.getSupplierId());
		
		return entity;
	}

	
	public Supplier mapEntityToObject(SupplierEntity entity) {
		Supplier table = new Supplier();
		
		table.setSupplierId(entity.getSupplierId());
		table.setName(entity.getName());
		table.setEmail(entity.getEmail());
		table.setSupplierId(entity.getSupplierId());
		return table;
	}
	
	public Supplier fetchRecordFromSupplierTable(String id) throws NoContentException {
		
		Optional<SupplierEntity> entity = supplierRepository.findById(id);
		if(!entity.isPresent()) {
			throw new NoContentException(HttpStatus.NO_CONTENT);
		}
		return mapEntityToObject(entity.get());
	
}

	public Supplier updateRecordIntoSupplierTable(Supplier supplier) {
		SupplierEntity entity = supplierRepository.save(mapObjectToEntity(supplier));
		  return mapEntityToObject(entity);
		 
}

}