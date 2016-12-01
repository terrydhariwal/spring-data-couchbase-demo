package com.boot.repository;

import java.util.List;

import org.springframework.data.couchbase.core.query.View;
import org.springframework.data.repository.CrudRepository;

import com.boot.model.Shipwreck;

public interface ShipwreckRepository extends CrudRepository<Shipwreck, String>{
	
	//N1QL queries
	List<Shipwreck> findByName(String name);
	List<Shipwreck> findByNameAndLatitude(String name, Double latitude);
	//Like Queries
	List<Shipwreck> findByNameLike(String name);
	List<Shipwreck> findByNameIsLike(String name);

	@View
	List<Shipwreck> findByYearDiscovered(Integer year);
	
	
}
