# Web and REST API Test Framework
This is a starter project for my Web and REST API Test Automation projects. It uses Page Object Model with page objects containing action methods as well.

## Components
- Selenide(Selenium wrapper - Web UI Testing)
- REST Assured(API Testing)
- JUnit(Test Orchestration)
- Allure(Reporting)
- Java Faker(Test Data Generation)

## Steps
1. Add the UI tests to the src/test/java directory under the ui.tests package
1. Add the page objects to the src/test/java directory under the ui.pageobject package
1. Add the API tests to the src/test/java directory under the api.tests package
1. Add any helper methods and entities to be used in tests to to the src/main/java directory under the entities and util packages

## Commandline Test Run
`mvn verify`

## Project Structure
- reports - contains allure results and screenshots
- src/main/java - Framework related classes
    - entities - Entities and Enums for test classes
- util - Helper classes
- src/test/resource - properties and configuration files
- src/test/java - Test Classes
    - api.tests - API Test Classes
    - ui.tests - UI Test Classes
    - ui.pageobjects - Pageobjects for UI Classes
- src/test/resource - Test Resources