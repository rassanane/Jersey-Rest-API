package com.example.spring.jersey.swagger.beans;

import java.util.Collection;

import com.example.spring.jersey.swagger.mongo.beans.Person;

/**
 * @author Rachid
 * 
 * This class presents a persons list response
 *
 */
public class PersonListResponse extends AbstractGenericResponse<Collection<Person>> {

    Collection<Person> result;

    @Override
    public Collection<Person> getResult() {
        return result;
    }

    @Override
    public void setResult(Collection<Person> persons) {
        result = persons;
    }
    
}
