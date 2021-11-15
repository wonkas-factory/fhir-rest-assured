package com.restassured.fhir.test.api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.text.MatchesPattern.matchesPattern;

import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import com.restassured.fhir.test.BaseAPI;

public class PatientTests extends BaseAPI {

	@Test
	public void getPatientTest() {
		String patientId = "uscore-example";
		
		given().
			spec(requestSpecGlobal).
			pathParam("id", patientId).
		when().
			get("/Patient/{id}").
		then().
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
	}
}
