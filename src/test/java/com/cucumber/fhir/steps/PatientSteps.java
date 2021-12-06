package com.cucumber.fhir.steps;

import io.cucumber.java8.En;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.SSLConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;
import static io.restassured.config.RestAssuredConfig.newConfig;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.text.MatchesPattern.matchesPattern;

import org.apache.http.HttpStatus;

import com.restassured.fhir.test.BaseAPI;
import com.restassured.fhir.test.Data;
import com.restassured.fhir.test.Logging;


public class PatientSteps implements En {
	protected static Data data = new Data();
	protected static RequestSpecification requestSpecGlobal;
	protected static Response response;
	protected static String patientId;

	public PatientSteps() {
		Given("I use the default WildFHIR server", () -> {
			requestSpecGlobal = BaseAPI.initialize();
        });
		
		When("I make GET Patient call with id {string}", (String id) -> {
			patientId = id;
			response = 
				given().
					spec(requestSpecGlobal).
					pathParam("id", patientId).
				when().
					get("/Patient/{id}");
		});
		
		Then("the JSON body matches the HL7 US Core profile", () -> {
			response.then().
				statusCode(HttpStatus.SC_OK).
				body(
					"resourceType", is("Patient"),
					"id", is(patientId),
					"birthDate", matchesPattern("^\\d{4}-\\d{2}-\\d{2}$"),
					"identifier.system", not(emptyArray()),
					"identifier.value", not(emptyArray()),
					"name.family", not(emptyArray()),
					"name.given", not(emptyArray()),
					"telecom.system", hasItem(matchesPattern("(?i)phone|fax|email|pager|url|sms|other")),
					"telecom.value", not(emptyArray()),
					"gender", matchesPattern("(?i)male|female|other|unknown"),
					"address", not(emptyArray()),
					"communication.language", not(emptyArray())
				);
		});
	}
}
