@FHIR
Feature: FHIR Patient tests
  FHIR Patient tests

  @Patient
  Scenario: Get Patient uscore-example
    Given I use the default WildFHIR server
    When I make GET Patient call with id "uscore-example"
    Then the JSON body matches the HL7 US Core profile
