# API Automation Framework - Hands-on with Open Weather endpoints

## Constructed by:
- **REST Assured**: REST services caller (http://rest-assured.io/)
- **TestNG**: Assertion framework for verifying purposes (https://testng.org/doc/index.html)
- **Cucumber Extent Report**
- **Unitils**: Reflection assertion tool help to verify api's response (http://www.unitils.org/summary.html)
- **Jackson**: Json processor for object converting purposes (https://github.com/FasterXML/jackson-core)
- **Restito**: Mocking framework help to construct mock service, mock data (https://github.com/mkotsur/restito)

## How to Run
 - Clone the project
 - Update your Open Weather **API key** in the environment YAML files ```/src/main/resources/environments/demo.yml```
 - Open terminal and execute the CLI: ```$mvn clean test -Dtestng.dtd.http=true -Denvironment=DEMO```
 - Open the Cucumber Extent Report at /target/cucumber-reports/report.html 
 
## Framework Functions Support:
- API Automation with BDD Cucumber
- Cucumber Extent Report
- API Mock Server
- CI/CD
- Environment Handler
- Test Data Hanlder
- API Test: Functional Test, Contract Test

## The project was set up on macOS
