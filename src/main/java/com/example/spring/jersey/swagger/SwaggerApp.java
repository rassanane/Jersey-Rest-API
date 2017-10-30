package com.example.spring.jersey.swagger;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.message.MessageProperties;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.springframework.stereotype.Component;

import com.example.spring.jersey.swagger.exception.AllExceptionMapper;
import com.example.spring.jersey.swagger.rest.PersonController;
import com.wordnik.swagger.config.ConfigFactory;
import com.wordnik.swagger.config.ScannerFactory;
import com.wordnik.swagger.config.SwaggerConfig;
import com.wordnik.swagger.jaxrs.config.ReflectiveJaxrsScanner;
import com.wordnik.swagger.jaxrs.listing.ApiDeclarationProvider;
import com.wordnik.swagger.jaxrs.listing.ApiListingResource;
import com.wordnik.swagger.jaxrs.listing.ApiListingResourceJSON;
import com.wordnik.swagger.jaxrs.listing.ResourceListingProvider;
import com.wordnik.swagger.jaxrs.reader.DefaultJaxrsApiReader;
import com.wordnik.swagger.reader.ClassReaders;

/**
 * @author Rachid
 *
 * A class to represent a rest services with Swagger : 
 * http://localhost:8080/jersey-swagger/swagger-ui/
 *
 */
@Component
@ApplicationPath("api")
public class SwaggerApp extends ResourceConfig {

    public SwaggerApp(){
    	
    	//My Rest services
        register(PersonController.class);
        
        register(JacksonFeature.class);
        register(AllExceptionMapper.class);
        
        //Swagger
        register(ApiListingResource.class);
        register(ApiDeclarationProvider.class);
        register(ApiListingResourceJSON.class);
        register(ResourceListingProvider.class);
        
        //properties
        property(MessageProperties.XML_FORMAT_OUTPUT, true);
        property(ServerProperties.TRACING, "ALL");
        
    }

    @PostConstruct
    /**
     * Initializes Swagger Configuration
     */
    public void initializeSwaggerConfiguration() {

        final ReflectiveJaxrsScanner scanner = new ReflectiveJaxrsScanner();
        scanner.setResourcePackage("com.example.spring.jersey.swagger");
        ScannerFactory.setScanner(scanner);
        ClassReaders.setReader(new DefaultJaxrsApiReader());
        final SwaggerConfig config = ConfigFactory.config();
        config.setApiVersion("1.0");
        config.setBasePath("http://localhost:8080/jersey-swagger/rest");
        
    }
    
}
