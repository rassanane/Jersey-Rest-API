package com.example.spring.jersey.swagger.beans;

import javax.xml.bind.annotation.XmlRootElement;

import com.example.spring.jersey.swagger.mongo.beans.Person;

/**
 * @author Rachid
 * 
 * This class presents a person response
 *
 */
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
