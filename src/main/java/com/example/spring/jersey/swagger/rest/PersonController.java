package com.sample.spring.jersey.swagger.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.sample.spring.jersey.swagger.beans.PersonListResponse;
import com.sample.spring.jersey.swagger.beans.PersonResponse;
import com.sample.spring.jersey.swagger.mongo.beans.Person;
import com.sample.spring.jersey.swagger.service.PersonService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@Controller
@Api(value="/Persons", description = "Endpoint for Persons with MongoDB")
@Path("/persons")
public class PersonController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonController.class.getName());

    @Autowired
    private PersonService personService;
    
    @GET
    @ApiOperation(
            value = "Lists all persons",
            notes = "Lists all persons"
    )
    @ApiResponses(value= {
            @ApiResponse(code = 200, message = "Successful retrieval of persons"),
            @ApiResponse(code = 404, message = "Person records not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getPersons() {

        List<Person> persons = personService.getAllPersons();
        if(persons == null || persons.size() == 0) {
            LOGGER.debug("getPersons No persons found");
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        else {
            LOGGER.info("getPersons [ personList= [{}]]", persons.toString());
            PersonListResponse personListResponse = new PersonListResponse();
            personListResponse.setResult(persons);
            return Response.ok(personListResponse)
            		.header("Access-Control-Allow-Origin", "*")
            		.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
            		.header("Access-Control-Allow-Credentials", "true")
            		.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
            		.build();
            
        }
    }

    @GET
    @ApiOperation(
            value = "Retrieves an Person",
            notes = "Retrieves an Person"
    )
    @ApiResponses(value= {
            @ApiResponse(code = 200, message = "Successful retrieval of person"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 404, message = "Person Not found")
    })
    @Path("{personId}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getPerson(@PathParam("personId") final Integer personId){
        PersonResponse personResponse = new PersonResponse();
        Person person = personService.getPerson(personId);
        if(person == null) {
            LOGGER.debug("getPerson not found [personId ={}]", personId);
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        else {
            LOGGER.info("getPerson [ personDetails= [{}]]", person.toString());
            personResponse.setResult(person);
            return Response.ok(personResponse)
            		.header("Access-Control-Allow-Origin", "*")
            		.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
            		.header("Access-Control-Allow-Credentials", "true")
            		.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
            		.build();
        }
    }

    @DELETE
    @ApiOperation(
            value = "Deletes an Person",
            notes = "Deletes an Person"
    )
    @ApiResponses(value= {
            @ApiResponse(code = 200, message = "Successful deletion of person"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 404, message = "Person Not found")
    })
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("{personId}")
    public Response deletePerson(@PathParam("personId") final Integer personId){
        Person  person  = personService.getPerson(personId);
        if(person == null){
            LOGGER.debug("deletePerson not found [personId ={}]", personId);
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        else {
            personService.removePerson(person);
            LOGGER.info("Deleted Person [personDetails= [{}]]", person.toString());
            return Response.ok()
            		.header("Access-Control-Allow-Origin", "*")
            		.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
            		.header("Access-Control-Allow-Credentials", "true")
            		.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
            		.build();
        }
    }

    @POST
    @ApiOperation(
            value = "Inserts a new person record",
            notes = "Inserts a new person record"
    )
    @ApiResponses(value= {
            @ApiResponse(code = 201, message = "Successful creation of person record"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response createPerson(final Person person){

        personService.addPerson(person);
        LOGGER.info("createPerson Created Person [personDetails=[{}]]", person.toString());
        return Response.status(Response.Status.CREATED)
        		.header("Access-Control-Allow-Origin", "*")
        		.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
        		.header("Access-Control-Allow-Credentials", "true")
        		.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
        		.build();
    }
    
    @PUT
    @ApiOperation(
            value = "Update a person record",
            notes = "Update a person record"
    )
    @ApiResponses(value= {
            @ApiResponse(code = 201, message = "Successful modification of person record"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updatePerson(final Person person){

        personService.updatePerson(person);
        LOGGER.info("Updated Person [personDetails=[{}]]", person.toString());
        return Response.status(Response.Status.CREATED)
        		.header("Access-Control-Allow-Origin", "*")
        		.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
        		.header("Access-Control-Allow-Credentials", "true")
        		.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
        		.build();
    }
    
    //Appelé avant le Post pour ajouter els headers
    @OPTIONS
    public Response getOptionsPersons() {

        return Response.ok()
        		.header("Access-Control-Allow-Origin", "*")
        		.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
        		.header("Access-Control-Allow-Credentials", "true")
        		.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
        		.build();
            
    }
    
    //Appelé avant suppression
    @OPTIONS
    @Path("{personId}")
    public Response getOptionsPerson(@PathParam("personId") final Integer personId) {

        return Response.ok()
        		.header("Access-Control-Allow-Origin", "*")
        		.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
        		.header("Access-Control-Allow-Credentials", "true")
        		.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
        		.build();
            
    }
    
}
