# Rest Assured Example 

This project uses Rest Assured to make a simple Get Patient API from a public FHIR API server. The documentation for the API is located [here](https://www.hl7.org/fhir/us/core/StructureDefinition-us-core-patient.html). This does some simple asserts of the required API response fields

## Quick Start

Pull down code and run```./mvnw clean test``` 

Folders
1. ```config``` is where to set the enviromnet variables
1. ```target/surefire-reports/html/index.html``` generated at runtime is the test report

## Installation

The basic installations to run the code. Note this was mostly ran on a macOS

1.	Git: To pull the project down ```brew install git```
2.	Java 8: To run the test suite ```brew cask install adoptopenjdk8```
