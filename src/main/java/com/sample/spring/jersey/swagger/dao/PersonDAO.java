package com.sample.spring.jersey.swagger.dao;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.sample.spring.jersey.swagger.mongo.beans.Person;

@Repository("personDAO")
public class PersonDAO {

    MongoTemplate mongoTemplate;

	public void addPerson(Person person) {
    	mongoTemplate.insert(person);
    }

	public void updatePerson(Person person) {

		Query query = new Query();
		query.addCriteria(Criteria.where("personId").is(person.getPersonId()));
		//query.fields().include("name");

		Update update = new Update();
		update.set("name", person.getName());
		update.set("age", person.getAge());
		update.set("job", person.getJob());
		
		mongoTemplate.updateFirst(query, update, Person.class);

	}

    public void removePerson(Person  person) {
    	mongoTemplate.remove(person);
    }

    public Person getPerson(final Integer personId) {
    	
    	Person person = mongoTemplate.findOne(query(where("personId").is(personId)), Person.class);
        return person;
    }

    public List<Person> listPersons() {

    	List<Person> persons =  mongoTemplate.findAll(Person.class);
        return persons;
    }
        
    public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
    
}
