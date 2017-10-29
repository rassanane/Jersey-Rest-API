package com.sample.restassured.test;

import static com.jayway.restassured.RestAssured.given;

import org.junit.Test;

public class ListPersonsTest extends FunctionalTest {

    @Test
    public void basicPingTest() {
        given().when().get("/persons").then().statusCode(200);
    }
    
}
