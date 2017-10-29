package com.sample.spring.jersey.swagger.beans;

import javax.xml.bind.annotation.XmlRootElement;

import com.sample.spring.jersey.swagger.mongo.beans.Person;

@XmlRootElement
public class PersonResponse extends AbstractGenericResponse<Person>
{
    Person result;

    @Override
    public Person getResult() {
        return result;
    }

    @Override
    public void setResult(Person person) {
        result = person;
    }
}
