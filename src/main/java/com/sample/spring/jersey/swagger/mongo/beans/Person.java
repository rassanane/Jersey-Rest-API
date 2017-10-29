package com.sample.spring.jersey.swagger.mongo.beans;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "persons")
public class Person {

	@Id
	private ObjectId _id;
	
	Integer personId;
	
	String name;

	Integer age;

	String job;

	//getter, setter, toString, Constructors

	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}
	
	@Override
	public String toString() {
		return "Person [personId=" + personId + ", name=" + name + ", age=" + age + "]";
	}
	
}