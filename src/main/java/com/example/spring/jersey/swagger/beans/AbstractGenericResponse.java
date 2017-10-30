package com.example.spring.jersey.swagger.beans;

import javax.xml.bind.annotation.XmlRootElement;

import com.example.spring.jersey.swagger.beans.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Rachid
 *
 * @param <T>
 * 
 * A generic response for a web service 
 * 
 */
@XmlRootElement(name = "GenericResponse")
public abstract class AbstractGenericResponse<T> implements GenericResponse<T> {

	protected boolean errors;
	protected List<Message> messageLists;

	public AbstractGenericResponse() {

	}

	@Override
	public boolean hasErrors() {
		return errors;
	}

	@Override
	public List<Message> getMessageList() {
		if (messageLists == null) {
			messageLists = new ArrayList<Message>();
		}
		return this.messageLists;
	}

	@Override
	public void setErrors(boolean errors) {
		this.errors = errors;
	}
	
}
