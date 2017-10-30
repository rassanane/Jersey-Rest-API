package com.sample.spring.jersey.swagger.beans;

import java.util.Collection;

import com.sample.spring.jersey.swagger.mongo.beans.Person;

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