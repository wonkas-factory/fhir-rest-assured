package com.restassured.fhir.test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.SSLConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

import static io.restassured.config.RestAssuredConfig.newConfig;

import org.testng.annotations.BeforeSuite;

public class BaseAPI {
	protected static Data data = new Data();
	protected static RequestSpecification requestSpecGlobal;

	@BeforeSuite
	public static RequestSpecification initialize() {
		Logging.logToTestNgReporter();
		
		RestAssured.baseURI = data.getEnvProp("host");
		RestAssured.basePath = data.getEnvProp("apiPath");
		RestAssured.config = newConfig().sslConfig(new SSLConfig().allowAllHostnames());
		RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

		RequestSpecBuilder builder = new RequestSpecBuilder();
		builder.addHeader("Accept", "application/json");
		builder.setContentType("application/json");
		requestSpecGlobal = builder.build();
		
		return requestSpecGlobal;
	}
}
