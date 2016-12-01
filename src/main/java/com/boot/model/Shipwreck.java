package com.boot.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
//import com.couchbase.client.java.repository.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import com.couchbase.client.java.repository.annotation.Field;

//import com.couchbase.client.java.repository.annotation.Field;

@Document
public class Shipwreck {

	// this also exists in JSON, without @Field,
	// however it is best practice to include this annotation because it
	// expresses intent
	int foo;

	@Id
	@Field
	Long id;

	@Field("Name")
	@Size(min = 3)
	String name;

	@Field
	String description;

	@Field
	String condition;

	@Field
	Integer depth;

	@Field
	Double latitude;

	@Field
	Double longitude;

	@Field
	Integer yearDiscovered;

	@Field
	private List<Child> children;

	@Field
	private NestedChild nestedChild;

	@Document
	static class NestedChild {

		@Field
		private String foo;

		@Field
		private Child child;

		public NestedChild() {
			foo = "bar";
			child = new Child("name2", 1);
		}

		public String getFoo() {
			return foo;
		}

		public void setFoo(String foo) {
			this.foo = foo;
		}

		public Child getChild() {
			return child;
		}

		public void setChild(Child child) {
			this.child = child;
		}
	}

	// https://docs.oracle.com/javase/tutorial/java/javaOO/nested.html
	@Document
	static class Child {

		@Field
		private String name;
		
		@Field
		private int age;

		Child(String name, int age) {
			this.name = name;
			this.age = age;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

	}

	public Shipwreck() {
		this.id = 1L;
		this.name = "name";
		this.description = "description";
		this.condition = "condition";
		this.depth = 1;
		this.latitude = 1.1;
		this.longitude = 1.1;
		this.yearDiscovered = 1;
		this.children = new ArrayList<Child>();
		children.add(new Child("name", 1));
		children.add(new Child("name", 2));
		this.nestedChild = new NestedChild();

//		System.out.println("In Shipwreck NoArg Controller");
//		System.out.println("id = " + this.id);
//		System.out.println("name = " + this.name);
//		System.out.println("children = " + this.children);
	}

	public Shipwreck(Long id, String name, String description, String condition, Integer depth, Double latitude,
			Double longitude, Integer yearDiscovered, List<Child> children, NestedChild nestedChild) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.condition = condition;
		this.depth = depth;
		this.latitude = latitude;
		this.longitude = longitude;
		this.yearDiscovered = yearDiscovered;
		this.children = children;
		this.nestedChild = nestedChild;
	}

	public int getFoo() {
		return foo;
	}

	public void setFoo(int foo) {
		this.foo = foo;
	}

	public List<Child> getChildren() {
		return children;
	}

	public void setChildren(List<Child> children) {
		this.children = children;
	}

	public NestedChild getNestedChild() {
		return nestedChild;
	}

	public void setNestedChild(NestedChild nestedChild) {
		this.nestedChild = nestedChild;
	}

	public long getId() {
		return id;
	}

	public void setId(Long idIndex) {
		this.id = idIndex;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public Integer getDepth() {
		return depth;
	}

	public void setDepth(Integer depth) {
		this.depth = depth;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Integer getYearDiscovered() {
		return yearDiscovered;
	}

	public void setYearDiscovered(Integer yearDiscovered) {
		this.yearDiscovered = yearDiscovered;
	}
}
