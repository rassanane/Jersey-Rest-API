package com.example.restassured.test;

import static com.jayway.restassured.RestAssured.given;

import org.junit.Test;

public class HelloWorldRestAssured {

	@Test
	public void makeSureThatGoogleIsUp() {
		given().when().get("http://www.google.com").then().statusCode(200);
	}

}
