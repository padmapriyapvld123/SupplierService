package com.nineleaps.supplier.repository;

import java.util.Optional;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import com.nineleaps.supplier.entity.SupplierEntity;
import com.nineleaps.supplier.model.Supplier;


public interface SupplierRepository extends CassandraRepository<SupplierEntity,String> {
	 Optional<SupplierEntity> findById(String id);
	
}
