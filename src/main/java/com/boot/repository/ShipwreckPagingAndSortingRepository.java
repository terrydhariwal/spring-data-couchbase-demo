package com.boot.repository;

import org.springframework.data.couchbase.repository.CouchbasePagingAndSortingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.boot.model.Shipwreck;

public interface ShipwreckPagingAndSortingRepository extends CouchbasePagingAndSortingRepository<Shipwreck, String> {
	  Iterable<Shipwreck> findAll(Sort sort);
	  Page<Shipwreck> findAll(Pageable pageable);
}
