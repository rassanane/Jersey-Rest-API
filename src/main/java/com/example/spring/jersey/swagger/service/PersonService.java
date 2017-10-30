package com.example.spring.jersey.swagger.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring.jersey.swagger.dao.PersonDAO;
import com.example.spring.jersey.swagger.mongo.beans.Person;

@Service
public class PersonService {

    @Autowired
    private PersonDAO personDAO;

    public List<Person> getAllPersons(){
        return personDAO.listPersons();
    }

    public Person getPerson(Integer personId) {
        return personDAO.getPerson(personId);
    }

    public void removePerson(Person  person) {
    	personDAO.removePerson(person);
    }

    public void addPerson(Person person) {
    	personDAO.addPerson(person);
    }
    
    public void updatePerson(Person person) {
    	personDAO.updatePerson(person);
    }
    
}
