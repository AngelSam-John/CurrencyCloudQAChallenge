# Rest API Test Framework created using Rest Assured + Jnuit + Cucumber + Maven with Allure Reporting.

The tech stack used for this framework creation are:

1. JAVA as the programming language for writing test code
2. junit as the testing framework
3. Maven as the build tool
4. IntelliJ as the preferred IDE for writing java code.

I have chosen RestAssured as the tool to validate API's as it is open source test framework and it can be written in java. Programming language I am familiar with along with Junit as the test framework.

Also, I have used BDD approach with cucumber - as it helps in understanding the test steps easy for non technical people as it is written in simple English language.

Allure report is used for reporting as it gives clear insights on request responses and it is completely open source.

## Getting Started. Required softwares.

1. Install JDK 7 or above
2. Install IntelliJ (latest Community edition)
3. Install Maven
4. Install Git
5. Install npm for viewing allure report

## Cloning & Importing the Project
1. Clone the project using 'Get from version control' option in intellij
2. Import the project in IntelliJ and wait for all the dependencies to be downloaded

## Run Project as TestNG and using Maven commands
1. Please update your login id and apikey in the *Configuration.properties* file present under the *src/main/resources* directory
2. To Run as junit, right click on the TestRunner file and click on run. To specify which scenario to execute,
   specify them as tag in 'TestRunner' file.
3. Execute as maven using - *mvn test* command in a terminal tab of intellij.

Kindly use the below link for fixing issues when running using maven commands - https://www.baeldung.com/java-lang-unsupportedclassversion

## Viewing the test report
1. Install npm
2. Install allure via terminal tab using *npm install -g allure-commandline —save-dev* command
3. To view the report, execute the command *allure serve target/allure-results* via terminal tab in intellij.

<img width="953" alt="Screenshot" src="https://user-images.githubusercontent.com/59449080/102023979-5aec8600-3d8f-11eb-84de-de5492c9a910.png">


## Framework on High level.
This framework is developed using RestAssured for Java along with junit and using BDD approach(cucumber).

1. Converted JSON Response Body to POJO
2. Separated Test Layer with API Services
3. Implemented REST Routes
4. Shared Test Context
5. Shared Scenario Context
6. Implemented Configuration Reader
7. Tests are written in Gherkin format using feature files
8. Console logs are viewed using log4j.
9. Request and Response logs are printed.
10. Result can be viewed in Allure reporting with Request and Responses.











