package com.sample.restassured.test;

import static com.jayway.restassured.RestAssured.given;

import org.junit.Test;

public class invalidPersonTest extends FunctionalTest {

    @Test
    public void invalidPerson() {
        given().when().get("/persons/999")
            .then().statusCode(500);
    }
    
}


