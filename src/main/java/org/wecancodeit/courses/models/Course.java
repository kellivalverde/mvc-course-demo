package org.wecancodeit.courses.models;

public class Course {
//POJO - plain old Java object

	private long id;
	private String name;
	private String description;

	//what ThymeLeaf is accessing (chops off "get")
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public Course(long id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	
		
	}

}
