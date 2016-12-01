package com.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.boot.model.Shipwreck;
import com.boot.repository.ShipwreckPagingAndSortingRepository;
import com.boot.repository.ShipwreckRepository;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.document.JsonArrayDocument;
import com.couchbase.client.java.document.json.JsonArray;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
//import com.couchbase.client.java.query.dsl.Sort;
import org.springframework.data.domain.Sort;

@RestController
@RequestMapping("api/v1")
public class ShipwreckController {
	
	@Autowired
	private ShipwreckRepository shipwreckRepository;
	
	@Autowired
	private ShipwreckPagingAndSortingRepository shipwreckPagingAndSortingRepository;
	
	//works
	@RequestMapping(value="shipwrecksSorted", method= RequestMethod.GET)
	public List<Shipwreck> sortedlist() {
		return (List<Shipwreck>) shipwreckPagingAndSortingRepository.findAll(new Sort(Sort.Direction.ASC, "Name"));
	}
	
	@RequestMapping(value="shipwrecksPagenate", method= RequestMethod.GET)
	public List<Shipwreck> pagenate(@RequestParam Integer offset, @RequestParam Integer pageSize) {
		Page<Shipwreck> shipWreckList = shipwreckPagingAndSortingRepository.findAll(new PageRequest(offset, pageSize));
		return shipWreckList.getContent();
	}
	

	//works
	//May want to suppress this method for performance reasons
	@RequestMapping(value="shipwrecks", method= RequestMethod.GET)
	public List<Shipwreck> list() {
		//return ShipwreckStub.list();
		return (List<Shipwreck>) shipwreckRepository.findAll();
	}
	
	@RequestMapping(value="createJsonArray", method= RequestMethod.GET)
	public void createJsonArray() {
		System.out.println("in createJsonArray");
		Cluster cluster = CouchbaseCluster.create("localhost");
		Bucket bucket = cluster.openBucket("default","");
		bucket.upsert(JsonArrayDocument.create("test",JsonArray.create().add("one").add("two")));
	}
	
	//works
	@RequestMapping(value="shipwrecks", method= RequestMethod.POST)
	public Shipwreck create(@RequestBody Shipwreck shipwreck) {
		//return ShipwreckStub.create(shipwreck);
		return shipwreckRepository.save(shipwreck);
	}
	
	//works
	@RequestMapping(value="shipwrecks/{id}", method= RequestMethod.GET)
	//public Shipwreck create(@PathVariable Long id) {
	public Shipwreck findOne(@PathVariable String id) {
		//return ShipwreckStub.get(id);
		return shipwreckRepository.findOne(id);
	}
	
	//works
	@RequestMapping(value="shipwrecks/{id}", method= RequestMethod.PUT)
	public Shipwreck update(@PathVariable Long id, @RequestBody Shipwreck shipwreck) {
		//return ShipwreckStub.update(id, shipwreck);
		return shipwreckRepository.save(shipwreck);
	}
	
	//works
	@RequestMapping(value="shipwrecks/{id}", method= RequestMethod.DELETE)
	//public Shipwreck delete(@PathVariable Long id) {
	public void delete(@PathVariable String id) {
		//return ShipwreckStub.delete(id);
		shipwreckRepository.delete(id);
	}
	
	//works
	@RequestMapping(value="shipwrecks/findByName/{name}", method= RequestMethod.GET)
	public List<Shipwreck> findByName(@PathVariable String name) {
		return shipwreckRepository.findByName(name);
	}
	
	//works
	@RequestMapping(value="shipwrecks/findByNameAndLatitude", method= RequestMethod.GET)
	public List<Shipwreck> findByNameAndLatitude(@RequestParam String name, @RequestParam Double latitude) {
		System.out.println("in findByNameAndLatitude");
		System.out.println("Name = " + name + ", Latitude = " + latitude);
		return shipwreckRepository.findByNameAndLatitude(name, latitude);
	}
	
	//works
	@RequestMapping(value="shipwrecks/findByYearDiscovered/{year}", method= RequestMethod.GET)
	public List<Shipwreck> findByYearDiscovered(@PathVariable Integer year) {
		System.out.println("in findByYearDiscovered");
		return shipwreckRepository.findByYearDiscovered(year);
	}
	
	//works
	@RequestMapping(value="shipwrecks/findByNameLike/{name}", method= RequestMethod.GET)
	public List<Shipwreck> findByNameLike(@PathVariable String name) {
		name = "%" + name  + "%";
		System.out.println("in findByNameLike(" + name + ")" );
		
		//Can use both Like and Islike
		//return shipwreckRepository.findByNameLike("%" + name + "%");
		return shipwreckRepository.findByNameIsLike("%" + name  + "%");
	}
	
	
	
}
