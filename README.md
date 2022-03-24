# Web and REST API Test Framework
This is a starter project for my Web and REST API Test Automation projects. It uses Page Object Model with page objects containing action methods as well.
It also includes some demo test cases for automating sample test cases in `Automationpractice.com` and `GoREST API`

## Components
- Selenide(Selenium wrapper - Web UI Testing)
- REST Assured(API Testing)
- JUnit(Test Orchestration)
- Allure(Reporting)
- Java Faker(Test Data Generation)

## Steps
1. Add the UI tests to the src/test/java directory under the ui.tests package
2. Add the page objects to the src/test/java directory under the ui.pageobject package
3. Add the API tests to the src/test/java directory under the api.tests package
4. Add any helper methods and entities to be used in tests to to the src/main/java directory under the entities and util packages

`Note: The files located within the demo.* package are sample ui & api tests and related test files. You can check those for help while creating tests`

## Commandline Test Run
`mvn test`

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